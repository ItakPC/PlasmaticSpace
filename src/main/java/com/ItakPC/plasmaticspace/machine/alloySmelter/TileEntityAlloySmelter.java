package com.ItakPC.plasmaticspace.machine.alloySmelter;

import com.ItakPC.plasmaticspace.recipe.AlloyRecipes;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAlloySmelter extends TileEntity implements ISidedInventory {

    private ItemStack slots[];

    public int cookTime;
    public int power = 12800;
    public int maxPower = 12800;
    public int speed = 100;

    private static final int[] slotsInput = new int[] {0, 1};
    private static final int[] slotFuel = new int[] {2};
    private static final int[] slotOutput = new int[] {3};

    private String customName;

    public TileEntityAlloySmelter() {
        slots = new ItemStack[4];
    }

    public void updateEntity() {
        boolean flag = this.hasPower();
        boolean flag1 = false;

        if(hasPower() && this.isCooking()) {
            this.power--;
        }

        if(!worldObj.isRemote) {
            if(this.hasItemPower(this.slots[2]) && this.power < (this.maxPower - this.getItemPower(this.slots[2]))) {
                this.power += getItemPower(this.slots[2]);

                if(this.slots[2] != null) {
                    flag1 = true;

                    this.slots[2].stackSize--;

                    if(this.slots[2].stackSize == 0) {
                        this.slots[2] = this.slots[2].getItem().getContainerItem(this.slots[2]);
                    }
                }
            }

            if(hasPower() && canSmelt()) {
                cookTime++;

                if(this.cookTime == this.speed) {
                    this.cookTime = 0;
                    this.smelt();
                    flag1 = true;
                }
            }else{
                cookTime = 0;
            }

            if(flag != this.hasPower()) {
                flag1 = true;
                AlloySmelter.updateBlockState(this.isCooking(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if(flag1) {
            this.markDirty();
        }
    }

    private boolean isCooking() {
        return this.cookTime > 0;
    }

    public boolean hasPower() {
        return power > 0;
    }

    private boolean canSmelt() {
        if(slots[0] == null || slots[1] == null) {
            return false;
        }

        ItemStack itemStack = AlloyRecipes.getResult(slots[0].getItem(), slots[1].getItem());

        if(itemStack == null) {
            return false;
        }

        if(slots[3] == null) {
            return true;
        }

        if(!slots[3].isItemEqual(itemStack)) {
            return false;
        }

        if(slots[3].stackSize < getInventoryStackLimit() && slots[3].stackSize < slots[3].getMaxStackSize()) {
            return true;
        }else{
            return slots[3].stackSize < itemStack.getMaxStackSize();
        }
    }

    private void smelt() {
        if(canSmelt()) {
           ItemStack itemStack = AlloyRecipes.getResult(slots[0].getItem(), slots[1].getItem());

            if(slots[3] == null) {
                slots[3] = itemStack.copy();
            }else if(slots[3].isItemEqual(itemStack)) {
                slots[3].stackSize += itemStack.stackSize;
            }

            for(int i = 0; i < 2; i++) {
                if(slots[i].stackSize <= 0) {
                    slots[i] = new ItemStack(slots[i].getItem().setFull3D());
                }else{
                    slots[i].stackSize--;
                }

                if(slots[i].stackSize <= 0) {
                    slots[i] = null;
                }
            }
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("slots", 10);
        this.slots = new ItemStack[getSizeInventory()];

        for(int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound item = list.getCompoundTagAt(i);
            byte b = item.getByte("item");

            if(b >= 0 && b < this.slots.length) {
                this.slots[b] = ItemStack.loadItemStackFromNBT(item);
            }
        }

        power = nbt.getShort("power");
        cookTime = nbt.getShort("cookTime");

        if(nbt.hasKey("customName")) {
            this.setInventoryName(nbt.getString("customName"));
        }
    }

    /** Used To Write To Named Binary Tag - power, items in inv */
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setShort("power", (short)power);
        nbt.setShort("cookTime", (short)cookTime);

        NBTTagList list = new NBTTagList();

        for(int i = 0; i < this.slots.length; i++) {
            if(this.slots[i] != null) {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("item", (byte)i);
                this.slots[i].writeToNBT(item);
                list.appendTag(item);
            }
        }

        nbt.setTag("slots", list);

        if(this.hasCustomInventoryName()) {
            nbt.setString("customName", this.getInventoryName());
        }

    }

    @Override
    public int[] getAccessibleSlotsFromSide(int i) {
        return i == 0 ? slotOutput : (i == 1 ? slotsInput : slotFuel);
    }

    /** WIP Used To Insert Item By hopper, pipe */
    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, int j) {
        return this.isItemValidForSlot(i, itemStack);
    }

    /** WIP Used To Pull Item By hopper, pipe */
    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int j) {
        return j != 0 || i != 1 || itemStack.getItem() == Items.bucket;
    }

    @Override
    public int getSizeInventory() {
        return this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return this.slots[i];
    }

    /** Used To Pull Items Out Of The Inv By Player */
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

    /** Used To Get Each ItemStack In Inv */
    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.slots[i] = itemstack;

        if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    /** Each ItemStack In Inv Can Store Only Up To 64 Items */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return i == 2 ? false : (i == 1 ? hasItemPower(itemStack) : true);
    }

    /** If items has got power > 0 then is has got power */
    private boolean hasItemPower(ItemStack itemStack) {
        return getItemPower(itemStack) > 0;
    }

    /** Gets power for each item specified. */
    private int getItemPower(ItemStack itemStack) {
        if(itemStack == null) {
            return 0;
        }else{
            Item item = itemStack.getItem();

            if(item == Items.redstone) return 400;
            if(item == Items.glowstone_dust) return 800;

        }

        return 0;
    }

    /** Used to draw the progress bar. */
    public int getProgressScaled(int i) {
        return (cookTime * i) / speed;
    }

    /** Used to draw the power bar. */
    public int getPowerScaled(int i) {
        return (power * i) / maxPower;
    }

    public void setInventoryName(String string) {

    }
}
