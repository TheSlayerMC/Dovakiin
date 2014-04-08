package net.dovakiin.client.render;

import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.client.model.ModelFish;
import net.dovakiin.entity.mob.peacefull.EntityFish;
import net.dovakiin.util.Utils;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderFish extends RenderLiving {

	private static final ResourceLocation tex = new ResourceLocation(Utils.PREFIX + "textures/entity/fish.png");
	private static final ResourceLocation tex1 = new ResourceLocation(Utils.PREFIX + "textures/entity/fish1.png");
	private static final ResourceLocation tex2 = new ResourceLocation(Utils.PREFIX + "textures/entity/fish2.png");
	private static final ResourceLocation tex3 = new ResourceLocation(Utils.PREFIX + "textures/entity/fish3.png");

	public RenderFish() {
		super(new ModelFish(), 0.2F);
	}

	protected ResourceLocation getEntityTexture() {
		int t = DovakiinAPI.rand.nextInt(4);
		if(t == 0)
			return tex;
		if(t == 1)
			return tex1;
		if(t == 2)
			return tex2;
		if(t == 3)
			return tex3;
		return tex;
	}

	public void renderMob(EntityFish var1, double var2, double var4, double var6, float var8, float var9) {
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		this.renderMob((EntityFish)var1, var2, var4, var6, var8, var9);
	}

	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return getEntityTexture();
	}
}