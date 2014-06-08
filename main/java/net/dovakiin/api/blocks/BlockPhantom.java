package net.dovakiin.api.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPhantom extends ModBlock{

	private Block turn, tex;

	public BlockPhantom(Block turnInto, Block texture) {
		super(Material.rock);
		turn = turnInto;
		tex = texture;
		setTickRandomly(true);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int j) {
		return tex.getBlockTextureFromSide(side);
	}

	@Override
	public void updateTick(World w, int x, int y, int z, Random r) { 
		check(w, x, y, z);
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, Block b) {
		check(w, x, y, z);
	}

	public void check(World w, int x, int y, int z){
		if(w.isBlockIndirectlyGettingPowered(x, y, z) && !w.isRemote){
			w.setBlock(x, y, z, turn);
		}
		
		boolean isTurnIntoNear = w.getBlock(x, y, z + 1) == turn || w.getBlock(x + 1, y, z) == turn || w.getBlock(x + 1, y, z + 1) == turn ||
				w.getBlock(x - 1, y, z) == turn || w.getBlock(x, y, z - 1) == turn || w.getBlock(x - 1, y, z - 1) == turn || w.getBlock(x + 2, y, z + 1) == turn ||
						w.getBlock(x + 1, y, z + 2) == turn || w.getBlock(x - 2, y, z - 2) == turn || w.getBlock(x + 2, y, z - 2) == turn || w.getBlock(x + 2, y, z + 2) == turn;
		
		if(isTurnIntoNear && !w.isRemote) {
			w.setBlock(x, y, z, turn);
		w.setBlock(x, y - 1, z, turn);
		}
	}
}