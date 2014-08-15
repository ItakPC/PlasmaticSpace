package com.ItakPC.plasmaticspace.item.tool;


import com.ItakPC.plasmaticspace.creativetabs.PlasmaCraftItems;
import com.ItakPC.plasmaticspace.tool.ItemDrill;
import net.minecraft.item.Item;

public class ItemPlasmaticFist extends ItemDrill {


    public ItemPlasmaticFist(Item.ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("itemPlasmaticFist");
        this.setMaxStackSize(1);
        this.setNoRepair();
        this.setCreativeTab(PlasmaCraftItems.PlasmaCraftItems);
    }
}
