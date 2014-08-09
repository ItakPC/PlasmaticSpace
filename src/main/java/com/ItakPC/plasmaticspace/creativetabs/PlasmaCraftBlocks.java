package com.ItakPC.plasmaticspace.creativetabs;

import com.ItakPC.plasmaticspace.init.world.Decoration;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PlasmaCraftBlocks {

    public static final CreativeTabs PlasmaCraftBlocks = new CreativeTabs("plasmaticspaceBlocks") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Decoration.blockBasalt);
        }

    };
}


