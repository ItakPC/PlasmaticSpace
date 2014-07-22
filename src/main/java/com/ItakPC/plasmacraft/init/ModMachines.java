package com.ItakPC.plasmacraft.init;

import com.ItakPC.plasmacraft.machine.BaseMachine;
import com.ItakPC.plasmacraft.machine.windmill.BlockConcreteSurface;
import com.ItakPC.plasmacraft.machine.windmill.BlockWindmill;
import com.ItakPC.plasmacraft.machine.windmill.TileEntityWindmill;
import com.ItakPC.plasmacraft.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockContainer;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModMachines {

    //public static final BaseMachine blockWindmill = new BlockWindmill();

    public static void init() {

        GameRegistry.registerTileEntity(TileEntityWindmill.class, "windmill");
        //GameRegistry.registerBlock(blockWindmill, "blockWindmill");

    }
}
