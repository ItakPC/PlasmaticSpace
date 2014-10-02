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

    private ResourceLocation backGround = new ResourceLocation(Reference.MOD_ID, "textures/gui/plate_cutting_machine.png");

    private TileCutter plateCuttingMachine;

    public GuiCutter(InventoryPlayer inventoryPlayer, TileCutter tileEntity) {

        super(new ContainerCutter(inventoryPlayer, tileEntity));
        plateCuttingMachine = tileEntity;

        this.xSize = 176;
        this.ySize = 166;

    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {

        String name;
        if(plateCuttingMachine.hasCustomInventoryName()) {
            name = plateCuttingMachine.getInventoryName();
        }
        else {
            name = I18n.format(plateCuttingMachine.getInventoryName(), new Object[0]);
        }
        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {

        GL11.glColor4f(1F, 1F, 1F, 1F);
        this.mc.getTextureManager().bindTexture(backGround);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
        int j;

        j = this.plateCuttingMachine.getEnergyScaled(12);
        if(j > 0) {
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - j, 176, 12 - j, 14, j + 2);
        }

        j = this.plateCuttingMachine.getProgressScaled(20);
        this.drawTexturedModalRect(k + 83, l + 34, 176, 14, j, 16);


    }
}