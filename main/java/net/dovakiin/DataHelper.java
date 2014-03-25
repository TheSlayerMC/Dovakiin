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

	public static NBTTagCompound getOrCreatePersistedTag(EntityPlayer player) {
		if (!player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
			player.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound());
		}
		return player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
	}
	
	public static NBTTagCompound getOrCreatePersistedTag(EntityLivingBase player) {
		if (!player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
			player.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound());
		}
		return player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
	}

	public static void setSwordLevel(EntityPlayer player, int level) {
		getOrCreatePersistedTag(player).setInteger(sword, level);
	}

	public static int getSwordLevel(EntityPlayer player) {
		return getOrCreatePersistedTag(player).getInteger(sword);
	}

	public static void setLevel(EntityPlayer player, int level) {
		getOrCreatePersistedTag(player).setInteger("Level", level);
	}

	public static int getLevel(EntityPlayer player) {
		return getOrCreatePersistedTag(player).getInteger("Level");
	}

	public static void setCoins(EntityPlayer player, int c) {
		getOrCreatePersistedTag(player).setInteger("Coins", c);
	}

	public static int getCoins(EntityPlayer player) {
		return getOrCreatePersistedTag(player).getInteger("Coins");
	}

	public static void setMobLevel(EntityLivingBase e, int l) {
		getOrCreatePersistedTag(e).setInteger("Mob Level", l);
	}

	public static int getMobLevel(EntityLivingBase e) {
		return getOrCreatePersistedTag(e).getInteger("Mob Level");
	}
}