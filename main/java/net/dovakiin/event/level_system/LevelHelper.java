package net.dovakiin.event.level_system;

import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.network.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class LevelHelper implements IExtendedEntityProperties{

	private static int level, experienceLevel, experienceTotal, coins;
	public static float levelXP;
	
	@Override
	public void saveNBTData(NBTTagCompound n) {
		n.setInteger("Level", level);
		n.setInteger("Experience", experienceLevel);
		n.setInteger("Total", experienceTotal);
		n.setInteger("Coins", coins);
		n.setFloat("XP", levelXP);
	}

	@Override
	public void loadNBTData(NBTTagCompound n) {
		level = n.getInteger("Level");
		experienceLevel = n.getInteger("Experience");
		experienceTotal = n.getInteger("Total");
		coins = n.getInteger("Coins");
		levelXP = n.getFloat("XP");
	}

	@Override
	public void init(Entity entity, World world) { }
	
	public static void addExperience(int par1, EntityPlayer player) {
        int j = Integer.MAX_VALUE - experienceTotal;
        
        if(par1 > j) 
            par1 = j;
        
        levelXP += (float)par1 / (float)xpBarCap();

        for(experienceTotal += par1; levelXP >= 1.0F; levelXP /= (float)xpBarCap()) {
        	levelXP = (levelXP - 1.0F) * (float)xpBarCap();
        	addLevel(1);
        }
    }

	public static void addLevel(int par1) {
        if(experienceLevel < 0) {
        	experienceLevel = 0;
        	levelXP = 0.0F;
        	experienceTotal = 0;
        }
        DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "[" +  DovakiinAPI.BLUE + "Dovakiin" +  DovakiinAPI.AQUA + "] " +  DovakiinAPI.AQUA + "Your level has gone up!");
    }
	
	public static int xpBarCap() {
        return experienceLevel >= 10 ? 2 : experienceLevel >= 40 ? 5 : experienceLevel >= 90 ? 8 : experienceLevel >= 150 ? 10 : experienceLevel >= 200 ? 15 : 17;
    }
	
	public static void setLevel(int l){
		experienceLevel += l;
	}
	
	public static int getLevel() {
		return experienceLevel;
	}
	
	public static void setCoins(int l){
		coins += l;
	}
	
	public static int getCoins() {
		return coins;
	}
}
