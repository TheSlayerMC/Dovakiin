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
	
	public static void loadPlayer(EntityPlayer player) {
		NBTTagCompound nbt = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		nbt.setInteger(sword, 0);
		nbt.setInteger("Level", 0);
		nbt.setInteger("Coins", 0);
		nbt.setInteger("MobLevel", 0);
	}

	public static void setSwordLevel(EntityPlayer player, int level) {
		player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setInteger(sword, level);
		Dovakiin.sendStats(player);
		for(int i = 0; i < 10; i++)
			System.out.println(level);
		for(int i = 0; i < 10; i++)
			System.out.println(getSwordLevel(player));
	}

	public static int getSwordLevel(EntityPlayer player) {
		return player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getInteger(sword);
	}
	
	public static void setLevel(EntityPlayer player, int level) {
		player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setInteger("Level", level);
		Dovakiin.sendStats(player);
	}

	public static int getLevel(EntityPlayer player) {
		return player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getInteger("Level");
	}
	
	public static void setCoins(EntityPlayer player, int c) {
		player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setInteger("Coins", c);
		Dovakiin.sendStats(player);
	}

	public static int getCoins(EntityPlayer player) {
		return player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getInteger("Coins");
	}
	
	public static void setMobLevel(EntityLivingBase e, int l) {
		e.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setInteger("MobLevel", l);
		Dovakiin.sendStats(Minecraft.getMinecraft().thePlayer);
	}

	public static int getMobLevel(EntityLivingBase e) {
		return e.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getInteger("MobLevel");
	}
}