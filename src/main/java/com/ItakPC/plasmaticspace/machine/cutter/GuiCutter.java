package com.ItakPC.plasmaticspace.machine.cutter;

import com.ItakPC.plasmaticspace.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiCutter extends GuiContainer {

    private ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/guiCutter.png");

    private TileEntityCutter cutter;

    public GuiCutter(InventoryPlayer inventoryPlayer, TileEntityCutter tileEntityCutter) {
        super(new ContainerCutter(inventoryPlayer, tileEntityCutter));
        cutter = tileEntityCutter;

        this.xSize = 176;
        this.ySize = 166;
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        this.fontRendererObj.drawString(I18n.format("Cutter"), this.xSize / 2 - 13, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}