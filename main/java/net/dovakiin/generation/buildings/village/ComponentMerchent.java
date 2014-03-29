package net.dovakiin.generation.buildings.village;

import java.util.List;
import java.util.Random;

import net.dovakiin.entity.mob.npc.EntityMerchent;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;

public class ComponentMerchent extends StructureVillagePieces.Village {

	private int averageGroundLevel = -1;

	public ComponentMerchent(Start villagePiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
		super();
		this.coordBaseMode = par5;
		this.boundingBox = par4StructureBoundingBox;
	}

	public static ComponentMerchent buildComponent(Start villagePiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, 10, 6, 10, p4);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new ComponentMerchent(villagePiece, p5, random, structureboundingbox, p4) : null;
	}

	private StructureBoundingBox sbb;
	private World w;

	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox sbb) {
		this.sbb = sbb;
		w = world;
		if (this.averageGroundLevel < 0) {
			this.averageGroundLevel = this.getAverageGroundLevel(world, sbb);

			if (this.averageGroundLevel < 0) {
				return true;
			}

			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 4, 0);
		}

		this.fill(0, 0, 0, 8, 1, 6, Blocks.cobblestone);//Base
		this.fill(1, 0, 1, 8, 1, 8, Blocks.cobblestone);
		this.fill(0, 0, 2, 8, 1, 9, Blocks.cobblestone);

		for(int w = 0; w < 9; w++){
			for(int w1 = 0; w1 < 10; w1++){
				this.placeBlockAtCurrentPosition(world, Blocks.planks, 0, w, 3, w1, sbb);//Roof
			}
		}

		for(int a = 0; a < 8; a++){
			for(int a1 = 0; a1 < 7; a1++){
				for(int a3 = 0; a3 < 3; a3++){
					this.placeBlockAtCurrentPosition(world, Blocks.air, 0, a1 + 1, a3, a + 1, sbb);
					this.placeBlockAtCurrentPosition(world, Blocks.air, 0, a1 + 1, a3 + 1, a + 1, sbb);
					this.placeBlockAtCurrentPosition(world, Blocks.planks, 0, a1 + 1, a3 + 2, a + 1, sbb);
				}
			}
		}

		this.placeBlockAtCurrentPosition(world, Blocks.glowstone, 0, 4, 4, 4, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.glowstone, 0, 4, 4, 5, sbb);

		this.fill(0, 0, 0, 8, 0, 8, Blocks.planks);

		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 0, 1, 0, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 0, 2, 0, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 0, 3, 0, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 1, 2, 0, sbb);//Front
		this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 2, 2, 0, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 3, 2, 0, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 3, 3, 0, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 4, 3, 0, sbb);
		this.placeDoorAtCurrentPosition(world, sbb, random, 4, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));
		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 5, 2, 0, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 5, 3, 0, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 6, 2, 0, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 7, 2, 0, sbb);

		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 8, 1, 0, sbb);//Left
		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 8, 2, 0, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 8, 3, 0, sbb);

		for(int l = 0; l < 8; l++)
			this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 8, 2, l + 1, sbb);

		for(int k = 0; k < 8; k++)
			this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 0, 2, k + 1, sbb);//Right

		for(int b = 0; b < 8; b++)
			this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, b, 2, 9, sbb);//Back

		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 0, 1, 9, sbb);//Right back
		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 0, 2, 9, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 0, 3, 9, sbb);

		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 8, 1, 9, sbb);//Left back
		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 8, 2, 9, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 8, 3, 9, sbb);

		for(int b = 0; b < 4; b++)
			this.placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 1, 1, b + 1, sbb);

		this.placeBlockAtCurrentPosition(world, Blocks.lit_redstone_lamp, 0, 2, 0, 2, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.redstone_torch, 0, 2, -1, 2, sbb);

		this.placeBlockAtCurrentPosition(world, Blocks.lit_redstone_lamp, 0, 6, 0, 2, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.redstone_torch, 0, 6, -1, 2, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 6, 1, 2, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.wooden_pressure_plate, 0, 6, 2, 2, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 7, 1, 2, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 2), 7, 1, 1, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 2), 6, 1, 1, sbb);


		this.placeBlockAtCurrentPosition(world, Blocks.lit_redstone_lamp, 0, 6, 0, 7, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.redstone_torch, 0, 6, -1, 7, sbb);

		this.placeBlockAtCurrentPosition(world, Blocks.lit_redstone_lamp, 0, 2, 0, 7, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.redstone_torch, 0, 2, -1, 7, sbb);
		for(int b = 0; b < 5; b++)
			this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, b + 1, 1, 6, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.fence_gate, this.getMetadataWithOffset(Blocks.fence_gate, 1), 5, 1, 7, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 5, 1, 8, sbb);

		this.placeBlockAtCurrentPosition(world, Blocks.log, 0, 7, 1, 8, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.leaves, 0, 7, 2, 8, sbb);

		EntityMerchent m = new EntityMerchent(world);
		m.setLocationAndAngles(4, 2, 6, 0.0F, 0.0F);
		world.spawnEntityInWorld(m);
		
		return true;
	}

	private void fill(int x, int y, int z, int x1, int y1, int z1, Block b){
		this.fillWithBlocks(w, sbb, x, y, z, x1, y1, z1, b, b, false);
	}
}
