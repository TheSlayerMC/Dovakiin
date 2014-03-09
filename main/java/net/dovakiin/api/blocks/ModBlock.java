package net.dovakiin.api.blocks;

import net.dovakiin.Dovakiin;
import net.dovakiin.util.LangRegistry;
import net.dovakiin.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlock extends Block{

	public ModBlock(Material material) {
		super(material);
		LangRegistry.addBlock(this);
		setCreativeTab(Dovakiin.blocks);
	}
	
	public Block registerBlock(String name){
		setBlockTextureName(Utils.PREFIX + name);
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}
}