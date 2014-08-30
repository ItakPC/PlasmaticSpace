package com.ItakPC.plasmaticspace.machine.cutter;

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
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class Cutter extends BlockContainer{

    private Random random;
    private final boolean isActive;
    private static boolean keepInventory = false;

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;

    public Cutter(boolean state){
        super(Material.iron);
        random = new Random();
        isActive = state;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + (this.isActive ? "machineSideActive" : "machineSideIdle"));
        this.iconFront = iconRegister.registerIcon(Reference.MOD_ID + ":" + "cutterFrontDefult");

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return metadata == 0 && side == 3 ? this.iconFront : (side == metadata ? iconFront : this.blockIcon);
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.setDefultDirection(world, x, y, z);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        int i = MathHelper.floor_double((double)(entityLivingBase.rotationYaw + 4.0F / 360F) + 0.5D) & 3;

        if(i == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if(i == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if(i == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if(i == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if(itemStack.hasDisplayName()) {
            //((TileEntityAlloySmelter)world.getTileEntity(x, y, z)).setCustomName(itemStack.getDisplayName());
        }
    }

    private void setDefultDirection(World world, int x, int y, int z) {
        if(!world.isRemote) {
            Block b1 = world.getBlock(x, y, z - 1);
            Block b2 = world.getBlock(x, y, z + 1);
            Block b3 = world.getBlock(x - 1, y, z - 1);
            Block b4 = world.getBlock(x + 1, y, z - 1);

            byte b0 = 3;

            if(b1.func_149730_j() && !b2.func_149730_j()) {
                b0 = 3;
            }

            if(b2.func_149730_j() && !b1.func_149730_j()) {
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

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            FMLNetworkHandler.openGui(player, PlasmaticSpace.instance, 3, world, x, y, z);
        }

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCutter();
    }
}