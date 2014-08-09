package com.ItakPC.plasmaticspace.init;

import com.ItakPC.plasmaticspace.item.BaseItem;
import com.ItakPC.plasmaticspace.item.ItemDebugTool;
import com.ItakPC.plasmaticspace.item.fuel.ItemBituminousCoal;
import com.ItakPC.plasmaticspace.item.fuel.ItemGraphite;
import com.ItakPC.plasmaticspace.item.fuel.ItemLigniteCoal;
import com.ItakPC.plasmaticspace.item.fuel.ItemSubbituminousCoal;
import com.ItakPC.plasmaticspace.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {


    public static final BaseItem debugTool = new ItemDebugTool();

    /** Coal */
    public static final BaseItem itemLigniteCoal = new ItemLigniteCoal();
    public static final BaseItem itemBituminousCoal = new ItemBituminousCoal();
    public static final BaseItem itemSubbituminousCoal = new ItemSubbituminousCoal();
    public static final BaseItem itemGraphite = new ItemGraphite();


    public static void init() {

        GameRegistry.registerItem(debugTool, "debugTool");

        /** Coal */
        GameRegistry.registerItem(itemLigniteCoal, "itemLigniteCoal");
        GameRegistry.registerItem(itemBituminousCoal, "itemBituminousCoal");
        GameRegistry.registerItem(itemSubbituminousCoal, "itemSubbituminousCoal");
        GameRegistry.registerItem(itemGraphite, "itemGraphite");

    }

}
