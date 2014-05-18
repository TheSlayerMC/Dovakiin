package net.dovakiin.client.gui;

import net.dovakiin.api.ContainerEmpty;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiStartingBook extends GuiContainer{

	private int page, maxPages = 2;
	
	private static final ResourceLocation texture = new ResourceLocation(Utils.PREFIX + "textures/gui/startingBook.png");
	private int bookImageWidth = 242;
	private int bookImageHeight = 150;

	public GuiStartingBook() {
		super(new ContainerEmpty());
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = "";
		s = DovakiinAPI.DARK_GRAY + "Dovakiin";
		this.fontRendererObj.drawString(s, xSize() - this.fontRendererObj.getStringWidth(s) / 2 - 30, 20, 4210752);
		s = DovakiinAPI.DARK_GRAY + "Welcome to the mod ";
		this.fontRendererObj.drawString(s, xSize() - this.fontRendererObj.getStringWidth(s) / 2 - 25, 40, 4210752);
		s = DovakiinAPI.DARK_GREEN + "\"Dovakiin\"";
		this.fontRendererObj.drawString(s, xSize() - this.fontRendererObj.getStringWidth(s) / 2 - 30, 50, 4210752);
		s = EnumChatFormatting.DARK_PURPLE + "Page: " + EnumChatFormatting.DARK_AQUA + page + " / " + maxPages;
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2 + 85, 130, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int b0 = (this.height - this.ySize) / 2;
		
		this.drawTexturedModalRect(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);
	}

	@Override
	public void initGui() {
		super.initGui();
		page1();
	}

	@Override
	protected void actionPerformed(GuiButton b) {
		switch(b.id){
		case 1:
			page1();
			break;
		case 2:
			page2();
			break;
		case 3:
			mc.thePlayer.closeScreen();
			break;
		}
	}

	private void page1(){
		buttonList.clear();		
		final int x = this.width / 2;
		final int z = this.height / 2;
		addButtons();
		String s = EnumChatFormatting.DARK_GREEN + "PASUJGAYFS";
		fontRendererObj.drawString(s, x + 140, z + 240, 4210752);
		page = 1;
	}
	
	private void page2(){
		buttonList.clear();
		final int x = this.width / 2 - 100;
		final int z = this.height / 2 - 175;
		addButtons();
		fontRendererObj.drawString("HFQAHGDYAFAYGYSFAYTFD", x, z, 4210752);
		page = 2;
	}
	
	public int xSize(){
		return (xSize / 2);
	}
	
	public int ySize(){
		return (ySize / 2);
	}
	
	private void addButtons(){
		final int x = this.width / 2 - 100;
		final int z = this.height / 2 - 175;
		this.buttonList.add(new GuiStartingBook.NextPageButton(1, x + 26, z + 220, false));
		this.buttonList.add(new GuiStartingBook.NextPageButton(2, x + 216, z + 220, true));
		this.buttonList.add(new GuiButton(3, x + 35, z + 250, "Exit"));
	}

	@SideOnly(Side.CLIENT)
	static class NextPageButton extends GuiButton {
		private final boolean reverseTex;

		public NextPageButton(int par1, int par2, int par3, boolean par4) {
			super(par1, par2, par3, 23, 13, "");
			this.reverseTex = par4;
		}

		public void drawButton(Minecraft m, int p_146112_2_, int p_146112_3_) {
			if (this.visible) {
				boolean flag = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				m.getTextureManager().bindTexture(GuiStartingBook.texture);
				int k = 0, l = 192;
				if(flag) 
					k += 23;
				if(!this.reverseTex) 
					l += 13;
				this.drawTexturedModalRect(this.xPosition, this.yPosition, k, l, 23, 13);
			}
		}
	}
}