package net.dovakiin.entity.mob.boss;

import java.util.Calendar;

import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGiantSkeleton extends EntityMob implements IRangedAttackMob, IBossDisplayData {

	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 10, 60);
	private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);
	private int tick;
	
	public EntityGiantSkeleton(World par1World) {
		super(par1World);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.experienceValue = 1000;
		if (par1World != null && !par1World.isRemote) {
			this.setCombatTask();
		}
		setSize(2F, 6F);
		tick = 50;
	}

	@Override
	public void onLivingUpdate() {
		if(tick == 0 && !worldObj.isRemote){
			EntitySkeleton z = new EntitySkeleton(worldObj);
            z.setLocationAndAngles(posX + 3, posY, posZ, this.rand.nextFloat() * 360.0F, 0.0F);
            EntitySkeleton z1 = new EntitySkeleton(worldObj);
            z1.setLocationAndAngles(posX - 3, posY, posZ, this.rand.nextFloat() * 360.0F, 0.0F);
            EntitySkeleton z2 = new EntitySkeleton(worldObj);
            z2.setLocationAndAngles(posX, posY, posZ + 3, this.rand.nextFloat() * 360.0F, 0.0F);
            EntitySkeleton z3 = new EntitySkeleton(worldObj);
            z3.setLocationAndAngles(posX, posY, posZ - 3, this.rand.nextFloat() * 360.0F, 0.0F);
            
            this.worldObj.spawnEntityInWorld(z);
            this.worldObj.spawnEntityInWorld(z1);
            this.worldObj.spawnEntityInWorld(z2);
            this.worldObj.spawnEntityInWorld(z3);

			tick = 300;
		}
		tick--;
		super.onLivingUpdate();
	}
	
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30D);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(13, new Byte((byte)0));
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected String getLivingSound() {
		return "mob.skeleton.say";
	}

	protected String getHurtSound() {
		return "mob.skeleton.hurt";
	}

	protected String getDeathSound() {
		return "mob.skeleton.death";
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		this.playSound("mob.skeleton.step", 0.15F, 1.0F);
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	public void onDeath(DamageSource d) {
		super.onDeath(d);
		if(d.getSourceOfDamage() instanceof EntityPlayer){
			int levels = 0;
			if(Dovakiin.level >= 16){
				levels = (int)(rand.nextInt(1) + 1.7);
			} else {
				levels = rand.nextInt(4) + 1;
			}
			Dovakiin.level += levels;
			DovakiinAPI.addChatMessage(DovakiinAPI.BLUE, Minecraft.getMinecraft().thePlayer.getDisplayName() + "Has killed the Giant Skeleton and gained " + levels + "levels");
		}
	}

	protected void dropFewItems(boolean par1, int par2) {
		this.dropItem(Dovakiin.skeletonEssence, 1);
	}

	protected void addRandomArmor() {
		super.addRandomArmor();
		this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
	}

	public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData) {
		par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);

		this.tasks.addTask(4, this.aiArrowAttack);
		this.addRandomArmor();
		this.enchantEquipment();

		this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ));

		if (this.getEquipmentInSlot(4) == null)
		{
			Calendar calendar = this.worldObj.getCurrentDate();

			if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
			{
				this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
				this.equipmentDropChances[4] = 0.0F;
			}
		}

		return par1EntityLivingData;
	}

	public void setCombatTask() {
		this.tasks.removeTask(this.aiAttackOnCollide);
		this.tasks.removeTask(this.aiArrowAttack);
		ItemStack itemstack = this.getHeldItem();

		if (itemstack != null && itemstack.getItem() == Items.bow) {
			this.tasks.addTask(4, this.aiArrowAttack);
		}
	}

	public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
		EntityArrow entityarrow = new EntityArrow(this.worldObj, this, par1EntityLivingBase, 1.6F, 12.0F);
		int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
		int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());

		if (i > 0) {
			entityarrow.setDamage(entityarrow.getDamage() + (double)i * 0.5D + 0.5D);
		}

		if (j > 0) {
			entityarrow.setKnockbackStrength(j);
		}

		if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0) {
			entityarrow.setFire(100);
		}

		this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.worldObj.spawnEntityInWorld(entityarrow);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
		super.setCurrentItemOrArmor(par1, par2ItemStack);

		if (!this.worldObj.isRemote && par1 == 0) {
			this.setCombatTask();
		}
	}

	public double getYOffset() {
		return super.getYOffset() - 0.5D;
	}
}