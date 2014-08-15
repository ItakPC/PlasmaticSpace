package com.ItakPC.plasmaticspace.machine.solidFuelFurnace;

import com.ItakPC.plasmaticspace.init.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySFFurnace extends TileEntity implements ISidedInventory{

    private static final int[] slots_input = new int[]{0};
    private static final int[] slots_fuel = new int[]{1};
    private static final int[] slots_output = new int[]{2};

    private String localizedName;

    /** The Speed Of Furnace */
    //TODO try to make an upgrade for it
    public int speed = 150;

    /** Number Of Ticks The Furnace Will Keep Burning */
    public int burnTime;

    /** Number Of Ticks That The Item Will Keep The Furnace Burning For */
    public int currentItemBurnTime;

    /** Number Of Ticks The Item Has Been Cooking For */
    public int cookTime;

    private ItemStack[] slots = new ItemStack[3];

    public void setGuiDisplayName(String displayName) {
        this.localizedName = displayName;
    }

    @Override
    public int getSizeInventory() {
        return this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return this.slots[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if(this.slots[i] != null) {
            ItemStack itemStack;

            if(this.slots[i].stackSize <= j) {
                itemStack = this.slots[i];
                this.slots[i] = null;
            }else{
                itemStack = this.slots[i].splitStack(j);

                if(this.slots[i].stackSize == 0) {
                    this.slots[i] = null;
                }
            }

            return itemStack;

        }

        return null;

    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if(this.slots[i] != null) {
            ItemStack itemStack = this.slots[i];
            this.slots[i] = null;
            return itemStack;
        }

        return null;

    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.slots[i] = itemstack;

        if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInventoryName() {
        return  this.hasCustomInventoryName() ? this.localizedName : "container.solidFuelFurnace";
    }

    public boolean hasCustomInventoryName() {
        return this.localizedName != null && this.localizedName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : p_70300_1_.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {


        return i == 2 ? false : (i == 1 ? isItemFuel(itemStack) : true);
    }

    public static boolean isItemFuel(ItemStack itemStack) {
        return getItemBurnTime(itemStack) > 0;
    }

    private static int getItemBurnTime(ItemStack itemStack) {
        if(itemStack == null){
            return 0;
        }else{
            Item item = itemStack.getItem();
            if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air){
                Block block = Block.getBlockFromItem(item);

                if(item == ModItems.itemLigniteCoal) return 2000;
                if(item == ModItems.itemBituminousCoal) return 2800;
                if(item == ModItems.itemSubbituminousCoal) return 2200;
                if(item == ModItems.itemGraphite) return 400;

                if(item == Items.coal) return 2000;
                if(item == Items.stick) return 400;
                if(item == Items.lava_bucket) return 25000;
                if(item == Items.blaze_rod) return 2800;
                if(block == Blocks.sapling) return 400;
                if(block == Blocks.coal_block) return 16000;

                return GameRegistry.getFuelValue(itemStack);

            }
        }
        return 0;
    }

    public void updateEntiy() {
        boolean flag = this.burnTime > 0;
        boolean flag1 = false;

        /** Is Used For Lowering Burn Time But Because Its Better Than Vannila It Will Keep The Same Level */
        //TODO Remove burnTime--;
        if (this.isBurning()) {
            this.burnTime--;
        }

        if (!this.worldObj.isRemote) {
            if (this.burnTime == 0 && this.canSmelt()) {
                this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.slots[1]);

                if (this.isBurning()) {
                    flag1 = true;

                    if (this.slots[1] != null) {
                        this.slots[1].stackSize--;

                        if (this.slots[1].stackSize == 0) {
                            this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);
                        }
                    }
                }
            }
        }

        if (this.isBurning() && this.canSmelt()) {
            this.cookTime++;

            if (this.cookTime == this.speed) {
                this.cookTime = 0;
                this.smeltItem();
                flag1 = true;

            } else {
                this.cookTime = 0;
            }

            if (flag != this.isBurning()) {
                flag1 = true;
                SolidFuelFurnace.updateBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if(flag1) {
            this.markDirty();
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);


            if (this.slots[2] == null) {
                this.slots[2] = itemStack.copy();
            } else if (this.slots[2].isItemEqual(itemStack)) {
                this.slots[2].stackSize += itemStack.stackSize;
            }

            this.slots[0].stackSize--;

            if (this.slots[0].stackSize <= 0) {
                this.slots[0] = null;
            }
        }
    }


    public boolean canSmelt() {
        if(this.slots[0] == null) {
            return false;
        }else {
            ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);

            if (itemStack == null) return false;
            if (this.slots[2] == null) return true;
            if (!this.slots[2].isItemEqual(itemStack)) return false;

            int result = this.slots[2].stackSize + itemStack.stackSize;

            return (result <= getInventoryStackLimit() && result <= itemStack.getMaxStackSize());
        }
/*
            if(this.slots[2] == null) {
                this.slots[2] = itemStack.copy();
            }else if(this.slots[2].isItemEqual(itemStack)) {
                this.slots[2].stackSize += itemStack.stackSize;
            }

            this.slots[0].stackSize--;

            if(this.slots[0].stackSize <= 0) {
                this.slots[0] = null;
            }
        }

        return false;*/
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    public int getSizedInventory() {
        return this.slots.length;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int slot) {
        return slot == 0 ? slots_input : (slot == 1 ? slots_fuel : slots_output);
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, int j) {
        return this.isItemValidForSlot(i, itemStack);
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int j) {
        return j != 0 || i != 1 || itemStack.getItem() == Items.bucket;
    }

    public int getBurnTimeRemainingScaled(int i) {
        if(this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = this.speed;
        }

        return this.burnTime * i / this.currentItemBurnTime;
    }

    public int getCookProgressScaled(int i) {
        return this.cookTime * i / this.speed;
    }
}
