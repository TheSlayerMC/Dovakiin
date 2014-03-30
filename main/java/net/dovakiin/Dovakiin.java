package net.dovakiin;

import net.dovakiin.api.blocks.BlockBerryPlant;
import net.dovakiin.api.blocks.BlockBucketInteraction;
import net.dovakiin.api.blocks.BlockMerchentSpawner;
import net.dovakiin.api.blocks.BlockSpawner;
import net.dovakiin.api.items.ItemCoin;
import net.dovakiin.api.items.ItemEgg;
import net.dovakiin.api.items.ModInformationBook;
import net.dovakiin.api.items.ModItem;
import net.dovakiin.client.DovakiinTabs;
import net.dovakiin.client.GuiHandler;
import net.dovakiin.network.PacketHandler;
import net.dovakiin.network.PacketOpenGui;
import net.dovakiin.network.PacketRequestBuy;
import net.dovakiin.network.PacketRequestStats;
import net.dovakiin.network.PacketSyncServer;
import net.dovakiin.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Utils.MOD_ID, name = Utils.MOD_NAME, version = Utils.MOD_VERSION)
public class Dovakiin {
		
	public static final PacketHandler packetHandler = new PacketHandler();
	
	@Instance(Utils.MOD_ID)
	public static Dovakiin instance;
	
	@SidedProxy(clientSide = Utils.PROXY_CLIENT, serverSide = Utils.PROXY_SERVER)
	public static CommonProxy proxy;
	
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
	public static final Block witherSkeletonSpawner = new BlockSpawner("Wither Skeleton").registerBlock("witherSkeletonSpawner");
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
	public static final Block giantSkeletonSpawner 	= new BlockSpawner("Giant Skeleton") .registerBlock("skeletonBossSpawner");
	public static final Block giantZombieSpawner 	= new BlockSpawner("Giant Zombie")	 .registerBlock("zombieBossSpawner");
	public static final Block giantZombieCreeper 	= new BlockSpawner("Giant Creeper")	 .registerBlock("creeperBossSpawner");

	public static final Block bucketInteraction 	= new BlockBucketInteraction()	 	 .registerBlock("bucketFiller");
	public static final Block merchentSpawner		= new BlockMerchentSpawner()		 .registerBlock("merchentSpawner");
	
	public static final Block greenBerryBush 		= new BlockBerryPlant(false)		 .registerBlock("greenBerryBush");
	public static final Block greenBerryBushRipe 	= new BlockBerryPlant(true)			 .registerBlock("greenBerryBushRipe");
	public static final Block waterBerryBush 		= new BlockBerryPlant(false)		 .registerBlock("waterBerryBush").setLightLevel(1.0F);
	public static final Block waterBerryBushRipe 	= new BlockBerryPlant(true)			 .registerBlock("waterBerryBushRipe").setLightLevel(1.0F);
	public static final Block desertBerryBush 		= new BlockBerryPlant(false)		 .registerBlock("desertBerryBush");
	public static final Block desertBerryBushRipe 	= new BlockBerryPlant(true)			 .registerBlock("desertBerryBushRipe");

	public static final Item greenBerry 			= new ModItem().registerItem("greenBerry");
	public static final Item desertBerry 			= new ModItem().registerItem("desertBerry");
	public static final Item waterBerry 			= new ModItem().registerItem("waterBerry");
	
	public static final Item dragonEssence 			= new ModItem(true).registerItem("dragonEssence").setCreativeTab(DovakiinTabs.misc);
	public static final Item witherEssence 			= new ModItem(true).registerItem("witherEssence").setCreativeTab(DovakiinTabs.misc);
	public static final Item skeletonEssence 		= new ModItem(true).registerItem("skeletonEssence").setCreativeTab(DovakiinTabs.misc);
	public static final Item zombieEssence 			= new ModItem(true).registerItem("zombieEssence").setCreativeTab(DovakiinTabs.misc);
	public static final Item creeperEssence 		= new ModItem(true).registerItem("creeperEssence").setCreativeTab(DovakiinTabs.misc);
	public static final Item endermanEssence 		= new ModItem(true).registerItem("endermanEssence").setCreativeTab(DovakiinTabs.misc);
	
	public static final Item startingBook 			= new ModInformationBook(GuiHandler.startingBook).registerItem("welcome");
	
	public static final Item greenDragon 			= new ItemEgg().registerItem("basicEgg");
	public static final Item coin 					= new ItemCoin().registerItem("coin");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		proxy.preInit(event);
		proxy.registerClient();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.init(event);
		packetHandler.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		proxy.postInit(event);
		packetHandler.registerPacket(PacketOpenGui.class);
		packetHandler.registerPacket(PacketRequestBuy.class);
		packetHandler.registerPacket(PacketSyncServer.class);
		packetHandler.registerPacket(PacketRequestStats.class);
		packetHandler.postInit();
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event){
		proxy.serverStarting(event);
	}
	
	public static void sendStats(EntityPlayer player) {
		packetHandler.sendTo(new PacketSyncServer().applyStats(player), (EntityPlayerMP)player);
	}
	
	@SideOnly(Side.CLIENT)
	public static int mobLevel, maxLevel, coins, LEVEL;
	@SideOnly(Side.CLIENT)
	public static int level, swordLevel;
}