package com.ItakPC.plasmaticspace.init;

import com.ItakPC.plasmaticspace.item.BaseItem;
import com.ItakPC.plasmaticspace.item.ItemDebugTool;
import com.ItakPC.plasmaticspace.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {

    public static final BaseItem debugTool = new ItemDebugTool();

    //public static final BaseItem itemWindmill = new ItemWindmill();

    public static void init() {

        GameRegistry.registerItem(debugTool, "debugTool");
        //GameRegistry.registerItem(itemWindmill, "itemWindmill");

    }

}
