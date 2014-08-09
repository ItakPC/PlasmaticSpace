package com.ItakPC.plasmaticspace.handler;

import com.ItakPC.plasmaticspace.init.ModItems;
import com.ItakPC.plasmaticspace.init.world.Ore;
import com.ItakPC.plasmaticspace.utility.Achievements;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class EventHandler {

    @SubscribeEvent
    public void pickUpEvent(PlayerEvent.ItemPickupEvent event) {
        if(event.pickedUp.getEntityItem().isItemEqual(new ItemStack(Ore.oreBituminousCoal))) {
            event.player.addStat(Achievements.enterPlasmaticAge, 1);
        }
    }

    @SubscribeEvent
    public void craftedEvent(PlayerEvent.ItemCraftedEvent event) {
        if(event.crafting.isItemEqual(new ItemStack(ModItems.debugTool))) {

        }
    }

    @SubscribeEvent
    public void smelteddEvent(PlayerEvent.ItemSmeltedEvent event) {
        if(event.smelting.isItemEqual(new ItemStack(Items.gold_ingot))) {

        }
    }
}
