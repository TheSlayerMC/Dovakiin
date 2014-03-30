package net.dovakiin;

import java.util.Random;

import net.dovakiin.api.DovakiinAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class DataHelper {

	private static String sword = "SwordLevel";

	public static void setSwordLevel(EntityPlayer player, int level) {
		getNBT(player).setInteger(sword, level);
		Dovakiin.sendStats(player);
	}

	public static int getSwordLevel(EntityPlayer player) {
		return player.worldObj.isRemote ? Dovakiin.swordLevel : getNBT(player).getInteger(sword);
	}

	public static void setLevel(EntityPlayer player, int level) {
		getNBT(player).setInteger("Level", level);
		Dovakiin.sendStats(player);
	}

	public static int getLevel(EntityPlayer player) {
		return player.worldObj.isRemote ? Dovakiin.level : getNBT(player).getInteger("Level");
	}
	
	public static void setMaxLevel(EntityPlayer player, int level){
		getNBT(player).setInteger("Max Level", level);
		Dovakiin.sendStats(player);
	}
	
	public static int getMaxLevel(EntityPlayer player) {
		return player.worldObj.isRemote ? Dovakiin.maxLevel : getNBT(player).getInteger("Max Level");
	}

	public static void setCoins(EntityPlayer player, int c) {
		getNBT(player).setInteger("Coins", c);
		Dovakiin.sendStats(player);
	}

	public static int getCoins(EntityPlayer player) {
		return player.worldObj.isRemote ? Dovakiin.coins : getNBT(player).getInteger("Coins");
	}

	public static void setMobLevel(EntityLivingBase e, int l) {
		getNBT(e).setInteger("Mob Level", l);
		Dovakiin.sendStats((EntityPlayer)e);
	}

	public static int getMobLevel(EntityLivingBase e) {
		return e.worldObj.isRemote ? Dovakiin.mobLevel : getNBT(e).getInteger("Mob Level");
	}
	
	public static NBTTagCompound getNBT(EntityPlayer player) {
		return player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
	}
	
	public static NBTTagCompound getNBT(EntityLivingBase mob) {
		return mob.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
	}
}