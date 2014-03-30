package net.dovakiin.api.blocks;

import java.util.Random;

import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.entity.mob.npc.EntityMerchent;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockMerchentSpawner extends ModBlock{

	public BlockMerchentSpawner() {
		super(Material.rock);
	}

	@Override
	public void onBlockAdded(World w, int x, int y, int z) {
		if(!w.isRemote){
			EntityMerchent m = new EntityMerchent(w);
			m.setLocationAndAngles(x + 0.5F, y + 2, z + 0.5F, DovakiinAPI.rand.nextFloat() * 360.0F, 0.0F);
			w.spawnEntityInWorld(m);
			w.setBlock(x, y, z, Blocks.planks);
		}
	}
}