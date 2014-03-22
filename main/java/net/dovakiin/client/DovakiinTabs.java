package net.dovakiin.client;

import net.dovakiin.Dovakiin;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DovakiinTabs {

	public static CreativeTabs items = new CreativeTabs("Dovakiin: Items"){
		@Override public Item getTabIconItem() { return Dovakiin.greenBerry; }
	};
	
	public static CreativeTabs blocks = new CreativeTabs("Dovakiin: Blocks"){
		@Override public Item getTabIconItem() { return Item.getItemFromBlock(Dovakiin.greenBerryBushRipe); }
	};
	
	public static CreativeTabs spawner = new CreativeTabs("Dovakiin: Spawner"){
		@Override public Item getTabIconItem() { return Item.getItemFromBlock(Dovakiin.dragonSpawner); }
	};
	
	public static CreativeTabs misc = new CreativeTabs("Dovakiin: Misc"){
		@Override public Item getTabIconItem() { return Dovakiin.dragonEssence; }
	};
	
}