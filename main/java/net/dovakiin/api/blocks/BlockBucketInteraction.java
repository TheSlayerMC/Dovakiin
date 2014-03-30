package net.dovakiin.api.blocks;

import net.dovakiin.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBucketInteraction extends ModBlock{

	@SideOnly(Side.CLIENT)
	private IIcon water, lava, empty, milk;

	public BlockBucketInteraction() {
		super(Material.rock);
		setHardness(1.0F);
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		if(par1 == 4)
			return water;
		if(par1 == 2)
			return lava;
		if(par1 == 3)
			return milk;
		if(par1 == 5)
			return empty;
		return blockIcon;
	}

	@Override
	public void registerBlockIcons(IIconRegister i) {
		water = i.registerIcon(Utils.PREFIX + "bucketFiller_water");
		lava  = i.registerIcon(Utils.PREFIX + "bucketFiller_lava");
		empty = i.registerIcon(Utils.PREFIX + "bucketFiller_empty");
		milk = i.registerIcon(Utils.PREFIX + "bucketFiller_milk");
		blockIcon = i.registerIcon("stone");
	}

	@Override
	public Block registerBlock(String name) {
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float x1, float y1, float z1) {
		int es = p.inventory.getFirstEmptyStack();
		ItemStack is = null;
		if(p.getHeldItem() != null && p.getHeldItem().getItem() == Items.bucket){
			p.getHeldItem().stackSize--;
			is = new ItemStack(Items.water_bucket);
			p.inventory.setInventorySlotContents(es, is);
			return true;
		}
		if(p.getHeldItem() != null && p.getHeldItem().getItem() == Items.water_bucket){
			p.getHeldItem().stackSize--;
			is = new ItemStack(Items.lava_bucket);
			p.inventory.setInventorySlotContents(es, is);
			return true;
		}
		if(p.getHeldItem() != null && p.getHeldItem().getItem() == Items.lava_bucket){
			p.getHeldItem().stackSize--;
			is = new ItemStack(Items.milk_bucket);
			p.inventory.setInventorySlotContents(es, is);
			return true;
		}
		if(p.getHeldItem() != null && p.getHeldItem().getItem() == Items.milk_bucket){
			p.getHeldItem().stackSize--;
			is = new ItemStack(Items.bucket);
			p.inventory.setInventorySlotContents(es, is);
			return true;
		}
		return false;
	}
}