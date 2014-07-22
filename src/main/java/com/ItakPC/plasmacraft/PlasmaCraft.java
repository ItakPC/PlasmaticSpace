package com.ItakPC.plasmacraft;

import com.ItakPC.plasmacraft.creativetabs.PlasmaCraftMachine;
import com.ItakPC.plasmacraft.event.HighlightEvent;
import com.ItakPC.plasmacraft.handler.ConfigurationHandler;
import com.ItakPC.plasmacraft.handler.GuiHandler;
import com.ItakPC.plasmacraft.init.ModBlocks;
import com.ItakPC.plasmacraft.init.ModItems;
import com.ItakPC.plasmacraft.init.ModMachines;
import com.ItakPC.plasmacraft.init.world.Decoration;
import com.ItakPC.plasmacraft.machine.windmill.ItemWindmill;
import com.ItakPC.plasmacraft.machine.windmill.BlockWindmill;
import com.ItakPC.plasmacraft.machine.windmill.TileEntityWindmill;
import com.ItakPC.plasmacraft.proxy.IProxy;
import com.ItakPC.plasmacraft.reference.Reference;
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
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, version = Reference.MOD_NAME, guiFactory = Reference.GuiFactoryClass)

public class PlasmaCraft {

    public static Item itemWindmill;
    public static Block blockWindmill;
    public static Block energyConduit;

    public static final int guiIDWindmillBasic = 0;

    @Mod.Instance
    public static PlasmaCraft instance;

    @SidedProxy(clientSide= Reference.CProxy, serverSide = Reference.SProxy)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        itemWindmill = new ItemWindmill().setTextureName("diamond").setCreativeTab(PlasmaCraftMachine.PlasmaCraftMachine).setUnlocalizedName("itemWindmill");
        blockWindmill = new BlockWindmill(Material.iron).setBlockName("blockWindmill");
        //energyConduit = new BlockEnergyConduit().setCreativeTab(PlasmaCraftMachine.PlasmaCraftMachine).setBlockName("energyConduit");

        GameRegistry.registerItem(itemWindmill, "itemWindmill");
        GameRegistry.registerBlock(blockWindmill, "blockWindmill");
        //GameRegistry.registerBlock(energyConduit, "energyConduit");

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        ModItems.init();
        ModBlocks.init();
        ModMachines.init();
        Decoration.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        GameRegistry.registerTileEntity(TileEntityWindmill.class, "windmillBasic");

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(new HighlightEvent());

        proxy.registerProxies();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}

