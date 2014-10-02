package com.ItakPC.plasmaticspace.machine.cutter;

import com.ItakPC.plasmaticspace.PlasmaticSpace;
import com.ItakPC.plasmaticspace.creativetabs.PlasmaCraftMachine;
import com.ItakPC.plasmaticspace.reference.Reference;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class CuttingMachine extends BlockContainer {

    private Random random;
    private final boolean isActive;
    private static boolean keepInventory = false;

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    @SideOnly(Side.CLIENT)
    private IIcon iconBottom;

    public CuttingMachine(boolean isActive) {

        super(Material.iron);
        random = new Random();
        this.isActive = isActive;

    }

    public TileEntity createNewTileEntity(World world, int metadata) {

        return new TileCutter();

    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {

        this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + "machine_side");
        this.iconFront = iconRegister.registerIcon(Reference.MOD_ID + ":" + (this.isActive ? "plate_cutting_machine_front_on" : "plate_cutting_machine_front_off"));
        this.iconTop = iconRegister.registerIcon(Reference.MOD_ID + ":" + "machine_top");
        this.iconBottom = iconRegister.registerIcon(Reference.MOD_ID + ":" + "machine_bottom");

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {

        return metadata == 0 && side == 3 ? this.iconFront : side == 1 ? this.iconTop : (side == 0 ? this.iconTop: (side == metadata ? this.iconFront : this.blockIcon));

    }

    public void onBlockAdded(World world, int x, int y, int z) {

        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);

    }

    public Item getItemDropped(int i, Random random, int j) {

        return Item.getItemFromBlock(PlasmaticSpace.cutterIdle);

    }

    public Item getItem(World world, int x, int y, int z)
    {
        return Item.getItemFromBlock(PlasmaticSpace.cutterIdle);
    }

    private void setDefaultDirection(World world, int x, int y, int z) {

        if(!world.isRemote) {
            Block block1 = world.getBlock(x, y, z - 1);
            Block block2 = world.getBlock(x, y, z + 1);
            Block block3 = world.getBlock(x - 1, y, z);
            Block block4 = world.getBlock(x + 1, y, z);

            byte b = 3;

            if(block1.func_149730_j() && !block2.func_149730_j()) {
                b = 3;
            }
            if(block2.func_149730_j() && !block1.func_149730_j()) {
                b = 2;
            }
            if(block3.func_149730_j() && !block4.func_149730_j()) {
                b = 5;
            }
            if(block4.func_149730_j() && !block3.func_149730_j()) {
                b = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b, 2);

        }

    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        if(!world.isRemote) {
            FMLNetworkHandler.openGui(player, PlasmaticSpace.instance, PlasmaticSpace.guiIDCutter, world, x, y, z);
        }
        return true;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack) {

        int l = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360F) + 0.5D) & 3;

        if(l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if(l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        if(l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if(l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if(itemstack.hasDisplayName()) {
            ((TileCutter)world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
        }

    }

    public static void updatePlateCuttingMachineBlockState(boolean active, World worldObj, int x, int y, int z) {

        int i = worldObj.getBlockMetadata(x, y, z);
        TileEntity tileEntity = worldObj.getTileEntity(x, y, z);

        keepInventory = true;

        if(active) {
            worldObj.setBlock(x, y, z, PlasmaticSpace.cutterActive);
        }
        else {
            worldObj.setBlock(x, y, z, PlasmaticSpace.cutterIdle);
        }

        keepInventory = false;

        worldObj.setBlockMetadataWithNotify(x, y, z, i, 2);

        if(tileEntity!= null) {
            tileEntity.validate();
            worldObj.setTileEntity(x, y, z, tileEntity);
        }

    }

    public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata) {

        if(!keepInventory) {

            TileCutter te = (TileCutter)world.getTileEntity(x, y, z);

            if(te != null) {

                for(int i = 0; i < te.getSizeInventory(); i++) {

                    ItemStack itemstack = te.getStackInSlot(i);

                    if(itemstack != null) {

                        float f = this.random.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                        while(itemstack.stackSize> 0) {

                            int j = this.random.nextInt(21) + 10;

                            if(j > itemstack.stackSize) {

                                j = itemstack.stackSize;

                            }

                            itemstack.stackSize -= j;

                            EntityItem item = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

                            if(itemstack.hasTagCompound()) {

                                item.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());

                            }

                            float f3 = 0.05F;
                            item.motionX = (double)((float)this.random.nextGaussian() * f3);
                            item.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
                            item.motionZ = (double)((float)this.random.nextGaussian() * f3);

                            world.spawnEntityInWorld(item);

                        }

                    }

                }

                world.func_147453_f(x, y, z, oldBlock);

            }


        }

        super.breakBlock(world, x, y, z, oldBlock, oldMetadata);

    }
}
