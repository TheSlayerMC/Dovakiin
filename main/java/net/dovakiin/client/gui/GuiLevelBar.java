package net.dovakiin.client.gui;

import net.dovakiin.DataHelper;
import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiLevelBar {
	
	private static ResourceLocation bar = new ResourceLocation(Utils.PREFIX + "textures/gui/levelBar.png");
	
	public static void draw(){
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		ScaledResolution sc = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		GuiIngame gig = mc.ingameGUI; 
		mc.getTextureManager().bindTexture(bar);
		int h = sc.getScaledHeight();
		int w = sc.getScaledWidth();
		
		int h1 = h - 118;
		int w1 = w - 220;
		int level = 100;//(121 * DataHelper.getLevel(player) / DataHelper.getMaxLevel(player));
		gig.drawTexturedModalRect(h1, w1, 0, 0, 201, 19);
		gig.drawTexturedModalRect(h1, w1, 0, 19, (int)(12.5F * level), 38);
	}
}