package com.ItakPC.plasmaticspace.init.world;

import com.ItakPC.plasmaticspace.block.BaseBlock;
import com.ItakPC.plasmaticspace.block.world.ore.coal.OreBituminousCoal;
import com.ItakPC.plasmaticspace.block.world.ore.coal.OreGraphite;
import com.ItakPC.plasmaticspace.block.world.ore.coal.OreLignite;
import com.ItakPC.plasmaticspace.block.world.ore.coal.OreSubbituminousCoal;
import com.ItakPC.plasmaticspace.block.world.ore.copper.OreChalcocite;
import com.ItakPC.plasmaticspace.block.world.ore.copper.OreChalcopyrite;
import com.ItakPC.plasmaticspace.block.world.ore.copper.OreMalachite;
import com.ItakPC.plasmaticspace.block.world.ore.gold.OreAuricupride;
import com.ItakPC.plasmaticspace.block.world.ore.gold.OreMaldonite;
import com.ItakPC.plasmaticspace.block.world.ore.iron.OreHematite;
import com.ItakPC.plasmaticspace.block.world.ore.iron.OreMagnetite;
import com.ItakPC.plasmaticspace.block.world.ore.lead.OreAnglesite;
import com.ItakPC.plasmaticspace.block.world.ore.lead.OreCerussite;
import com.ItakPC.plasmaticspace.block.world.ore.lead.OreGalena;
import com.ItakPC.plasmaticspace.block.world.ore.other.OreAluminum;
import com.ItakPC.plasmaticspace.block.world.ore.other.OreIridium;
import com.ItakPC.plasmaticspace.block.world.ore.other.OreTitanium;
import com.ItakPC.plasmaticspace.block.world.ore.other.OreUranium;
import com.ItakPC.plasmaticspace.block.world.ore.silver.OreArgentite;
import com.ItakPC.plasmaticspace.block.world.ore.silver.OreChlorargyrite;
import com.ItakPC.plasmaticspace.block.world.ore.tin.OreCassiterite;
import com.ItakPC.plasmaticspace.block.world.ore.tin.OreStannite;
import com.ItakPC.plasmaticspace.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import scala.xml.dtd.impl.Base;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class Ore {

    /** Iron */
    public static final BaseBlock oreHematite = new OreHematite();
    public static final BaseBlock oreMagnetite = new OreMagnetite();

    /** Gold */
    public static final BaseBlock oreMaldonite = new OreMaldonite();
    public static final BaseBlock oreAuricupride = new OreAuricupride();

    /** Coal */
    public static final BaseBlock oreLignite = new OreLignite();
    public static final BaseBlock oreSubbituminousCoal = new OreSubbituminousCoal();
    public static final BaseBlock oreBituminousCoal = new OreBituminousCoal();
    public static final BaseBlock oreGraphite = new OreGraphite();

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
    public static final BaseBlock oreUranium = new OreUranium();
    public static final BaseBlock oreAluminum = new OreAluminum();
    public static final BaseBlock oreTitanium = new OreTitanium();
    public static final BaseBlock oreIridium = new OreIridium();

    public static void init() {

        /** Game Registry */

        //Iron
        GameRegistry.registerBlock(oreHematite, "oreHematite");
        GameRegistry.registerBlock(oreMagnetite, "oreMagnetite");

        //Gold
        GameRegistry.registerBlock(oreMaldonite, "oreMaldonite");
        GameRegistry.registerBlock(oreAuricupride, "oreAuricupride");

        //Coal
        GameRegistry.registerBlock(oreLignite, "oreLignite");
        GameRegistry.registerBlock(oreSubbituminousCoal, "oreSubbituminousCoal");
        GameRegistry.registerBlock(oreBituminousCoal, "oreBituminousCoal");
        GameRegistry.registerBlock(oreGraphite, "oreGraphite");

        //Copper
        GameRegistry.registerBlock(oreChalcopyrite, "oreChalcopyrite");
        GameRegistry.registerBlock(oreChalcocite, "oreChalcocite");
        GameRegistry.registerBlock(oreMalachite, "oreMalachite");

        //Tin
        GameRegistry.registerBlock(oreCassiterite, "oreCassiterite");
        GameRegistry.registerBlock(oreStannite, "oreStannite");

        //Silver
        GameRegistry.registerBlock(oreArgentite, "oreArgentite");
        GameRegistry.registerBlock(oreChlorargyrite, "oreChlorargyrite");

        //Lead
        GameRegistry.registerBlock(oreGalena, "oreGalena");
        GameRegistry.registerBlock(oreCerussite, "oreCerussite");
        GameRegistry.registerBlock(oreAnglesite, "oreAnglesite");

        GameRegistry.registerBlock(oreUranium, "oreUranium");
        GameRegistry.registerBlock(oreAluminum, "oreAluminum");
        GameRegistry.registerBlock(oreTitanium, "oreTitanium");
        GameRegistry.registerBlock(oreIridium, "oreIridium");

    }

}
