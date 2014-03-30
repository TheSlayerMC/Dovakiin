package net.dovakiin;

import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.client.DovakiinCommands;
import net.dovakiin.entity.misc.egg.EntityGreenDragonEgg;
import net.dovakiin.entity.mob.EntityWitherSkeleton;
import net.dovakiin.entity.mob.boss.EntityGiantCreeper;
import net.dovakiin.entity.mob.boss.EntityGiantSkeleton;
import net.dovakiin.entity.mob.boss.EntityGiantZombie;
import net.dovakiin.entity.mob.npc.EntityMerchent;
import net.dovakiin.event.BonemealEvent;
import net.dovakiin.event.LevelEvent;
import net.dovakiin.event.RenderEvent;
import net.dovakiin.generation.BerryWorldGen;
import net.dovakiin.generation.WorldGenerationBuildings;
import net.dovakiin.generation.buildings.village.ComponentMerchent;
import net.dovakiin.generation.buildings.village.Field3;
import net.dovakiin.generation.buildings.village.VillageGardenHandler;
import net.dovakiin.generation.buildings.village.VillageMerchentHandler;
import net.dovakiin.network.PacketHandler;
import net.dovakiin.util.Config;
import net.dovakiin.util.KeyHandler;
import net.dovakiin.util.LangRegistry;
import net.dovakiin.util.Utils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerClient(){ }
	
	//ClientProxy
	public void preInit(FMLPreInitializationEvent event){
		NetworkRegistry nr = NetworkRegistry.INSTANCE;
		nr.newChannel(Utils.MOD_NAME, new PacketHandler());
		RenderEvent.register();
		//BannedEvent.register();
		BonemealEvent.register();
		LevelEvent.register();
		//ClientPlayerEvent.register();
		Config.init();
		LangRegistry.init();
		if(Utils.DEBUG){
		    LangRegistry.addBlockNames();
		    LangRegistry.addItemNames();
		    LangRegistry.addEggNames();
		}
		LangRegistry.closeFile();
		DovakiinAPI.addVillagePiece(ComponentMerchent.class, "Merchent");
		DovakiinAPI.addVillagePiece(Field3.class, "Garden");
		DovakiinAPI.addVillageCreationHandler(new VillageMerchentHandler());
		DovakiinAPI.addVillageCreationHandler(new VillageGardenHandler());
		
		DovakiinAPI.registerMob(EntityGiantSkeleton.class, "Giant Skeleton");
		DovakiinAPI.registerMob(EntityGiantZombie.class, "Giant Zombie");
		DovakiinAPI.registerMob(EntityGiantCreeper.class, "Giant Creeper");
		DovakiinAPI.registerMob(EntityWitherSkeleton.class, "Wither Skeleton");
		DovakiinAPI.registerMob(EntityMerchent.class, "Merchent");
		DovakiinAPI.registerEggEntity(EntityGreenDragonEgg.class, "Green");
		
		FMLCommonHandler.instance().bus().register(new KeyHandler());
	}
	
	public void init(FMLInitializationEvent event){
		GameRegistry.registerWorldGenerator(new BerryWorldGen(), 9);
		GameRegistry.registerWorldGenerator(new WorldGenerationBuildings(), 10);
	}
	
	public void postInit(FMLPostInitializationEvent event){ }
	
	public void serverStarting(FMLServerStartingEvent event){
		DovakiinAPI.registerCommand(new DovakiinCommands());
	}
}