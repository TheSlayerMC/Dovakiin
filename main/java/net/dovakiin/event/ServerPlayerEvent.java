package net.dovakiin.event;

import net.dovakiin.Dovakiin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ServerPlayerEvent {

	@SubscribeEvent
	public void joined(EntityJoinWorldEvent ev){
		boolean hasGotten = false;
		if(ev.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) ev.entity;
			if(!ev.world.isRemote && !hasGotten){
				if(!player.inventory.hasItem(Dovakiin.startingBook)) {
					int es = player.inventory.getFirstEmptyStack();
					player.inventory.setInventorySlotContents(es, new ItemStack(Dovakiin.startingBook, 1));
				}
				hasGotten = true;
			}
		}
	}

	public static void register(){
		MinecraftForge.EVENT_BUS.register(new ServerPlayerEvent());
	}
}