package com.ItakPC.plasmacraft.cable;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergyConduit extends TileEntity {

	public ForgeDirection[] connections = new ForgeDirection[6];
	
	public TileEntityEnergyConduit() {
		
	}
	
	public void updateEntity() {
		this.updateConnections();
	}
	
	public void updateConnections() {
		if(this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof TileEntityEnergyConduit) connections[0] = ForgeDirection.UP; 
		else connections[0] = null;	
		if(this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof TileEntityEnergyConduit) connections[1] = ForgeDirection.DOWN; 
		else connections[1] = null;	
		if(this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof TileEntityEnergyConduit) connections[2] = ForgeDirection.NORTH; 
		else connections[2] = null;	
		if(this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof TileEntityEnergyConduit) connections[3] = ForgeDirection.SOUTH; 
		else connections[3] = null;	
		if(this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof TileEntityEnergyConduit) connections[4] = ForgeDirection.WEST; 
		else connections[4] = null;	
		if(this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof TileEntityEnergyConduit) connections[5] = ForgeDirection.EAST; 
		else connections[5] = null;	
		
	}
	
	public boolean onlyOneOpposite(ForgeDirection[] directions) {
		ForgeDirection mainDirection = null;
		boolean isOpposite = false;
		
		for(int i = 0; i < directions.length; i++){
			if(mainDirection == null && directions[i] != null) mainDirection = directions[i];
			
			if(directions[i] != null && mainDirection != directions[i]) {
				if(!isOpposite(mainDirection, directions[i])) return false;
				else isOpposite = true;
			}
		}
		
		
		return isOpposite;
	}
	
	public boolean isOpposite(ForgeDirection direction1, ForgeDirection direction2) {
		if((direction1.equals(ForgeDirection.NORTH) && direction2.equals(ForgeDirection.SOUTH)) || (direction1.equals(ForgeDirection.SOUTH) && direction2.equals(ForgeDirection.NORTH))) return true;
		if((direction1.equals(ForgeDirection.UP) && direction2.equals(ForgeDirection.DOWN)) || (direction1.equals(ForgeDirection.DOWN) && direction2.equals(ForgeDirection.UP))) return true;
		if((direction1.equals(ForgeDirection.WEST) && direction2.equals(ForgeDirection.EAST)) || (direction1.equals(ForgeDirection.EAST) && direction2.equals(ForgeDirection.WEST))) return true;
		
		return false;	
	}
	
}
