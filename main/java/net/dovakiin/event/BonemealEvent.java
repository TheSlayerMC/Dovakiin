package net.dovakiin.event;

import net.dovakiin.Dovakiin;
import net.dovakiin.api.blocks.BlockBerryPlant;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BonemealEvent {

	@SubscribeEvent
	public void bonemeal(net.minecraftforge.event.entity.player.BonemealEvent event){
		boolean i = event.entityPlayer.getHeldItem().getItem().equals(new ItemStack(Items.dye, event.entityPlayer.getHeldItem().stackSize, 15));
		
		if(event.block == Blocks.cactus){
			event.entityPlayer.worldObj.setBlock(event.x, event.y + 1, event.z, Blocks.cactus);
			if(i)
				event.entityPlayer.getHeldItem().stackSize--;
		}
		
		if(event.block == Blocks.reeds){
			event.entityPlayer.worldObj.setBlock(event.x, event.y + 1, event.z, Blocks.reeds);
			if(i)
				event.entityPlayer.getHeldItem().stackSize--;
		}
		
		if(event.block == Dovakiin.greenBerryBush){
			((BlockBerryPlant)Dovakiin.greenBerryBush).grow(event.world, event.x, event.y, event.z);
			if(i)
				event.entityPlayer.getHeldItem().stackSize--;
		}
		
		if(event.block == Dovakiin.desertBerryBush){
			((BlockBerryPlant)Dovakiin.desertBerryBush).grow(event.world, event.x, event.y, event.z);
			if(i)
				event.entityPlayer.getHeldItem().stackSize--;
		}
		event.entityPlayer.getHeldItem().stackSize--;
	}
	
	public static void register(){
		MinecraftForge.EVENT_BUS.register(new BonemealEvent());
	}
}