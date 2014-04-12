package net.dovakiin.client.gui;

import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.network.ExtendedPlayer;
import net.dovakiin.util.Config;
import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiLevelBar{

	private static ResourceLocation bar = new ResourceLocation(Utils.PREFIX + "textures/gui/levelBar.png");

	static int levelHeight = 490;
	static int levelWidth = 610;

	public static void draw(){
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		mc.getTextureManager().bindTexture(bar);
		levelUp(player);
	}

	private static void levelUp(EntityPlayer player) {
		Minecraft mc = Minecraft.getMinecraft(); 
		int j1, l1, j2, k2, j, i1, i2;
		ScaledResolution sc = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		int h = sc.getScaledHeight();
		int w = sc.getScaledWidth();
		int h1 = h - Config.levelHeight;
		int w1 = w - Config.levelWidth;
		short short1;
		ExtendedPlayer props = ExtendedPlayer.get(player);
		GuiIngame g = mc.ingameGUI;
		FontRenderer fontrenderer = mc.fontRenderer;
		mc.mcProfiler.startSection("levelBar");
		mc.getTextureManager().bindTexture(bar);
		j1 = props.xpBarCap(player);

		if (j1 > 0) {
			short1 = 256;
			l1 = (int)(props.levelXP * (float)(short1 + 1));
			g.drawTexturedModalRect(w1, h1, 0, 0, short1, 19);

			if (l1 > 0) {
				g.drawTexturedModalRect(w1 + 5, h1 + 5, 5, 24, l1, 33);
			}
		}
		if(props.experienceLevel >= 245)
			mc.fontRenderer.drawString(DovakiinAPI.GOLD + "Lv: " + props.experienceLevel + " (Max)", w1 + 100, h1 + 6, 0, false);
		if(props.experienceLevel == 0)
			mc.fontRenderer.drawString(DovakiinAPI.GOLD + "Lv: 0", w1 + 118, h1 + 6, 0, false);
		else 
			mc.fontRenderer.drawString(DovakiinAPI.GOLD + "Lv: " + props.experienceLevel, w1 + 118, h1 + 6, 0, false);
		
		String s = "Coins: " + props.getCoins(player);
		mc.fontRenderer.drawString(DovakiinAPI.GOLD + s, w1 + 110, h1 + 20, 0, false);
	}

	/*private static void levelUp(EntityPlayer p){
		Minecraft mc = Minecraft.getMinecraft(); 
		ScaledResolution sc = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		ExtendedPlayer props = ExtendedPlayer.get(p);
		GuiIngame g = mc.ingameGUI;
		int h = sc.getScaledHeight();
		int w = sc.getScaledWidth();
		int h1 = h - Config.levelHeight;
		int w1 = w - Config.levelWidth;
		int maxLevel = 245;
		int level = props.getLevel();//The level that the player has (The amount of mobs it kills)
		int levelWidth = (int)(((float)level / (float)maxLevel) * 245F);
		//int levelWidth = (int)(LevelEvent.expGained / maxLevel * 245F);

		g.drawTexturedModalRect(w1, h1, 0, 0, 256, 19);

		if(level > 0)
			g.drawTexturedModalRect(w1 + 5, h1 + 5, 5, 24, levelWidth, 33);

		if(level >= maxLevel)
			mc.fontRenderer.drawString(DovakiinAPI.GOLD + "Lv: " + level + " (Max)", w1 + 100, h1 + 6, 0, false);
		else
			mc.fontRenderer.drawString(DovakiinAPI.GOLD + "Lv: " + level, w1 + 118, h1 + 6, 0, false);
		String s = "Coins: " + props.getCoins(p);
		mc.fontRenderer.drawString(DovakiinAPI.GOLD + s, w1 + 110, h1 + 20, 0, false);
	}*/


}