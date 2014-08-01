package com.ItakPC.plasmacraft.init;

import com.ItakPC.plasmacraft.event.HighlightEvent;
import com.ItakPC.plasmacraft.handler.GuiHandler;
import com.ItakPC.plasmacraft.machine.BaseMachine;
import com.ItakPC.plasmacraft.machine.windmill.BlockConcreteSurface;
import com.ItakPC.plasmacraft.machine.windmill.BlockWindmill;
import com.ItakPC.plasmacraft.machine.windmill.TileEntityWindmill;
import com.ItakPC.plasmacraft.proxy.IProxy;
import com.ItakPC.plasmacraft.reference.Reference;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.BlockContainer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import static cpw.mods.fml.relauncher.Side.*;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModMachines {


}