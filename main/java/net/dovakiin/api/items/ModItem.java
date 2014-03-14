package net.dovakiin.api.items;

import java.util.List;

import net.dovakiin.Dovakiin;
import net.dovakiin.util.LangRegistry;
import net.dovakiin.util.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItem extends Item{

	public Item registerItem(String name){
		setTextureName(Utils.PREFIX + name);
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		return this;
	}
	
	public ModItem() {
		LangRegistry.addItem(this);
		setCreativeTab(Dovakiin.items);
		if(this.getUnlocalizedName().substring(5).contains("Essence")){
			setMaxStackSize(1);
		}
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
		if(this.getUnlocalizedName().substring(5).contains("Berry")){
			list.add("Warning: They're spikey!");
		}
		
		if(this.getUnlocalizedName().substring(5).contains("Essence")){
			list.add("Used for crafting");
		}
	}
	
}