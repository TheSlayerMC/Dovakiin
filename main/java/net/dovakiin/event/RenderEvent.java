package net.dovakiin.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.client.gui.GuiBanned;
import net.dovakiin.client.gui.GuiLevelBar;
import net.dovakiin.util.Config;
import net.dovakiin.util.UpdateChecker;
import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RenderEvent {
	private boolean hasSeen;

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRenderOverlay(RenderGameOverlayEvent event){
		if(event.isCancelable() || event.type != ElementType.EXPERIENCE) return;
		GuiLevelBar.draw();
	}

	@SubscribeEvent
    public void renderTick(TickEvent.RenderTickEvent event) {
        if(event.phase == TickEvent.Phase.END) {
            if(Minecraft.getMinecraft().currentScreen instanceof GuiOptions) {
                GuiOptions gui = (GuiOptions)Minecraft.getMinecraft().currentScreen;
                String s = "Hit O";
                gui.drawString(Minecraft.getMinecraft().fontRenderer, s, gui.width - Minecraft.getMinecraft().fontRenderer.getStringWidth(s) - 2, gui.height - 10, 16777215);

                if(!keyDown && Keyboard.isKeyDown(Keyboard.KEY_O)) {
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
                    FMLClientHandler.instance().showGuiScreen(new GuiBanned());
                }
                keyDown = Keyboard.isKeyDown(Keyboard.KEY_O);
            }
        }
    }
	
	private boolean keyDown = false;
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onPlayerLogin(EntityJoinWorldEvent e) {
		if(Config.canShowWelcome){
			if(e.entity instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer)e.entity;
				if(p.worldObj.isRemote) {
					if(!hasSeen) {
						try {
							if(!UpdateChecker.isOnline()){
								if (p.getDisplayName().equals("The_SlayerMC")) {
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.DARK_PURPLE, "Oh hey! Look! A developer!"));
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.LIGHT_PURPLE, "Your internet crashed from how awesome you are."));
								} else {
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA, "Thank you " + p.getDisplayName() + ", for downloading and playing" + DovakiinAPI.GREEN + " Dovakiin!"));
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA, "[Version: " + Utils.MOD_VERSION + "]"));
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.LIGHT_PURPLE, "Unable to check for latest version, you may want to check"));
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.LIGHT_PURPLE, " your internet connection!"));
								}
							}
							if(UpdateChecker.isUpdateAvailable() && UpdateChecker.isOnline()) {
								if (p.getDisplayName().equals("The_SlayerMC") && !BannedEvent.isBanned(p)) {
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.DARK_PURPLE, "Oh hey! A Developer!"));
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.YELLOW, "Wow, you don't even have the newest version of your own mod... Nice.."));
								} else {
									BufferedReader versionFile = new BufferedReader(new InputStreamReader(new URL("https://raw.github.com/TheSlayerMC/Dovakiin/master/Version.txt").openStream()));
									String curVersion = versionFile.readLine();
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA, "Thank you " + p.getDisplayName() + ", for downloading and playing" + DovakiinAPI.GREEN + " Dovakiin!"));
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.RED, "[Version: " + Utils.MOD_VERSION + "]"));
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.YELLOW, "A Dovakiin update is avaliable."));
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.YELLOW, "[New Version: " + curVersion + "]")); 
								}
							}
							if((!UpdateChecker.isUpdateAvailable()) && UpdateChecker.isOnline() && !BannedEvent.isBanned(p)) {
								if (p.getDisplayName().equals("The_SlayerMC")) {
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.DARK_PURPLE, "Oh hey! Look! A developer!"));
								} else {
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA, "Thank you "  + p.getDisplayName() + ", for downloading and playing" + DovakiinAPI.GREEN + " Dovakiin!"));
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA, "[Version: " + Utils.MOD_VERSION + "]"));
									p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.GREEN, "Dovakiin is up to date."));
								}
							}
							if(BannedEvent.isBanned(p)){
								p.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.GREEN, "You have been banned!"));
							}
						} catch(MalformedURLException e1) {
							e1.printStackTrace();

						} catch(IOException e1) {
							e1.printStackTrace();
						}
					}
					hasSeen = true;
				}
			}
		}
	} 
	
	public static void register(){
		MinecraftForge.EVENT_BUS.register(new RenderEvent());
	}
}