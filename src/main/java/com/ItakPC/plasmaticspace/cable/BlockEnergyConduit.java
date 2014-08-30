package com.ItakPC.plasmaticspace.cable;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockEnergyConduit extends BlockContainer {

    float pixel = 1F/16F;

    public BlockEnergyConduit(Material material) {
        super(material);
        this.setBlockName("conduit");
        this.useNeighborBrightness = true;
        this.setBlockBounds(11*pixel/2, 11*pixel/2, 11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) == null) {
            TileEntityEnergyConduit conduit = (TileEntityEnergyConduit) world.getTileEntity(x, y, z);


            if (conduit != null) {
                float minX = 11 * pixel / 2 - (conduit.connections[4] != null ? (11 * pixel / 2) : 0);
                float maxX = 1 - 11 * pixel / 2 + (conduit.connections[5] != null ? (11 * pixel / 2) : 0);

                float minY = 11 * pixel / 2 - (conduit.connections[0] != null ? (11 * pixel / 2) : 0);
                float maxY = 1 - 11 * pixel / 2 + (conduit.connections[1] != null ? (11 * pixel / 2) : 0);

                float minZ = 11 * pixel / 2 - (conduit.connections[2] != null ? (11 * pixel / 2) : 0);
                float maxZ = 1 - 11 * pixel / 2 + (conduit.connections[3] != null ? (11 * pixel / 2) : 0);

                this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
            }
        }

            return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);

        }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        TileEntityEnergyConduit conduit = (TileEntityEnergyConduit) world.getTileEntity(x, y, z);

        if(conduit != null) {
            float minX = 11 * pixel / 2-(conduit.connections[4]!=null?(11 * pixel / 2):0);
            float maxX = 1 - 11 * pixel / 2 + (conduit.connections[5]!=null?(11 * pixel / 2):0);

            float minY = 11 * pixel / 2-(conduit.connections[0]!=null?(11 * pixel / 2):0);
            float maxY = 1 - 11 * pixel / 2 + (conduit.connections[1]!=null?(11 * pixel / 2):0);

            float minZ = 11 * pixel / 2-(conduit.connections[2]!=null?(11 * pixel / 2):0);
            float maxZ = 1 - 11 * pixel / 2 + (conduit.connections[3]!=null?(11 * pixel / 2):0);

            this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
        }

        return AxisAlignedBB.getBoundingBox(x+this.minX, y+this.minY, z+this.minZ, x+this.maxX, y+this.maxY, z+this.maxZ);

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

    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityEnergyConduit();
    }
}
