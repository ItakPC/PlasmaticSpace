package com.ItakPC.plasmaticspace.item.tool;

import com.ItakPC.plasmaticspace.PlasmaticSpace;
import com.ItakPC.plasmaticspace.creativetabs.PlasmaCraftItems;
import com.ItakPC.plasmaticspace.reference.Reference;
import com.ItakPC.plasmaticspace.tool.ItemDrill;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class ItemPlasmaticFist extends ItemDrill {

    public ItemPlasmaticFist(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("itemPlasmaticFist");
        this.setMaxStackSize(1);
        this.setNoRepair();
        this.setCreativeTab(PlasmaCraftItems.PlasmaCraftItems);
    }

    public void icon(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + "itemPlasmaticFist");
    }
}
