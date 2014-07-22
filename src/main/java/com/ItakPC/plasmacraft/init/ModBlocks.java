package com.ItakPC.plasmacraft.init;

import com.ItakPC.plasmacraft.machine.BaseMachine;
import com.ItakPC.plasmacraft.machine.windmill.BlockConcreteSurface;
import com.ItakPC.plasmacraft.machine.windmill.TileEntityWindmill;
import com.ItakPC.plasmacraft.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
    public class ModBlocks {

        public static final BaseMachine blockConcreteSurface = new BlockConcreteSurface(Material.rock);

        public static void init() {

            GameRegistry.registerBlock(blockConcreteSurface, "blockConcreteSurface");
        }
    }