package com.ItakPC.plasmaticspace.machine.windmill;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerWindTurbine extends Container {

    private TileWindTurbine tileEntityWindmill;

    public ContainerWindTurbine(InventoryPlayer inventoryPlayer, TileWindTurbine tileEntityWindmill) {
        this.tileEntityWindmill = tileEntityWindmill;

        this.addSlotToContainer(new Slot(tileEntityWindmill, 0, 152, 64));

        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i*18, 142));
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, 9+j+i*9, 8 + j*18, 84+i*18));
            }
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }
}
