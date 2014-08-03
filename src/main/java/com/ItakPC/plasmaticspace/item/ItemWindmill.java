package com.ItakPC.plasmaticspace.item;

import com.ItakPC.plasmaticspace.PlasmaticSpace;
import com.ItakPC.plasmaticspace.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWindmill extends Item {

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world,int x, int y, int z, int side, float x2, float y2, float z2) {
        if(side == 1) {
            if (!world.isRemote) {
                world.setBlock(x, y + 1, z, PlasmaticSpace.blockWindmill);
                LogHelper.info("clicked");

                return true;
            }
        }
        return false;
    }
}

