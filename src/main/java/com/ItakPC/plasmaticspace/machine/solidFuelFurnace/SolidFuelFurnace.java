package com.ItakPC.plasmaticspace.machine.solidFuelFurnace;

import com.ItakPC.plasmaticspace.PlasmaticSpace;
import com.ItakPC.plasmaticspace.reference.Reference;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SolidFuelFurnace extends BlockContainer {

    private final boolean isActive;

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    private static boolean keepInventory;

    public SolidFuelFurnace(boolean isActive) {
        super(Material.iron);

        this.isActive = isActive;
    }

    public Item getItemDropped(World world, int x, int y, int z) {
        return Item.getItemFromBlock(PlasmaticSpace.solidFuelFurnaceIdle);
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y ,z);
    }

    private void setDefaultDirection(World world, int x, int y, int z) {
        if(!world.isRemote) {
            Block b1 = world.getBlock(x, y, z - 1);
            Block b2 = world.getBlock(x, y, z + 1);
            Block b3 = world.getBlock(x - 1, y, z);
            Block b4 = world.getBlock(x + 1, y, z);

            byte b0 = 3;

            if(b1.func_149730_j() && !b2.func_149730_j()) {
                b0 = 3;
            }

            if(b2.func_149730_j() && !b3.func_149730_j()) {
                b0 = 2;
            }

            if(b3.func_149730_j() && !b4.func_149730_j()) {
                b0 = 5;
            }

            if(b4.func_149730_j() && !b3.func_149730_j()) {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + "solidFuelFurnaceSide");
        this.iconTop = iconRegister.registerIcon(Reference.MOD_ID + ":" + "solidFuelFurnaceTop");
        this.iconFront = iconRegister.registerIcon(Reference.MOD_ID + ":" + (this.isActive ? "solidFurnaceFront_Active" : "solidFurnaceFront_Idle"));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 3 ? this.iconFront : (side == 1 ? this.iconTop : side == 0 ? this.iconTop : this.blockIcon);

    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntitySFFurnace();
    }


    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if(world.isRemote) {
            FMLNetworkHandler.openGui(player, PlasmaticSpace.instance, PlasmaticSpace.guiIdSFFurnace, world, x, y, z);
        }

        return true;
    }


    public void onBlockPlacedBy(World world, int x, int y, int z, EntityPlayer entityPlayer, ItemStack itemStack) {
        int l = MathHelper.floor_double((double)(entityPlayer.rotationYaw * 4.0F / 360.F) + 0.5D) & 3;

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

        if(itemStack.hasDisplayName()) {
           //(TileEntitySFFurnace)world.getTileEntity(x, y, z).setGuiDisplayName(itemStack.getDisplayName());
        }
    }

    public static void updateBlockState(boolean active, World world, int xCoord, int yCoord, int zCoord) {
        int i = world.getBlockMetadata(xCoord, yCoord, zCoord);

        TileEntity tileEntity = world.getTileEntity(xCoord, yCoord, zCoord);
        keepInventory = true;

        if(active) {
            world.setBlock(xCoord, yCoord, zCoord, PlasmaticSpace.solidFuelFurnaceActive);
        }else{
            world.setBlock(xCoord, yCoord, zCoord, PlasmaticSpace.solidFuelFurnaceIdle);
        }

        keepInventory = false;

        world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

        if(tileEntity != null) {
            tileEntity.validate();
            world.setTileEntity(xCoord, yCoord, zCoord, tileEntity);
        }
    }
}
