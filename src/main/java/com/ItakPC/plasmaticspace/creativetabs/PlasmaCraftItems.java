package com.ItakPC.plasmaticspace.creativetabs;

import com.ItakPC.plasmaticspace.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PlasmaCraftItems {

    public static final CreativeTabs PlasmaCraftItems = new CreativeTabs("plasmaticspaceItems") {
        @Override
        public Item getTabIconItem() {
            return ModItems.debugTool;
        }

    };
}
