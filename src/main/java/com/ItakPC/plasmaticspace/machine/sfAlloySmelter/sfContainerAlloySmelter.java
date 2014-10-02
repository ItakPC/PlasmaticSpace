package com.ItakPC.plasmaticspace.machine.sfAlloySmelter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class sfContainerAlloySmelter extends Container {

    private sfTileAlloySmleter alloyFurnace;

    private int lastAlloyFurnaceCookTime;
    private int lastAlloyFurnaceBurnTime;
    private int lastItemBurnTime;

    public sfContainerAlloySmelter(InventoryPlayer inventoryPlayer, sfTileAlloySmleter tileEntity) {

        this.alloyFurnace = tileEntity;

        this.addSlotToContainer(new Slot(tileEntity, 0, 45, 17));
        this.addSlotToContainer(new Slot(tileEntity, 1, 67, 17));
        this.addSlotToContainer(new Slot(tileEntity, 2, 56, 53));
        this.addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntity, 3, 116, 35));

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
        crafting.sendProgressBarUpdate(this, 0, this.alloyFurnace.alloyFurnaceCookTime);
        crafting.sendProgressBarUpdate(this, 1, this.alloyFurnace.alloyFurnaceBurnTime);
        crafting.sendProgressBarUpdate(this, 2, this.alloyFurnace.currentItemBurnTime);

    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int x = 0; x < this.crafters.size(); x++)
        {
            ICrafting crafting = (ICrafting)this.crafters.get(x);

            if (this.lastAlloyFurnaceCookTime != this.alloyFurnace.alloyFurnaceCookTime)
            {
                crafting.sendProgressBarUpdate(this, 0, this.alloyFurnace.alloyFurnaceCookTime);
            }

            if (this.lastAlloyFurnaceBurnTime != this.alloyFurnace.alloyFurnaceBurnTime)
            {
                crafting.sendProgressBarUpdate(this, 1, this.alloyFurnace.alloyFurnaceBurnTime);
            }

            if (this.lastItemBurnTime != this.alloyFurnace.currentItemBurnTime)
            {
                crafting.sendProgressBarUpdate(this, 2, this.alloyFurnace.currentItemBurnTime);
            }
        }

        this.lastAlloyFurnaceCookTime = this.alloyFurnace.alloyFurnaceCookTime;
        this.lastAlloyFurnaceBurnTime = this.alloyFurnace.alloyFurnaceBurnTime;
        this.lastItemBurnTime = this.alloyFurnace.currentItemBurnTime;
    }

    public void updateProgressBar(int slot, int newValue) {

        if(slot == 0) {
            this.alloyFurnace.alloyFurnaceCookTime = newValue;
        }
        if(slot == 1) {
            this.alloyFurnace.alloyFurnaceBurnTime = newValue;
        }
        if(slot == 2) {
            this.alloyFurnace.currentItemBurnTime = newValue;
        }

    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {

        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotNumber);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotNumber == 3) {
                if (!this.mergeItemStack(itemstack1, 4, 40, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotNumber != 1 && slotNumber != 0 && slotNumber != 2) {
                if(FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {

                    if(!this.mergeItemStack(itemstack1, 0, 2, false)) {

                        return null;

                    }

                }
                else if(sfTileAlloySmleter.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 2, 3, false)) {
                        return null;
                    }
                }
                else if (slotNumber >= 4 && slotNumber < 31) {
                    if (!this.mergeItemStack(itemstack1, 31, 40, false)) {
                        return null;
                    }
                }
                else if (slotNumber >= 31 && slotNumber < 40 && !this.mergeItemStack(itemstack1, 4, 31, false)) {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 4, 40, false)) {
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

        return alloyFurnace.isUseableByPlayer(player);

    }

}


/*package com.ItakPC.plasmaticspace.machine.sfAlloySmelter;

import com.ItakPC.plasmaticspace.slot.SlotDual;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerAlloySmelter extends Container {

    private TileEntityAlloySmelter sfAlloySmelter;

    private int cookTime;
    private int power;
    private int lastItemBurnTime;

    public ContainerAlloySmelter(InventoryPlayer inventoryPlayer, TileEntityAlloySmelter tileEntityAlloySmelter) {
        sfAlloySmelter = tileEntityAlloySmelter;

        cookTime = 0;
        power = 0;
        lastItemBurnTime = 0;

        this.addSlotToContainer(new Slot(tileEntityAlloySmelter, 0, 8, 64));
        this.addSlotToContainer(new Slot(tileEntityAlloySmelter, 1, 59, 24));
        this.addSlotToContainer(new Slot(tileEntityAlloySmelter, 2, 59, 46));
        this.addSlotToContainer(new SlotDual(inventoryPlayer.player, tileEntityAlloySmelter, 3, 119, 35));

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
        crafting.sendProgressBarUpdate(this, 0, this.sfAlloySmelter.alloyFurnaceCookTime);
        crafting.sendProgressBarUpdate(this, 1, this.sfAlloySmelter.alloyFurnaceBurnTime);
    }

    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ != 1 && p_82846_2_ != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (p_82846_2_ >= 3 && p_82846_2_ < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (p_82846_2_ >= 30 && p_82846_2_ < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(p_82846_1_, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}*/
