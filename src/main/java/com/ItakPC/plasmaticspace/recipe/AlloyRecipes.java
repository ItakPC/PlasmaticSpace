package com.ItakPC.plasmaticspace.recipe;

import com.ItakPC.plasmaticspace.PlasmaticSpace;
import com.ItakPC.plasmaticspace.init.ModItems;
import com.ItakPC.plasmaticspace.init.world.Ore;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AlloyRecipes {

    public AlloyRecipes() {

    }

    public static ItemStack getResult(Item item, Item item1) {
        return getOutput(item, item1);
    }

    private static ItemStack getOutput(Item item, Item item1) {
        /** Test */
        if(item == ModItems.itemGraphite && item1 == ModItems.itemBituminousCoal || item == ModItems.itemBituminousCoal && item1 == ModItems.itemGraphite) {
            return new ItemStack(ModItems.debugTool, 1);
        }

        return null;
    }
}
