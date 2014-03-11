package net.dovakiin.event;

import java.util.Random;

import net.dovakiin.Dovakiin;
import net.dovakiin.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DropsEvent {

	private Random rand = new Random();
	Entity e = null; 
	@SubscribeEvent
	public void onDrop(LivingDropsEvent event){
		EntityLivingBase e = event.entityLiving;
		if(e instanceof EntityDragon){
			event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.dragonEssence)));
		}
		if(e instanceof EntityWither){
			event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.witherEssence)));
		}
	}

	@SubscribeEvent
	public void onKilledMob(LivingDeathEvent event){
		e = event.entity;
		if(!event.entity.worldObj.isRemote){
			if(event.entity instanceof EntityMob && isHoldingSword()){
				Dovakiin.swordLevel += rand.nextInt(1) + 0.3F;
				
				if(Dovakiin.swordLevel <= 10 && ((ItemSword)Items.diamond_sword).getMaxDamage() > 10000){
					((ItemSword)Items.diamond_sword).setMaxDamage(((ItemSword)Items.diamond_sword).getMaxDamage() + (int)Dovakiin.swordLevel + 300);
					((ItemSword)Items.diamond_sword).setTextureName(Utils.PREFIX + "dragonEssence");
				}
				
				if(Dovakiin.swordLevel <= 20){
					((ItemSword)Items.diamond_sword).setMaxDamage(((ItemSword)Items.diamond_sword).getMaxDamage() + (int)Dovakiin.swordLevel + 600);
					((ItemSword)Items.diamond_sword).setTextureName(Utils.PREFIX + "creeperEssence");
				}
			}
		}
	}

	private boolean isHoldingSword(){
		return ((EntityPlayer)e).getHeldItem().getItem() instanceof ItemSword;
	}
}
