package net.dovakiin.client;

import net.dovakiin.CommonProxy;
import net.dovakiin.Dovakiin;
import net.dovakiin.client.render.RenderCreeperBoss;
import net.dovakiin.client.render.RenderEgg;
import net.dovakiin.client.render.RenderMerchent;
import net.dovakiin.client.render.RenderSkeletonBoss;
import net.dovakiin.client.render.RenderWitherSkeleton;
import net.dovakiin.client.render.RenderZombieBoss;
import net.dovakiin.entity.misc.EntityEgg;
import net.dovakiin.entity.mob.EntityWitherSkeleton;
import net.dovakiin.entity.mob.boss.EntityGiantCreeper;
import net.dovakiin.entity.mob.boss.EntityGiantSkeleton;
import net.dovakiin.entity.mob.boss.EntityGiantZombie;
import net.dovakiin.entity.mob.npc.EntityMerchent;
import net.dovakiin.util.KeyHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
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
		RenderingRegistry.registerEntityRenderingHandler(EntityMerchent.class, new RenderMerchent());

		RenderingRegistry.registerEntityRenderingHandler(EntityEgg.class, new RenderEgg());
	}
	
}
