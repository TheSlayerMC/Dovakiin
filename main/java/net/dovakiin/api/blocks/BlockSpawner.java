package net.dovakiin.api.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dovakiin.Dovakiin;
import net.dovakiin.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSpawner extends BlockContainer {

	private String name;
    private Random rand = new Random();

	public BlockSpawner(String name) {
		super(Material.rock);
		setCreativeTab(Dovakiin.spawner);
		LangRegistry.addBlock(this);
		disableStats();
		setHardness(2.0F);
		this.name = name;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var1) {
		TileEntityMobSpawner spawner = new TileEntityMobSpawner();
		spawner.func_145881_a().setEntityName(name);
		return spawner;
	}
	
    public Item getItemDropped(int i, Random r, int j) {
        return null;
    }

    public int quantityDropped(Random r) {
        return 0;
    }
	
    @Override
    public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
        return 17 + rand.nextInt(17) + rand.nextInt(17);
    }
	
    public boolean isOpaqueCube() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World w, int x, int y, int z) {
        return Item.getItemFromBlock(this);
    }
    
    @Override
    protected boolean canSilkHarvest() {
    	return true;
    }
    
	public Block registerBlock(String name){
		GameRegistry.registerBlock(this, name);
		setBlockName(name);
		setBlockTextureName("mob_spawner");
		return this;
	}
}