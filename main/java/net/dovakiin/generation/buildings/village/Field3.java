package net.dovakiin.generation.buildings.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class Field3 extends StructureVillagePieces.Village{

	private Block cropTypeA;
	private Block cropTypeB;

	public Field3() {}

	public Field3(StructureVillagePieces.Start par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
		super(par1ComponentVillageStartPiece, par2);
		this.coordBaseMode = par5;
		this.boundingBox = par4StructureBoundingBox;
		this.cropTypeA = this.func_151560_a(par3Random);
		this.cropTypeB = this.func_151560_a(par3Random);
	}

	protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
		super.func_143012_a(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("CA1", Block.blockRegistry.getIDForObject(this.cropTypeA));
		par1NBTTagCompound.setInteger("CB1", Block.blockRegistry.getIDForObject(this.cropTypeB));
	}

	protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
		super.func_143011_b(par1NBTTagCompound);
		this.cropTypeA = Block.getBlockById(par1NBTTagCompound.getInteger("CA1"));
		this.cropTypeB = Block.getBlockById(par1NBTTagCompound.getInteger("CB1"));
	}

	private Block func_151560_a(Random p_151560_1_) {
		switch (p_151560_1_.nextInt(5)) {
		case 0:
			return Blocks.anvil;
		case 1:
			return Blocks.bookshelf;
		default:
			return Blocks.reeds;
		}
	}

	public static StructureVillagePieces.Field2 buildComponent(StructureVillagePieces.Start par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 7, 4, 9, par6);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(par1List, structureboundingbox) == null ? new StructureVillagePieces.Field2(par0ComponentVillageStartPiece, par7, par2Random, structureboundingbox, par6) : null;
	}

	public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
		if (this.field_143015_k < 0) {
			this.field_143015_k = this.getAverageGroundLevel(par1World, par3StructureBoundingBox);

			if (this.field_143015_k < 0) {
				return true;
			}

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
		}

		this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 6, 4, 8, Blocks.air, Blocks.air, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 2, 0, 7, Blocks.farmland, Blocks.farmland, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 0, 1, 5, 0, 7, Blocks.farmland, Blocks.farmland, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 0, 0, 8, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 0, 0, 6, 0, 8, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 0, 5, 0, 0, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 8, 5, 0, 8, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 0, 1, 3, 0, 7, Blocks.water, Blocks.water, false);
		int i;

		for (i = 1; i <= 7; ++i) {
			this.placeBlockAtCurrentPosition(par1World, this.cropTypeA, MathHelper.getRandomIntegerInRange(par2Random, 2, 7), 1, 1, i, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, this.cropTypeA, MathHelper.getRandomIntegerInRange(par2Random, 2, 7), 2, 1, i, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, this.cropTypeB, MathHelper.getRandomIntegerInRange(par2Random, 2, 7), 4, 1, i, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, this.cropTypeB, MathHelper.getRandomIntegerInRange(par2Random, 2, 7), 5, 1, i, par3StructureBoundingBox);
		}

		for (i = 0; i < 9; ++i) {
			for (int j = 0; j < 7; ++j) {
				this.clearCurrentPositionBlocksUpwards(par1World, j, 4, i, par3StructureBoundingBox);
				this.func_151554_b(par1World, Blocks.dirt, 0, j, -1, i, par3StructureBoundingBox);
			}
		}
		return true;
	}
}