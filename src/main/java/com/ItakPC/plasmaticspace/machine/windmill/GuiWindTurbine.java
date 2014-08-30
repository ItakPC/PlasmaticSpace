package com.ItakPC.plasmaticspace.machine.windmill;

import com.ItakPC.plasmaticspace.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiWindTurbine extends GuiContainer{

    public final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/guiWindmill.png");

    private TileWindTurbine tileEntityWindmill;

    public GuiWindTurbine(InventoryPlayer inventoryPlayer, TileWindTurbine tileEntityWindmill) {
        super(new ContainerWindTurbine(inventoryPlayer, tileEntityWindmill));

        this.tileEntityWindmill = tileEntityWindmill;

        this.xSize = 176;
        this.ySize = 166;
    }

    public void drawGuiContainerForegroundLayer(float f, int i, int j) {
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int i = this.tileEntityWindmill.getPowerScaled(53);
        drawTexturedModalRect(guiLeft+152, guiTop+7+53-i, 177, 1, 16, i);


    }
}

