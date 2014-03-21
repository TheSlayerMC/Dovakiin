package net.dovakiin.event;

import net.dovakiin.Dovakiin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class PlayerEvent {

	@SubscribeEvent
	public void crafting(ItemCraftedEvent event){
		int empty = event.player.inventory.getFirstEmptyStack();
		boolean hasCrafted = event.player.inventory.hasItem(Dovakiin.startingBook);

		if(event.crafting.getItem() instanceof ItemSword && !hasCrafted){
			event.player.inventory.setInventorySlotContents(empty, new ItemStack(Dovakiin.startingBook));
			System.out.println("CRAFTED");
			hasCrafted = true;
		}
	}

	@SubscribeEvent
	public void joined(EntityJoinWorldEvent ev){
		if(ev.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) ev.entity;
			if(!player.inventory.hasItem(Dovakiin.startingBook)) {
				int es = player.inventory.getFirstEmptyStack();
				player.inventory.setInventorySlotContents(es, new ItemStack(Dovakiin.startingBook, 1));
			}
		}
	}

	public static void register(){
		MinecraftForge.EVENT_BUS.register(new PlayerEvent());
	}
}