package net.dovakiin.event;

import net.dovakiin.client.gui.GuiBanned;
import net.dovakiin.client.gui.GuiMerchant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class TickHandler {

	private boolean keyDown;

	@SubscribeEvent
	public void renderTick(TickEvent.RenderTickEvent event) {
		if(event.phase == TickEvent.Phase.END) {
			if(Minecraft.getMinecraft().currentScreen instanceof GuiOptions) {
				GuiOptions gui = (GuiOptions)Minecraft.getMinecraft().currentScreen;
				String s = "Hit M For the merchent menu";
				gui.drawString(Minecraft.getMinecraft().fontRenderer, s, gui.width - Minecraft.getMinecraft().fontRenderer.getStringWidth(s) - 10, gui.height - 20, 16777215);

				if(!keyDown && Keyboard.isKeyDown(Keyboard.KEY_M)) {
					Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
					FMLClientHandler.instance().showGuiScreen(new GuiMerchant());				}
				keyDown = Keyboard.isKeyDown(Keyboard.KEY_M);
			}
		}
	}
}