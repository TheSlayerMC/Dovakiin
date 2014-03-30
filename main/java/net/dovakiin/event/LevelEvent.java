package net.dovakiin.event;

import java.awt.List;
import java.util.Random;

import net.dovakiin.DataHelper;
import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.entity.misc.EntityEgg;
import net.dovakiin.entity.mob.npc.EntityMerchent;
import net.dovakiin.network.PacketSyncServer;
import net.dovakiin.util.LangRegistry;
import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LevelEvent {

	@SubscribeEvent
	public void onDrop(LivingDropsEvent event){
		EntityLivingBase e = event.entityLiving;
		Random r = DovakiinAPI.rand;
		if(e instanceof EntityDragon){
			event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.dragonEssence)));
			for(int i = 0; i < r.nextInt(500); i++)
				event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.coin)));
		}
		if(e instanceof EntityWither){
			event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.witherEssence)));
			for(int i = 0; i < r.nextInt(500); i++)
				event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.coin)));
		}
		if(e instanceof EntityMob){
			for(int i = 0; i < r.nextInt(80); i++)
				event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.coin)));
		}
		if(e instanceof EntitySlime){
			for(int i = 0; i < r.nextInt(20); i++)
				event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.coin)));
		}
		if(e instanceof EntityAnimal){
			for(int i = 0; i < r.nextInt(2) + 1; i++)
				event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.coin)));
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(EntityJoinWorldEvent event){
		Minecraft mc = Minecraft.getMinecraft();
		Entity entity = event.entity;
		if(entity instanceof EntityLiving && !(entity instanceof EntityEgg) && !(entity instanceof EntityMerchent) && !(entity instanceof EntityAgeable)) {
			setName((EntityLiving)entity, getAlteredEntityName((EntityLiving)entity));
		}
		if(entity instanceof EntityEgg){
			//setEggName((EntityEgg)entity, ((EntityPlayer)event.entity).getDisplayName());
		}
		if(entity instanceof EntityMerchent){
			setName((EntityLiving)entity, getAlteredEntityName((EntityLiving)entity));
		}
	}

	public static Entity setName(EntityLivingBase entity, String name) {
		EntityPlayer p = DovakiinAPI.getClientPlayer();
		((EntityLiving)entity).setCustomNameTag(DovakiinAPI.AQUA + name + DovakiinAPI.GREEN + " Lv: " + DovakiinAPI.GOLD + DataHelper.getMobLevel(entity));
		if(entity instanceof EntityMerchent)
			((EntityLiving)entity).setCustomNameTag(DovakiinAPI.AQUA + "Merchent");
		return entity;
	}

	public static Entity setEggName(EntityEgg entity, String user) {
		((EntityLiving)entity).setCustomNameTag(DovakiinAPI.AQUA + user + "'s Egg");
		return entity;
	}

	@SubscribeEvent
	public void onKilledMob(LivingDeathEvent event){
		if(event.source.getSourceOfDamage() instanceof EntityPlayer){
			EntityPlayer p = (EntityPlayer)event.source.getSourceOfDamage();

			if(p.getHeldItem() != null && p.getHeldItem().getItem() instanceof ItemSword){
				int level = (DovakiinAPI.rand.nextInt(8) / 2);
				System.out.println((float)level);
				
				if(DataHelper.getLevel(p) >= 245){
					level = 0;
					DataHelper.setLevel(p, 245);
				}
				
				//DataHelper.setSwordLevel(p, DataHelper.getSwordLevel(p) + level);
				DataHelper.setLevel(p, DataHelper.getLevel(p) + level);
				
				p.addChatComponentMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "[" + DovakiinAPI.BLUE + "Dovakiin" + DovakiinAPI.AQUA + "]" + " " + p.getDisplayName() + " Has Slain A " + getAlteredEntityName((EntityLiving)event.entityLiving)));
			}
		}
	}
	
	public static void addLevel(int par1, EntityPlayer p) {
        int j = Integer.MAX_VALUE - DataHelper.getLevel(p), level = DataHelper.getLevel(p);

        if (par1 > j) {
            par1 = j;
        }

        level += (float)par1 / (float)DataHelper.getMaxLevel();

        for (level += par1; level >= 1.0F; level /= (float)DataHelper.getMaxLevel()) {
            level = (int)((level - 1.0F) * (float)DataHelper.getMaxLevel());
            DataHelper.setLevel(p, level + 1);
        }
    }

	@SubscribeEvent
	public void onEntitySetTarget(LivingSetAttackTargetEvent event) {
		if (event.target != null && event.target instanceof EntityPlayer && event.entityLiving.func_94060_bK() != event.target) {
			if (event.target.isInvisible()) {
				((EntityLiving)event.entity).setAttackTarget(null);
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

	public static void register(){
		MinecraftForge.EVENT_BUS.register(new LevelEvent());
	}
}
