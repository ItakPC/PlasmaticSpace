package com.ItakPC.plasmaticspace.handler;

import com.ItakPC.plasmaticspace.PlasmaticSpace;
import com.ItakPC.plasmaticspace.machine.cutter.TileCutter;
import com.ItakPC.plasmaticspace.machine.sfAlloySmelter.sfContainerAlloySmelter;
import com.ItakPC.plasmaticspace.machine.sfAlloySmelter.sfGuiAlloySmelter;
import com.ItakPC.plasmaticspace.machine.sfAlloySmelter.sfTileAlloySmleter;
import com.ItakPC.plasmaticspace.machine.cutter.ContainerCutter;
import com.ItakPC.plasmaticspace.machine.cutter.GuiCutter;
import com.ItakPC.plasmaticspace.machine.windmill.ContainerWindTurbine;
import com.ItakPC.plasmaticspace.machine.windmill.GuiWindTurbine;
import com.ItakPC.plasmaticspace.machine.windmill.TileWindTurbine;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        switch (ID) {
            case PlasmaticSpace.guiIDWindmillBasic:
                while (tileEntity instanceof TileWindTurbine && world.getBlockMetadata(x, y, z) < 8) {
                    y++;
                }

                return new ContainerWindTurbine(player.inventory, (TileWindTurbine) world.getTileEntity(x, y, z));

        }

        if(tileEntity != null) {
            switch (ID) {
                case PlasmaticSpace.guiIDAlloySmelter:
                    if(tileEntity instanceof sfTileAlloySmleter) {
                        return new sfContainerAlloySmelter(player.inventory, (sfTileAlloySmleter) tileEntity);
                    }

                    return null;

                case PlasmaticSpace.guiIDCutter:
                    if(tileEntity instanceof TileCutter) {
                        return new ContainerCutter(player.inventory, (TileCutter) tileEntity);
                    }

                    return null;
            }


        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        switch (ID) {
            case PlasmaticSpace.guiIDWindmillBasic:
                while (tileEntity instanceof TileWindTurbine && world.getBlockMetadata(x, y, z) < 8) {
                    y++;
                }

                return new GuiWindTurbine(player.inventory, (TileWindTurbine) world.getTileEntity(x, y, z));

        }

        if(tileEntity != null) {
            switch (ID) {
                case PlasmaticSpace.guiIDAlloySmelter:
                    if(tileEntity instanceof sfTileAlloySmleter) {
                        return new sfGuiAlloySmelter(player.inventory, (sfTileAlloySmleter) tileEntity);
                    }

                    return null;

                case PlasmaticSpace.guiIDCutter:
                    if(tileEntity instanceof TileCutter) {
                        return new GuiCutter(player.inventory, (TileCutter) tileEntity);
                    }

                    return null;
            }

        }

        return null;
    }
}

