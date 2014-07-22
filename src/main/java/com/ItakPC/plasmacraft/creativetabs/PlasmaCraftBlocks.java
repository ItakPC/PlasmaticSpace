package com.ItakPC.plasmacraft.creativetabs;

import com.ItakPC.plasmacraft.init.ModItems;
import com.ItakPC.plasmacraft.init.world.Decoration;
import com.ItakPC.plasmacraft.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PlasmaCraftBlocks {

    public static final CreativeTabs PlasmaCraftBlocks = new CreativeTabs("plasmacraftBlocks") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Decoration.blockBasalt);
        }

    };
}


