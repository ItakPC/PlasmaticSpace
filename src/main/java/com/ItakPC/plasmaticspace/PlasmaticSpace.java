package com.ItakPC.plasmaticspace;

import com.ItakPC.plasmaticspace.cable.BlockEnergyConduit;
import com.ItakPC.plasmaticspace.cable.TileEntityEnergyConduit;
import com.ItakPC.plasmaticspace.creativetabs.PlasmaCraftMachine;
import com.ItakPC.plasmaticspace.handler.ConfigurationHandler;
import com.ItakPC.plasmaticspace.handler.GuiHandler;
import com.ItakPC.plasmaticspace.init.ModBlocks;
import com.ItakPC.plasmaticspace.init.ModItems;
import com.ItakPC.plasmaticspace.init.world.Decoration;
import com.ItakPC.plasmaticspace.init.world.Ore;
import com.ItakPC.plasmaticspace.machine.windmill.ItemWindmill;
import com.ItakPC.plasmaticspace.machine.windmill.BlockWindmill;
import com.ItakPC.plasmaticspace.machine.windmill.TileEntityWindmill;
import com.ItakPC.plasmaticspace.proxy.IProxy;
import com.ItakPC.plasmaticspace.reference.Reference;
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
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.world.World;

@Mod(modid = Reference.MOD_ID, version = Reference.MOD_NAME, guiFactory = Reference.GuiFactoryClass)

public class PlasmaticSpace {

    public static Item itemWindmill;
    public static Block blockWindmill;
    public static Block energyConduit;

    public static final int guiIDWindmillBasic = 0;

    @Mod.Instance
    public static PlasmaticSpace instance;

    @SidedProxy(clientSide= Reference.CProxy, serverSide = Reference.SProxy)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        itemWindmill = new ItemWindmill().setTextureName("diamond").setCreativeTab(PlasmaCraftMachine.PlasmaCraftMachine).setUnlocalizedName("itemWindmill");
        blockWindmill = new BlockWindmill(Material.iron).setBlockName("blockWindmill");
        energyConduit = new BlockEnergyConduit(Material.iron).setBlockName("energyConduit").setCreativeTab(PlasmaCraftMachine.PlasmaCraftMachine);

        GameRegistry.registerItem(itemWindmill, "itemWindmill");
        GameRegistry.registerBlock(blockWindmill, "blockWindmill");
        GameRegistry.registerBlock(energyConduit, "energyConduit");

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        ModItems.init();
        ModBlocks.init();
        Decoration.init();
        Ore.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        GameRegistry.registerTileEntity(TileEntityWindmill.class, "windmillBasic");
        GameRegistry.registerTileEntity(TileEntityEnergyConduit.class, "energyConduit");

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        proxy.registerProxies();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}

