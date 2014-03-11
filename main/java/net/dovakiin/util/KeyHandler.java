package net.dovakiin.util;

import net.dovakiin.api.DovakiinAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyHandler {
	private KeyBinding stats = new KeyBinding("Stats", Keyboard.KEY_X, "Dovakiin");

	public KeyHandler() {
		ClientRegistry.registerKeyBinding(stats);
	}

	@SubscribeEvent
	public void KeyInputEvent(KeyInputEvent event) {
		if(stats.isPressed()) {
			if(Minecraft.getMinecraft().currentScreen == null) {
				DovakiinAPI.openGui(0);
			}
		}
	}
}