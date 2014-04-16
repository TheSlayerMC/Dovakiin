package net.dovakiin.event.level_system;

import org.lwjgl.opengl.GL11;

import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.network.ExtendedPlayer;
import net.dovakiin.util.Config;
import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
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
		if(Minecraft.getMinecraft().currentScreen == null) {
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
		short short1;
		FontRenderer f = mc.fontRenderer;
		mc.mcProfiler.startSection("levelBar");
		mc.getTextureManager().bindTexture(level);
		j1 = LevelHelper.xpBarCap();

		if (j1 > 0) {
			short1 = 256;
			l1 = (int)(LevelHelper.levelXP * (float)(short1));
			g.drawTexturedModalRect(w1, h1, 0, 0, short1, 19);

			if (l1 > 0) {
				g.drawTexturedModalRect(w1 + 5, h1 + 5, 5, 24, l1, 9);
			}
		}
		if(LevelHelper.getLevel() >= 245)
			mc.fontRenderer.drawString(DovakiinAPI.GOLD + "Lv: " + LevelHelper.getLevel() + " (Max)", w1 + 100, h1 + 6, 0, false);
		if(LevelHelper.getLevel() == 0)
			mc.fontRenderer.drawString(DovakiinAPI.GOLD + "Lv: 0", w1 + 118, h1 + 6, 0, false);
		else 
			mc.fontRenderer.drawString(DovakiinAPI.GOLD + "Lv: " + LevelHelper.getLevel(), w1 + 118, h1 + 6, 0, false);

		String st = "Coins: " + LevelHelper.getCoins();
		int k1 = f.getStringWidth(st);
		mc.fontRenderer.drawString(DovakiinAPI.GOLD + st, w1 - k1 + 120, h1 + 20, 0, false);
		String st1 = I18n.format("Time: ", new Object[] {StringUtils.ticksToElapsedTime((int)(120500L - mc.theWorld.getTotalWorldTime()))});
		k1 = f.getStringWidth(st1);
		mc.fontRenderer.drawString(DovakiinAPI.GOLD + st1, w1 - k1 + 120, h1 + 30, 0, false);
	}

	private void onTickEnd() { }

	private void onTickStart() { }
}