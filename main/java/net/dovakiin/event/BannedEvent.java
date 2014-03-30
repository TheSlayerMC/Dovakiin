package net.dovakiin.event;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import net.dovakiin.client.gui.GuiBanned;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BannedEvent {

	@SubscribeEvent
	public void banned(RenderGameOverlayEvent.Pre e) throws MalformedURLException, IOException{
		if(isBanned(Minecraft.getMinecraft().thePlayer)){
			Minecraft.getMinecraft().displayGuiScreen(new GuiBanned());
		}
	}

	public static boolean isBanned(EntityPlayer thePlayer) {
		return false;
	}

	/*public static boolean isBanned(EntityPlayer player) throws MalformedURLException, IOException{
		BufferedReader file = null;
		String name = null;
		try{
			file = new BufferedReader(new InputStreamReader(new URL("https://raw.github.com/TheSlayerMC/Dovakiin/master/BannedPlayers.txt").openStream()));
			name = file.readLine();
			String line = null;
			while ((line = file.readLine()) != null)
			file.close();

			return !name.equals(player.getDisplayName());
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	} */
	
	/*@SuppressWarnings("null")
	public static boolean isBanned(EntityPlayer player) {
        StringBuilder source = new StringBuilder();
        BufferedReader reader = null;
        String name = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new URL("https://raw.github.com/TheSlayerMC/Dovakiin/master/BannedPlayers.txt").openStream()));
            name = reader.readLine();
            while ((name = reader.readLine()) != null)
                source.append(name);
            reader.close();
            
            return !name.equals(player.getDisplayName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/

	public static void register(){
		MinecraftForge.EVENT_BUS.register(new BannedEvent());
	}
}