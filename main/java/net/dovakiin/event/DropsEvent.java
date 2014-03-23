package net.dovakiin.event;

import java.util.Random;

import net.dovakiin.DataHelper;
import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.network.PacketRequestStats;
import net.dovakiin.util.LangRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityBat;
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
	
	@SubscribeEvent
	public void onPlayerLoggedIn(EntityJoinWorldEvent event){
		Entity entity = event.entity;
		if(entity instanceof EntityLiving) {
			setName((EntityLiving) entity, getAlteredEntityName((EntityLiving)entity));
		}
	}
	
	public static Entity setName(EntityLivingBase entity, String name) {
		((EntityLiving)entity).setCustomNameTag(DovakiinAPI.AQUA + name + DovakiinAPI.GREEN + " Lv: " + DovakiinAPI.GOLD + Dovakiin.mobLevel);
		return entity;
	}

	@SubscribeEvent
	public void onKilledMob(LivingDeathEvent event){
		EntityPlayer p = (EntityPlayer)event.source.getEntity();
		if(!event.entity.worldObj.isRemote){
			if(p.getHeldItem().getItem() instanceof ItemSword && !(event.entity instanceof EntityBat)){
				final int level = rand.nextInt(2) + 1;
				DataHelper.loadPlayer(p);
				Dovakiin.packetHandler.sendToServer(new PacketRequestStats());

				DataHelper.setSwordLevel(p, DataHelper.getSwordLevel(p) + level);
				p.addChatComponentMessage(DovakiinAPI.addChatMessage("[" + DovakiinAPI.BLUE + "Dovakiin" + DovakiinAPI.AQUA + "]" + " " + p.getDisplayName() + " Has Gained " + DovakiinAPI.GREEN + level + DovakiinAPI.AQUA + " Level!"));
				p.addChatComponentMessage(DovakiinAPI.addChatMessage("[" + DovakiinAPI.BLUE + "Dovakiin" + DovakiinAPI.AQUA + "]" + " " + p.getDisplayName() + "'s Level Is Now: " + DovakiinAPI.GREEN + DataHelper.getSwordLevel(p)));
			}
		}
	}
	
	private String getAlteredEntityName(EntityLiving entity) {
		return EntityList.getEntityString(entity);
	}
	
	private static String getUnalteredItemName(Item item) {
        return StatCollector.translateToLocal(item.getUnlocalizedName() + ".name");
	}
	
	private static String getAlteredItemName(Item item, String name) {
		LangRegistry.addToFile(item.getUnlocalizedName() + name + ".name=" + name);
        return StatCollector.translateToLocal(item.getUnlocalizedName() + name + ".name");
	}
	
	private static String getUnalteredName(Entity entity) {
		String s = EntityList.getEntityString(entity);
        return StatCollector.translateToLocal("entity." + s + ".name");
	}

	private boolean isHoldingSword(EntityPlayer p){
		if(p.getHeldItem().getItem() instanceof ItemSword){
			return true;
		}
		return false;
	}
	
	public static void register(){
		MinecraftForge.EVENT_BUS.register(new DropsEvent());
	}
}
