package com.ItakPC.plasmaticspace.init.world;

import com.ItakPC.plasmaticspace.block.BaseBlock;
import com.ItakPC.plasmaticspace.block.world.ore.copper.OreChalcocite;
import com.ItakPC.plasmaticspace.block.world.ore.copper.OreChalcopyrite;
import com.ItakPC.plasmaticspace.block.world.ore.copper.OreMalachite;
import com.ItakPC.plasmaticspace.block.world.ore.lead.OreAnglesite;
import com.ItakPC.plasmaticspace.block.world.ore.lead.OreCerussite;
import com.ItakPC.plasmaticspace.block.world.ore.lead.OreGalena;
import com.ItakPC.plasmaticspace.block.world.ore.silver.OreArgentite;
import com.ItakPC.plasmaticspace.block.world.ore.silver.OreChlorargyrite;
import com.ItakPC.plasmaticspace.block.world.ore.tin.OreCassiterite;
import com.ItakPC.plasmaticspace.block.world.ore.tin.OreStannite;
import com.ItakPC.plasmaticspace.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class Ore {

    /** Iron */
    /*public static final BaseBlock oreHematite = new OreHematite();
    public static final BaseBlock oreMagnetite = new OreMagnetite();*/

    /** Gold */
    /*public static final BaseBlock oreMaldonite = new OreMaldonite();
    public static final BaseBlock oreAuricupride = new OreAuricupride();*/

    /** Coal */
    /*public static final BaseBlock oreLignite = new OreLignite();
    public static final BaseBlock oreSubbituminousCoal = new OreSubbituminousCoal();
    public static final BaseBlock oreBituminousCoal = new OreBituminousCoal();
    public static final BaseBlock oreGraphite = new OreGraphite();*/

    /** Copper */
    public static final BaseBlock oreChalcopyrite = new OreChalcopyrite();
    public static final BaseBlock oreChalcocite = new OreChalcocite();
    public static final BaseBlock oreMalachite = new OreMalachite();

    /** Tin */
    public static final BaseBlock oreCassiterite = new OreCassiterite();
    public static final BaseBlock oreStannite = new OreStannite();

    /** Silver */
    public static final BaseBlock oreArgentite = new OreArgentite();
    public static final BaseBlock oreChlorargyrite = new OreChlorargyrite();

    /** Lead */
    public static final BaseBlock oreGalena = new OreGalena();
    public static final BaseBlock oreCerussite = new OreCerussite();
    public static final BaseBlock oreAnglesite = new OreAnglesite();

    /** Other */
    //public static final BaseBlock oreUranium = new OreUranium();
    //public static final BaseBlock oreAluminum = new OreAluminum();
    //public static final BaseBlock oreTitanium = new OreTitanium();

    public static void init() {

        GameRegistry.registerBlock(oreChalcopyrite, "oreChalcopyrite");
        GameRegistry.registerBlock(oreChalcocite, "oreChalcocite");
        GameRegistry.registerBlock(oreMalachite, "oreMalachite");

        GameRegistry.registerBlock(oreCassiterite, "oreCassiterite");
        GameRegistry.registerBlock(oreStannite, "oreStannite");

        GameRegistry.registerBlock(oreArgentite, "oreArgentite");
        GameRegistry.registerBlock(oreChlorargyrite, "oreChlorargyrite");

        GameRegistry.registerBlock(oreGalena, "oreGalena");
        GameRegistry.registerBlock(oreCerussite, "oreCerussite");
        GameRegistry.registerBlock(oreAnglesite, "oreAnglesite");
    }

}
