package net.dovakiin.client.gui;

import net.dovakiin.api.ContainerEmpty;
import net.dovakiin.network.ExtendedPlayer;
import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiItemStats extends GuiContainer{

	private static final ResourceLocation texture = new ResourceLocation(Utils.PREFIX + "textures/gui/stats.png");
	private static final ResourceLocation texture2 = new ResourceLocation(Utils.PREFIX + "textures/gui/levelBar.png");

	public GuiItemStats() {
		super(new ContainerEmpty());
	}

	public void drawDefaultLevels(){
		mc.getTextureManager().bindTexture(texture2);
		int w = (width - xSize) / 2 - 346;
		int h = (height - ySize) / 2 - 151;
		drawTexturedModalRect(w, h, 0, 62, 190, 19);
		drawTexturedModalRect(w, h + 42, 0, 62, 190, 19);
		drawTexturedModalRect(w, h + 84, 0, 62, 190, 19);
		drawTexturedModalRect(w, h + 126, 0, 62, 190, 19);
		drawTexturedModalRect(w, h + 168, 0, 62, 190, 19);
	}

	public void drawLevels(){
		EntityPlayer player = mc.thePlayer;
		ExtendedPlayer props = ExtendedPlayer.get(player);

		mc.getTextureManager().bindTexture(texture2);
		int w = (width - xSize) / 2 - 346;
		int h = (height - ySize) / 2 - 151;
		int cap = props.normalCap();
		short short1 = 190;
		int xp;
		if(cap > 0){
			xp = (int)(props.bowXP * (float)(short1));
			if(xp > 0) 
				drawTexturedModalRect(w, h, 0, 43, xp, 19);
			xp = (int)(props.swordXP * (float)(short1));
			if(xp > 0)
				drawTexturedModalRect(w, h + 42, 0, 43, xp, 19);
			xp = (int)(props.hoeXP * (float)(short1));
			if(xp > 0)
				drawTexturedModalRect(w, h + 84, 0, 43, xp, 19);
			xp = (int)(props.pickXP * (float)(short1));
			if(xp > 0)
				drawTexturedModalRect(w, h + 126, 0, 43, xp, 19);
			xp = (int)(props.headXP * (float)(short1));
			if(xp > 0)
				drawTexturedModalRect(w, h + 168, 0, 43, xp, 19);
		}
		String s = "Lv: ";
		fontRendererObj.drawString(s + props.getBowLevel(), w + 75, h + 6, 0xF0F0F0);
		fontRendererObj.drawString(s + props.getSwordLevel(), w + 75, h + 48, 0xF0F0F0);
		fontRendererObj.drawString(s + props.getHoeLevel(), w + 75, h + 90, 0xF0F0F0);
		fontRendererObj.drawString(s + props.getPickaxeLevel(), w + 75, h + 132, 0xF0F0F0);
		fontRendererObj.drawString(s + props.getHeadLevel(), w + 75, h + 174, 0xF0F0F0);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(texture);
		xSize = 250;
		ySize = 226;
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int var1, int var2) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawDefaultLevels();
		drawLevels();
	}
}