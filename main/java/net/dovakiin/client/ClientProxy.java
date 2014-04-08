package net.dovakiin.client;

import net.dovakiin.CommonProxy;
import net.dovakiin.Dovakiin;
import net.dovakiin.client.render.RenderCreeperBoss;
import net.dovakiin.client.render.RenderEgg;
import net.dovakiin.client.render.RenderFish;
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
import net.dovakiin.entity.mob.passive.EntityFish;
import net.dovakiin.event.ServerPlayerEvent;
import net.dovakiin.util.Utils;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy{

	//private static final ResourceLocation greenDragon = new ResourceLocation(Utils.PREFIX + "textures/entity/egg.png");
	private static final ResourceLocation basicEgg = new ResourceLocation(Utils.PREFIX + "textures/entity/dragonEgg.png");


	@Override
	public void registerClient() { 
		NetworkRegistry nr = NetworkRegistry.INSTANCE;
		ServerPlayerEvent.register();
		nr.registerGuiHandler(Dovakiin.instance, new GuiHandler());
		RenderingRegistry.registerEntityRenderingHandler(EntityGiantSkeleton.class, new RenderSkeletonBoss());
		RenderingRegistry.registerEntityRenderingHandler(EntityWitherSkeleton.class, new RenderWitherSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityGiantZombie.class, new RenderZombieBoss());
		RenderingRegistry.registerEntityRenderingHandler(EntityGiantCreeper.class, new RenderCreeperBoss());
		RenderingRegistry.registerEntityRenderingHandler(EntityMerchent.class, new RenderMerchent());
		RenderingRegistry.registerEntityRenderingHandler(EntityFish.class, new RenderFish());

		RenderingRegistry.registerEntityRenderingHandler(EntityEgg.class, new RenderEgg(basicEgg, 0.7F));
	}
}