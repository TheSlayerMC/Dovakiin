package net.dovakiin.api.items;

import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.client.DovakiinTabs;
import net.dovakiin.event.level_system.LevelHelper;
import net.dovakiin.network.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCoin extends ModItem{

	public ItemCoin() {
		setMaxStackSize(1);
		setCreativeTab(DovakiinTabs.misc);
	}

	@Override
	public void onUpdate(ItemStack i, World w, Entity e, int j, boolean b) {
		if(!w.isRemote){
			if(e instanceof EntityPlayer){
				EntityPlayer p = (EntityPlayer)e;
				LevelHelper.setCoins(i.stackSize + DovakiinAPI.rand.nextInt(3));
				p.inventory.setInventorySlotContents(j, null);
			} else {
				i = null;
			}
		} else {
			if(e instanceof EntityPlayer){
				EntityPlayer p = (EntityPlayer)e;
				p.inventory.setInventorySlotContents(j, null);
			} else {
				i = null;
			}
		}
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}
}