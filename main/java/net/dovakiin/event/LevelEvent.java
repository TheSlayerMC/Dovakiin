package net.dovakiin.event;

import java.util.Random;

import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.entity.misc.EntityEgg;
import net.dovakiin.entity.mob.EntityWitherSkeleton;
import net.dovakiin.entity.mob.npc.EntityMerchent;
import net.dovakiin.network.ExtendedPlayer;
import net.dovakiin.util.Config;
import net.dovakiin.util.LangRegistry;
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
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LevelEvent {

	@SubscribeEvent
	public void onDrop(LivingDropsEvent event){
		EntityLivingBase e = event.entityLiving;
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		Random r = DovakiinAPI.rand;
		ExtendedPlayer props = ExtendedPlayer.get(p);
		if(event.source.getSourceOfDamage() instanceof EntityPlayer){
			EntitySkeleton s = new EntitySkeleton(event.entity.worldObj);
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
			if(s.getSkeletonType() == 1 || e instanceof EntityWitherSkeleton){
				if(props.getHeadLevel() < 10){
					if(r.nextInt(50) == 1)
						event.drops.add(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, new ItemStack(Items.skull, 1, 1)));
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
			ExtendedPlayer.register((EntityPlayer) event.entity);

		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPlayer.EXTENDED_PROPERTIES_NAME) == null)
			event.entity.registerExtendedProperties(ExtendedPlayer.EXTENDED_PROPERTIES_NAME, new ExtendedPlayer((EntityPlayer) event.entity));
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(EntityJoinWorldEvent event){
		Entity entity = event.entity;
		if(entity instanceof EntityLiving && !(entity instanceof EntityEgg) && !(entity instanceof EntityMerchent) && !(entity instanceof EntityAgeable) && !(entity instanceof EntityWaterMob)) {
			setName((EntityLiving)entity, getEntityName((EntityLiving)entity));
		}
		if(entity instanceof EntityEgg){
			//setEggName((EntityEgg)entity, ((EntityPlayer)event.entity).getDisplayName());
		}
		if(entity instanceof EntityMerchent){
			setName((EntityLiving)entity, getEntityName((EntityLiving)entity));
		}
	}

	public static Entity setName(EntityLivingBase entity, String name) {
		EntityPlayer p = DovakiinAPI.getClientPlayer();
		((EntityLiving)entity).setCustomNameTag(DovakiinAPI.AQUA + name + DovakiinAPI.GREEN + " Lv: " + DovakiinAPI.GOLD + DovakiinAPI.rand.nextInt(24));
		if(entity instanceof EntityMerchent)
			((EntityLiving)entity).setCustomNameTag(DovakiinAPI.AQUA + "Merchent");
		return entity;
	}

	public static Entity setEggName(EntityEgg entity, String user) {
		((EntityLiving)entity).setCustomNameTag(DovakiinAPI.AQUA + user + "'s Egg");
		return entity;
	}

	public static int expGained = (int)((float)DovakiinAPI.rand.nextInt(3) / 2);
	public static int headXP = (int)((float)DovakiinAPI.rand.nextInt(10) / 2);
	public static int bowXP = (int)((float)DovakiinAPI.rand.nextInt(5) / 2);
	public static int swordXP = (int)((float)DovakiinAPI.rand.nextInt(5) / 2);
	public static int pickXP = (int)((float)DovakiinAPI.rand.nextInt(5) / 2);

	@SubscribeEvent
	public void onKilledMob(LivingDeathEvent event){
		if(event.source.getSourceOfDamage() instanceof EntityPlayer){
			EntityPlayer p = (EntityPlayer)event.source.getSourceOfDamage();

			ExtendedPlayer props = ExtendedPlayer.get(p);

			if(p.getHeldItem() != null && p.getHeldItem().getItem() instanceof ItemSword){ 
				props.addSwordExperience(swordXP, p);
			}

			if(p.getHeldItem() != null && p.getHeldItem().getItem() instanceof ItemBow && event.source.isProjectile()){ 
				props.addBowExperience(bowXP, p);
			}

			EntitySkeleton s = new EntitySkeleton(event.entity.worldObj);

			if(s.getSkeletonType() == 1 || event.entityLiving instanceof EntityWitherSkeleton){
				props.addHeadExperience(headXP, p);
			}
			//props.resetPlayer(p);
			if(Config.canShowDeathMessage)
				DovakiinAPI.sendMessageToAll(p.getDisplayName() + " Has Slain A " + getEntityName((EntityLiving)event.entityLiving));
			props.addExperience(expGained, p);
		}
	}

	@SubscribeEvent
	public void onBlockHarvested(HarvestDropsEvent event){
		EntityPlayer p = event.harvester;
		Random r = DovakiinAPI.rand;
		ExtendedPlayer props = ExtendedPlayer.get(p);
		if(event.harvester != null) {
			if(event.harvester.getHeldItem() != null && event.harvester.getHeldItem().getItem() instanceof ItemPickaxe) {
				if(props.getPickaxeLevel() < 10){
					if(r.nextInt(20) < 2){
						ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(event.block, 1, event.blockMetadata));
						if(stack != null && event.block != Blocks.redstone_ore && event.block != Blocks.lapis_ore) {
							event.drops.clear();
							event.drops.add(stack.copy());
						}
					}
				}
				props.addPickaxeExperience(pickXP, p);
			}
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

	private String getEntityName(EntityLiving entity) {
		return EntityList.getEntityString(entity);
	}

	private static String getUnalteredItemName(Item item) {
		return StatCollector.translateToLocal(item.getUnlocalizedName() + ".name");
	}

	private static String setAlteredItemName(Item item, String name) {
		LangRegistry.addToFile("item." + name + ".name=" + name);
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
