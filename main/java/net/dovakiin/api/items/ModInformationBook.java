package net.dovakiin.api.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.client.DovakiinTabs;
import net.dovakiin.network.PacketOpenGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ModInformationBook extends ModItem{

	private int GUI_ID;
	
	public ModInformationBook(int guiID){
		GUI_ID = guiID;
		setCreativeTab(DovakiinTabs.misc);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p) {
		DovakiinAPI.openGui(GUI_ID);
		return i;
	}
	
	@Override
	public Item registerItem(String name) {
		setUnlocalizedName(name);
		setTextureName("book_normal");
		GameRegistry.registerItem(this, name);
		return this;
	}
	
}