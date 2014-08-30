package com.ItakPC.plasmaticspace.machine.cutter;

import com.ItakPC.plasmaticspace.slot.SlotDual;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerCutter extends Container {

    private TileEntityCutter cutter;

    private int cookTime;
    private int power;
    private int lastItemBurnTime;

    public ContainerCutter(InventoryPlayer inventoryPlayer, TileEntityCutter tileEntityCutter) {
        cutter = tileEntityCutter;

        cookTime = 0;
        power = 0;
        lastItemBurnTime = 0;

        this.addSlotToContainer(new Slot(tileEntityCutter, 0, 8, 64));
        this.addSlotToContainer(new Slot(tileEntityCutter, 1, 80, 58));
        this.addSlotToContainer(new Slot(tileEntityCutter, 2, 80, 15));

        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i*18, 142));
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, 9+j+i*9, 8 + j*18, 84+i*18));
            }
        }
    }

    public void sendCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.cutter.cookTime);
        crafting.sendProgressBarUpdate(this, 1, this.cutter.power);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}