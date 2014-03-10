package net.dovakiin.event;

import net.dovakiin.Dovakiin;
import net.dovakiin.api.blocks.BlockBerryPlant;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BonemealEvent {

	@SubscribeEvent
	public void bonemeal(net.minecraftforge.event.entity.player.BonemealEvent event){
		if(event.block == Blocks.cactus){
			event.entityPlayer.worldObj.setBlock(event.x, event.y + 1, event.z, Blocks.cactus);
		}
		
		if(event.block == Blocks.reeds){
			event.entityPlayer.worldObj.setBlock(event.x, event.y + 1, event.z, Blocks.reeds);
		}
		
		if(event.block == Dovakiin.greenBerryBush){
			((BlockBerryPlant)Dovakiin.greenBerryBush).grow(event.world, event.x, event.y, event.z);
		}
		
		if(event.block == Dovakiin.desertBerryBush){
			((BlockBerryPlant)Dovakiin.desertBerryBush).grow(event.world, event.x, event.y, event.z);
		}
	}
}