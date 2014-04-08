package net.dovakiin.client.gui;

import net.dovakiin.api.ContainerEmpty;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiEgg extends GuiContainer {
	
	public GuiEgg() { super(new ContainerEmpty()); }
	
	private static final ResourceLocation texture = new ResourceLocation(Utils.PREFIX + "textures/gui/blank.png");

	public static int green = DovakiinAPI.rand.nextInt(1000) + 10;

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		int height = 5;
		String text = "Green Dragon Egg";
		Minecraft.getMinecraft().fontRenderer.drawString(DovakiinAPI.AQUA + text, 5, height, 4210752);
		height += 10;
		text = "Time Left: " + green;
		Minecraft.getMinecraft().fontRenderer.drawString(DovakiinAPI.AQUA + text, 5, height, 4210752);
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