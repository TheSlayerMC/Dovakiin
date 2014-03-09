package net.dovakiin.client.gui;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.GuiAccessDenied;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiBanned extends GuiScreen{
	
	public void draw(){
		 this.drawDefaultBackground();
		 int offset = Math.max(85 - 2 * 10, 10);
		 this.drawCenteredString(this.fontRendererObj, Minecraft.getMinecraft().thePlayer.getDisplayName() + " you have been banned from the mod 'Dovakiin'.", this.width / 2, offset, 0xFFFFFF);
		 offset += 10;
		 this.drawCenteredString(this.fontRendererObj, "Please contact The_SlayerMC for help.", this.width / 2, offset, 0xFFFFFF);
		 offset += 10;
		 this.drawCenteredString(this.fontRendererObj, "If you believe this is a miss understanding, contact The_SlayerMC.", this.width / 2, offset, 0xFFFFFF);
	}
	
    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 75, this.height - 38, I18n.format("gui.done")));
    }
    
    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        if (par1GuiButton.enabled && par1GuiButton.id == 1) {
            FMLClientHandler.instance().showGuiScreen(new GuiMainMenu());
        }
    }
	
}
