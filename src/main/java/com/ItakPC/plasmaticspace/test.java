package com.ItakPC.plasmaticspace;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class test {

    public int speed = 100;
    private ItemStack slots[];

    private static final int[] slotsInput = new int[] {0};
    private static final int[] slotFuel = new int[] {1};
    private static final int[] slotOutput = new int[] {2};
    private static final int[] slotsUpgrade = new int[] {3, 4, 5, 6};

    public test() {
        slots = new ItemStack[7];
    }
    public void upgrades(ItemStack itemStack) {
        if(itemStack == null) {
            speed = 100;
        }else{
            Item upgradeSlot1 = slots[3].getItem();
            Item upgradeSlot2 = slots[4].getItem();
            Item upgradeSlot3 = slots[5].getItem();
            Item upgradeSlot4 = slots[6].getItem();

            /** Should double the speed */
            if(upgradeSlot1 == Items.redstone) speed = 200;
            if(upgradeSlot2 == Items.redstone) speed = 200;
            if(upgradeSlot3 == Items.redstone) speed = 200;
            if(upgradeSlot4 == Items.redstone) speed = 200;

            /** Should quadruple the speed */
            if(upgradeSlot1 == Items.glowstone_dust) speed = 400;
            if(upgradeSlot2 == Items.glowstone_dust) speed = 400;
            if(upgradeSlot3 == Items.glowstone_dust) speed = 400;
            if(upgradeSlot4 == Items.glowstone_dust) speed = 400;

        }
    }
}

