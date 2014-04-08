package net.dovakiin.network;

import net.dovakiin.Dovakiin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties{

	public static EntityPlayer player;
	public final static String EXTENDED_PROPERTIES_NAME = "ExtendedPlayer";

	public static int coins, level, sword;

	public ExtendedPlayer() { }

	public ExtendedPlayer(EntityPlayer p){
		player = p;
	}

	public static final ExtendedPlayer get(EntityPlayer player) {
		return (ExtendedPlayer)player.getExtendedProperties(EXTENDED_PROPERTIES_NAME);
	}

	@Override
	public void saveNBTData(NBTTagCompound n) {
		NBTTagCompound prop = new NBTTagCompound();
		prop.setInteger("Coins", coins);
		prop.setInteger("Level", level);
		prop.setInteger("Sword", sword);
		n.setTag(EXTENDED_PROPERTIES_NAME, prop);
	}

	@Override
	public void loadNBTData(NBTTagCompound n) {
		NBTTagCompound prop = (NBTTagCompound)n.getTag(EXTENDED_PROPERTIES_NAME);
		this.coins = prop.getInteger("Coins");
		this.level = prop.getInteger("Level");
		this.sword = prop.getInteger("Sword");
	}

	@Override
	public void init(Entity e, World n) { }

	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(ExtendedPlayer.EXTENDED_PROPERTIES_NAME, new ExtendedPlayer(player));
	}
	
	public static void setLevel(int l) {
		level += l;
	}

	public static int getLevel() {
		return level;
	}

	public static void setCoins(int c) {
		coins += c;
	}
	
	public static int getSwordLevel() {
		return sword;
	}

	public static void setSwordLevel(int s) {
		sword += s;
	}

	public static int getCoins(EntityPlayer player) {
		return coins == -1 ? coins = 0 : coins;
	}
}