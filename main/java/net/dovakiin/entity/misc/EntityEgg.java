package net.dovakiin.entity.misc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.client.GuiHandler;
import net.dovakiin.entity.mob.boss.EntityGiantCreeper;
import net.dovakiin.entity.mob.boss.EntityGiantZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityEgg extends EntityModMob {

	public int ticks;

	public EntityEgg(World par1World) {
		super(par1World);
		setSize(0.4F, 0.5F);
		ticks = rand.nextInt(100) + 10;
		isImmuneToFire = true;
		this.experienceValue = 0;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected boolean isMovementCeased() {
		return true;
	}

	@Override
	protected boolean isMovementBlocked() {
		return true;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}
	
	@Override
	public boolean canPickUpLoot() {
		return false;
	}
	
	@Override
	public boolean allowLeashing() {
		return false;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1) {
        return 15728880;
    }

    @Override
    public float getBrightness(float par1) {
        return 1.0F;
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		return !(par1DamageSource.getSourceOfDamage() instanceof EntityPlayer);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		boolean light = worldObj.getBlockLightValue((int)posX, (int)posY, (int)posZ) >= 4;
		if(ticks == 0 && !worldObj.isRemote && light){
			EntitySkeleton e = new EntitySkeleton(worldObj);
            e.setLocationAndAngles(posX, posY, posZ, 360.0F, 0.0F);
			//worldObj.spawnEntityInWorld(e);
			//this.setDead();
		}
		ticks--;
	}
	
	@Override
	protected void fall(float par1) { }
	
	@Override
	public void onDeath(DamageSource par1DamageSource) {
		super.onDeath(par1DamageSource);
		if(!(par1DamageSource.getSourceOfDamage() instanceof EntityPlayer))
			DovakiinAPI.addChatMessage("Your egg has hatched!");
	}
}