package net.dovakiin.entity.misc.egg;

import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.client.GuiHandler;
import net.dovakiin.client.Sounds;
import net.dovakiin.entity.misc.EntityEgg;
import net.dovakiin.entity.mob.npc.EntityMerchent;
import net.dovakiin.network.PacketOpenGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityGreenDragonEgg extends EntityEgg{

	public EntityGreenDragonEgg(World par1World) {
		super(par1World, 100/*DovakiinAPI.rand.nextInt(1000) + 10*/, GuiHandler.greenDragon);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!worldObj.isRemote/* && light*/){
			if(ticks == 0){
				EntityMerchent e = new EntityMerchent(worldObj);
				e.setLocationAndAngles(posX, posY + 0.1F, posZ, 360.0F, 0.0F);
				worldObj.spawnEntityInWorld(e);
				this.setDead();
				//DovakiinAPI.sendChatMessage(DovakiinAPI.getClientPlayer(), DovakiinAPI.DARK_AQUA + "Your dragon has hatched!");
			}
		}
		if(ticks == 50){
			//playSound(Sounds.eggHatch);
		}
		ticks--;
	}
}