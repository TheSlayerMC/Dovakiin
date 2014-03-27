package net.dovakiin.entity.misc;

import net.dovakiin.Dovakiin;
import net.dovakiin.client.Sounds;
import net.dovakiin.network.PacketOpenGui;
import net.dovakiin.util.Utils;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityEgg extends EntityModMob {

	protected int ticks, guiID;
	public boolean light = worldObj.getBlockLightValue((int)posX, (int)posY, (int)posZ) >= 4;

	public EntityEgg(World par1World, int tick, int GUI) {
		super(par1World);
		setSize(0.4F, 0.5F);
		ticks = tick;
		guiID = GUI;
		isImmuneToFire = true;
		this.experienceValue = 0;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
	}

	public String playSound(String sounds){
		return Sounds.playSound(Utils.PREFIX + sounds, worldObj, this);
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
	protected boolean interact(EntityPlayer par1EntityPlayer) {
		if(ticks != 0){
			Dovakiin.packetHandler.sendToServer(new PacketOpenGui().setID(guiID));
			return true;
		}
		return super.interact(par1EntityPlayer);
	}

	@Override
	protected void fall(float par1) { }

	@Override
	public void onDeath(DamageSource par1DamageSource) {
		super.onDeath(par1DamageSource);
	}
}