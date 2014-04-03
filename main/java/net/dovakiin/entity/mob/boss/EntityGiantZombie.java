package net.dovakiin.entity.mob.boss;

import java.util.Calendar;

import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGiantZombie extends EntityMob implements IBossDisplayData {
	
	private int tick;
	public EntityGiantZombie(World par1World) {
		super(par1World);
		tick = 30;
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(2F, 6F);
		this.experienceValue = 1000;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
	}

	protected String getLivingSound() {
		return "mob.zombie.say";
	}

	protected String getHurtSound() {
		return "mob.zombie.hurt";
	}

	protected String getDeathSound() {
		return "mob.zombie.death";
	}

	protected void func_145780_a(int x, int y, int z, Block b) {
		this.playSound("mob.zombie.step", 0.15F, 1.0F);
	}

	protected Item getDropItem() {
		return Items.rotten_flesh;
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}
	
	@Override
	public void onLivingUpdate() {
		if(tick == 0 && !worldObj.isRemote){
			EntityZombie z = new EntityZombie(worldObj);
            z.setLocationAndAngles(posX - 3, posY, posZ, this.rand.nextFloat() * 360.0F, 0.0F);
            EntityZombie z1 = new EntityZombie(worldObj);
            z1.setLocationAndAngles(posX + 3, posY, posZ, this.rand.nextFloat() * 360.0F, 0.0F);
            EntityZombie z2 = new EntityZombie(worldObj);
            z2.setLocationAndAngles(posX, posY, posZ + 3, this.rand.nextFloat() * 360.0F, 0.0F);
            EntityZombie z3 = new EntityZombie(worldObj);
            z3.setLocationAndAngles(posX, posY, posZ - 3, this.rand.nextFloat() * 360.0F, 0.0F);
            
            EntityZombie z4 = new EntityZombie(worldObj);
            z4.setLocationAndAngles(posX + 2, posY, posZ - 2, this.rand.nextFloat() * 360.0F, 0.0F);
            EntityZombie z5 = new EntityZombie(worldObj);
            z5.setLocationAndAngles(posX - 2, posY, posZ + 2, this.rand.nextFloat() * 360.0F, 0.0F);
            EntityZombie z6 = new EntityZombie(worldObj);
            z6.setLocationAndAngles(posX + 2, posY, posZ + 2, this.rand.nextFloat() * 360.0F, 0.0F);
            EntityZombie z7 = new EntityZombie(worldObj);
            z7.setLocationAndAngles(posX - 2, posY, posZ - 2, this.rand.nextFloat() * 360.0F, 0.0F);
            
            this.worldObj.spawnEntityInWorld(z);
            this.worldObj.spawnEntityInWorld(z1);
            this.worldObj.spawnEntityInWorld(z2);
            this.worldObj.spawnEntityInWorld(z3);
            this.worldObj.spawnEntityInWorld(z4);
            this.worldObj.spawnEntityInWorld(z5);
            this.worldObj.spawnEntityInWorld(z6);
            this.worldObj.spawnEntityInWorld(z7);

			tick = 300;
		}
		tick--;
		addRandomArmor();
		super.onLivingUpdate();
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	protected void addRandomArmor() {
		this.setCurrentItemOrArmor(0, new ItemStack(Items.diamond_sword));
		this.setCurrentItemOrArmor(4, new ItemStack(Items.golden_helmet));
	}

	public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData) {
		par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);

		this.addRandomArmor();
		this.enchantEquipment();

		//this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ));

		if(this.getEquipmentInSlot(4) == null) {
			Calendar calendar = this.worldObj.getCurrentDate();

			if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
				this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
				this.equipmentDropChances[4] = 0.0F;
			}
		}
		return par1EntityLivingData;
	}

	protected void dropFewItems(boolean par1, int par2) {
		this.dropItem(Dovakiin.zombieEssence, 1);
	}

	protected void dropRareDrop(int par1) {
		switch (this.rand.nextInt(3)) {
		case 0:
			this.dropItem(Items.iron_ingot, 1);
			break;
		case 1:
			this.dropItem(Items.carrot, 1);
			break;
		case 2:
			this.dropItem(Items.potato, 1);
		}
	}
}