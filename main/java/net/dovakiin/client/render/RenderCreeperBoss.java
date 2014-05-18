package net.dovakiin.client.render;

import net.dovakiin.entity.mob.boss.EntityGiantCreeper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCreeperBoss extends RenderLiving {

    private static final ResourceLocation creeperTextures = new ResourceLocation("textures/entity/creeper/creeper.png");
    private ModelBase creeperModel = new ModelCreeper(2.0F);

    public RenderCreeperBoss() {
        super(new ModelCreeper(), 0.5F);
    }

    protected void preRenderCallback(EntityGiantCreeper par1EntityGiantCreeper, float par2) {
        float f1 = par1EntityGiantCreeper.getCreeperFlashIntensity(par2);
        float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;

        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f1 > 1.0F) {
            f1 = 1.0F;
        }

        f1 *= f1;
        f1 *= f1;
        float f3 = (1.0F + f1 * 0.4F) * f2;
        float f4 = (1.0F + f1 * 0.1F) / f2;
        GL11.glScalef(f3, f4, f3);
    }

    protected int getColorMultiplier(EntityGiantCreeper par1EntityGiantCreeper, float par2, float par3) {
        float f2 = par1EntityGiantCreeper.getCreeperFlashIntensity(par3);

        if ((int)(f2 * 10.0F) % 2 == 0) {
            return 0;
        } else {
            int i = (int)(f2 * 0.2F * 255.0F);

            if (i < 0) {
                i = 0;
            }

            if (i > 255) {
                i = 255;
            }

            short short1 = 255;
            short short2 = 255;
            short short3 = 255;
            return i << 24 | short1 << 16 | short2 << 8 | short3;
        }
    }

    protected ResourceLocation getEntityTexture(EntityGiantCreeper par1EntityGiantCreeper) {
        return creeperTextures;
    }

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		BossStatus.setBossStatus((EntityGiantCreeper)var1, true);
		super.doRender(var1, var2, var4, var6, var8, var9);
	}
    
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        this.preRenderCallback((EntityGiantCreeper)par1EntityLivingBase, par2);
        GL11.glScalef(3.0F, 3.0F, 3.0F);
    }

    protected int getColorMultiplier(EntityLivingBase par1EntityLivingBase, float par2, float par3) {
        return this.getColorMultiplier((EntityGiantCreeper)par1EntityLivingBase, par2, par3);
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.getEntityTexture((EntityGiantCreeper)par1Entity);
    }
}