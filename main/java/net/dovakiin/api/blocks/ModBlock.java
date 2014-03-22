package net.dovakiin.api.blocks;

import net.dovakiin.client.DovakiinTabs;
import net.dovakiin.util.LangRegistry;
import net.dovakiin.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlock extends Block{

	public ModBlock(Material material) {
		super(material);
		LangRegistry.addBlock(this);
		setCreativeTab(DovakiinTabs.blocks);
	}
	
	public Block registerBlock(String name){
		setBlockTextureName(Utils.PREFIX + name);
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}
}