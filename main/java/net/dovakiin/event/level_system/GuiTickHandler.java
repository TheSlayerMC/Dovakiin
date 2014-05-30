package net.dovakiin.event.level_system;

import org.lwjgl.opengl.GL11;

import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.network.ExtendedPlayer;
import net.dovakiin.util.Config;
import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class GuiTickHandler {

	private static ResourceLocation level = new ResourceLocation(Utils.PREFIX + "textures/gui/levelBar.png");

	@SubscribeEvent
	public void onTick(PlayerTickEvent event){
		if(event.phase == Phase.START){
			onTickStart();
		} else {
			//onTickEnd();
		}
	}

	@SubscribeEvent
	public void onRender(RenderTickEvent event){
		onTickRender();
	}		

	private void onTickRender() {
		if(Minecraft.getMinecraft().currentScreen == new GuiIngameMenu() || Minecraft.getMinecraft().currentScreen == null) {
			drawLevelBar();
		}
	}

	public void drawLevelBar(){
		Minecraft mc = Minecraft.getMinecraft();
		int j1, l1, j2, k2, j, i1, i2;
		ScaledResolution s = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);;
		int h = s.getScaledHeight();
		int w = s.getScaledWidth();
		GuiIngame g = mc.ingameGUI;
		int h1 = h - 490;//Config.levelHeight;
		int w1 = w - 610;//Config.levelWidth;
		short short1 = 256;
		int width = w / 2 - short1 / 2;
		FontRenderer f = mc.fontRenderer;
		mc.mcProfiler.startSection("levelBar");
		mc.getTextureManager().bindTexture(level);
		j1 = LevelHelper.xpBarCap();

		if (j1 > 0) {
			l1 = (int)(LevelHelper.levelXP * (float)(short1));
			g.drawTexturedModalRect(width, 22, 0, 0, short1, 19);

			if (l1 > 0) {
				g.drawTexturedModalRect(width + 5, 22 + 5, 5, 24, l1, 9);
			}
		}
		if(LevelHelper.getLevel() >= 245)
			mc.fontRenderer.drawString(DovakiinAPI.GOLD + "Lv: " + LevelHelper.getLevel() + " (Max)", width + 100, 22 + 6, 0, false);
		if(LevelHelper.getLevel() == 0)
			mc.fontRenderer.drawString(DovakiinAPI.GOLD + "Lv: 0", width + 118, 22 + 6, 0, false);
		else 
			mc.fontRenderer.drawString(DovakiinAPI.GOLD + "Lv: " + LevelHelper.getLevel(), width + 118, 22 + 6, 0, false);

		String st = "Coins: " + LevelHelper.getCoins();
		int k1 = f.getStringWidth(st);
		mc.fontRenderer.drawString(DovakiinAPI.GOLD + st, width - k1 + 140, 22 + 20, 0, false);
		st = "Time: " + formatTime(getWorldTime(mc));
		mc.fontRenderer.drawString(DovakiinAPI.GOLD + st, width + 90, 22 + 30, 0, false);
	}
	
	public static Long getWorldTime(Minecraft mc){	
		Long time = Long.valueOf(mc.theWorld.provider.getWorldTime());
		return time;
	}
	
	public static String formatTime(Long time) {
		int hours24 = (int)(time.longValue() / 1000L + 6L) % 24;
		int hours = hours24 % 12;
		int minutes = (int)((float)time.longValue() / 16.666666F % 60.0F);
		String time1 = String.format("%02d:%02d %s", new Object[] { Integer.valueOf(hours < 1 ? 12 : hours), Integer.valueOf(minutes), hours24 < 12 ? "AM" : "PM" });
		return time1;
	}	

	private void onTickEnd() { }

	private void onTickStart() { }
}