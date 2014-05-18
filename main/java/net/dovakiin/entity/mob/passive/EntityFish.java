package net.dovakiin.entity.mob.passive;

import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityFish extends EntityWaterMob {

	public EntityFish(World w) {
		super(w);
		setSize(0.5F, 0.5F);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2.0D);
	}

	@Override
	protected String getLivingSound() {
		return super.getLivingSound();
	}

	@Override
	protected String getHurtSound() {
		return super.getHurtSound();
	}

	@Override
	protected String getDeathSound() {
		return super.getDeathSound();
	}

	protected Item dropItem() {
		return Items.fish;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		Item i = dropItem();
		this.dropItem(i, 1);
	}

	public boolean isInWater() {
		return this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.6000000238418579D, 0.0D), Material.water, this);
	}
}