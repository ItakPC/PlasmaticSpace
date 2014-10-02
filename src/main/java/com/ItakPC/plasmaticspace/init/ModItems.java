package com.ItakPC.plasmaticspace.init;

import com.ItakPC.plasmaticspace.item.BaseItem;
import com.ItakPC.plasmaticspace.item.ItemDebugTool;
import com.ItakPC.plasmaticspace.item.ingot.*;
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

    /** Metals */
    public static final BaseItem itemCopper = new ItemCopperIngot();
    public static final BaseItem itemTin = new ItemTinIngot();
    public static final BaseItem itemZinc = new ItemZincIngot();

    /** Alloys */
    public static final BaseItem itemBronze = new ItemBronzeIngot();
    public static final BaseItem itemBrass = new ItemBrassIngot();

    /** Plates */

    //TODO Plates and Tubes And Other Junk :D
    /*public static final BaseItem itemUnfinishedCopperPlate = new ItemCopperPlate();
    public static final BaseItem itemUnfinishedTinPlate = new ItemCopperPlate();
    public static final BaseItem itemUnfinishedZincPlate = new ItemCopperPlate();
    public static final BaseItem itemUnfinishedBronzePlate = new ItemCopperPlate();
    public static final BaseItem itemUnfinishedBrassPlate = new ItemCopperPlate();

    public static final BaseItem itemCopperPlate = new ItemCopperPlate();
    public static final BaseItem itemTinPlate = new ItemCopperPlate();
    public static final BaseItem itemZincPlate = new ItemCopperPlate();
    public static final BaseItem itemBronzePlate = new ItemCopperPlate();
    public static final BaseItem itemBrassPlate = new ItemCopperPlate();*/
    /** Tubes */

    public static void init() {

        GameRegistry.registerItem(debugTool, "debugTool");

        /** Coal */
        GameRegistry.registerItem(itemLigniteCoal, "itemLigniteCoal");
        GameRegistry.registerItem(itemBituminousCoal, "itemBituminousCoal");
        GameRegistry.registerItem(itemSubbituminousCoal, "itemSubbituminousCoal");
        GameRegistry.registerItem(itemGraphite, "itemGraphite");

        /** Metals */
        GameRegistry.registerItem(itemCopper, "itemCopper");
        GameRegistry.registerItem(itemTin, "itemTin");
        GameRegistry.registerItem(itemZinc, "itemZinc");

        /** Alloys */
        GameRegistry.registerItem(itemBronze, "itemBronze");
        GameRegistry.registerItem(itemBrass, "itemBrass");

        /** Plates */



        /** Tubes */

    }

}
