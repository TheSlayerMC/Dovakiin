package net.dovakiin.api.items;

import java.util.List;
import java.util.Random;

import net.dovakiin.Dovakiin;
import net.dovakiin.api.WorldGenAPI;
import net.dovakiin.client.DovakiinTabs;
import net.dovakiin.util.LangRegistry;
import net.dovakiin.util.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItem extends Item {

	public ModItem() {
		this(false);
		LangRegistry.addItem(this);
		setCreativeTab(DovakiinTabs.items);			
	}

	public ModItem(boolean flag){
		if(flag){
			setCreativeTab(DovakiinTabs.misc);
			setMaxStackSize(1);
			LangRegistry.addItem(this);
		}
	}

	public Item registerItem(String name){
		setTextureName(Utils.PREFIX + name);
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		return this;
	}

	private boolean isEssence(){
		return getUnlocalizedName().substring(5).contains("Essence");
	}
	
	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int par7, float par8, float par9, float par10) {
		
		return true;
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