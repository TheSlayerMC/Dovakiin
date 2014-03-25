package net.dovakiin;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.dovakiin.api.*;
import net.dovakiin.client.*;
import net.dovakiin.entity.misc.EntityEgg;
import net.dovakiin.entity.mob.EntityWitherSkeleton;
import net.dovakiin.entity.mob.boss.*;
import net.dovakiin.entity.mob.npc.EntityMerchent;
import net.dovakiin.event.*;
import net.dovakiin.generation.*;
import net.dovakiin.generation.buildings.village.ComponentMerchent;
import net.dovakiin.generation.buildings.village.VillageMerchentHandler;
import net.dovakiin.network.PacketHandler;
import net.dovakiin.util.*;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class CommonProxy {

	public void registerClient(){ }
	
	//ClientProxy
	public void preInit(FMLPreInitializationEvent event){
		NetworkRegistry nr = NetworkRegistry.INSTANCE;
		RenderEvent.register();
		BannedEvent.register();
		BonemealEvent.register();
		DropsEvent.register();
		ClientPlayerEvent.register();
		nr.newChannel(Utils.MOD_NAME, new PacketHandler());
		Config.init();
		LangRegistry.init();
		if(Utils.DEBUG){
		    LangRegistry.addBlockNames();
		    LangRegistry.addItemNames();
		    LangRegistry.addEggNames();
		}
		LangRegistry.closeFile();
		
		
		DovakiinAPI.registerMob(EntityGiantSkeleton.class, "Giant Skeleton");
		DovakiinAPI.registerMob(EntityGiantZombie.class, "Giant Zombie");
		DovakiinAPI.registerMob(EntityGiantCreeper.class, "Giant Creeper");
		DovakiinAPI.registerMob(EntityWitherSkeleton.class, "Wither Skeleton");
		DovakiinAPI.registerMob(EntityMerchent.class, "Merchent");
		DovakiinAPI.registerEntity(EntityEgg.class, "Egg");
		
		FMLCommonHandler.instance().bus().register(new KeyHandler());
	}
	
	public void init(FMLInitializationEvent event){
		GameRegistry.registerWorldGenerator(new BerryWorldGen(), 9);
		GameRegistry.registerWorldGenerator(new WorldGenerationBuildings(), 10);
		DovakiinAPI.addVillagePiece(ComponentMerchent.class, "MERCHENT");
		DovakiinAPI.addVillageCreationHandler(new VillageMerchentHandler());
	}
	
	public void postInit(FMLPostInitializationEvent event){ }
	
	public void serverStarting(FMLServerStartingEvent event){
		DovakiinAPI.registerCommand(new DovakiinCommands());
	}
}