package net.dovakiin.generation;

import java.util.Random;

import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWaterBerry extends WorldGenerator {

	@Override
	public boolean generate(World world, Random var2, int x, int y, int z) {
		for(int i = 0; i < 40; i++){
			if ((world.getBlock(x, y + 1, z) == Blocks.water) && (world.getBlock(x, y - 1, z) == Blocks.gravel) 
					|| (world.getBlock(x, y + 1, z) == Blocks.water) && (world.getBlock(x, y - 1, z) == Blocks.dirt) 
					|| (world.getBlock(x, y + 1, z) == Blocks.water) && (world.getBlock(x, y - 1, z) == Blocks.sand)) {
				world.setBlock(x, y, z, Dovakiin.waterBerryBush);
				return true;
			}
		}
		return false;
	}
}