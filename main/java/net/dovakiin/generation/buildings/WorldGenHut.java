package net.dovakiin.generation.buildings;

import java.util.Random;

import net.dovakiin.Dovakiin;
import net.dovakiin.api.WorldGenAPI;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenHut extends WorldGenerator{

	@Override
	public boolean generate(World w, Random r, int x, int y, int z) {
		
		WorldGenAPI.drawHollowCube(5, w, x, y - 1, z, Blocks.planks);
		WorldGenAPI.drawRectangle(3, 3, 1, w, x + 1, y + 4, z + 1, Blocks.air);
		WorldGenAPI.drawRectangle(3, 3, 1, w, x + 1, y + 5, z + 1, Blocks.planks);
		WorldGenAPI.drawRectangle(3, 1, 1, w, x + 1, y + 2, z, Blocks.glass);
		WorldGenAPI.drawRectangle(1, 3, 1, w, x, y + 2, z + 1, Blocks.glass);
		WorldGenAPI.drawRectangle(1, 3, 1, w, x + 4, y + 2, z + 1, Blocks.glass);
		WorldGenAPI.drawRectangle(3, 1, 1, w, x + 1, y + 2, z + 4, Blocks.glass);

		WorldGenAPI.drawRectangle(3, 3, 1, w, x + 1, y, z + 1, Dovakiin.phantomPlanks);
		
		WorldGenAPI.drawRectangle(1, 1, 4, w, x + 2, y + 1, z + 2, Blocks.fence);

		w.setBlock(x, y + 3, z + 2, Blocks.glass);
		w.setBlock(x + 4, y + 3, z + 2, Blocks.glass);
		w.setBlock(x + 2, y + 3, z, Blocks.glass);
		w.setBlock(x + 2, y + 3, z + 4, Blocks.glass);

		w.setBlock(x, y + 1, z + 2, Blocks.air);
		w.setBlock(x, y + 2, z + 2, Blocks.air);

		ItemStack[] items = {new ItemStack(Items.apple), new ItemStack(Items.saddle), new ItemStack(Items.arrow), new ItemStack(Items.blaze_rod), 
				new ItemStack(Items.beef), new ItemStack(Items.golden_horse_armor)};
		
		WorldGenAPI.placeChestWithContents(w, x + 3, y + 1, z + 3, 2, items, 6, true);
	
		WorldGenAPI.placeSignWithText(w, x + 3, y + 2, z + 2, 4, new String[] {"", "GET OUT!", "", ""}, false);
		
		return true;
	}
}