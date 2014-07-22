package com.ItakPC.plasmacraft.handler;

import com.ItakPC.plasmacraft.PlasmaCraft;
import com.ItakPC.plasmacraft.machine.windmill.ContainerWindmill;
import com.ItakPC.plasmacraft.machine.windmill.GuiWindmill;
import com.ItakPC.plasmacraft.machine.windmill.TileEntityWindmill;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        switch (ID) {
            case PlasmaCraft.guiIDWindmillBasic:
                while (tileEntity instanceof TileEntityWindmill && world.getBlockMetadata(x, y, z) < 8) {
                    y++;
                }

                return new ContainerWindmill(player.inventory, (TileEntityWindmill) world.getTileEntity(x, y, z));

        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        switch (ID) {
            case PlasmaCraft.guiIDWindmillBasic:
                while (tileEntity instanceof TileEntityWindmill && world.getBlockMetadata(x, y, z) < 8) {
                    y++;
                }

                return new GuiWindmill(player.inventory, (TileEntityWindmill) world.getTileEntity(x, y, z));

        }

        return null;
    }
}

