package net.dovakiin.generation;

import java.util.Random;

import net.dovakiin.generation.buildings.WorldGenHut;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenerationBuildings implements IWorldGenerator{

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

		if(random.nextInt(10) == 0) {
			int xPos = x + random.nextInt(16) + 8;
			int yPos = random.nextInt(80);
			int zPos = z + random.nextInt(16) + 8;
			(new WorldGenHut()).generate(world, random, xPos, yPos, zPos);
		}
	}

	private void generateNether(World world, Random random, int x, int z) { }

	private void generateEnd(World world, Random random, int x, int z) { }

}