package com.ItakPC.plasmaticspace.machine.alloySmelter;

import com.ItakPC.plasmaticspace.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiAlloySmelter extends GuiContainer {

    private ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/guiAlloySmelter.png");

    private TileEntityAlloySmelter alloySmelter;

    public GuiAlloySmelter(InventoryPlayer inventoryPlayer, TileEntityAlloySmelter tileEntityAlloySmelter) {
        super(new ContainerAlloySmelter(inventoryPlayer, tileEntityAlloySmelter));
        alloySmelter = tileEntityAlloySmelter;

        this.xSize = 176;
        this.ySize = 166;
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        this.fontRendererObj.drawString(I18n.format("Alloy Smelter"), this.xSize / 2 - 13, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if(alloySmelter.hasPower()) {
            int i1 = alloySmelter.getPowerScaled(53);
            drawTexturedModalRect(guiLeft + 8, guiTop + 53 - i1, 177, 55 - i1, 1, i1);
        }
    }
}
