package com.ItakPC.plasmaticspace.machine.cutter;

import com.ItakPC.plasmaticspace.slot.SlotDual;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerCutter extends Container {

    private TileCutter plateCuttingMachine;

    private int lastEnergyStored;
    private int lastProgress;

    public ContainerCutter(InventoryPlayer inventoryPlayer, TileCutter tileEntity) {

        this.plateCuttingMachine = tileEntity;

        this.addSlotToContainer(new Slot(tileEntity, 0, 56, 17));
        this.addSlotToContainer(new Slot(tileEntity, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntity, 2, 116, 35));

        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 9; y++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, y + x * 9 + 9, 8 + y * 18, 84 + x * 18));
            }
        }

        for(int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, x, 8 + x * 18, 142));
        }

    }

    public void addCraftingToCrafters(ICrafting crafting) {

        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.plateCuttingMachine.storedEnergy);
        crafting.sendProgressBarUpdate(this, 1, this.plateCuttingMachine.progress);

    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int x = 0; x < this.crafters.size(); x++)
        {
            ICrafting crafting = (ICrafting)this.crafters.get(x);

            if (this.lastEnergyStored != this.plateCuttingMachine.storedEnergy);
            {
                crafting.sendProgressBarUpdate(this, 0, this.plateCuttingMachine.storedEnergy);
            }

            if (this.lastProgress != this.plateCuttingMachine.progress)
            {
                crafting.sendProgressBarUpdate(this, 1, this.plateCuttingMachine.progress);
            }
        }

        this.lastEnergyStored = this.plateCuttingMachine.storedEnergy;
        this.lastProgress = this.plateCuttingMachine.progress;

    }

    public void updateProgressBar(int slot, int newValue) {

        if(slot == 0) {
            this.plateCuttingMachine.storedEnergy = newValue;
        }
        if(slot == 1) {
            this.plateCuttingMachine.progress = newValue;
        }

    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {

        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotNumber);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotNumber == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotNumber != 1 && slotNumber != 0) {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (slotNumber >= 3 && slotNumber < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return null;
                    }
                }
                else if (slotNumber >= 30 && slotNumber < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            }
            else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {

        return plateCuttingMachine.isUseableByPlayer(player);

    }
}