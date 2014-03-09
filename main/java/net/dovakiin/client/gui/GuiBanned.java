package net.dovakiin.client.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.GuiAccessDenied;
import cpw.mods.fml.client.GuiModsMissing;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class GuiBanned extends GuiScreen{
	
	private ResourceLocation face = new ResourceLocation(Utils.PREFIX + "textures/gui/banned.png");
	
	public void drawScreen(int i, int j, float k){
		this.drawDefaultBackground();
		int offset = Math.max(85 - 2 * 50, 80);
		this.drawCenteredString(this.fontRendererObj, DovakiinAPI.RED + "Dear, " + DovakiinAPI.AQUA + Minecraft.getMinecraft().thePlayer.getDisplayName() + DovakiinAPI.RED + " you have been banned from the mod" + DovakiinAPI.GREEN + " 'Dovakiin'.", this.width / 2, offset, 0xFFFFFF);
		offset += 10;
		this.drawCenteredString(this.fontRendererObj, DovakiinAPI.RED + "Please contact " + DovakiinAPI.YELLOW + "The_SlayerMC " + DovakiinAPI.RED + "for help or to know the reason why.", this.width / 2, offset, 0xFFFFFF);
		offset += 10;
		this.drawCenteredString(this.fontRendererObj, DovakiinAPI.RED + "If you believe this is a miss understanding, contact me, " + DovakiinAPI.YELLOW +"The_SlayerMC.", this.width / 2, offset, 0xFFFFFF);
		offset += 10;
		this.drawCenteredString(this.fontRendererObj, DovakiinAPI.RED + "And no, you can't un ban your self!", this.width / 2, offset, 0xFFFFFF);
		offset += 10;
		this.drawCenteredString(this.fontRendererObj, DovakiinAPI.RED + "The file to ban people is stored on the server side, not the client.", this.width / 2, offset, 0xFFFFFF);
		String s = DovakiinAPI.AQUA + "Minecraft: 1.7.2";
		this.drawCenteredString(this.fontRendererObj, s, this.width / 2 + 400, height - 20, 0xFFFFFF);
		s = DovakiinAPI.AQUA + "Dovakiin: " + Utils.MOD_VERSION;
		this.drawCenteredString(this.fontRendererObj, s, this.width / 2 - 400, height - 20, 0xFFFFFF);
		s = DovakiinAPI.AQUA + "Banned: " + DovakiinAPI.DARK_GREEN + Minecraft.getMinecraft().thePlayer.getDisplayName();
		this.drawCenteredString(this.fontRendererObj, s, this.width / 2 - 300, height - 20, 0xFFFFFF);
		super.drawScreen(i, j, k);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}
	
	@Override
	public void initGui() {
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height - 38, I18n.format("Return back to main menu...")));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.enabled && par1GuiButton.id == 1) {
			this.mc.theWorld.sendQuittingDisconnectingPacket();
			this.mc.loadWorld((WorldClient)null);
			this.mc.displayGuiScreen(new GuiMainMenu());
		}
	}
}