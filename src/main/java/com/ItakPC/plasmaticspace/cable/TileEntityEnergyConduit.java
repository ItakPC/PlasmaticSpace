package com.ItakPC.plasmaticspace.cable;

import com.ItakPC.plasmaticspace.machine.windmill.TileEntityWindmill;
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
        if(isConduit(xCoord, yCoord+1, zCoord)) connections[1] = ForgeDirection.UP;
        else connections[1] = null;
        if(isConduit(xCoord, yCoord-1, zCoord)) connections[0] = ForgeDirection.DOWN;
        else connections[0] = null;
        if(isConduit(xCoord, yCoord, zCoord-1) || isBaseWindmill(xCoord, yCoord, zCoord-1)) connections[2] = ForgeDirection.NORTH;
        else connections[2] = null;
        if(isConduit(xCoord, yCoord, zCoord+1) || isBaseWindmill(xCoord, yCoord, zCoord+1)) connections[3] = ForgeDirection.SOUTH;
        else connections[3] = null;
        if(isConduit(xCoord-1, yCoord, zCoord) || isBaseWindmill(xCoord-1, yCoord+1, zCoord)) connections[4] = ForgeDirection.WEST;
        else connections[4] = null;
        if(isConduit(xCoord+1, yCoord, zCoord) || isBaseWindmill(xCoord+1, yCoord, zCoord)) connections[5] = ForgeDirection.EAST;
        else connections[5] = null;

    }

    public boolean isConduit(int x, int y, int z) {
        return this.worldObj.getTileEntity(x, y, z) instanceof TileEntityEnergyConduit;
    }

    public boolean isBaseWindmill(int x, int y, int z) {
        return this.worldObj.getTileEntity(x, y, z) instanceof TileEntityWindmill && this.worldObj.getBlockMetadata(x, y, z) == 1;
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
