package net.dovakiin.client.render.block;

import java.util.Random;

import net.dovakiin.api.blocks.tileentity.TileEntityBucket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class RenderBucketBlock extends TileEntitySpecialRenderer {

	Random random = new Random();

	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8) {
		renderLabel("Bucket Interaction", x, y + 1, z);
		//ForgeHooksClient.renderInventoryItem(new RenderBlocks(), Minecraft.getMinecraft().renderEngine, new ItemStack(Items.bucket), false, (float)var2, (float)var6, (float)var4 + 1);
		Tessellator tes = Tessellator.instance;
		ItemStack stack = new ItemStack(Items.bucket);

		 GL11.glPushMatrix();
	        float var10 = (float) (x - 0.5F);
	        float var11 = (float) (y - 0.5F);
	        float var12 = (float) (z - 0.5F);
	        GL11.glTranslatef(var10, var11, var12);
	        renderDroppedItem(stack, var1.getWorldObj(), var8);
	        GL11.glPopMatrix();
	}

	private void renderDroppedItem(ItemStack stack, World w, float f) {  
		float tick = 180F;
		EntityItem entityitem = new EntityItem(w, 0, 0, 0, stack);
        entityitem.getEntityItem().stackSize = 1;
        entityitem.hoverStart = 0;
        float f3 = (((float)entityitem.age + f) / 20.0F + entityitem.hoverStart) * (tick / (float)Math.PI);
        
        
        if(tick == 0){
        	tick = 180F;
        }
        
        GL11.glPushMatrix();
        GL11.glRotatef(f3, 0.0F, 1.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef(1F, 0.5555F, 1.0F);
        GL11.glScalef(1F, 1F, 1F);

        RenderItem.renderInFrame = true;
        RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 1.0D, 0.0D, 0.0F, 0.0F);
        RenderItem.renderInFrame = false;

        GL11.glPopMatrix();
        tick--;
	}

	protected void renderLabel(String text, double x, double y, double z) {
		RenderManager renderManager = RenderManager.instance;
		FontRenderer var12 = Minecraft.getMinecraft().fontRenderer;
		float var13 = 1.6F;
		float baseScale = 0.016666668F * var13;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.0F, (float) y + 0.7f, (float) z);
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(-baseScale, -baseScale, baseScale);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Tessellator tessellator = Tessellator.instance;
		byte var16 = 0;
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		tessellator.startDrawingQuads();
		int var17 = var12.getStringWidth(text) / 2;
		tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
		tessellator.addVertex((double) (-var17 - 1), (double) (-1 + var16), 0.0D);
		tessellator.addVertex((double) (-var17 - 1), (double) (8 + var16), 0.0D);
		tessellator.addVertex((double) (var17 + 1), (double) (8 + var16), 0.0D);
		tessellator.addVertex((double) (var17 + 1), (double) (-1 + var16), 0.0D);
		tessellator.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		var12.drawString(text, -var12.getStringWidth(text) / 2, var16, 553648127);
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}
}