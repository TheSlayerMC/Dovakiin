package net.dovakiin.api.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.dovakiin.Dovakiin;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockBerryPlant extends ModBlock implements IShearable {
	
	public BlockBerryPlant() {
		super(Material.leaves);
		setCreativeTab(Dovakiin.blocks);
		setHardness(0.4F);
		setTickRandomly(true);
		setStepSound(Block.soundTypeGrass);
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World w, int x, int y, int z, int meta) {
		if(this == Dovakiin.greenBerryBushRipe) {
			w.setBlock(x, y, z, Dovakiin.greenBerryBush);
		}
		if(this == Dovakiin.waterBerryBushRipe) {
			w.setBlock(x, y, z, Dovakiin.waterBerryBush);
		}
		if(this == Dovakiin.desertBerryBushRipe) {
			w.setBlock(x, y, z, Dovakiin.desertBerryBush);
		}
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		player.attackEntityFrom(DamageSource.cactus, 1);
	}
	
	@Override
	public void updateTick(World w, int x, int y, int z, Random r) {
		if(r.nextInt(2) == 0 && w.getBlock(x, y, z) == Dovakiin.greenBerryBush){
			w.setBlock(x, y, z, Dovakiin.greenBerryBushRipe);
		}
		if(r.nextInt(2) == 0 && w.getBlock(x, y, z) == Dovakiin.waterBerryBush){
			w.setBlock(x, y, z, Dovakiin.waterBerryBushRipe);
		}
		if(r.nextInt(2) == 0 && w.getBlock(x, y, z) == Dovakiin.desertBerryBush){
			w.setBlock(x, y, z, Dovakiin.desertBerryBushRipe);
		}
	}
	
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		if (this == Dovakiin.greenBerryBushRipe) {
			return Dovakiin.greenBerry;
		}
		if (this == Dovakiin.waterBerryBushRipe) {
			return Dovakiin.waterBerry;
		}
		if (this == Dovakiin.desertBerryBushRipe) {
			return Dovakiin.desertBerry;
		} else {
			return null;
		}
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public void grow(World w, int x, int y, int z){
		if(this == Dovakiin.greenBerryBush){
			w.setBlock(x, y, z, Dovakiin.greenBerryBushRipe);
		}
		if(this == Dovakiin.desertBerryBush){
			w.setBlock(x, y, z, Dovakiin.desertBerryBushRipe);
		}
	}
	
	@Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z){
        return !(world.getBlock(x, y, z) == Dovakiin.greenBerryBushRipe) || !(world.getBlock(x, y, z) == Dovakiin.waterBerryBushRipe) || !(world.getBlock(x, y, z) == Dovakiin.desertBerryBushRipe);
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune){
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
        return ret;
    }
}