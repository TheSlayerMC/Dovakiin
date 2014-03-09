package net.dovakiin.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import net.dovakiin.client.gui.GuiBanned;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BannedEvent {

	@SubscribeEvent
	public void banned(RenderGameOverlayEvent e) throws MalformedURLException, IOException{
		if(isBanned(Minecraft.getMinecraft().thePlayer)){
			Minecraft.getMinecraft().displayGuiScreen(new GuiBanned());
		}
	}

	public static boolean isBanned(EntityPlayer player) throws MalformedURLException, IOException{
		/*BufferedReader file = new BufferedReader(new InputStreamReader(new URL("https://raw.github.com/TheSlayerMC/Dovakiin/master/BannedPlayers.txt").openStream()));
		String name = file.readLine();
		String line = null;
		//while ((line = file.readLine()) != null)
		file.close();

		return !name.equals(player.getDisplayName());*/
		return false;
	} 
}