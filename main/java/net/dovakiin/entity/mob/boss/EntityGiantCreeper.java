package net.dovakiin.entity.mob.boss;

import net.dovakiin.Dovakiin;
import net.dovakiin.entity.mob.EntityAICreeperSwell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityGiantCreeper extends EntityMob implements IBossDisplayData {

	private int lastActiveTime;
	private int timeSinceIgnited;
	private int fuseTime = 30;
	private int explosionRadius = 6;
	private int tick;

	public EntityGiantCreeper(World par1World) {
		super(par1World);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAICreeperSwell(this));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, false));
		this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		setSize(2F, 4F);
		tick = 70;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}

	public boolean isAIEnabled() {
		return true;
	}

	public int getMaxSafePointTries() {
		return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
	}

	protected void fall(float par1) {
		super.fall(par1);
		this.timeSinceIgnited = (int)((float)this.timeSinceIgnited + par1 * 1.5F);

		if (this.timeSinceIgnited > this.fuseTime - 5) {
			this.timeSinceIgnited = this.fuseTime - 5;
		}
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte) - 1));
		this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("Fuse", (short)this.fuseTime);
		par1NBTTagCompound.setByte("ExplosionRadius", (byte)this.explosionRadius);
		par1NBTTagCompound.setBoolean("ignited", this.func_146078_ca());
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("Fuse", 99)) {
			this.fuseTime = par1NBTTagCompound.getShort("Fuse");
		}

		if (par1NBTTagCompound.hasKey("ExplosionRadius", 99)) {
			this.explosionRadius = par1NBTTagCompound.getByte("ExplosionRadius");
		}

		if (par1NBTTagCompound.getBoolean("ignited")) {
			this.func_146079_cb();
		}
	}

	public void onUpdate() {
		this.lastActiveTime = this.timeSinceIgnited;

		if (this.func_146078_ca()) {
			this.setCreeperState(1);
		}

		int i = this.getCreeperState();

		if (i > 0 && this.timeSinceIgnited == 0) {
			this.playSound("creeper.primed", 1.0F, 0.5F);
		}

		this.timeSinceIgnited += i;

		if (this.timeSinceIgnited < 0) {
			this.timeSinceIgnited = 0;
		}

		if (this.timeSinceIgnited >= this.fuseTime) {
			this.timeSinceIgnited = this.fuseTime;
			this.func_146077_cc();
			timeSinceIgnited--;
		}
		super.onUpdate();
	}
	
	@Override
	public void onLivingUpdate() {
		if(tick == 0 && !worldObj.isRemote){
			EntityCreeper z = new EntityCreeper(worldObj);
            z.setLocationAndAngles(posX + 3, posY, posZ, this.rand.nextFloat() * 360.0F, 0.0F);
            EntityCreeper z1 = new EntityCreeper(worldObj);
            z1.setLocationAndAngles(posX - 3, posY, posZ, this.rand.nextFloat() * 360.0F, 0.0F);
            EntityCreeper z2 = new EntityCreeper(worldObj);
            z2.setLocationAndAngles(posX, posY, posZ + 3, this.rand.nextFloat() * 360.0F, 0.0F);
            EntityCreeper z3 = new EntityCreeper(worldObj);
            z3.setLocationAndAngles(posX, posY, posZ - 3, this.rand.nextFloat() * 360.0F, 0.0F);
            
            this.worldObj.spawnEntityInWorld(z);
            this.worldObj.spawnEntityInWorld(z1);
            this.worldObj.spawnEntityInWorld(z2);
            this.worldObj.spawnEntityInWorld(z3);

			tick = 400;
		}
		tick--;
		super.onLivingUpdate();
	}

	protected String getHurtSound() {
		return "mob.creeper.say";
	}

	protected String getDeathSound()
	{
		return "mob.creeper.death";
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public float getCreeperFlashIntensity(float par1) {
		return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * par1) / (float)(this.fuseTime - 2);
	}

	public int getCreeperState() {
		return this.dataWatcher.getWatchableObjectByte(16);
	}

	public void setCreeperState(int par1) {
		this.dataWatcher.updateObject(16, Byte.valueOf((byte)par1));
	}

	private void func_146077_cc() {
		if (!this.worldObj.isRemote) {
			boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius, flag);
		}
	}
	
	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	@Override
	protected void dropFewItems(boolean par1, int par2) {
		this.dropItem(Dovakiin.creeperEssence, 1);
	}

	public boolean func_146078_ca()
	{
		return this.dataWatcher.getWatchableObjectByte(18) != 0;
	}

	public void func_146079_cb()
	{
		this.dataWatcher.updateObject(18, Byte.valueOf((byte)1));
	}
}