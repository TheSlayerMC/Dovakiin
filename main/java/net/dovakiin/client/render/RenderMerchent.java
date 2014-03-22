package net.dovakiin.client.render;

import org.lwjgl.opengl.GL11;

import net.dovakiin.entity.mob.npc.EntityMerchent;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

public class RenderMerchent extends RenderBiped{
	
    private static final ResourceLocation tex = new ResourceLocation("textures/entity/steve.png");


	public RenderMerchent() {
		super(new ModelBiped(), 0.5F);
	}
	
    protected ResourceLocation getEntityTexture() {
        return tex;
    }
    
	public void renderMob(EntityMerchent var1, double var2, double var4, double var6, float var8, float var9) {
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		this.renderMob((EntityMerchent)var1, var2, var4, var6, var8, var9);
	}

    protected ResourceLocation getEntityTexture(EntityLiving par1EntityLiving) {
        return this.getEntityTexture((EntityMerchent)par1EntityLiving);
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return getEntityTexture();
    }

}
