package com.ItakPC.plasmaticspace.machine.solidFuelFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;

public class ContainerSFFurnace extends Container {

    private TileEntitySFFurnace tileEntity;

    public int lastburnTime;

    public int lastcurrentItemBurnTime;

    public int lastcookTime;

    public ContainerSFFurnace(InventoryPlayer inventory, TileEntitySFFurnace tileEntitySFFurnace) {
        this.tileEntity = tileEntitySFFurnace;

        this.addSlotToContainer(new Slot(tileEntitySFFurnace, 0, 59, 35));
        this.addSlotToContainer(new Slot(tileEntitySFFurnace, 1, 8, 64));
        this.addSlotToContainer(new SlotFurnace(inventory.player, tileEntitySFFurnace, 2, 119, 35));

        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i*18, 142));
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventory, 9+j+i*9, 8 + j*18, 84+i*18));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tileEntity.cookTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileEntity.burnTime);
        iCrafting.sendProgressBarUpdate(this, 2, this.tileEntity.currentItemBurnTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for(int i = 0; i < this.crafters.size(); i++) {
            ICrafting iCrafting = (ICrafting) this.crafters.get(i);

            if(this.lastcookTime != this.tileEntity.cookTime) {
                iCrafting.sendProgressBarUpdate(this, 0, this.tileEntity.cookTime);
            }

            if(this.lastburnTime != this.tileEntity.burnTime) {
                iCrafting.sendProgressBarUpdate(this, 0, this.tileEntity.burnTime);
            }

            if(this.lastcurrentItemBurnTime != this.tileEntity.currentItemBurnTime) {
                iCrafting.sendProgressBarUpdate(this, 0, this.tileEntity.currentItemBurnTime);
            }
        }

        this.lastcookTime = this.tileEntity.cookTime;
        this.lastburnTime = this.tileEntity.burnTime;
        this.lastcurrentItemBurnTime = this.tileEntity.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int newValue) {

    }
}
