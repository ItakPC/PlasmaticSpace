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
import com.ItakPC.plasmaticspace.machine.solidFuelFurnace.SolidFuelFurnace;
import com.ItakPC.plasmaticspace.machine.solidFuelFurnace.TileEntitySFFurnace;
import com.ItakPC.plasmaticspace.machine.windmill.ItemWindmill;
import com.ItakPC.plasmaticspace.machine.windmill.BlockWindmill;
import com.ItakPC.plasmaticspace.machine.windmill.TileEntityWindmill;
import com.ItakPC.plasmaticspace.proxy.IProxy;
import com.ItakPC.plasmaticspace.reference.Reference;
import com.ItakPC.plasmaticspace.utility.Achievements;
import com.ItakPC.plasmaticspace.world.OreGen;
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
    public static Item itemPlasmaticFist;

    public static Block blockWindmill;
    public static Block energyConduit;
    public static Block solidFuelFurnaceIdle;
    public static Block solidFuelFurnaceActive;

    public static final int guiIDWindmillBasic = 0;
    public static final int guiIdSFFurnace = 1;

    public static Item.ToolMaterial PlasmaMaterial = EnumHelper.addToolMaterial("plasma", 16, 32000, 32F, 32F, 0);

    OreGen oreGen = new OreGen();

    @Mod.Instance
    public static PlasmaticSpace instance;

    @SidedProxy(clientSide= Reference.CProxy, serverSide = Reference.SProxy)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        itemWindmill = new ItemWindmill().setTextureName("diamond").setCreativeTab(PlasmaCraftMachine.PlasmaCraftMachine).setUnlocalizedName("itemWindmill");
        itemPlasmaticFist = new ItemPlasmaticFist(PlasmaMaterial).setTextureName("itemPlasmaticFist").setUnlocalizedName("itemPlasmaticFist");

        blockWindmill = new BlockWindmill(Material.iron).setBlockName("blockWindmill");
        energyConduit = new BlockEnergyConduit(Material.iron).setBlockName("energyConduit").setCreativeTab(PlasmaCraftMachine.PlasmaCraftMachine);

        solidFuelFurnaceActive = new SolidFuelFurnace(true).setBlockName("solidFuelFurnaceActive");
        solidFuelFurnaceIdle = new SolidFuelFurnace(false).setBlockName("solidFuelFurnaceIdle").setCreativeTab(PlasmaCraftMachine.PlasmaCraftMachine);

        GameRegistry.registerItem(itemWindmill, "itemWindmill");
        GameRegistry.registerItem(itemPlasmaticFist, "itemPlasmaticFist");

        GameRegistry.registerBlock(blockWindmill, "blockWindmill");
        GameRegistry.registerBlock(energyConduit, "energyConduit");

        GameRegistry.registerBlock(solidFuelFurnaceActive, "solidFuelFurnaceActive");
        GameRegistry.registerBlock(solidFuelFurnaceIdle, "solidFuelFurnaceIdle");

        GameRegistry.registerFuelHandler(new FuelHandler());

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        FMLCommonHandler.instance().bus().register(new OreGen());
        FMLCommonHandler.instance().bus().register(new EventHandler());

        GameRegistry.registerWorldGenerator(oreGen, 0);

        ModItems.init();
        ModBlocks.init();
        Decoration.init();
        Ore.init();
        ModTools.init();
        Achievements.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        GameRegistry.registerTileEntity(TileEntityWindmill.class, "windmillBasic");
        GameRegistry.registerTileEntity(TileEntityEnergyConduit.class, "energyConduit");
        GameRegistry.registerTileEntity(TileEntitySFFurnace.class, "sffurnace");

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        proxy.registerProxies();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}

