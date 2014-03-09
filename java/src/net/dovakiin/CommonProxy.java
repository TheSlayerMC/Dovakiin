package net.dovakiin;

import net.dovakiin.util.LangRegistry;
import net.dovakiin.util.Utils;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void registerClient(){ }
	
	public void preInit(FMLPreInitializationEvent event){
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
