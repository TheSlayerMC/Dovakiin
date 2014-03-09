package net.dovakiin.client.gui;

import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.EnumChatFormatting;

public class GUIOverlay {
	
	public void draw(){
		if(Minecraft.getMinecraft().playerController.shouldDrawHUD()) return;
		if(Minecraft.getMinecraft().currentScreen == null){
			String text = Utils.MOD_NAME + ": " + Utils.MOD_VERSION;
			Minecraft.getMinecraft().fontRenderer.drawString(EnumChatFormatting.AQUA + text, 2, 2, 4210752);
			text = "Time: ";//  + Minecraft.getMinecraft().theWorld.getWorldTime();
			Minecraft.getMinecraft().fontRenderer.drawString(EnumChatFormatting.AQUA + text, 2, 12, 4210752);
			text = "Enimes around: "  + Minecraft.getMinecraft().theWorld.countEntities(EnumCreatureType.monster, true);
			Minecraft.getMinecraft().fontRenderer.drawString(EnumChatFormatting.AQUA + text, 2, Minecraft.getMinecraft().displayHeight / 2 - 9, 4210752);
			text = "Friendly creatures around: "  + Minecraft.getMinecraft().theWorld.countEntities(EnumCreatureType.creature, true);
			Minecraft.getMinecraft().fontRenderer.drawString(EnumChatFormatting.AQUA + text, 2, Minecraft.getMinecraft().displayHeight / 2 - 20, 4210752);
		}
	}
}