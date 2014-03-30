package net.dovakiin.api;

import net.dovakiin.Dovakiin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerEmpty extends Container{
	
	public ContainerEmpty() { }
	
	public ContainerEmpty(EntityPlayer p) {
		Dovakiin.sendStats(p);
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

}
