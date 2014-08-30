package com.ItakPC.plasmaticspace.world;

import com.ItakPC.plasmaticspace.block.BaseBlock;
import com.ItakPC.plasmaticspace.init.world.Decoration;
import com.ItakPC.plasmaticspace.init.world.Ore;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class WorldGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {

            case 1: generateEnd(world, random, chunkX * 16, chunkZ * 16);
            case 0: generateSurface(world, random, chunkX * 16, chunkZ * 16);
            case -1: generateNether(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateEnd(World world, Random random, int i, int j) {

    }

    private void generateNether(World world, Random random, int i, int j) {

    }

    private void generateSurface(World world, Random random, int x, int z) {
        /** ore, world, random, posX, posZ, maxX, maxZ, maxVeinSize, chancetospawn, minY, maxY */

        this.addOreSpawn(Decoration.blockBasalt, world, random, x, z, 16, 16, 24, 3F, 30, 60);
        this.addOreSpawn(Decoration.blockLimestone, world, random, x, z, 16, 16, 24, 2F, 30, 60);
        this.addOreSpawn(Decoration.blockMarble, world, random, x, z, 16, 16, 24, 3F, 30, 60);
    }

    private void addOreSpawn(BaseBlock block, World world, Random random, int x, int z, int maxX, int maxZ, int maxVeinSize, float chance, int minY, int maxY) {
        for(int i = 0; i < chance; i++) {
            int posX = x + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY- minY);
            int posZ = z + random.nextInt(maxZ);
            (new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
        }
    }
}