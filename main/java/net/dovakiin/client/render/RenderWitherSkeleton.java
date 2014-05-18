package net.dovakiin.client.render;

import net.dovakiin.client.render.model.ModelWitherSkeleton;
import net.dovakiin.entity.mob.EntityWitherSkeleton;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWitherSkeleton extends RenderBiped {
	
	private static final ResourceLocation witherSkeletonTextures = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");

	public RenderWitherSkeleton() {
		super(new ModelWitherSkeleton(), 0.5F);
	}

	protected void preRenderCallback(EntityWitherSkeleton par1EntityWitherSkeleton, float par2) {
		GL11.glScalef(1.2F, 1.2F, 1.2F);
	}

	protected void func_82422_c() {
		GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
	}

	protected ResourceLocation getEntityTexture(EntityWitherSkeleton par1EntityWitherSkeleton) {
		return witherSkeletonTextures;
	}
	
	public void renderMob(EntityWitherSkeleton var1, double var2, double var4, double var6, float var8, float var9) {
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		this.renderMob((EntityWitherSkeleton)var1, var2, var4, var6, var8, var9);
	}

	protected ResourceLocation getEntityTexture(EntityLiving par1EntityLiving) {
		return this.getEntityTexture((EntityWitherSkeleton)par1EntityLiving);
	}

	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
		this.preRenderCallback((EntityWitherSkeleton)par1EntityLivingBase, par2);
	}

	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getEntityTexture((EntityWitherSkeleton)par1Entity);
	}
}