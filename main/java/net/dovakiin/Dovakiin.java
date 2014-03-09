package net.dovakiin;

import net.dovakiin.util.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;

@Mod(modid = Utils.MOD_ID, name = Utils.MOD_NAME, version = Utils.MOD_VERSION)
public class Dovakiin {
	
	public final String id = Utils.MOD_ID, name = Utils.MOD_NAME, version = Utils.MOD_VERSION;

	@Instance(id)
	public Dovakiin instance;
	
	@SidedProxy(clientSide = Utils.PROXY_CLIENT, serverSide = Utils.PROXY_SERVER)
	public static CommonProxy proxy;
	
	public static CreativeTabs items = new CreativeTabs("Dovakiin: Items"){
		@Override public Item getTabIconItem() { return Items.apple; }
	};
	
	public static CreativeTabs blocks = new CreativeTabs("Dovakiin: Blocks"){
		@Override public Item getTabIconItem() { return Item.getItemFromBlock(Blocks.acacia_stairs); }
	};
	
	public static CreativeTabs misc = new CreativeTabs("Dovakiin: Misc"){
		@Override public Item getTabIconItem() { return Items.bucket; }
	};
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		proxy.preInit(event);
		proxy.registerClient();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		proxy.postInit(event);
	}
	
}