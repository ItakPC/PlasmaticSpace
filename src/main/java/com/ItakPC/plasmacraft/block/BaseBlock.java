package com.ItakPC.plasmacraft.block;

import com.ItakPC.plasmacraft.creativetabs.PlasmaCraftBlocks;
import com.ItakPC.plasmacraft.creativetabs.PlasmaCraftItems;
import com.ItakPC.plasmacraft.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BaseBlock extends Block {

    public BaseBlock(Material material) {
        super(material);
        this.setCreativeTab(PlasmaCraftBlocks.PlasmaCraftBlocks);
    }


    public BaseBlock() {
        this(Material.rock);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
