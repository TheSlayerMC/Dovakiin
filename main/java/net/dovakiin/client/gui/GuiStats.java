package net.dovakiin.client.gui;

import net.dovakiin.DataHelper;
import net.dovakiin.Dovakiin;
import net.dovakiin.api.ContainerEmpty;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiStats extends GuiContainer {
	
	public GuiStats() {
		super(new ContainerEmpty());
	}

	private static final ResourceLocation texture = new ResourceLocation(Utils.PREFIX + "textures/gui/blank.png");

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		String text = Utils.MOD_NAME + ": " + Utils.MOD_VERSION;
		Minecraft.getMinecraft().fontRenderer.drawString(DovakiinAPI.AQUA + text, 5, 5, 4210752);
		text = "Time: " + Minecraft.getMinecraft().theWorld.getWorldTime();
		Minecraft.getMinecraft().fontRenderer.drawString(DovakiinAPI.AQUA + text, 5, 15, 4210752);
		text = "Level: " + DataHelper.getLevel(player);
		Minecraft.getMinecraft().fontRenderer.drawString(DovakiinAPI.AQUA + text, 5, 25, 4210752);
		text = "Sword Level: " + DataHelper.getSwordLevel(player);
		Minecraft.getMinecraft().fontRenderer.drawString(DovakiinAPI.AQUA + text, 5, 35, 4210752);
		text = "Coin Total: " + DataHelper.getCoins(player);
		Minecraft.getMinecraft().fontRenderer.drawString(DovakiinAPI.AQUA + text, 5, 45, 4210752);
		text = "Enemys around: "  + Minecraft.getMinecraft().theWorld.countEntities(EnumCreatureType.monster, true);
		Minecraft.getMinecraft().fontRenderer.drawString(DovakiinAPI.AQUA + text, 5, 55, 4210752);
		text = "Friendly creatures around: "  + Minecraft.getMinecraft().theWorld.countEntities(EnumCreatureType.creature, true);
		Minecraft.getMinecraft().fontRenderer.drawString(DovakiinAPI.AQUA + text, 5, 65, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int par1, int par2) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}