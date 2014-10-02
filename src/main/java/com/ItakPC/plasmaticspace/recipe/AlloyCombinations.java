package com.ItakPC.plasmaticspace.recipe;

import net.minecraft.item.ItemStack;

public class AlloyCombinations {

    private ItemStack input1;
    private ItemStack input2;
    private ItemStack result;

    public AlloyCombinations(ItemStack itemstack1, ItemStack itemstack2, ItemStack itemstack3) {

        input1 = itemstack1;
        input2 = itemstack2;
        result = itemstack3;

    }

    public ItemStack getResult() {

        return result;

    }

    public boolean match(ItemStack itemstack1, ItemStack itemstack2) {

        if(equals(itemstack1, input1) && equals(itemstack2, input2)) {
            return true;
        }

        return false;

    }

    public boolean equals(ItemStack itemstack1, ItemStack itemstack2) {

        if(itemstack1.getItem() != itemstack2.getItem()) {
            return false;
        }
        if(itemstack1.stackSize - itemstack2.stackSize < 0) {
            return false;
        }
        if(itemstack1.getItemDamage() != itemstack2.getItemDamage()) {
            return false;
        }

        return true;

    }

    public int getIngredient1StackSize() {

        return input1.stackSize;

    }

    public int getIngredient2StackSize() {

        return input2.stackSize;

    }

    public int getResultStackSize() {

        return result.stackSize;

    }

    public boolean isItemIngredient(ItemStack itemstack) {

        if(itemstack.getItem() == input1.getItem() || itemstack.getItem() == input2.getItem()) {
            return true;
        }

        return false;

    }
}
