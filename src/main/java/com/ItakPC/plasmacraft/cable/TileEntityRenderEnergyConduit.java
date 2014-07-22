package com.ItakPC.plasmacraft.cable;

import com.ItakPC.plasmacraft.reference.Reference;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRenderEnergyConduit /* extends TileEntitySpecialRenderer*/ {

	/*ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/models/conduitEnergy.png");
	boolean drawInside = true;
	
	float pixel = 1F/16F;
	float texturePixel = 1F/32F;
	
	public void renderTileEntityAt(TileEntity tileentity, double translationX, double translationY, double translationZ, float f) {
		GL11.glTranslated(translationX, translationY, translationZ);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.bindTexture(texture);
		{
			TileEntityEnergyConduit conduit = (TileEntityEnergyConduit) tileentity;
			
			if(!conduit.onlyOneOpposite(conduit.connections)) drawCore(tileentity);
				
			else{
				
				for(int i = 0; i < conduit.connections.length;i++) {
					if(conduit.connections[i] != null) {
						drawStraightLine(conduit.connections[i]);
						break;
					}
				}
			}
					
				for(int i = 0; i < conduit.connections.length;i++) {
						if(conduit.connections[i] != null) {
							drawConnector(conduit.connections[i]);
						
						}
					}
				}
			
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-translationX, -translationY, -translationZ);
	}
	
	public void drawStraightLine(ForgeDirection direction) {

		 Tessellator tessellator = Tessellator.instance;
		 tessellator.startDrawingQuads(); 
		 {
			 
			 GL11.glTranslatef(0.5F, 0.5F, 0.5F);	
				
				if(direction.equals(ForgeDirection.UP)) {
					//ROTATE
				}else if(direction.equals(ForgeDirection.DOWN)) {
					//GL11.glRotatef(180, 1, 0, 0);
				}else if(direction.equals(ForgeDirection.SOUTH) || direction.equals(ForgeDirection.NORTH)) {
					GL11.glRotatef(90, 1, 0, 0);
			
				}else if(direction.equals(ForgeDirection.WEST) || direction.equals(ForgeDirection.EAST)) {
					GL11.glRotatef(90, 0, 0, 1);
				}
				
				GL11.glTranslatef(-0.5F, -0.5F, -0.5F);	
			 
			 tessellator.addVertexWithUV(1-11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			 
			 tessellator.addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 0*texturePixel);
	 
			 tessellator.addVertexWithUV(1-11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			 
			 tessellator.addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 0*texturePixel);
			 
			 if(drawInside) {
				 tessellator.addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 5*texturePixel);
				 tessellator.addVertexWithUV(1-11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);

				 tessellator.addVertexWithUV(1-11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 5*texturePixel);
				 tessellator.addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 5*texturePixel);

				 tessellator.addVertexWithUV(1-11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 5*texturePixel);
				 tessellator.addVertexWithUV(1-11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 5*texturePixel);

				 tessellator.addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 5*texturePixel);
				 tessellator.addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);

			 }
		 }
		 
		 tessellator.draw();
		 
		 GL11.glTranslatef(0.5F, 0.5F, 0.5F);	
			
			if(direction.equals(ForgeDirection.UP)) {
				//ROTATE
			}else if(direction.equals(ForgeDirection.DOWN)) {
				//GL11.glRotatef(-180, 1, 0, 0);
			}else if(direction.equals(ForgeDirection.SOUTH) || direction.equals(ForgeDirection.NORTH)) {
				GL11.glRotatef(-90, 1, 0, 0);
		
			}else if(direction.equals(ForgeDirection.WEST) || direction.equals(ForgeDirection.EAST)) {
				GL11.glRotatef(-90, 0, 0, 1);
			}    
			
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);	
	}
	
	public void drawConnector(ForgeDirection direction) {
		
		
		 Tessellator tessellator = Tessellator.instance;
		 tessellator.startDrawingQuads(); 
		 {
			 
			 GL11.glTranslatef(0.5F, 0.5F, 0.5F);	
				
				if(direction.equals(ForgeDirection.UP)) {
					//ROTATE
				}else if(direction.equals(ForgeDirection.DOWN)) {
					GL11.glRotatef(180, 1, 0, 0);
				}else if(direction.equals(ForgeDirection.SOUTH)) {
					GL11.glRotatef(90, 1, 0, 0);
				}else if(direction.equals(ForgeDirection.NORTH)) {
					GL11.glRotatef(270, 1, 0, 0);
				}else if(direction.equals(ForgeDirection.WEST)) {
					GL11.glRotatef(90, 0, 0, 1);
				}else if(direction.equals(ForgeDirection.EAST)) {
					GL11.glRotatef(270, 0, 0, 1);
				}
				
				GL11.glTranslatef(-0.5F, -0.5F, -0.5F);	
			 
			 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
			 
			 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
	 
			 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
			 
			 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
			 
			 if(drawInside) {
				 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
				 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
 
				 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);
				 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
				 
				 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);
				 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
	 
				 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
				 tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);
				 tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
				 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
				
				
				 
			 }
		 }
		 
		 tessellator.draw();
		 
		 GL11.glTranslatef(0.5F, 0.5F, 0.5F);	
			
			if(direction.equals(ForgeDirection.UP)) {
				//ROTATE
			}else if(direction.equals(ForgeDirection.DOWN)) {
				GL11.glRotatef(-180, 1, 0, 0);
			}else if(direction.equals(ForgeDirection.SOUTH)) {
				GL11.glRotatef(-90, 1, 0, 0);
			}else if(direction.equals(ForgeDirection.NORTH)) {
				GL11.glRotatef(-270, 1, 0, 0);
			}else if(direction.equals(ForgeDirection.WEST)) {
				GL11.glRotatef(-90, 0, 0, 1);
			}else if(direction.equals(ForgeDirection.EAST)) {
				GL11.glRotatef(-270, 0, 0, 1);
			}
			
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);	
	}
	
	public void drawCore(TileEntity tileentity) {
		 Tessellator tessellator = Tessellator.instance;
		 tessellator.startDrawingQuads(); {
		 
		 //SOUTH
	     tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
		 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
		 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 0*texturePixel);
		 tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
		 
		 tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
		 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
		 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 0*texturePixel);
		 tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
		 
		 tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
		 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
		 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
		 tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 5*texturePixel);
		 
		 tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
		 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
		 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
		 tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 5*texturePixel);
		 
		 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
		 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
		 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
		 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
		 
		 tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
		 tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
		 tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
		 tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);

		 if(drawInside) {
			 tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);		

			 tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);

			 tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
	 
			 tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
	 
			 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);

			 tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
			 tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			 tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
	
			
		 }
		 }
		 tessellator.draw();
	}*/

}
