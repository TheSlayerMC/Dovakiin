package net.dovakiin;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class DovakiinTabs {

	public static CreativeTabs items = new CreativeTabs("Dovakiin: Items"){
		@Override public Item getTabIconItem() { return Items.apple; }
	};
	
	public static CreativeTabs blocks = new CreativeTabs("Dovakiin: Blocks"){
		@Override public Item getTabIconItem() { return Item.getItemFromBlock(Blocks.acacia_stairs); }
	};
	
	public static CreativeTabs misc = new CreativeTabs("Dovakiin: Misc"){
		@Override public Item getTabIconItem() { return Items.bucket; }
	};
	
}
