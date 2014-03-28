package net.dovakiin.api.items;

import java.util.Random;

import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.client.DovakiinTabs;
import net.dovakiin.entity.misc.EntityEgg;
import net.dovakiin.entity.misc.egg.EntityGreenDragonEgg;
import net.dovakiin.util.LangRegistry;
import net.dovakiin.util.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemEgg extends Item{

	public ItemEgg() {
		LangRegistry.addEgg(this);
		setCreativeTab(DovakiinTabs.items);
	}
	
	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if(!w.isRemote){
			EntityEgg e = null;
			if(i.getItem() == Dovakiin.greenDragon)
				e = new EntityGreenDragonEgg(w);
			
			e.setLocationAndAngles(x + 0.5, y + 1, z + 0.5, DovakiinAPI.rand.nextFloat() * 360F, 0.0F);
			w.spawnEntityInWorld(e);
			i.stackSize--;
			return true;
		}
		return false;
	}

	public Item registerItem(String name){
		setTextureName(Utils.PREFIX + name);
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		return this;
	}
}