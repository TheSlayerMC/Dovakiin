package net.dovakiin.event;

import java.util.Random;

import net.dovakiin.DataHelper;
import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.entity.misc.EntityEgg;
import net.dovakiin.entity.mob.npc.EntityMerchent;
import net.dovakiin.network.PacketSyncServer;
import net.dovakiin.util.LangRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
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

public class DropsEvent {

	@SubscribeEvent
	public void onDrop(LivingDropsEvent event){
		EntityLivingBase e = event.entityLiving;
		Random r = new Random();
		if(e instanceof EntityDragon){
			event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.dragonEssence)));
		}
		if(e instanceof EntityWither){
			event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.witherEssence)));
		}
		if(e instanceof EntityMob){
			for(int i = 0; i < r.nextInt(50); i++)
				event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.coin)));
		}
		if(e instanceof EntitySlime){
			for(int i = 0; i < r.nextInt(10); i++)
				event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.coin)));
		}
		if(e instanceof EntityAnimal){
			for(int i = 0; i < 1; i++)
				event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Dovakiin.coin)));
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(EntityJoinWorldEvent event){
		Minecraft mc = Minecraft.getMinecraft();
		Entity entity = event.entity;
		//if(mc.currentScreen != null){
		if(entity instanceof EntityLiving && !(entity instanceof EntityEgg) && !(entity instanceof EntityMerchent)) {
			setName((EntityLiving)entity, getAlteredEntityName((EntityLiving)entity));
		}
		if(entity instanceof EntityEgg){
			//setEggName((EntityEgg)entity, ((EntityPlayer)event.entity).getDisplayName());
		}
		//}
	}

	public static Entity setName(EntityLivingBase entity, String name) {
		((EntityLiving)entity).setCustomNameTag(DovakiinAPI.AQUA + name + DovakiinAPI.GREEN + " Lv: " + DovakiinAPI.GOLD + DataHelper.getMobLevel(entity));
		if(EntityList.getEntityString(entity) == "EntityHorse")
			((EntityLiving)entity).setCustomNameTag(DovakiinAPI.AQUA + "Horse" + DovakiinAPI.GREEN + " Lv: " + DovakiinAPI.GOLD + DataHelper.getMobLevel(entity));

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
				final int level = DovakiinAPI.rand.nextInt(2) + 1;
				DataHelper.setSwordLevel(p, DataHelper.getSwordLevel(p) + level);
				p.addChatComponentMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "[" + DovakiinAPI.BLUE + "Dovakiin" + DovakiinAPI.AQUA + "]" + " " + p.getDisplayName() + " Has Slain A " + getAlteredEntityName((EntityLiving)event.entityLiving)));
				p.addChatComponentMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "[" + DovakiinAPI.BLUE + "Dovakiin" + DovakiinAPI.AQUA + "]" + " " + p.getDisplayName() + " Has Gained " + DovakiinAPI.GREEN + level + DovakiinAPI.AQUA + " Level!"));
				p.addChatComponentMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "[" + DovakiinAPI.BLUE + "Dovakiin" + DovakiinAPI.AQUA + "]" + " " + p.getDisplayName() + "'s Level Is Now: " + DovakiinAPI.GREEN + DataHelper.getSwordLevel(p)));
			}
			Dovakiin.packetHandler.sendToServer(new PacketSyncServer(p));
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
		MinecraftForge.EVENT_BUS.register(new DropsEvent());
	}
}
