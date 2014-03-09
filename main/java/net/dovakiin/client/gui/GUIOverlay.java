package net.dovakiin.client.gui;

import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;


public class GUIOverlay {

	public void draw(){
		if(Minecraft.getMinecraft().playerController.shouldDrawHUD()) return;
		
		String text = Utils.MOD_NAME + ": " + Utils.MOD_VERSION;
		Minecraft.getMinecraft().fontRenderer.drawString(EnumChatFormatting.AQUA + text, 2, 2, 4210752);
	}

}
