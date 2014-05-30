package net.dovakiin.event;

import net.dovakiin.Dovakiin;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class ClientPlayerEvent {

	@SubscribeEvent
	public void crafting(ItemCraftedEvent event){
		int empty = event.player.inventory.getFirstEmptyStack();
		boolean hasCrafted = event.player.inventory.hasItem(Dovakiin.startingBook);

		if(event.crafting.getItem() instanceof ItemSword && !hasCrafted && !event.player.worldObj.isRemote){
			EntityItem item = new EntityItem(event.player.worldObj, event.player.posX, event.player.posY + 0.5F, event.player.posZ, new ItemStack(Dovakiin.startingBook));
			event.player.worldObj.spawnEntityInWorld(item);
		}
	}

	@SubscribeEvent
	public void joined(EntityJoinWorldEvent ev){ }

	public static void register(){
		FMLCommonHandler.instance().bus().register(new ClientPlayerEvent());
	}
}