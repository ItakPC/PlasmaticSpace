package com.ItakPC.plasmacraft.machine.windmill;

import com.ItakPC.plasmacraft.PlasmaCraft;
import com.ItakPC.plasmacraft.init.ModBlocks;
import com.ItakPC.plasmacraft.init.ModMachines;
import com.ItakPC.plasmacraft.machine.BaseMachine;
import com.ItakPC.plasmacraft.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import sun.rmi.runtime.Log;

public class BlockConcreteSurface extends BaseMachine {

    public BlockConcreteSurface(Material material) {
        super(Material.rock);
        this.setBlockName("concreteSurface");
        this.setBlockBounds(0, 0, 0, 1, 0.3F, 1);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    /*public void onNeighborBlockChange(Block neighborBlock, World world, int x, int y, int z) {
        updateMultiblockStructure(world, x, y, z);
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        updateMultiblockStructure(world, x, y, z);
    }

    public void updateMultiblockStructure(World world, int x, int y, int z) {
        isMultiblockStructure(world, x, y, z);
    }

    public boolean isMultiblockStructure(World world, int x1, int y1, int z1) {
        boolean mStructure = false;
        boolean currentCheckStructure = true;

        for(int x2 = 0; x2 < 3; x2++) {
            if (!mStructure) {

                currentCheckStructure = true;

                for (int x3 = 0; x3 < 3; x3++) {
                    for (int z3 = 0; x3 < 3; z3++) {
                        if (currentCheckStructure && !world.getBlock(x1+x2-x3, y1, z1 - z3).equals(ModBlocks.blockConcreteSurface)) {
                            world.setBlock(x1+x2-x3, y1, z1 - z3, Blocks.anvil);


                            currentCheckStructure = false;
                        }
                    }
                }
            }

            mStructure = currentCheckStructure;

        }

        LogHelper.info(currentCheckStructure);

        return false;
    }*/
}
