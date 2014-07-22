package com.ItakPC.plasmacraft.machine.windmill;

import com.ItakPC.plasmacraft.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiWindmill extends GuiContainer{

    public final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/windmillBasicGUI.png");

    private TileEntityWindmill tileEntityWindmill;

    public GuiWindmill(InventoryPlayer inventoryPlayer, TileEntityWindmill tileEntityWindmill) {
        super(new ContainerWindmill(inventoryPlayer, tileEntityWindmill));

        this.tileEntityWindmill = tileEntityWindmill;

        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int i = this.tileEntityWindmill.getPowerScaled(53);
        drawTexturedModalRect(guiLeft+152, guiTop+7+53-i, 177, 1, 16, i);
    }
}
