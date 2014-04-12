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

	public static int coins, sword, experienceLevel, experienceTotal;
	public static float levelXP;

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
		prop.setFloat("LevelXP", levelXP);
		prop.setInteger("Sword", sword);
		prop.setInteger("XpLevel", this.experienceLevel);
        prop.setInteger("XpTotal", this.experienceTotal);
		n.setTag(EXTENDED_PROPERTIES_NAME, prop);
	}

	@Override
	public void loadNBTData(NBTTagCompound n) {
		NBTTagCompound prop = (NBTTagCompound)n.getTag(EXTENDED_PROPERTIES_NAME);
		this.coins = prop.getInteger("Coins");
		this.levelXP = prop.getFloat("LevelXP");
		this.sword = prop.getInteger("Sword");
		this.experienceLevel = prop.getInteger("XpLevel");
        this.experienceTotal = prop.getInteger("XpTotal");
	}

	@Override
	public void init(Entity e, World n) { }

	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(ExtendedPlayer.EXTENDED_PROPERTIES_NAME, new ExtendedPlayer(player));
	}

	public void addLevel(int par1, EntityPlayer p) {
		ExtendedPlayer props = ExtendedPlayer.get(p);
		int field_82249_h;
        field_82249_h = p.ticksExisted;

		props.experienceLevel += par1;

        if (props.experienceLevel < 0) {
        	props.experienceLevel = 0;
        	props.levelXP = 0.0F;
        	props.experienceTotal = 0;
        }

        if (par1 > 0 && props.experienceLevel % 5 == 0 && (float)field_82249_h < (float)p.ticksExisted - 100.0F) {
            float f = props.experienceLevel > 30 ? 1.0F : (float)props.experienceLevel / 30.0F;
        }
    }
	
	public void addExperience(int par1, EntityPlayer player)
    {
		ExtendedPlayer props = ExtendedPlayer.get(player);
        int j = Integer.MAX_VALUE - props.experienceTotal;

        if (par1 > j) {
            par1 = j;
        }

        props.levelXP += (float)par1 / (float)xpBarCap(player);

        for (props.experienceTotal += par1; props.levelXP >= 1.0F; props.levelXP /= (float)xpBarCap(player)) {
        	props.levelXP = (props.levelXP - 1.0F) * (float)xpBarCap(player);
            addLevel(1, player);
        }
    }
	
	public static int xpBarCap(EntityPlayer player) {
		ExtendedPlayer props = ExtendedPlayer.get(player);
        return experienceLevel >= 10 ? 17 : experienceLevel >= 40 ? 15 : experienceLevel >= 90 ? 10 : 8;
    }
	
	public static int getLevel() {
		return (int)experienceTotal;
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