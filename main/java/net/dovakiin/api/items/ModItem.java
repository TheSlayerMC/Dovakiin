package net.dovakiin.api.items;

import net.dovakiin.Dovakiin;
import net.dovakiin.util.LangRegistry;
import net.dovakiin.util.Utils;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItem extends Item{

	public ModItem() {
		LangRegistry.addItem(this);
		setCreativeTab(Dovakiin.items);
	}
	
	public Item registerItem(String name){
		setTextureName(Utils.PREFIX + name);
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		return this;
	}
	
}
