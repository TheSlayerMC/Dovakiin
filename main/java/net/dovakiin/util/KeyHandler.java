package net.dovakiin.util;

import net.dovakiin.Dovakiin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

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
				openGui(0);
			}
		}
	}
	
	public void openGui(int id){
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		p.openGui(Dovakiin.instance, id, p.worldObj, (int)p.posX, (int)p.posY, (int)p.posZ);
	}
}