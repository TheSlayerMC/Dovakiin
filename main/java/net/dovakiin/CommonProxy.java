package net.dovakiin;

import net.dovakiin.event.*;
import net.dovakiin.util.*;
import net.minecraftforge.common.*;
import cpw.mods.fml.common.event.*;

public class CommonProxy {

	public void registerClient(){ }
	
	public void preInit(FMLPreInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new RenderEvent());
		MinecraftForge.EVENT_BUS.register(new BannedEvent());

		LangRegistry.init();
		if(Utils.DEBUG){
		    LangRegistry.addBlockNames();
		    LangRegistry.addItemNames();
		}
		LangRegistry.closeFile();
	}
	
	public void init(FMLInitializationEvent event){ }
	
	public void postInit(FMLPostInitializationEvent event){ }
	
}
