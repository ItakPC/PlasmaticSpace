package com.ItakPC.plasmacraft.init.world;

import com.ItakPC.plasmacraft.block.BaseBlock;
import com.ItakPC.plasmacraft.block.world.decor.brick.BlockBasaltBrick;
import com.ItakPC.plasmacraft.block.world.decor.brick.BlockLimestoneBrick;
import com.ItakPC.plasmacraft.block.world.decor.brick.BlockMarbleBrick;
import com.ItakPC.plasmacraft.block.world.decor.lights.BlockBasaltLight;
import com.ItakPC.plasmacraft.block.world.decor.lights.BlockMarbleLight;
import com.ItakPC.plasmacraft.block.world.decor.smallBrick.BlockBasaltSmallBrick;
import com.ItakPC.plasmacraft.block.world.decor.smallBrick.BlockLimestoneSmallBrick;
import com.ItakPC.plasmacraft.block.world.decor.smallBrick.BlockMarbleSmallBrick;
import com.ItakPC.plasmacraft.block.world.decor.smooth.BlockLimestone;
import com.ItakPC.plasmacraft.block.world.decor.smooth.BlockBasalt;
import com.ItakPC.plasmacraft.block.world.decor.smooth.BlockMarble;
import com.ItakPC.plasmacraft.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class Decoration {

    /* Smooth Blocks */
    public static final BaseBlock blockMarble = new BlockMarble();
    public static final BaseBlock blockLimestone = new BlockLimestone();
    public static final BaseBlock blockBasalt = new BlockBasalt();

    /* Bricks */
    public static final BaseBlock blockBasaltBrick = new BlockBasaltBrick();
    public static final BaseBlock blockMarbleBrick = new BlockMarbleBrick();
    public static final BaseBlock blockLimestoneBrick = new BlockLimestoneBrick();

    /* Small Bricks */
    public static final BaseBlock blockBasaltSmallBrick = new BlockBasaltSmallBrick();
    public static final BaseBlock blockMarbleSmallBrick = new BlockMarbleSmallBrick();
    public static final BaseBlock blockLimestoneSmallBrick = new BlockLimestoneSmallBrick();

    /* Lights */
    public static final BaseBlock blockBasaltLight = new BlockBasaltLight();
    public static final BaseBlock blockMarbleLight = new BlockMarbleLight();


    public static void init() {
        GameRegistry.registerBlock(blockMarble, "blockMarble");
        GameRegistry.registerBlock(blockLimestone, "blockLimestone");
        GameRegistry.registerBlock(blockBasalt, "blockBasalt");

        GameRegistry.registerBlock(blockBasaltSmallBrick, "blockBasaltSmallBrick");
        GameRegistry.registerBlock(blockMarbleSmallBrick, "blockMarbleSmallBrick");
        GameRegistry.registerBlock(blockLimestoneSmallBrick, "blockLimestoneSmallBrick");

        GameRegistry.registerBlock(blockBasaltBrick, "blockBasaltBrick");
        GameRegistry.registerBlock(blockMarbleBrick, "blockMarbleBrick");
        GameRegistry.registerBlock(blockLimestoneBrick, "blockLimestoneBrick");

        GameRegistry.registerBlock(blockBasaltLight, "blockBasaltLight");
        GameRegistry.registerBlock(blockMarbleLight, "blockMarbleLight");
    }
}
