package net.dovakiin.entity.mob.npc;

import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.client.GuiHandler;
import net.dovakiin.entity.misc.EntityModMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMerchent extends EntityModMob{

	private int tick;

	public EntityMerchent(World par1World) {
		super(par1World);
		isImmuneToFire = true;
		this.experienceValue = 0;
		tick = 1000;
	}

	@Override
	public boolean allowLeashing() {
		return false;
	}

	@Override
	protected void fall(float par1) { }

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		addRandomArmor();
		tick--;
	}

	@Override
	protected void addRandomArmor() {
		if(tick <= 1000)
			this.setCurrentItemOrArmor(0, new ItemStack(Items.nether_star));
		if(tick <= 900)
			this.setCurrentItemOrArmor(0, new ItemStack(Items.diamond));
		if(tick <= 800)
			this.setCurrentItemOrArmor(0, new ItemStack(Items.arrow));
		if(tick <= 700)
			this.setCurrentItemOrArmor(0, new ItemStack(Items.bed));
		if(tick <= 600)
			this.setCurrentItemOrArmor(0, new ItemStack(Items.boat));
		if(tick <= 500)
			this.setCurrentItemOrArmor(0, new ItemStack(Items.bone));
		if(tick <= 400)
			this.setCurrentItemOrArmor(0, new ItemStack(Items.book));
		if(tick <= 300)
			this.setCurrentItemOrArmor(0, new ItemStack(Items.egg));
		if(tick <= 200)
			this.setCurrentItemOrArmor(0, new ItemStack(Items.gold_ingot));
		if(tick <= 100)
			this.setCurrentItemOrArmor(0, new ItemStack(Items.feather));
		if(tick <= 0)
			this.setCurrentItemOrArmor(0, new ItemStack(Items.experience_bottle));
		if(tick == 0)
			tick = 1000;
	}
	
	@Override
	protected boolean interact(EntityPlayer par1EntityPlayer) {
		if(!par1EntityPlayer.worldObj.isRemote){
			switch(rand.nextInt(5)){
			case 0:
				par1EntityPlayer.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "Merchent: " + DovakiinAPI.YELLOW + "Welcome!"));
				break;
			case 1:
				par1EntityPlayer.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "Merchent: " + DovakiinAPI.YELLOW + "We have alot to choose from!"));
				break;
			case 2:
				par1EntityPlayer.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "Merchent: " + DovakiinAPI.YELLOW + "Fancy what im holding?"));
				break;
			case 3:
				par1EntityPlayer.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "Merchent: " + DovakiinAPI.YELLOW + "We have shiny things!"));
				break;
			case 4:
				par1EntityPlayer.addChatMessage(DovakiinAPI.addChatMessage(DovakiinAPI.AQUA + "Merchent: " + DovakiinAPI.YELLOW + "Take a look around?"));
				break;
			}
			DovakiinAPI.openGui(GuiHandler.startingBook);
			return true;
		}
		return super.interact(par1EntityPlayer);
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
}