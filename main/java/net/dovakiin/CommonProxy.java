package net.dovakiin;

import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.client.DovakiinCommands;
import net.dovakiin.entity.misc.egg.EntityGreenDragonEgg;
import net.dovakiin.entity.mob.EntityWitherSkeleton;
import net.dovakiin.entity.mob.boss.EntityGiantCreeper;
import net.dovakiin.entity.mob.boss.EntityGiantSkeleton;
import net.dovakiin.entity.mob.boss.EntityGiantZombie;
import net.dovakiin.entity.mob.npc.EntityMerchent;
import net.dovakiin.entity.mob.passive.EntityFish;
import net.dovakiin.event.BannedEvent;
import net.dovakiin.event.BonemealEvent;
import net.dovakiin.event.ClientPlayerEvent;
import net.dovakiin.event.KeyHandler;
import net.dovakiin.event.RenderEvent;
import net.dovakiin.event.TickHandler;
import net.dovakiin.event.level_system.GuiTickHandler;
import net.dovakiin.event.level_system.LevelEvent;
import net.dovakiin.generation.BerryWorldGen;
import net.dovakiin.generation.WorldGenerationBuildings;
import net.dovakiin.network.PacketHandler;
import net.dovakiin.util.Config;
import net.dovakiin.util.LangRegistry;
import net.dovakiin.util.Utils;
import net.minecraft.enchantment.EnchantmentArrowDamage;
import net.minecraft.enchantment.EnchantmentArrowInfinite;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

	public void registerClient(){ }

	//ClientProxy
	public void preInit(FMLPreInitializationEvent event){
		Config.init();
		NetworkRegistry.INSTANCE.newChannel(Utils.MOD_NAME, new PacketHandler());
		//RenderEvent.register();
		//BannedEvent.register();
		BonemealEvent.register();
		LevelEvent.register();
		
		LangRegistry.init();
		if(Utils.DEBUG){
			LangRegistry.addBlockNames();
			LangRegistry.addItemNames();
			LangRegistry.addEggNames();
		}
		LangRegistry.closeFile();
		
		/*DovakiinAPI.addVillagePiece(ComponentMerchent.class, "Merchent");
		DovakiinAPI.addVillagePiece(Field3.class, "Garden");
		DovakiinAPI.addVillageCreationHandler(new VillageMerchentHandler());
		DovakiinAPI.addVillageCreationHandler(new VillageGardenHandler());*/

		DovakiinAPI.registerEntity(EntityGiantSkeleton.class, "Giant Skeleton", DovakiinAPI.mobID++);
		DovakiinAPI.registerEntity(EntityGiantZombie.class, "Giant Zombie", DovakiinAPI.mobID++);
		DovakiinAPI.registerEntity(EntityGiantCreeper.class, "Giant Creeper", DovakiinAPI.mobID++);
		DovakiinAPI.registerEntity(EntityWitherSkeleton.class, "Wither Skeleton", DovakiinAPI.mobID++);
		DovakiinAPI.registerEntity(EntityMerchent.class, "Merchent", DovakiinAPI.mobID++);
		DovakiinAPI.registerEntity(EntityFish.class, "Fish", DovakiinAPI.mobID++);
		DovakiinAPI.registerEggEntity(EntityGreenDragonEgg.class, "Green Dragon", DovakiinAPI.mobID++);

		FMLCommonHandler.instance().bus().register(new KeyHandler());
		FMLCommonHandler.instance().bus().register(new TickHandler());
	}

	public void init(FMLInitializationEvent event){
		GameRegistry.registerWorldGenerator(new BerryWorldGen(), 9);
		GameRegistry.registerWorldGenerator(new WorldGenerationBuildings(), 10);
		FMLCommonHandler.instance().bus().register(new GuiTickHandler());
		ClientPlayerEvent.register();
	}

	public void postInit(FMLPostInitializationEvent event){ }

	public void serverStarting(FMLServerStartingEvent event){
		DovakiinAPI.registerCommand(new DovakiinCommands());
	}
}