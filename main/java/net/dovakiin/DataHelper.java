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
		player.getEntityData().setInteger(sword, level);
	}

	public static int getSwordLevel(EntityPlayer player) {
		return player.getEntityData().getInteger(sword);
	}
	
	public static void setLevel(EntityPlayer player, int level) {
		player.getEntityData().setInteger("Level", level);
	}

	public static int getLevel(EntityPlayer player) {
		return player.getEntityData().getInteger("Level");
	}
	
	public static void setCoins(EntityPlayer player, int c) {
		player.getEntityData().setInteger("Coins", c);
	}

	public static int getCoins(EntityPlayer player) {
		return player.getEntityData().getInteger("Coins");
	}
	
	public static void setMobLevel(EntityLivingBase e, int l) {
		e.getEntityData().setInteger("MobLevel", getSwordLevel((EntityPlayer)e) * 2 / 2 + 1);
	}

	public static int getMobLevel(EntityLivingBase e) {
		return e.getEntityData().getInteger("MobLevel");
	}
}