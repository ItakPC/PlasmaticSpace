package com.ItakPC.plasmacraft.cable;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockEnergyConduit extends BlockContainer{
	
	float pixel = 1F/16F;

	public BlockEnergyConduit() {
		super(Material.iron);
		this.useNeighborBrightness=true;
		
		this.setBlockBounds(11*pixel/2, 11*pixel/2, 11*pixel/2, 11*pixel/2, 11*pixel/2, 11*pixel/2);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		TileEntityEnergyConduit conduit = (TileEntityEnergyConduit)world.getTileEntity(x, y, z);
		
		if(conduit != null) {
			float minZ = 11*pixel/2-(conduit.connections[2]!=null?(11*pixel/2):0);
			float maxZ = 1-11*pixel/2+(conduit.connections[3]!=null?(11*pixel/2):0);
			
			this.setBlockBounds(11*pixel/2, 11*pixel/2, minZ, 11*pixel/2, 11*pixel/2, maxZ);
		}
		
		return AxisAlignedBB.getAABBPool().getAABB(x+this.minX, y+this.minY, z+this.minX, x+this.maxX, y+this.maxY, z+this.maxZ);
		
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

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityEnergyConduit();
	}

}
