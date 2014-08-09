package com.ItakPC.plasmaticspace.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class PlasmaCraftMachine {

    public static final CreativeTabs PlasmaCraftMachine = new CreativeTabs("plasmaticspaceMachine") {

        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.redstone_torch);
        }

    };
}
