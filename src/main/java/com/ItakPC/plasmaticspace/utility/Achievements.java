package com.ItakPC.plasmaticspace.utility;

import com.ItakPC.plasmaticspace.PlasmaticSpace;
import com.ItakPC.plasmaticspace.block.BaseBlock;
import com.ItakPC.plasmaticspace.init.world.Ore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

public class Achievements {

    public static Achievement enterPlasmaticAge;
    public static Achievement betterFurnace;

    public static void init() {

        enterPlasmaticAge = new Achievement("achievement.enterPlasmaticAge", "mineOre", 0, 0, new ItemStack(Ore.oreBituminousCoal), (Achievement) AchievementList.buildBetterPickaxe).initIndependentStat().setSpecial().registerStat();
        betterFurnace = new Achievement("achievement.betterFurnace", "craftFurnace", 2, 0, new ItemStack(PlasmaticSpace.solidFuelFurnaceIdle), (Achievement) Achievements.enterPlasmaticAge).initIndependentStat().registerStat();

        AchievementPage.registerAchievementPage(new AchievementPage("PlasmaticSpace", new Achievement[]{enterPlasmaticAge, betterFurnace}));
    }
}
