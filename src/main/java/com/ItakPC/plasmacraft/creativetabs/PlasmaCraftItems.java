package com.ItakPC.plasmacraft.creativetabs;

import com.ItakPC.plasmacraft.init.ModItems;
import com.ItakPC.plasmacraft.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PlasmaCraftItems {

    public static final CreativeTabs PlasmaCraftItems = new CreativeTabs("plasmacraftItems") {
        @Override
        public Item getTabIconItem() {
            return ModItems.debugTool;
        }

    };
}
