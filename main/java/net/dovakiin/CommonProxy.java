package net.dovakiin;

import net.dovakiin.event.*;
import net.dovakiin.generation.BerryWorldGen;
import net.dovakiin.generation.WorldGenBerrys;
import net.dovakiin.util.*;
import net.minecraftforge.common.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerClient(){ }
	
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
	}
	
	public void init(FMLInitializationEvent event){
		GameRegistry.registerWorldGenerator(new BerryWorldGen(), 10);
	}
	
	public void postInit(FMLPostInitializationEvent event){ }
	
}