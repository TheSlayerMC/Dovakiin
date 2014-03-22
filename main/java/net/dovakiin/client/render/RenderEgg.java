package net.dovakiin.client.render;

import org.lwjgl.opengl.GL11;

import net.dovakiin.client.model.ModelDragonEgg;
import net.dovakiin.client.model.ModelEgg;
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

	private ModelBase model;
	//private static final ResourceLocation tex = new ResourceLocation(Utils.PREFIX + "textures/entity/egg.png");
	private static final ResourceLocation tex = new ResourceLocation(Utils.PREFIX + "textures/entity/dragonEgg.png");

    public RenderEgg() {
        super(new ModelDragonEgg(), 0.2F);
    }

    protected ResourceLocation getEntityTexture(EntityEgg par1EntityCow) {
        return tex;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.getEntityTexture((EntityEgg)par1Entity);
    }
    
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
    	GL11.glScalef(0.3F, 0.3F, 0.3F);
    }
}