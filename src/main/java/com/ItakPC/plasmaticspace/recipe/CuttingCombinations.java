package com.ItakPC.plasmaticspace.recipe;

import net.minecraft.item.ItemStack;

public class CuttingCombinations {

        private ItemStack input;
        private ItemStack result;

        public CuttingCombinations(ItemStack itemstack1, ItemStack itemstack2) {

            input = itemstack1;
            result = itemstack2;

        }

        public ItemStack getResult() {

            return result;

        }

        public boolean match(ItemStack itemstack1) {

            if(equals(itemstack1, input)) {
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

        public int getIngredientStackSize() {

            return input.stackSize;

        }

        public int getResultStackSize() {

            return result.stackSize;

        }

        public boolean isItemIngredient(ItemStack itemstack) {

            if(itemstack.getItem() == input.getItem()) {
                return true;
            }

            return false;

        }

    }

