package com.ItakPC.plasmaticspace.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class CuttingRecipes {

    private static final CuttingRecipes CUTTING_BASE = new CuttingRecipes();

    private ArrayList<CuttingCombinations> cuttingOptions = new ArrayList<CuttingCombinations>();

    public static final CuttingRecipes cutting() {

        return CUTTING_BASE;

    }

    private CuttingRecipes() {

        //addRecipe(new ItemStack(Items.gold_ingot), new ItemStack(Items.iron_ingot));

    }

    public void addRecipe(ItemStack ingredient, ItemStack result) {

        cuttingOptions.add(new CuttingCombinations(ingredient, result));

    }

    public int getRecipeIndex(ItemStack ingredient) {

        for(int x = 0; x < cuttingOptions.size(); x++) {
            if(cuttingOptions.get(x).match(ingredient)) {
                return x;
            }
        }

        return -1;

    }

    public ItemStack getCuttingResult(ItemStack ingredient) {

        if(getRecipeIndex(ingredient) != -1) {
            return cuttingOptions.get(getRecipeIndex(ingredient)).getResult();
        }
        else {
            return null;
        }

    }

    public ArrayList<CuttingCombinations> getRecipeList() {

        return cuttingOptions;

    }

    public boolean isItemAlloyIngredient(ItemStack itemstack) {

        for(int x = 0; x < cuttingOptions.size(); x++) {
            if(cuttingOptions.get(x).isItemIngredient(itemstack)) {
                return true;
            }
        }

        return false;

    }
}
