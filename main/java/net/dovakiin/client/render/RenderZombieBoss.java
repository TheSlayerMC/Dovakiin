package net.dovakiin.client.render;

import net.dovakiin.entity.mob.boss.EntityGiantZombie;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZombieBoss extends RenderBiped {
    private static final ResourceLocation ZombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");

    public RenderZombieBoss() {
        super(new ModelZombie(), 1.5F);
    }

    protected ResourceLocation getEntityTexture() {
        return ZombieTextures;
    }
    
    protected void func_82422_c() {
        GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
    }
    
	public void renderMob(EntityGiantZombie var1, double var2, double var4, double var6, float var8, float var9) {
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		this.renderMob((EntityGiantZombie)var1, var2, var4, var6, var8, var9);
		BossStatus.setBossStatus((EntityGiantZombie)var1, false);
	}

    protected ResourceLocation getEntityTexture(EntityLiving par1EntityLiving) {
        return this.getEntityTexture((EntityGiantZombie)par1EntityLiving);
    }

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
    	GL11.glScalef(3.0F, 3.0F, 3.0F);
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return getEntityTexture();
    }
}