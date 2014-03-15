package net.dovakiin.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dovakiin.api.ContainerEmpty;
import net.dovakiin.api.DovakiinAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemBook;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiStartingBook extends GuiContainer{

	private int page = 0, maxPages = 2;
	
	private static final ResourceLocation texture = new ResourceLocation("textures/gui/book.png");
	private int bookImageWidth = 192;
	private int bookImageHeight = 192;

	public GuiStartingBook() {
		super(new ContainerEmpty());
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = null;
		s = DovakiinAPI.DARK_GRAY + "Dovakiin";
		this.fontRendererObj.drawString(s, xSize() - this.fontRendererObj.getStringWidth(s) / 2 + 5, 20, 4210752);
		s = DovakiinAPI.DARK_GRAY + "Welcome to the mod ";
		this.fontRendererObj.drawString(s, xSize() - this.fontRendererObj.getStringWidth(s) / 2 + 5, 40, 4210752);
		s = DovakiinAPI.DARK_GREEN + "'Dovakiin'";
		this.fontRendererObj.drawString(s, xSize() - this.fontRendererObj.getStringWidth(s) / 2 + 5, 50, 4210752);
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
		//page1();
	}

	/*@Override
	protected void actionPerformed(GuiButton b) {
		switch(b.id){
		case 1:
			page1();
			break;
		case 2:
			page2();
			break;
		}
	}

	private void page1(){
		String s = "PASUJGAYFS";
		this.fontRendererObj.drawString(s, xSize() - this.fontRendererObj.getStringWidth(s) / 2 + 5, 20, 4210752);
		addMoveButtons();
		page = 1;
	}
	
	private void page2(){
		this.buttonList.clear();
		this.fontRendererObj.drawString("PAGE 2", xSize() - this.fontRendererObj.getStringWidth("PAGE 2") / 2 - 25, ySize(), 10);
		addMoveButtons();
		page = 2;
	}*/
	
	public int xSize(){
		return (xSize / 2);
	}
	
	public int ySize(){
		return (ySize / 2);
	}
	
	private void addMoveButtons(){
		final int x = this.width / 2 - 100;
		final int z = this.height / 2 - 175;
		this.buttonList.add(new GuiStartingBook.NextPageButton(2, x + 140, z + 240, true));
		this.buttonList.add(new GuiStartingBook.NextPageButton(1, x + 46, z + 240, false));
	}

	@SideOnly(Side.CLIENT)
	static class NextPageButton extends GuiButton {
		private final boolean field_146151_o;

		public NextPageButton(int par1, int par2, int par3, boolean par4) {
			super(par1, par2, par3, 23, 13, "");
			this.field_146151_o = par4;
		}

		public void drawButton(Minecraft m, int p_146112_2_, int p_146112_3_) {
			if (this.visible) {
				boolean flag = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				m.getTextureManager().bindTexture(GuiStartingBook.texture);
				int k = 0;
				int l = 192;

				if (flag) {
					k += 23;
				}

				if (!this.field_146151_o) {
					l += 13;
				}

				this.drawTexturedModalRect(this.xPosition, this.yPosition, k, l, 23, 13);
			}
		}
	}
}