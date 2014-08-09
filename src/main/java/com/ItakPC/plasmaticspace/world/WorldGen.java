package com.ItakPC.plasmaticspace.world;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class WorldGen {

    public static IWorldGenerator OreGen;

    public static void registerOreGen() {

        GameRegistry.registerWorldGenerator(OreGen, 1);

    }

    public static void defineOre() {

        OreGen = new OreGen();

    }
}
