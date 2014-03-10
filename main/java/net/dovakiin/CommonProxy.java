package net.dovakiin;

import net.dovakiin.api.*;
import net.dovakiin.client.*;
import net.dovakiin.entity.mob.boss.*;
import net.dovakiin.event.*;
import net.dovakiin.generation.*;
import net.dovakiin.util.*;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerClient(){ }
	
	//ClientProxy
	public void preInit(FMLPreInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new RenderEvent());
		MinecraftForge.EVENT_BUS.register(new BannedEvent());
		MinecraftForge.EVENT_BUS.register(new BonemealEvent());
		MinecraftForge.EVENT_BUS.register(new DropsEvent());
		
		Config.init();
		LangRegistry.init();
		if(Utils.DEBUG){
		    LangRegistry.addBlockNames();
		    LangRegistry.addItemNames();
		}
		LangRegistry.closeFile();
		DovakiinAPI.registerMob(EntityGiantSkeleton.class, "Giant Skeleton");
	}
	
	public void init(FMLInitializationEvent event){
		GameRegistry.registerWorldGenerator(new BerryWorldGen(), 10);
	}
	
	public void postInit(FMLPostInitializationEvent event){ }
	
	public void serverStarting(FMLServerStartingEvent event){
		/*if (MinecraftServer.getServer().getCommandManager() instanceof ServerCommandManager) {
			((CommandHandler) MinecraftServer.getServer().getCommandManager()).registerCommand(new ClearCommand());
		}
		if (MinecraftServer.getServer().getCommandManager() instanceof ServerCommandManager) {
			((CommandHandler) MinecraftServer.getServer().getCommandManager()).registerCommand(new HealCommand());
		}*/
	}
	
}