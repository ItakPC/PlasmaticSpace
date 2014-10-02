package com.ItakPC.plasmaticspace.recipe;

        import com.ItakPC.plasmaticspace.init.ModItems;
        import ic2.api.item.IC2Items;
        import net.minecraft.init.Items;
        import net.minecraft.item.ItemStack;
        import net.minecraftforge.oredict.OreDictionary;

        import java.util.ArrayList;

public class AlloyRecipes {

    private static final AlloyRecipes SMELTING_BASE = new AlloyRecipes();

    private ArrayList<AlloyCombinations> alloys = new ArrayList<AlloyCombinations>();

    public static final AlloyRecipes smelting() {

        return SMELTING_BASE;

    }

    private AlloyRecipes() {

        this.addRecipe(new ItemStack(ModItems.itemCopper, 2), new ItemStack(ModItems.itemZinc, 2), new ItemStack(ModItems.itemBrass, 4), 4, 4);
        this.addRecipe(new ItemStack(ModItems.itemCopper, 3), new ItemStack(ModItems.itemTin, 1), new ItemStack(ModItems.itemBronze, 4), 4, 4);

    }

    public void addRecipe(ItemStack ingredient1, ItemStack ingredient2, ItemStack result, int i, int i1) {

        alloys.add(new AlloyCombinations(ingredient1, ingredient2, result));

    }

    private int getRecipeIndex(ItemStack ingredient1, ItemStack ingredient2) {

        for(int x = 0; x < alloys.size(); x++) {
            if(alloys.get(x).match(ingredient1, ingredient2)) {
                return x;
            }
        }

        return -1;

    }

    public int getRecipeIndex(ItemStack result) {

        for(int x = 0; x < alloys.size(); x++) {
            if(alloys.get(x).getResult().equals(result)) {
                return x;
            }
        }

        return -1;

    }

    public ItemStack getSmeltingResult(ItemStack ingredient1, ItemStack ingredient2) {

        if(getRecipeIndex(ingredient1, ingredient2) != -1) {
            return alloys.get(getRecipeIndex(ingredient1, ingredient2)).getResult();
        }
        else {
            return null;
        }

    }

    public ArrayList<AlloyCombinations> getRecipeList() {

        return alloys;

    }

    public boolean isItemAlloyIngredient(ItemStack itemstack) {

        for(int x = 0; x < alloys.size(); x++) {
            if(alloys.get(x).isItemIngredient(itemstack)) {
                return true;
            }
        }

        return false;

    }

}
/*package com.ItakPC.plasmaticspace.recipe;

import com.ItakPC.plasmaticspace.PlasmaticSpace;
import com.ItakPC.plasmaticspace.init.ModItems;
import com.ItakPC.plasmaticspace.init.world.Ore;
import com.ItakPC.plasmaticspace.machine.sfAlloySmelter.AlloySmelter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class AlloyRecipes {

    public AlloyRecipes() {

    }

    public static ItemStack getSmeltingResult(Item item, Item item1) {
        return getOutput(item, item1);
    }

    public static ItemStack getOutput(Item item, Item item1) {
        if(item == ModItems.itemBituminousCoal && item == ModItems.itemSubbituminousCoal) {
            return new ItemStack(ModItems.itemGraphite, 2);
        }

        return null;
    }

}
*/