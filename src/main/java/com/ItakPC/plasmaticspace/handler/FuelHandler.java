package com.ItakPC.plasmaticspace.handler;

import com.ItakPC.plasmaticspace.init.ModItems;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Mod;
import net.minecraft.item.ItemStack;

public class FuelHandler implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack fuel) {
        if(fuel.getItem() == ModItems.itemLigniteCoal) return 1600;
        if(fuel.getItem() == ModItems.itemSubbituminousCoal) return 1800;
        if(fuel.getItem() == ModItems.itemBituminousCoal) return 2400;
        if(fuel.getItem() == ModItems.itemGraphite) return 200;

        return 0;
    }
}
