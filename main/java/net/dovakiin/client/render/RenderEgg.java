package net.dovakiin.client.render;

import org.lwjgl.opengl.GL11;

import net.dovakiin.client.render.model.ModelDragonEgg;
import net.dovakiin.client.render.model.ModelEgg;
import net.dovakiin.entity.misc.EntityEgg;
import net.dovakiin.util.Utils;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;

public class RenderEgg extends RenderLiving {

	private ResourceLocation texture;
	private float fl;
	
    public RenderEgg(ResourceLocation r, float f) {
        super(new ModelDragonEgg(), f / 2 - 2);
        texture = r;
        fl = f;
    }

    protected ResourceLocation getEntityTexture(EntityEgg par1EntityCow) {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.getEntityTexture((EntityEgg)par1Entity);
    }
    
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
    	GL11.glScalef(fl, fl, fl);
    }
}