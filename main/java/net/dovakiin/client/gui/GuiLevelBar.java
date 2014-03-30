package net.dovakiin.client.gui;

import net.dovakiin.DataHelper;
import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.util.Config;
import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiLevelBar {

	private static ResourceLocation bar = new ResourceLocation(Utils.PREFIX + "textures/gui/levelBar.png");

	public static void draw(){
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		mc.getTextureManager().bindTexture(bar);
		levelUp(player);
	}

	private static void levelUp(EntityPlayer p){
		Minecraft mc = Minecraft.getMinecraft(); 
		ScaledResolution sc = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		GuiIngame g = mc.ingameGUI;
		int h = sc.getScaledHeight();
		int w = sc.getScaledWidth();
		int h1 = h - Config.levelHeight;
		int w1 = w - Config.levelWidth;
		int maxLevel = DataHelper.getMaxLevel();
		int level = DataHelper.getLevel(p);
		if(maxLevel > 0){
			g.drawTexturedModalRect(w1, h1, 0, 0, 256, 19);

			if(level > 0){
				g.drawTexturedModalRect(w1 + 5, h1 + 5, 5, 24, level, 33);
			}
		}
		if(level >= maxLevel){
			mc.fontRenderer.drawString(DovakiinAPI.GOLD + "Lv: " + level + " (Max)", w1 + 100, h1 + 6, 0, false);
		}else{
			mc.fontRenderer.drawString(DovakiinAPI.GOLD + "Lv: " + level, w1 + 118, h1 + 6, 0, false);
		}
	}
}