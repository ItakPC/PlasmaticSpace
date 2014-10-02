package com.ItakPC.plasmaticspace.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotDual extends Slot {

    public SlotDual(EntityPlayer player, IInventory iInventory, int i, int j, int k) {
        super(iInventory, i, j, k);
    }
}
