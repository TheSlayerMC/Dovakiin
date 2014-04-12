package net.dovakiin.network;

import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties{

	public static EntityPlayer player;
	public final static String EXTENDED_PROPERTIES_NAME = "ExtendedPlayer";

	public static int coins, experienceLevel, experienceTotal, maxStatLevel = 10, bowTotal, bowExperience, headTotal, headExperience, hoeTotal, hoeExperience, pickTotal, pickExperience, swordTotal, swordExperience;
	public static float levelXP, swordXP, bowXP, headXP, pickXP, hoeXP;

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
		prop.setFloat("SwordXP", swordXP);
		prop.setFloat("BowXP", bowXP);
		prop.setFloat("HeadXP", headXP);
		prop.setFloat("PickXP", pickXP);
		prop.setFloat("HoeXP", hoeXP);
		prop.setInteger("XpLevel", this.experienceLevel);
        prop.setInteger("XpTotal", this.experienceTotal);
		prop.setInteger("XpBow", this.bowExperience);
        prop.setInteger("TotalBow", this.bowTotal);
        prop.setInteger("XpHead", this.headExperience);
        prop.setInteger("TotalHead", this.headTotal);
        prop.setInteger("XpHoe", this.hoeExperience);
        prop.setInteger("TotalHoe", this.hoeTotal);
        prop.setInteger("XpPick", this.pickExperience);
        prop.setInteger("TotalPick", this.pickTotal);
        prop.setInteger("XpSword", this.swordExperience);
        prop.setInteger("TotalSword", this.swordTotal);
        n.setTag(EXTENDED_PROPERTIES_NAME, prop);
	}

	@Override
	public void loadNBTData(NBTTagCompound n) {
		NBTTagCompound prop = (NBTTagCompound)n.getTag(EXTENDED_PROPERTIES_NAME);
		this.coins = prop.getInteger("Coins");
		this.experienceLevel = prop.getInteger("XpLevel");
        this.experienceTotal = prop.getInteger("XpTotal");
        this.bowExperience = prop.getInteger("XpBow");
        this.bowTotal = prop.getInteger("TotalBow");
        this.headExperience = prop.getInteger("XpHead");
        this.headTotal = prop.getInteger("TotalHead");
        this.hoeExperience = prop.getInteger("XpHoe");
        this.hoeTotal = prop.getInteger("TotalHoe");
        this.pickExperience = prop.getInteger("XpPick");
        this.pickTotal = prop.getInteger("TotalPick");
        this.swordExperience = prop.getInteger("XpSword");
        this.swordTotal = prop.getInteger("TotalSword");
		this.levelXP = prop.getFloat("LevelXP");
		this.swordXP = prop.getFloat("SwordXP");
		this.bowXP = prop.getFloat("BowXP");
		this.headXP = prop.getFloat("HeadXP");
		this.pickXP = prop.getFloat("PickXP");
		this.hoeXP = prop.getFloat("HoeXP");
	}

	@Override
	public void init(Entity e, World n) { }

	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(ExtendedPlayer.EXTENDED_PROPERTIES_NAME, new ExtendedPlayer(player));
	}
	
	private void addHeadLevel(int par1, EntityPlayer p) {
		ExtendedPlayer props = ExtendedPlayer.get(p);
		int field_82249_h;
        field_82249_h = p.ticksExisted;

		props.headExperience += par1;

        if (props.headExperience < 0) {
        	props.headExperience = 0;
        	props.headXP = 0.0F;
        	props.headTotal = 0;
        }

        if (par1 > 0 && props.headExperience % 5 == 0 && (float)field_82249_h < (float)p.ticksExisted - 100.0F) {
            float f = props.headExperience > 30 ? 1.0F : (float)props.headExperience / 30.0F;
        }
    }
	
	private void addBowLevel(int par1, EntityPlayer p) {
		ExtendedPlayer props = ExtendedPlayer.get(p);
		int field_82249_h;
        field_82249_h = p.ticksExisted;

		props.bowExperience += par1;

        if (props.bowExperience < 0) {
        	props.bowExperience = 0;
        	props.bowXP = 0.0F;
        	props.bowTotal = 0;
        }

        if (par1 > 0 && props.bowExperience % 5 == 0 && (float)field_82249_h < (float)p.ticksExisted - 100.0F) {
            float f = props.bowExperience > 30 ? 1.0F : (float)props.bowExperience / 30.0F;
        }
    }
	
	private void addHoeLevel(int par1, EntityPlayer p) {
		ExtendedPlayer props = ExtendedPlayer.get(p);
		int field_82249_h;
        field_82249_h = p.ticksExisted;

		props.hoeExperience += par1;

        if (props.hoeExperience < 0) {
        	props.hoeExperience = 0;
        	props.hoeXP = 0.0F;
        	props.hoeTotal = 0;
        }

        if (par1 > 0 && props.hoeExperience % 5 == 0 && (float)field_82249_h < (float)p.ticksExisted - 100.0F) {
            float f = props.hoeExperience > 30 ? 1.0F : (float)props.hoeExperience / 30.0F;
        }
    }
	
	private void addPickaxeLevel(int par1, EntityPlayer p) {
		ExtendedPlayer props = ExtendedPlayer.get(p);
		int field_82249_h;
        field_82249_h = p.ticksExisted;

		props.pickExperience += par1;

        if (props.pickExperience < 0) {
        	props.pickExperience = 0;
        	props.pickXP = 0.0F;
        	props.pickTotal = 0;
        }

        if (par1 > 0 && props.pickExperience % 5 == 0 && (float)field_82249_h < (float)p.ticksExisted - 100.0F) {
            float f = props.pickExperience > 30 ? 1.0F : (float)props.pickExperience / 30.0F;
        }
    }
	
	private void addSwordLevel(int par1, EntityPlayer p) {
		ExtendedPlayer props = ExtendedPlayer.get(p);
		int field_82249_h;
        field_82249_h = p.ticksExisted;

		props.swordExperience += par1;

        if (props.swordExperience < 0) {
        	props.swordExperience = 0;
        	props.swordXP = 0.0F;
        	props.swordTotal = 0;
        }

        if (par1 > 0 && props.swordExperience % 5 == 0 && (float)field_82249_h < (float)p.ticksExisted - 100.0F) {
            float f = props.swordExperience > 30 ? 1.0F : (float)props.swordExperience / 30.0F;
        }
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
        }
    }
	
	public void addSwordExperience(int par1, EntityPlayer player) {
		ExtendedPlayer props = ExtendedPlayer.get(player);
        int j = Integer.MAX_VALUE - props.swordTotal;

        if (par1 > j) {
            par1 = j;
        }

        props.swordXP += (float)par1 / (float)normalCap();

        for (props.swordTotal += par1; props.swordXP >= 1.0F; props.swordXP /= (float)normalCap()) {
        	props.swordXP = (props.swordXP - 1.0F) * (float)18;
        	addSwordLevel(1, player);
        	addExperience((int)((float)DovakiinAPI.rand.nextInt(15) / 2), player);
        }
    }
	
	public void addHoeExperience(int par1, EntityPlayer player) {
		ExtendedPlayer props = ExtendedPlayer.get(player);
        int j = Integer.MAX_VALUE - props.hoeTotal;

        if (par1 > j) {
            par1 = j;
        }

        props.hoeXP += (float)par1 / (float)normalCap();

        for (props.hoeTotal += par1; props.hoeXP >= 1.0F; props.hoeXP /= (float)normalCap()) {
        	props.hoeXP = (props.hoeXP - 1.0F) * (float)18;
        	addHoeLevel(1, player);
        	addExperience((int)((float)DovakiinAPI.rand.nextInt(15) / 2), player);
        }
    }
	
	public void addPickaxeExperience(int par1, EntityPlayer player) {
		ExtendedPlayer props = ExtendedPlayer.get(player);
        int j = Integer.MAX_VALUE - props.pickTotal;

        if (par1 > j) {
            par1 = j;
        }

        props.pickXP += (float)par1 / (float)normalCap();

        for (props.pickTotal += par1; props.pickXP >= 1.0F; props.pickXP /= (float)normalCap()) {
        	props.pickXP = (props.pickXP - 1.0F) * (float)18;
        	addPickaxeLevel(1, player);
        	addExperience((int)((float)DovakiinAPI.rand.nextInt(15) / 2), player);
        }
    }
	
	public void addHeadExperience(int par1, EntityPlayer player) {
		ExtendedPlayer props = ExtendedPlayer.get(player);
        int j = Integer.MAX_VALUE - props.headTotal;

        if (par1 > j) {
            par1 = j;
        }

        props.headXP += (float)par1 / (float)normalCap();

        for (props.headTotal += par1; props.headXP >= 1.0F; props.headXP /= (float)normalCap()) {
        	props.headXP = (props.headXP - 1.0F) * (float)18;
        	addHeadLevel(1, player);
        	addExperience((int)((float)DovakiinAPI.rand.nextInt(15) / 2), player);
        }
    }
	
	public void addBowExperience(int par1, EntityPlayer player) {
		ExtendedPlayer props = ExtendedPlayer.get(player);
        int j = Integer.MAX_VALUE - props.bowTotal;

        if (par1 > j) {
            par1 = j;
        }

        props.bowXP += (float)par1 / (float)normalCap();

        for (props.bowTotal += par1; props.bowXP >= 1.0F; props.bowXP /= (float)normalCap()) {
        	props.bowXP = (props.bowXP - 1.0F) * (float)18;
        	addBowLevel(1, player);
        	addExperience((int)((float)DovakiinAPI.rand.nextInt(15) / 2), player);
        }
    }
	
	public static int xpBarCap(EntityPlayer player) {
        return experienceLevel >= 10 ? 2 : experienceLevel >= 40 ? 5 : experienceLevel >= 90 ? 8 : experienceLevel >= 150 ? 10 : experienceLevel >= 200 ? 15 : 17;
    }
	
	public static int normalCap(){
		return 20;
	}
	
	public static void resetPlayer(EntityPlayer p){
		coins = 0;
		experienceLevel = 0;
        experienceTotal = 0;
        bowExperience = 0;
        bowTotal = 0;
        headExperience = 0; 
        headTotal = 0;
        hoeExperience = 0;
        hoeTotal = 0;
        pickExperience = 0;
        pickTotal = 0;
        swordExperience = 0;
        swordTotal = 0;
		levelXP = 0;
		swordXP = 0;
		bowXP = 0;
		headXP = 0;
		pickXP = 0;
		hoeXP = 0;
	}
	
	public static void setLevel(int l){
		experienceLevel = l;
	}
	
	public static int getLevel() {
		return experienceLevel;
	}

	public static void setCoins(int c) {
		coins += c;
	}
	
	public static int getSwordLevel() {
		return swordExperience;
	}
	
	public static int getBowLevel(){
		return bowExperience;
	}
	
	public static int getHeadLevel(){
		return headExperience;
	}
	
	public static int getPickaxeLevel(){
		return pickExperience;
	}
	
	public static int getHoeLevel(){
		return hoeExperience;
	}

	public static int getCoins(EntityPlayer player) {
		return coins <= -1 ? coins = 0 : coins;
	}
}