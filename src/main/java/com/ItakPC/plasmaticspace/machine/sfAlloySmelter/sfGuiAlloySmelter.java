package com.ItakPC.plasmaticspace.machine.sfAlloySmelter;

import com.ItakPC.plasmaticspace.reference.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class sfGuiAlloySmelter extends GuiContainer {

    private ResourceLocation backGround = new ResourceLocation(Reference.MOD_ID, "textures/gui/alloy_smelter.png");

    private sfTileAlloySmleter alloyFurnace;

    public sfGuiAlloySmelter(InventoryPlayer inventoryPlayer, sfTileAlloySmleter tileEntity) {

        super(new sfContainerAlloySmelter(inventoryPlayer, tileEntity));
        alloyFurnace = tileEntity;

        this.xSize = 176;
        this.ySize = 166;

    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {

        String name;
        if(alloyFurnace.hasCustomInventoryName()) {
            name = alloyFurnace.getInventoryName();
        }
        else {
            name = I18n.format(alloyFurnace.getInventoryName(), new Object[0]);
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

        if(alloyFurnace.isBurning()) {
            j = this.alloyFurnace.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - j, 176, 12 - j, 14, j + 2);
        }

        j = this.alloyFurnace.getCookTimeRemainingScaled(24);
        //System.out.println(j);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, j + 1, 16);


    }

/*import com.ItakPC.plasmaticspace.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiAlloySmelter extends GuiContainer {

    private ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/guiAlloySmelter.png");

    private TileEntityAlloySmelter sfAlloySmelter;

    public GuiAlloySmelter(InventoryPlayer inventoryPlayer, TileEntityAlloySmelter tileEntityAlloySmelter) {
        super(new ContainerAlloySmelter(inventoryPlayer, tileEntityAlloySmelter));
        sfAlloySmelter = tileEntityAlloySmelter;

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

        /*if(sfAlloySmelter.hasPower()) {
            int i1 = sfAlloySmelter.getPowerScaled(53);
            drawTexturedModalRect(guiLeft + 8, guiTop + 53 - i1, 177, 55 - i1, 1, i1);
        }*/
    }

