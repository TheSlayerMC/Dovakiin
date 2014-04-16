package net.dovakiin.network;

import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.event.level_system.LevelHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties{

	public static EntityPlayer player;
	public final static String EXTENDED_PROPERTIES_NAME = "ExtendedPlayer";

	public static int maxStatLevel = 10, bowTotal, bowExperience, headTotal, headExperience, hoeTotal, hoeExperience, pickTotal, pickExperience, swordTotal, swordExperience;
	public static float swordXP, bowXP, headXP, pickXP, hoeXP;

	public ExtendedPlayer() { }

	@Override
	public void saveNBTData(NBTTagCompound n) {
		NBTTagCompound prop = new NBTTagCompound();
		prop.setFloat("SwordXP", swordXP);
		prop.setFloat("BowXP", bowXP);
		prop.setFloat("HeadXP", headXP);
		prop.setFloat("PickXP", pickXP);
		prop.setFloat("HoeXP", hoeXP);
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
		this.swordXP = prop.getFloat("SwordXP");
		this.bowXP = prop.getFloat("BowXP");
		this.headXP = prop.getFloat("HeadXP");
		this.pickXP = prop.getFloat("PickXP");
		this.hoeXP = prop.getFloat("HoeXP");
	}

	@Override
	public void init(Entity e, World n) { }
	
	private void addHeadLevel(int par1, EntityPlayer p) {
		int field_82249_h;
        field_82249_h = p.ticksExisted;

		headExperience += par1;

        if (headExperience < 0) {
        	headExperience = 0;
        	headXP = 0.0F;
        	headTotal = 0;
        }

        if (par1 > 0 && headExperience % 5 == 0 && (float)field_82249_h < (float)p.ticksExisted - 100.0F) {
            float f = headExperience > 30 ? 1.0F : (float)headExperience / 30.0F;
        }
        DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "[" +  DovakiinAPI.BLUE + "Dovakiin" +  DovakiinAPI.AQUA + "] " +  DovakiinAPI.AQUA + "Your wither decapitation level has gone up!");
    }
	
	private void addBowLevel(int par1, EntityPlayer p) {
		int field_82249_h;
        field_82249_h = p.ticksExisted;

		bowExperience += par1;

        if (bowExperience < 0) {
        	bowExperience = 0;
        	bowXP = 0.0F;
        	bowTotal = 0;
        }

        if (par1 > 0 && bowExperience % 5 == 0 && (float)field_82249_h < (float)p.ticksExisted - 100.0F) {
            float f = bowExperience > 30 ? 1.0F : (float)bowExperience / 30.0F;
        }
        DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "[" +  DovakiinAPI.BLUE + "Dovakiin" +  DovakiinAPI.AQUA + "] " +  DovakiinAPI.AQUA + "Your bow level has gone up!");
    }
	
	private void addHoeLevel(int par1, EntityPlayer p) {
		int field_82249_h;
        field_82249_h = p.ticksExisted;

		hoeExperience += par1;

        if (hoeExperience < 0) {
        	hoeExperience = 0;
        	hoeXP = 0.0F;
        	hoeTotal = 0;
        }

        if (par1 > 0 && hoeExperience % 5 == 0 && (float)field_82249_h < (float)p.ticksExisted - 100.0F) {
            float f = hoeExperience > 30 ? 1.0F : (float)hoeExperience / 30.0F;
        }
        DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "[" +  DovakiinAPI.BLUE + "Dovakiin" +  DovakiinAPI.AQUA + "] " +  DovakiinAPI.AQUA + "Your hoe level has gone up!");
    }
	
	private void addPickaxeLevel(int par1, EntityPlayer p) {
		int field_82249_h;
        field_82249_h = p.ticksExisted;

		pickExperience += par1;

        if (pickExperience < 0) {
        	pickExperience = 0;
        	pickXP = 0.0F;
        	pickTotal = 0;
        }

        if (par1 > 0 && pickExperience % 5 == 0 && (float)field_82249_h < (float)p.ticksExisted - 100.0F) {
            float f = pickExperience > 30 ? 1.0F : (float)pickExperience / 30.0F;
        }
        DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "[" +  DovakiinAPI.BLUE + "Dovakiin" +  DovakiinAPI.AQUA + "] " +  DovakiinAPI.AQUA + "Your pickaxe level has gone up!");
    }
	
	private void addSwordLevel(int par1, EntityPlayer p) {
		int field_82249_h;
        field_82249_h = p.ticksExisted;

		swordExperience += par1;

        if (swordExperience < 0) {
        	swordExperience = 0;
        	swordXP = 0.0F;
        	swordTotal = 0;
        }

        if (par1 > 0 && swordExperience % 5 == 0 && (float)field_82249_h < (float)p.ticksExisted - 100.0F) {
            float f = swordExperience > 30 ? 1.0F : (float)swordExperience / 30.0F;
        }
        
        DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "[" +  DovakiinAPI.BLUE + "Dovakiin" +  DovakiinAPI.AQUA + "] " +  DovakiinAPI.AQUA + "Your sword level has gone up!");
    }
	
	public void addSwordExperience(int par1, EntityPlayer player) {
        int j = Integer.MAX_VALUE - swordTotal;

        if (par1 > j) {
            par1 = j;
        }

        swordXP += (float)par1 / (float)normalCap();

        for (swordTotal += par1; swordXP >= 1.0F; swordXP /= (float)normalCap()) {
        	swordXP = (swordXP - 1.0F) * (float)18;
        	addSwordLevel(1, player);
        	LevelHelper.addExperience((int)((float)DovakiinAPI.rand.nextInt(15) / 2), player);
        }
    }
	
	public void addHoeExperience(int par1, EntityPlayer player) {
        int j = Integer.MAX_VALUE - hoeTotal;

        if (par1 > j) {
            par1 = j;
        }

        hoeXP += (float)par1 / (float)normalCap();

        for (hoeTotal += par1; hoeXP >= 1.0F; hoeXP /= (float)normalCap()) {
        	hoeXP = (hoeXP - 1.0F) * (float)18;
        	addHoeLevel(1, player);
        	LevelHelper.addExperience((int)((float)DovakiinAPI.rand.nextInt(15) / 2), player);
        }
    }
	
	public void addPickaxeExperience(int par1, EntityPlayer player) {
        int j = Integer.MAX_VALUE - pickTotal;

        if (par1 > j) {
            par1 = j;
        }

        pickXP += (float)par1 / (float)normalCap();

        for (pickTotal += par1; pickXP >= 1.0F; pickXP /= (float)normalCap()) {
        	pickXP = (pickXP - 1.0F) * (float)18;
        	addPickaxeLevel(1, player);
        	LevelHelper.addExperience((int)((float)DovakiinAPI.rand.nextInt(15) / 2), player);
        }
    }
	
	public void addHeadExperience(int par1, EntityPlayer player) {
        int j = Integer.MAX_VALUE - headTotal;

        if (par1 > j) {
            par1 = j;
        }

        headXP += (float)par1 / (float)normalCap();

        for (headTotal += par1; headXP >= 1.0F; headXP /= (float)normalCap()) {
        	headXP = (headXP - 1.0F) * (float)18;
        	addHeadLevel(1, player);
        	LevelHelper.addExperience((int)((float)DovakiinAPI.rand.nextInt(15) / 2), player);
        }
    }
	
	public void addBowExperience(int par1, EntityPlayer player) {
        int j = Integer.MAX_VALUE - bowTotal;

        if (par1 > j) {
            par1 = j;
        }

        bowXP += (float)par1 / (float)normalCap();

        for(bowTotal += par1; bowXP >= 1.0F; bowXP /= (float)normalCap()) {
        	bowXP = (bowXP - 1.0F) * (float)18;
        	addBowLevel(1, player);
        	LevelHelper.addExperience((int)((float)DovakiinAPI.rand.nextInt(15) / 2), player);
        }
    }
	
	public static int normalCap(){
		return 23;
	}
	
	public static void resetPlayer(EntityPlayer p){
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
		swordXP = 0;
		bowXP = 0;
		headXP = 0;
		pickXP = 0;
		hoeXP = 0;
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
}