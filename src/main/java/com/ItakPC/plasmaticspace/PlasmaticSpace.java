package com.ItakPC.plasmaticspace;

import com.ItakPC.plasmaticspace.cable.BlockEnergyConduit;
import com.ItakPC.plasmaticspace.cable.TileEntityEnergyConduit;
import com.ItakPC.plasmaticspace.creativetabs.PlasmaCraftMachine;
import com.ItakPC.plasmaticspace.handler.ConfigurationHandler;
import com.ItakPC.plasmaticspace.handler.EventHandler;
import com.ItakPC.plasmaticspace.handler.FuelHandler;
import com.ItakPC.plasmaticspace.handler.GuiHandler;
import com.ItakPC.plasmaticspace.init.ModBlocks;
import com.ItakPC.plasmaticspace.init.ModItems;
import com.ItakPC.plasmaticspace.init.ModTools;
import com.ItakPC.plasmaticspace.init.world.Decoration;
import com.ItakPC.plasmaticspace.init.world.Ore;
import com.ItakPC.plasmaticspace.item.tool.ItemPlasmaticFist;
import com.ItakPC.plasmaticspace.machine.alloySmelter.AlloySmelter;
import com.ItakPC.plasmaticspace.machine.alloySmelter.TileEntityAlloySmelter;
import com.ItakPC.plasmaticspace.machine.cutter.Cutter;
import com.ItakPC.plasmaticspace.machine.cutter.TileEntityCutter;
import com.ItakPC.plasmaticspace.machine.windmill.ItemWindTurbine;
import com.ItakPC.plasmaticspace.machine.windmill.TileWindTurbine;
import com.ItakPC.plasmaticspace.machine.windmill.WindTurbine;
import com.ItakPC.plasmaticspace.proxy.IProxy;
import com.ItakPC.plasmaticspace.reference.Reference;
import com.ItakPC.plasmaticspace.utility.Achievements;
import com.ItakPC.plasmaticspace.world.OreGen;
import com.ItakPC.plasmaticspace.world.WorldGen;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = Reference.MOD_ID, version = Reference.MOD_NAME, guiFactory = Reference.GuiFactoryClass)

public class PlasmaticSpace {


    public static Item itemWindmill;

    /** Tools */
    public static Item itemPlasmaticFist;

    /** Generators */
    public static Block blockWindmill;

    /** Conduits */
    public static Block energyConduit;

    /** Alloy Smelter */
    public static Block alloySmelterIdle;
    public static Block alloySmelterActive;

    /** Cutter */
    public static Block cutterIdle;
    public static Block cutterActive;

    /** GUI ID's */
    public static final int guiIDWindmillBasic = 0;
    public static final int guiIDAlloySmelter = 2;
    public static final int guiIDCutter = 3;

    /** Materials */
    public static Item.ToolMaterial PlasmaMaterial = EnumHelper.addToolMaterial("plasma", 16, 32000, 32F, 32F, 0);

    /** World Generators */
    OreGen oreGen = new OreGen();
    WorldGen worldGen = new WorldGen();

    @Mod.Instance
    public static PlasmaticSpace instance;

    @SidedProxy(clientSide= Reference.CProxy, serverSide = Reference.SProxy)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        itemWindmill = new ItemWindTurbine().setTextureName("diamond").setCreativeTab(PlasmaCraftMachine.PlasmaCraftMachine).setUnlocalizedName("itemWindmill");
        itemPlasmaticFist = new ItemPlasmaticFist(PlasmaMaterial).setTextureName("itemPlasmaticFist").setUnlocalizedName("itemPlasmaticFist");

        blockWindmill = new WindTurbine(Material.iron).setBlockName("blockWindmill");
        energyConduit = new BlockEnergyConduit(Material.iron).setBlockName("energyConduit").setCreativeTab(PlasmaCraftMachine.PlasmaCraftMachine);

        alloySmelterActive = new AlloySmelter(true).setBlockName("alloySmelter");
        alloySmelterIdle = new AlloySmelter(false).setBlockName("alloySmelter").setCreativeTab(PlasmaCraftMachine.PlasmaCraftMachine);

        cutterActive = new Cutter(true).setBlockName("cutter");
        cutterIdle = new Cutter(false).setBlockName("cutter").setCreativeTab(PlasmaCraftMachine.PlasmaCraftMachine);

        GameRegistry.registerItem(itemWindmill, "itemWindmill");
        GameRegistry.registerItem(itemPlasmaticFist, "itemPlasmaticFist");

        GameRegistry.registerBlock(blockWindmill, "blockWindmill");
        GameRegistry.registerBlock(energyConduit, "energyConduit");

        GameRegistry.registerBlock(alloySmelterActive, "alloySmelterActive");
        GameRegistry.registerBlock(alloySmelterIdle, "alloySmelterIdle");

        GameRegistry.registerBlock(cutterActive, "cutterActive");
        GameRegistry.registerBlock(cutterIdle, "cutterIdle");

        GameRegistry.registerFuelHandler(new FuelHandler());

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        FMLCommonHandler.instance().bus().register(new OreGen());
        FMLCommonHandler.instance().bus().register(new EventHandler());

        GameRegistry.registerWorldGenerator(oreGen, 0);
        GameRegistry.registerWorldGenerator(worldGen, 0);

        ModItems.init();
        ModBlocks.init();
        Decoration.init();
        Ore.init();
        ModTools.init();
        Achievements.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        GameRegistry.registerTileEntity(TileWindTurbine.class, "windmillBasic");
        GameRegistry.registerTileEntity(TileEntityEnergyConduit.class, "energyConduit");
        GameRegistry.registerTileEntity(TileEntityAlloySmelter.class, "alloySmelter");
        GameRegistry.registerTileEntity(TileEntityCutter.class, "cutter");

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        proxy.registerProxies();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}

