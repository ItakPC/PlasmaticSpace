package com.ItakPC.plasmacraft.machine.windmill;

import com.ItakPC.plasmacraft.PlasmaCraft;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWindmill extends BlockContainer {

    public BlockWindmill(Material material) {
        super(material);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
        float pixel = 1F/16F;

        if(blockAccess.getBlockMetadata(x, y, z) < 7) {
            this.setBlockBounds(pixel*4, 0, pixel*4, 1-pixel*4, 1, 1-pixel*4);
        }else{
            this.setBlockBounds(0 ,0, 0, 1, 1, 1);
        }

    }

    public int getRenderType() {
        return -1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityWindmill();
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int metadata){
        if(world.getBlock(x, y+1, z).equals(PlasmaCraft.blockWindmill)) world.setBlockToAir(x, y + 1, z);
        if(world.getBlock(x, y-1, z).equals(PlasmaCraft.blockWindmill)) world.setBlockToAir(x, y-1, z);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            FMLNetworkHandler.openGui(player, PlasmaCraft.instance, 0, world, x, y, z);
        }

        return true;
    }
}

