package com.ItakPC.plasmaticspace.machine.sfAlloySmelter;

import com.ItakPC.plasmaticspace.recipe.AlloyRecipes;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class sfTileAlloySmleter extends TileEntity implements ISidedInventory {

    private String localizedName;
    private ItemStack slots[];

    private static final int[] slots_top = new int[] {0, 1};
    private static final int[] slots_bottom = new int[] {3};
    private static final int[] slots_side = new int[] {0, 1};

    public int alloyFurnaceCookTime;
    public int alloyFurnaceBurnTime;
    public int currentItemBurnTime;

    public sfTileAlloySmleter() {

        slots = new ItemStack[4];

    }

    public void setGuiDisplayName(String displayName) {

        this.localizedName = displayName;

    }

    @Override
    public int getSizeInventory() {

        return slots.length;

    }

    @Override
    public ItemStack getStackInSlot(int slot) {

        return slots[slot];

    }

    @Override
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

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {

        if(slots[slot] != null) {

            ItemStack itemstack = this.slots[slot];
            this.slots[slot] = null;
            return itemstack;

        }
        return null;

    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack) {

        slots[slot] = itemstack;
        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
            itemstack.stackSize = getInventoryStackLimit();
        }

    }

    @Override
    public String getInventoryName() {

        if(this.hasCustomInventoryName()) {
            return this.localizedName;
        }
        else {
            return "container.alloyFurnace";
        }

    }

    @Override
    public boolean hasCustomInventoryName() {

        return this.localizedName != null && this.localizedName.length() > 0;

    }

    @Override
    public int getInventoryStackLimit() {

        return 64;

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {

        if(worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
            return false;
        }
        else {
            return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64;
        }

    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {

        return true;

    }

    public static boolean isItemFuel(ItemStack itemstack) {

        return getItemBurnTime(itemstack) > 0;

    }

    public static int getItemBurnTime(ItemStack itemstack) {

        if (itemstack == null) {
            return 0;
        }
        else {
            Item item = itemstack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab) {
                    return 150;
                }

                if (block.getMaterial() == Material.wood) {
                    return 300;
                }

                if (block == Blocks.coal_block) {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return GameRegistry.getFuelValue(itemstack);
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

    public void writeToNBT(NBTTagCompound nbt) {

        super.writeToNBT(nbt);
        nbt.setShort("BurnTime", (short)alloyFurnaceBurnTime);
        nbt.setShort("CookTime", (short)alloyFurnaceCookTime);
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

        alloyFurnaceBurnTime = nbt.getShort("BurnTime");
        alloyFurnaceCookTime = nbt.getShort("CookTime");

    }

    public int getCookTimeRemainingScaled(int scale) {

        return this.alloyFurnaceCookTime * scale / 200;

    }

    public int getBurnTimeRemainingScaled(int scale) {

        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 200;
        }

        return this.alloyFurnaceBurnTime * scale / this.currentItemBurnTime;

    }

    public boolean isSmelting() {
        return this.alloyFurnaceCookTime > 0;
    }

    public boolean isBurning() {

        return this.alloyFurnaceBurnTime > 0;

    }

    private boolean canSmelt() {

        if(slots[0] == null || slots[1] == null) {
            return false;
        }
        else {
            ItemStack itemstack = AlloyRecipes.smelting().getSmeltingResult(this.slots[0], this.slots[1]);

            if(itemstack == null) {
                return false;
            }
            if(this.slots[3] == null) {
                return true;
            }
            if(!this.slots[3].isItemEqual(itemstack)) {
                return false;
            }

            int result = slots[3].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.slots[3].getMaxStackSize();
        }

    }

    private void smeltItems() {

        if(canSmelt()) {
            ItemStack itemstack = AlloyRecipes.smelting().getSmeltingResult(slots[0], slots[1]);
            int resultStackSize = AlloyRecipes.smelting().getRecipeList().get(AlloyRecipes.smelting().getRecipeIndex(itemstack)).getResultStackSize();

            if(slots[3] == null) {
                slots[3] = itemstack.copy();
            }
            else if(slots[3].isItemEqual(itemstack)) {
                slots[3].stackSize += resultStackSize;
            }

            for(int x = 0; x < 2; x++) {
                if(slots[x].stackSize <= 0) {
                    slots[x] = new ItemStack(slots[x].getItem());
                }
                else {
                    if(x == 0) {
                        slots[x].stackSize -= AlloyRecipes.smelting().getRecipeList().get(AlloyRecipes.smelting().getRecipeIndex(itemstack)).getIngredient1StackSize();
                    }
                    if(x == 1) {
                        slots[x].stackSize -= AlloyRecipes.smelting().getRecipeList().get(AlloyRecipes.smelting().getRecipeIndex(itemstack)).getIngredient2StackSize();
                    }
                }
                if(slots[x].stackSize <= 0) {
                    slots[x] = null;
                }
            }
        }

    }

    public void updateEntity() {

        boolean flag = this.isBurning();
        boolean flag1 = false;

        /** This should lower the burn time while burning && smelting. **/ //Otherwise i guess it will not lower it :D
        if (this.isBurning() && this.isSmelting()) {
            --this.alloyFurnaceBurnTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.alloyFurnaceBurnTime == 0 && this.canSmelt()) {
                this.currentItemBurnTime = this.alloyFurnaceBurnTime = getItemBurnTime(this.slots[2]);

                if (this.alloyFurnaceBurnTime > 0) {
                    flag1 = true;

                    if (this.slots[2] != null) {
                        --this.slots[2].stackSize;

                        if (this.slots[2].stackSize == 0) {
                            this.slots[2] = slots[2].getItem().getContainerItem(slots[2]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt()) {
                ++this.alloyFurnaceCookTime;

                if (this.alloyFurnaceCookTime == 200) {
                    this.alloyFurnaceCookTime = 0;
                    this.smeltItems();
                    flag1 = true;
                }
            }
            else {
                this.alloyFurnaceCookTime = 0;
            }

            if (flag != this.isBurning()) {
                flag1 = true;
                sfAlloySmelter.updateBlockState(this.alloyFurnaceCookTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1) {
            this.markDirty();
        }

    }

}
