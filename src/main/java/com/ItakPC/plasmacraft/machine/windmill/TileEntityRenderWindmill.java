package com.ItakPC.plasmacraft.machine.windmill;

import com.ItakPC.plasmacraft.PlasmaCraft;
import com.ItakPC.plasmacraft.cable.TileEntityEnergyConduit;
import com.ItakPC.plasmacraft.reference.Reference;
import com.ItakPC.plasmacraft.utility.LogHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class TileEntityRenderWindmill extends TileEntitySpecialRenderer {

    private final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/models/windmillBasic.png");
    private final ResourceLocation textureBox = new ResourceLocation(Reference.MOD_ID, "textures/blocks/windmillBox.png");
    private final ResourceLocation conduit = new ResourceLocation(Reference.MOD_ID, "textures/models/conduitEnergy.png");

    private float pixel = 1F / 16F;
    float texturePixel = 1F / 32F;

    private int textureWidth = 32;
    private int textureHeight = 32;

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        int x1 = tileEntity.xCoord;
        int y1 = tileEntity.yCoord;
        int z1 = tileEntity.zCoord;

        while (tileEntity.getWorldObj().getBlockMetadata(x1, y1, z1) < 7 && tileEntity.getWorldObj().getBlock(x1, y1, z1).equals(PlasmaCraft.blockWindmill)) {
            y1++;
        }

        int direction = tileEntity.getWorldObj().getBlockMetadata(x1, y1, z1) - 8;
        int metadata = tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
            /*Rendering Of Windmill*/
        GL11.glTranslatef((float) x, (float) y, (float) z);

        GL11.glTranslatef(0.5F, 0, 0.5F);

        GL11.glRotatef(direction * 90, 0, 1, 0);

        GL11.glTranslatef(-0.5F, 0, -0.5F);

        if (metadata == 1) {
            TileEntity conduit = tileEntity.getWorldObj().getTileEntity(tileEntity.xCoord + 1, tileEntity.yCoord, tileEntity.zCoord);
            if (conduit instanceof TileEntityEnergyConduit) drawConnection(ForgeDirection.EAST);

            conduit = tileEntity.getWorldObj().getTileEntity(tileEntity.xCoord - 1, tileEntity.yCoord, tileEntity.zCoord);
            if (conduit instanceof TileEntityEnergyConduit) drawConnection(ForgeDirection.WEST);

            conduit = tileEntity.getWorldObj().getTileEntity(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord - 1);
            if (conduit instanceof TileEntityEnergyConduit) drawConnection(ForgeDirection.NORTH);

            conduit = tileEntity.getWorldObj().getTileEntity(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord + 1);
            if (conduit instanceof TileEntityEnergyConduit) drawConnection(ForgeDirection.SOUTH);

        }


        Tessellator tesselator = Tessellator.instance;
        this.bindTexture(texture);
        tesselator.startDrawing(GL11.GL_QUADS);
        {

            if (metadata > 0 && metadata < 7) {
                tesselator.addVertexWithUV((16 - 8) / 2 * pixel, 0, 1 - (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 1 * (1F / textureHeight));
                tesselator.addVertexWithUV((16 - 8) / 2 * pixel, 1, 1 - (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 0 * (1F / textureHeight));
                tesselator.addVertexWithUV((16 - 8) / 2 * pixel, 1, (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 0 * (1F / textureHeight));
                tesselator.addVertexWithUV((16 - 8) / 2 * pixel, 0, (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 1 * (1F / textureHeight));

                tesselator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 0, 1 - (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 1 * (1F / textureHeight));
                tesselator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 1, 1 - (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 0 * (1F / textureHeight));
                tesselator.addVertexWithUV((16 - 8) / 2 * pixel, 1, 1 - (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 0 * (1F / textureHeight));
                tesselator.addVertexWithUV((16 - 8) / 2 * pixel, 0, 1 - (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 1 * (1F / textureHeight));

                tesselator.addVertexWithUV((16 - 8) / 2 * pixel, 0, (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 1 * (1F / textureHeight));
                tesselator.addVertexWithUV((16 - 8) / 2 * pixel, 1, (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 0 * (1F / textureHeight));
                tesselator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 1, (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 0 * (1F / textureHeight));
                tesselator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 0, (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 1 * (1F / textureHeight));

                tesselator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 0, (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 1 * (1F / textureHeight));
                tesselator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 1, (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 0 * (1F / textureHeight));
                tesselator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 1, 1 - (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 0 * (1F / textureHeight));
                tesselator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 0, 1 - (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 1 * (1F / textureHeight));
            }

            if (metadata > 7) {
                this.bindTexture(textureBox);

                tesselator.addVertexWithUV(1, 1, 1, 1, 1);
                tesselator.addVertexWithUV(1, 1, 0, 1, 0);
                tesselator.addVertexWithUV(0, 1, 0, 0, 0);
                tesselator.addVertexWithUV(0, 1, 1, 0, 1);

                tesselator.addVertexWithUV(0, 0, 1, 0, 0);
                tesselator.addVertexWithUV(0, 0, 0, 0, 1);
                tesselator.addVertexWithUV(1, 0, 0, 1, 1);
                tesselator.addVertexWithUV(1, 0, 1, 1, 0);

                tesselator.addVertexWithUV(1, 0, 1, 1, 1);
                tesselator.addVertexWithUV(1, 1, 1, 1, 0);
                tesselator.addVertexWithUV(0, 1, 1, 0, 0);
                tesselator.addVertexWithUV(0, 0, 1, 0, 1);

                tesselator.addVertexWithUV(1, 0, 0, 1, 1);
                tesselator.addVertexWithUV(0, 0, 0, 0, 1);
                tesselator.addVertexWithUV(0, 1, 0, 0, 0);
                tesselator.addVertexWithUV(1, 1, 0, 1, 0);

                tesselator.addVertexWithUV(0, 0, 0, 1, 1);
                tesselator.addVertexWithUV(0, 0, 1, 0, 1);
                tesselator.addVertexWithUV(0, 1, 1, 0, 0);
                tesselator.addVertexWithUV(0, 1, 0, 1, 0);

                tesselator.addVertexWithUV(1, 0, 0, 1, 1);
                tesselator.addVertexWithUV(1, 1, 0, 1, 0);
                tesselator.addVertexWithUV(1, 1, 1, 0, 0);
                tesselator.addVertexWithUV(1, 0, 1, 0, 1);

            }
            tesselator.draw();
            if (metadata > 7) drawRotor(tileEntity);

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }

    private void drawConnection(ForgeDirection direction) {

        Tessellator tessellator = Tessellator.instance;
        this.bindTexture(conduit);
        tessellator.startDrawing(GL11.GL_QUADS);
        {

            GL11.glTranslatef(0.5F, 0.5F, 0.5F);

            if (direction.equals(ForgeDirection.EAST)) {
                GL11.glRotatef(-90, 1, 0, 0);
            } else if(direction.equals(ForgeDirection.NORTH)) {
                GL11.glRotatef(90, 0, 0, 1);
            } else if (direction.equals(ForgeDirection.SOUTH)) {
                GL11.glRotatef(-90, 0, 0, 1);
            } else if (direction.equals(ForgeDirection.WEST)) {
                GL11.glRotatef(90, 1, 0, 0);
            }

            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 4 * pixel, 1 - 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 1 - 11 * pixel / 2, 14 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1, 1 - 11 * pixel / 2, 14 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1 - 4 * pixel, 1 - 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);

            tessellator.addVertexWithUV(11 * pixel / 2, 1 - 4 * pixel, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1, 11 * pixel / 2, 14 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 11 * pixel / 2, 14 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 4 * pixel, 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);

            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 4 * pixel, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 11 * pixel / 2, 14 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 1 - 11 * pixel / 2, 14 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 4 * pixel, 1 - 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);

            tessellator.addVertexWithUV(11 * pixel / 2, 1 - 4 * pixel, 1 - 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1, 1 - 11 * pixel / 2, 14 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1, 11 * pixel / 2, 14 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1 - 4 * pixel, 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);

            if (true) {
                tessellator.addVertexWithUV(11 * pixel / 2, 1 - 4 * pixel, 1 - 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(11 * pixel / 2, 1, 1 - 11 * pixel / 2, 14 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 1 - 11 * pixel / 2, 14 * texturePixel, 5 * texturePixel);
                tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 4 * pixel, 1 - 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);

                tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 4 * pixel, 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 11 * pixel / 2, 14 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(11 * pixel / 2, 1, 11 * pixel / 2, 14 * texturePixel, 5 * texturePixel);
                tessellator.addVertexWithUV(11 * pixel / 2, 1 - 4 * pixel, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);

                tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 4 * pixel, 1 - 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 1 - 11 * pixel / 2, 14 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 11 * pixel / 2, 14 * texturePixel, 5 * texturePixel);
                tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 4 * pixel, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);

                tessellator.addVertexWithUV(11 * pixel / 2, 1 - 4 * pixel, 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(11 * pixel / 2, 1, 11 * pixel / 2, 14 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(11 * pixel / 2, 1, 1 - 11 * pixel / 2, 14 * texturePixel, 5 * texturePixel);
                tessellator.addVertexWithUV(11 * pixel / 2, 1 - 4 * pixel, 1 - 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);

            }

            tessellator.draw();


            GL11.glTranslatef(0.5F, 0.5F, 0.5F);

            if (direction.equals(ForgeDirection.EAST)) {
                GL11.glRotatef(90, 1, 0, 0);
            } else if(direction.equals(ForgeDirection.NORTH)) {
                GL11.glRotatef(-90, 0, 0, 1);
            } else if (direction.equals(ForgeDirection.SOUTH)) {
                GL11.glRotatef(90, 0, 0, 1);
            } else if (direction.equals(ForgeDirection.WEST)) {
                GL11.glRotatef(-90, 1, 0, 0);
            }

            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        }
    }

    private void drawRotor(TileEntity tileEntity) {
        TileEntityWindmill windmill = (TileEntityWindmill) tileEntity.getWorldObj().getTileEntity(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);

        GL11.glTranslatef(0, 0.5F, 0.5F);
        GL11.glRotatef(windmill.rotation, 5, 0, 0);
        GL11.glTranslatef(0, -0.5F, -0.5F);

        Tessellator tesselator = Tessellator.instance;
        this.bindTexture(texture);
        tesselator.startDrawing(GL11.GL_QUADS);
        {
            /* Front Of Rotor*/
            tesselator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 1*pixel+0.5F, 9*(1F/textureWidth), 1*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 2.5F, 1*pixel+0.5F, 9*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 2.5F, -1*pixel+0.5F, 8*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, -1*pixel+0.5F, 8*(1F/textureWidth), 1*(1F/textureHeight));

            tesselator.addVertexWithUV(-2*pixel, -1.5F, 1*pixel+0.5F, 9*(1F/textureWidth), 1*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 1*pixel+0.5F, 9*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, -1*pixel+0.5F, 8*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, -1.5F, -1*pixel+0.5F, 8*(1F/textureWidth), 1*(1F/textureHeight));

            tesselator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 2.5F, 9*(1F/textureWidth), 1*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 2.5F, 9*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 0.5F+1*pixel, 8*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 0.5F+1*pixel, 8*(1F/textureWidth), 1*(1F/textureHeight));

            tesselator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 0.5F-1*pixel, 9*(1F/textureWidth), 1*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 0.5F-1*pixel, 9*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, -1.5F, 8*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, -1.5F, 8*(1F/textureWidth), 1*(1F/textureHeight));

            /* Back Of Rotor */
            tesselator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, -1*pixel+0.5F, 9*(1F/textureWidth), 1*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 2.5F, -1*pixel+0.5F, 9*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 2.5F, 1*pixel+0.5F, 8*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 1*pixel+0.5F, 8*(1F/textureWidth), 1*(1F/textureHeight));

            tesselator.addVertexWithUV(-2*pixel, -1.5F, -1*pixel+0.5F, 9*(1F/textureWidth), 1*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, -1*pixel+0.5F, 9*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 1*pixel+0.5F, 8*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, -1.5F, 1*pixel+0.5F, 8*(1F/textureWidth), 1*(1F/textureHeight));

            tesselator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 0.5F+1*pixel, 9*(1F/textureWidth), 1*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 0.5F+1*pixel, 9*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 2.5F, 8*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 2.5F, 8*(1F/textureWidth), 1*(1F/textureHeight));

            tesselator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, -1.5F, 9*(1F/textureWidth), 1*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, -1.5F, 9*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 0.5F-1*pixel, 8*(1F/textureWidth), 0*(1F/textureHeight));
            tesselator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 0.5F-1*pixel, 8*(1F/textureWidth), 1*(1F/textureHeight));
        }
        tesselator.draw();
    }
}

