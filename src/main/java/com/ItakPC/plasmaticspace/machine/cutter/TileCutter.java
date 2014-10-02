package com.ItakPC.plasmaticspace.machine.cutter;

import com.ItakPC.plasmaticspace.recipe.CuttingRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileCutter extends TileEntity implements ISidedInventory {

    public int storedEnergy = 12000;
    public int maxPower = 12000;
    public int energyComsumptionPerTick = 3;
    private final int MAX_PROGRESS = 200;
    public int progress;

    private static final int[] slots_top = new int[] {0};
    private static final int[] slots_bottom = new int[] {2};
    private static final int[] slots_side = new int[] {0};

    private String localizedName;
    private ItemStack slots[];

    public TileCutter() {

        slots = new ItemStack[3];

    }

    public void setGuiDisplayName(String displayName) {

        this.localizedName = displayName;

    }

    public int getSizeInventory() {

        return slots.length;

    }

    public ItemStack getStackInSlot(int slot) {

        return slots[slot];

    }

    public ItemStack decrStackSize(int slot, int var2) {

        if (this.slots[slot] != null) {
            ItemStack itemstack;

            if (this.slots[slot].stackSize <= var2) {
                itemstack = this.slots[slot];
                this.slots[slot] = null;
                return itemstack;
            }
            else {
                itemstack = this.slots[slot].splitStack(var2);

                if (this.slots[slot].stackSize == 0) {
                    this.slots[slot] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }

    }

    public ItemStack getStackInSlotOnClosing(int slot) {

        if(slots[slot] != null) {

            ItemStack itemstack = this.slots[slot];
            this.slots[slot] = null;
            return itemstack;

        }
        return null;

    }

    public void setInventorySlotContents(int slot, ItemStack itemstack) {

        slots[slot] = itemstack;
        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
            itemstack.stackSize = getInventoryStackLimit();
        }

    }

    public String getInventoryName() {

        if(this.hasCustomInventoryName()) {
            return this.localizedName;
        }
        else {
            return "container.plateCuttingMachine";
        }

    }

    public boolean hasCustomInventoryName() {

        return this.localizedName != null && this.localizedName.length() > 0;

    }

    public int getInventoryStackLimit() {

        return 64;

    }

    public boolean isUseableByPlayer(EntityPlayer player) {

        if(worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
            return false;
        }
        else {
            return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64;
        }

    }

    public void openInventory() {}

    public void closeInventory() {}

    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {

        return true;

    }

    public boolean hasEnergyStored() {

        return storedEnergy > 0;

    }

    public int getProgressScaled(int scale) {

        if(progress == 0) {
            return 0;
        }
        return this.progress * scale / 200;

    }

    public int getEnergyScaled(int scale) {

        if(storedEnergy == 0) {
            return 0;
        }

        return this.storedEnergy * scale / maxPower;
    }

    public boolean isRunning() {

        return progress > 0;

    }

    public boolean canSmelt() {

        if(slots[0] == null) {
            return false;
        }
        else {
            ItemStack itemstack = CuttingRecipes.cutting().getCuttingResult(this.slots[0]);

            if(itemstack == null) {
                return false;
            }
            if(this.slots[2] == null) {
                return true;
            }
            if(!this.slots[2].isItemEqual(itemstack)) {
                return false;
            }

            int result = slots[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.slots[2].getMaxStackSize();
        }

    }

    public void writeToNBT(NBTTagCompound nbt) {

        super.writeToNBT(nbt);
        nbt.setShort("Progress", (short)progress);
        NBTTagList list = new NBTTagList();

        for(int x = 0; x < slots.length; x++) {
            if(slots[x] != null) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte)x);
                slots[x].writeToNBT(compound);
                list.appendTag(compound);
            }
        }

        nbt.setTag("Items", list);

    }

    public void readFromNBT(NBTTagCompound nbt) {

        super.readFromNBT(nbt);
        NBTTagList list = nbt.getTagList("Items", 10);
        slots = new ItemStack[getSizeInventory()];

        for(int x = 0; x < list.tagCount(); x++) {
            NBTTagCompound compound = (NBTTagCompound)list.getCompoundTagAt(x);
            byte b = compound.getByte("Slot");

            if(b >= 0 && b < slots.length) {
                slots[b] = ItemStack.loadItemStackFromNBT(compound);
            }
        }

        progress = nbt.getShort("Progress");

    }

    private void smeltItems() {

        if(canSmelt()) {
            ItemStack itemstack = CuttingRecipes.cutting().getCuttingResult(this.slots[0]);
            int recipeIndex = CuttingRecipes.cutting().getRecipeIndex(this.slots[0]);
            int resultStackSize = CuttingRecipes.cutting().getRecipeList().get(recipeIndex).getResultStackSize();

            if(slots[2] == null) {
                slots[2] = itemstack.copy();
            }
            else if(slots[2].isItemEqual(itemstack)) {
                slots[2].stackSize += resultStackSize;
            }

            if(slots[0].stackSize <= 0) {
                slots[0] = new ItemStack(slots[0].getItem());
            }
            else {
                slots[0].stackSize -= CuttingRecipes.cutting().getRecipeList().get(recipeIndex).getIngredientStackSize();
            }
            if(slots[0].stackSize <= 0) {
                slots[0] = null;
            }
        }

    }

    public void updateEntity() {

        super.updateEntity();

        boolean flag = this.isRunning();
        boolean flag1 = false;

        if(!worldObj.isRemote) {
            if(this.hasEnergyStored() && this.storedEnergy > energyComsumptionPerTick && this.canSmelt()) {

                if(this.progress == 200) {
                    this.progress = 0;
                    this.smeltItems();
                    flag1 = true;
                }

                ++this.progress;
                storedEnergy -= energyComsumptionPerTick;
            }
            else {
                this.progress = 0;
            }

            if(flag != isRunning()) {
                flag1 = true;
                CuttingMachine.updatePlateCuttingMachineBlockState(progress > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if(flag1) {
            markDirty();
        }

    }

    public int[] getAccessibleSlotsFromSide(int side) {

        return side == 0 ? slots_bottom : (side == 1 ? slots_top : slots_side);

    }

    public boolean canInsertItem(int i, ItemStack itemstack, int j) {

        return this.isItemValidForSlot(i, itemstack);

    }

    public boolean canExtractItem(int i, ItemStack itemstack, int j) {

        return j != 0 || i != 1;

    }

}