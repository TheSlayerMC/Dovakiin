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
	
	public static int getMaxLevel() {
		return 245;
	}

	public static void setCoins(EntityPlayer player, int c) {
		getNBT(player).setInteger("Coins", c);
		Dovakiin.sendStats(player);
	}

	public static int getCoins(EntityPlayer player) {
		return player.worldObj.isRemote ? Dovakiin.coins : getNBT(player).getInteger("Coins");
	}

	public static void setMobLevel(EntityLivingBase e) {
		getNBT((EntityPlayer)e).setInteger("Mob Level", getLevel((EntityPlayer)e) == 0 ? 1 : getLevel((EntityPlayer)e) / 2 + 1);
		Dovakiin.sendStats((EntityPlayer)e);
	}

	public static int getMobLevel(EntityLivingBase player) {
		return player.worldObj.isRemote ? Dovakiin.mobLevel : player.getEntityData().getInteger("Mob Level");
	}
	
	public static NBTTagCompound getNBT(EntityPlayer player) {
		return player.getEntityData();
	}
}