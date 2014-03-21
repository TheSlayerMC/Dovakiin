package net.dovakiin.client;

import net.dovakiin.CommonProxy;
import net.dovakiin.Dovakiin;
import net.dovakiin.client.model.ModelEgg;
import net.dovakiin.client.render.*;
import net.dovakiin.entity.misc.*;
import net.dovakiin.entity.mob.*;
import net.dovakiin.entity.mob.boss.*;
import net.dovakiin.util.KeyHandler;
import net.minecraft.client.model.ModelCreeper;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerClient() { 
		NetworkRegistry.INSTANCE.registerGuiHandler(Dovakiin.instance, new GuiHandler());
		FMLCommonHandler.instance().bus().register(new KeyHandler());
		RenderingRegistry.registerEntityRenderingHandler(EntityGiantSkeleton.class, new RenderSkeletonBoss());
		RenderingRegistry.registerEntityRenderingHandler(EntityWitherSkeleton.class, new RenderWitherSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityGiantZombie.class, new RenderZombieBoss());
		RenderingRegistry.registerEntityRenderingHandler(EntityGiantCreeper.class, new RenderCreeperBoss());

		RenderingRegistry.registerEntityRenderingHandler(EntityEgg.class, new RenderEgg());
	}
	
}
