package net.dovakiin.api;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.world.World;

public class WorldGenAPI {

	public static void drawCube(int size, World w, int x, int y, int z, Block b){
		for(int x1 = 0; x1 < size; x1++){
			for(int z1 = 0; z1 < size; z1++){
				for(int y1 = 0; y1 < size; y1++){
					w.setBlock(x + x1, y + y1 + 1, z + z1, b);
				}
			}
		}
	}

	public static void drawHollowCube(int size, World w, int x, int y, int z, Block b){
		for(int x1 = 0; x1 < size; x1++){
			for(int z1 = 0; z1 < size; z1++){
				for(int y1 = 0; y1 < size; y1++){
					w.setBlock(x + x1, y + y1 + 1, z + z1, b);
				}
			}
		}

		for(int x1 = 0; x1 < size - 2; x1++){
			for(int z1 = 0; z1 < size - 2; z1++){
				for(int y1 = 0; y1 < size - 2; y1++){
					w.setBlock(x + x1 + 1, y + y1 + 2, z + z1 + 1, Blocks.air);
				}
			}
		}
	}

	public static void drawRectangle(int east, int south, int height, World w, int x, int y, int z, Block b){
		for(int x1 = 0; x1 < east; x1++){
			for(int z1 = 0; z1 < south; z1++){
				for(int y1 = 0; y1 < height; y1++){
					w.setBlock(x + x1, y + y1, z + z1, b);
				}
			}
		}
	}

	/**
	 * @param w: World
	 * @param x: X pos
	 * @param y: Y pos
	 * @param z: Z pos
	 * @param meta: metadata
	 * @param is: An array of ItemStack holding different blocks/items
	 * @param amountOfItems: The amount of ItemStacks your array has to generate
	 * @param trapped: Is it a trapped chest?
	 */
	public static void placeChestWithContents(World w, int x, int y, int z, int meta, ItemStack[] is, int amountOfItems, boolean trapped){
		Random r = new Random();
		
		if(trapped) w.setBlock(x, y, z, Blocks.trapped_chest, meta, 2);
		else w.setBlock(x, y, z, Blocks.chest, meta, 2);
		
		TileEntityChest chest = (TileEntityChest)w.getTileEntity(x, y, z);

		if(chest != null && !w.isRemote){
			for(int i = 0; i < r.nextInt(26) + 1; i++){
				ItemStack it = is[r.nextInt(amountOfItems - 1) + 1];
				chest.setInventorySlotContents(r.nextInt(26) + 1, it);
			}
		}
	}

	public static void placeSignWithText(World w, int x, int y, int z, int meta, String[] text, boolean standing){
		if(standing) w.setBlock(x, y, z, Blocks.standing_sign, meta, 2);
		else w.setBlock(x, y, z, Blocks.wall_sign, meta, 2);

		TileEntitySign sign = (TileEntitySign)w.getTileEntity(x, y, z);

		if(sign != null && !w.isRemote)
			sign.signText = text;
	}

	/*public static void drawHollowRectangle(int east, int south, int height, World w, int x, int y, int z, Block b){
		for(int x1 = 0; x1 < east; x1++){
			for(int z1 = 0; z1 < south; z1++){
				for(int y1 = 0; y1 < height; y1++){
					w.setBlock(x + x1, y + y1, z + z1, b);
				}
			}
		}

		for(int x1 = 0; x1 < east; x1++){
			for(int z1 = 0; z1 < south; z1++){
				for(int y1 = 0; y1 < height - 2; y1++){
					w.setBlock(x + x1 + 1, y + y1 + 1, z + z1 + 1, Blocks.air);
				}
			}
		}
	}*/

	public static void drawCone(int width, int length, int height, World w, int x, int y, int z, Block b){
		for(int x1 = 0; x1 < width; x1++){
			for(int z1 = 0; z1 < length; z1++){
				for(int y1 = 0; y1 < height; y1++){
					w.setBlock(x + x1, y + y1, z + z1, b);//WIP (Not working as of yet)
				}
			}
		}
	}
}