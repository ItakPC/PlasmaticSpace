package com.ItakPC.plasmaticspace.machine.solidFuelFurnace;

import com.ItakPC.plasmaticspace.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiSFFurnace extends GuiContainer{

    public static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/guiSFFurnace.png");

    public TileEntitySFFurnace tileEntity;

    public GuiSFFurnace(InventoryPlayer inventory, TileEntitySFFurnace tileEntitySFFurnace) {
        super(new ContainerSFFurnace(inventory, tileEntitySFFurnace));

        this.tileEntity = tileEntitySFFurnace;

        this.xSize = 176;
        this.ySize = 166;
    }

    public void drawGuiContainerForegroundLayer(int x, int y) {
        String name = this.tileEntity.hasCustomInventoryName() ? this.tileEntity.getInventoryName() : I18n.format(this.tileEntity.getInventoryName(), new Object[0]);

        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name), 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

    }
}
