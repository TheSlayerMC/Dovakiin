package net.dovakiin.event;

import java.util.Random;

import net.dovakiin.Dovakiin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DropsEvent {

	private Random rand = new Random();

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
	
	public void onPlayerLoggedIn(EntityJoinWorldEvent event){
		Entity entity = event.entity;
		if(entity instanceof EntityLiving) {
			setName((EntityLiving) entity, getAlteredEntityName((EntityLiving) entity));
		}
	}

	@SubscribeEvent
	public void onKilledMob(LivingDeathEvent event){
		/*EntityPlayer p = (EntityPlayer)event.source.getEntity();
		if(!event.entity.worldObj.isRemote){
			if(event.entity instanceof EntityMob && isHoldingSword(p)){
				float level = rand.nextInt(1) + 0.3F;
				Dovakiin.swordLevel += level;
				
				if(Dovakiin.swordLevel <= 10 && ((ItemSword)Items.diamond_sword).getMaxDamage() > 10000){
					((ItemSword)Items.diamond_sword).setMaxDamage(((ItemSword)Items.diamond_sword).getMaxDamage() + (int)Dovakiin.swordLevel + 300);
					((ItemSword)Items.diamond_sword).setTextureName(Utils.PREFIX + "dragonEssence");
				}
				
				if(Dovakiin.swordLevel <= 20){
					((ItemSword)Items.diamond_sword).setMaxDamage(((ItemSword)Items.diamond_sword).getMaxDamage() + (int)Dovakiin.swordLevel + 600);
					((ItemSword)Items.diamond_sword).setTextureName(Utils.PREFIX + "creeperEssence");
				}
				p.addChatComponentMessage(DovakiinAPI.addChatMessage(DovakiinAPI.BLUE, "[Dovakiin] " + p.getDisplayName() + " Has Gained " + level + " Levels!"));
			}
		}
		
		/*if(p.getHeldItem() == null) {
		} else if(p.getHeldItem().getItem() != null && isHoldingSword(p)) {
			getUnalteredItemName(p.getHeldItem().getItem());
		}*/
	}
	
	private String getAlteredEntityName(EntityLiving entity) {
		return EntityList.getEntityString(entity) + "JAHSBJAGBVHFSVGBA";
	}
	
	private static String getUnalteredItemName(Item item) {
        return StatCollector.translateToLocal(item.getUnlocalizedName() + ".name");
	}
	
	private static Entity setName(EntityLiving entity, String name) {
		entity.setCustomNameTag(name);
		return entity;
	}
	
	private static String getUnalteredName(Entity entity) {
		String s = EntityList.getEntityString(entity);
        if(s == null) 
        	s = "generic";
        return StatCollector.translateToLocal("entity." + s + ".name");
	}

	private boolean isHoldingSword(EntityPlayer p){
		return p.getHeldItem().getItem() instanceof ItemSword;
	}
	
	public static void register(){
		MinecraftForge.EVENT_BUS.register(new DropsEvent());
	}
}
