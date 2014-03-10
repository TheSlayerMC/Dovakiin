package net.dovakiin;

import net.dovakiin.api.blocks.BlockBerryPlant;
import net.dovakiin.api.blocks.BlockSpawner;
import net.dovakiin.api.items.ModItem;
import net.dovakiin.util.*;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;

@Mod(modid = Utils.MOD_ID, name = Utils.MOD_NAME, version = Utils.MOD_VERSION)
public class Dovakiin {
	
	public static int level = 0; 
	public static float swordLevel = 0.0F;
		
	@Instance(Utils.MOD_ID)
	public static Dovakiin instance;
	
	@SidedProxy(clientSide = Utils.PROXY_CLIENT, serverSide = Utils.PROXY_SERVER)
	public static CommonProxy proxy;
	
	public static CreativeTabs items = new CreativeTabs("Dovakiin: Items"){
		@Override public Item getTabIconItem() { return Dovakiin.greenBerry; }
	};
	
	public static CreativeTabs blocks = new CreativeTabs("Dovakiin: Blocks"){
		@Override public Item getTabIconItem() { return Item.getItemFromBlock(Dovakiin.greenBerryBushRipe); }
	};
	
	public static CreativeTabs spawner = new CreativeTabs("Dovakiin: Spawner"){
		@Override public Item getTabIconItem() { return Item.getItemFromBlock(Dovakiin.dragonSpawner); }
	};
	
	public static CreativeTabs misc = new CreativeTabs("Dovakiin: Misc"){
		@Override public Item getTabIconItem() { return Dovakiin.dragonEssence; }
	};
	
	public static final Block pigSpawner 			= new BlockSpawner("Pig")			 .registerBlock("pigSpawner");
	public static final Block sheepSpawner 			= new BlockSpawner("Sheep")			 .registerBlock("sheepSpawner");
	public static final Block cowSpawner 			= new BlockSpawner("Cow")			 .registerBlock("cowSpawner");
	public static final Block horseSpawner 			= new BlockSpawner("EntityHorse")	 .registerBlock("horseSpawner");
	public static final Block chikenSpawner 		= new BlockSpawner("Chicken")		 .registerBlock("chickenSpawner");
	public static final Block creeperSpawner 		= new BlockSpawner("Creeper")		 .registerBlock("creeperSpawner");
	public static final Block endermanSpawner 		= new BlockSpawner("Enderman")		 .registerBlock("endermanSpawner");
	public static final Block witherSpawner 		= new BlockSpawner("WitherBoss")	 .registerBlock("witherSpawner");
	public static final Block dragonSpawner 		= new BlockSpawner("EnderDragon")	 .registerBlock("dragonSpawner");
	public static final Block skeletonSpawner 		= new BlockSpawner("Skeleton")		 .registerBlock("skeletonSpawner");
	public static final Block pigmanSpawner 		= new BlockSpawner("PigZombie")		 .registerBlock("pigmanSpawner");
	public static final Block witherSkeletonSpawner = new BlockSpawner("WitherSkeleton") .registerBlock("witherSkeletonSpawner");
	public static final Block blazeSpawner 			= new BlockSpawner("Blaze")			 .registerBlock("blazeSpawner");
	public static final Block villagerSpawner 		= new BlockSpawner("Villager")		 .registerBlock("villagerSpawner");
	public static final Block slimeSpawner 			= new BlockSpawner("Slime")			 .registerBlock("slimeSpawner");
	public static final Block magmaCubeSpawner 		= new BlockSpawner("LavaSlime")		 .registerBlock("magmaCubeSpawner");
	public static final Block xpSpawner 			= new BlockSpawner("ThrownExpBottle").registerBlock("xpPotionSpawner");
	public static final Block spiderSpawner 		= new BlockSpawner("Spider")		 .registerBlock("spiderSpawner");
	public static final Block caveSpiderSpawner 	= new BlockSpawner("CaveSpider")	 .registerBlock("caveSpiderSpawner");
	public static final Block silverfishSpawner 	= new BlockSpawner("Silverfish")	 .registerBlock("SilverfishSpawner");
	public static final Block batSpawner 			= new BlockSpawner("Bat")			 .registerBlock("batSpawner");
	public static final Block witchSpawner 			= new BlockSpawner("Witch")			 .registerBlock("witchSpawner");
	public static final Block squidSpawner 			= new BlockSpawner("Squid")			 .registerBlock("squidSpawner");
	public static final Block wolfSpawner 			= new BlockSpawner("Wolf")			 .registerBlock("wolfSpawner");
	public static final Block mushroomSpawner 		= new BlockSpawner("MushroomCow")	 .registerBlock("mushroomSpawner");
	public static final Block ozelotSpawner 		= new BlockSpawner("Ozelot")		 .registerBlock("ozelotSpawner");
	public static final Block snowManSpawner 		= new BlockSpawner("SnowMan")		 .registerBlock("snowManSpawner");
	public static final Block giantSpawner 			= new BlockSpawner("Giant")			 .registerBlock("giantSpawner");
	public static final Block villagerGolemSpawner 	= new BlockSpawner("VillagerGolem")	 .registerBlock("golemSpawner");
	
	public static final Block greenBerryBush 		= new BlockBerryPlant()				 .registerBlock("greenBerryBush");
	public static final Block greenBerryBushRipe 	= new BlockBerryPlant()				 .registerBlock("greenBerryBushRipe");
	public static final Block waterBerryBush 		= new BlockBerryPlant()				 .registerBlock("waterBerryBush").setLightLevel(1.0F);
	public static final Block waterBerryBushRipe 	= new BlockBerryPlant()				 .registerBlock("waterBerryBushRipe").setLightLevel(1.0F);
	public static final Block desertBerryBush 		= new BlockBerryPlant()				 .registerBlock("desertBerryBush");
	public static final Block desertBerryBushRipe 	= new BlockBerryPlant()				 .registerBlock("desertBerryBushRipe");
	
	public static final Item greenBerry 			= new ModItem().registerItem("greenBerry");
	public static final Item desertBerry 			= new ModItem().registerItem("desertBerry");
	public static final Item waterBerry 			= new ModItem().registerItem("waterBerry");
	
	public static final Item dragonEssence 			= new ModItem().registerItem("dragonEssence").setCreativeTab(misc);
	public static final Item witherEssence 			= new ModItem().registerItem("witherEssence").setCreativeTab(misc);
	public static final Item skeletonEssence 		= new ModItem().registerItem("skeletonEssence").setCreativeTab(misc);
	public static final Item zombieEssence 			= new ModItem().registerItem("zombieEssence").setCreativeTab(misc);
	public static final Item creeperEssence 		= new ModItem().registerItem("creeperEssence").setCreativeTab(misc);
	public static final Item endermanEssence 		= new ModItem().registerItem("endermanEssence").setCreativeTab(misc);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		proxy.preInit(event);
		proxy.registerClient();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		proxy.postInit(event);
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event){
		proxy.serverStarting(event);
	}
	
}