package net.dovakiin.api.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.entity.mob.npc.EntityMerchent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockMerchentSpawner extends ModBlock{

	public BlockMerchentSpawner() {
		super(Material.rock);
		setBlockTextureName("sand");
	}
	
	@Override
	public Block registerBlock(String name) {
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}

	@Override
	public void onBlockAdded(World w, int x, int y, int z) {
		if(!w.isRemote){
			EntityMerchent m = new EntityMerchent(w);
			m.setLocationAndAngles(x + 0.5F, y + 2, z + 0.5F, 0.0F, 0.0F);
			w.spawnEntityInWorld(m);
			w.setBlock(x, y, z, Blocks.planks);
		}
	}
}