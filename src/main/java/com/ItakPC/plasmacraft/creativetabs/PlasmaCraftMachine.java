package com.ItakPC.plasmacraft.creativetabs;

import com.ItakPC.plasmacraft.init.ModItems;
import com.ItakPC.plasmacraft.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class PlasmaCraftMachine {

    public static final CreativeTabs PlasmaCraftMachine = new CreativeTabs("plasmacraftMachine") {

        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.redstone_torch);
        }

    };
}
