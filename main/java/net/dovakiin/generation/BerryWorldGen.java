package net.dovakiin.generation;

import java.util.Random;

import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class BerryWorldGen implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case -1: generateNether(world, random, chunkX * 16, chunkZ * 16);
		case 0: generateOverworld(world, random, chunkX * 16, chunkZ * 16);
		case 1: generateEnd(world, random, chunkX * 16, chunkZ * 16); 
		}
	}

	private void generateOverworld(World world, Random random, int x, int z) {

		BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(x + 16, z + 16);
		
		for(int i = 0; i < 1; i++){
			int xPos = x + random.nextInt(16) + 8;
			int yPos = random.nextInt(250);
			int zPos = z + random.nextInt(16) + 8;
			(new WorldGenBerrys(Dovakiin.greenBerryBush, Blocks.grass)).generate(world, random, xPos, yPos, zPos);
		}

		if(biome == BiomeGenBase.desert || biome == BiomeGenBase.desertHills){
			for(int i = 0; i < 1; i++){
				int xPos = x + random.nextInt(16) + 8;
				int yPos = random.nextInt(250);
				int zPos = z + random.nextInt(16) + 8;
				(new WorldGenBerrys(Dovakiin.desertBerryBush, Blocks.sand)).generate(world, random, xPos, yPos, zPos);
			}
		}

		for (int i = 0; i < 12; i++) {
			int xPos = x + random.nextInt(16) + 8;
			int yPos = random.nextInt(250);
			int zPos = z + random.nextInt(16) + 8;
			(new WorldGenWaterBerry()).generate(world, random, xPos, yPos, zPos);
		}
	}

	private void generateNether(World world, Random random, int x, int z) { }

	private void generateEnd(World world, Random random, int x, int z) { }

}