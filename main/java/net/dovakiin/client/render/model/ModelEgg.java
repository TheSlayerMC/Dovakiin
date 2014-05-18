package net.dovakiin.client.render.model;

import org.lwjgl.opengl.GL11;

import net.dovakiin.entity.misc.EntityEgg;
import net.dovakiin.entity.mob.boss.EntityGiantSkeleton;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelEgg extends ModelBase {

	ModelRenderer Bottom;
	ModelRenderer mid;
	ModelRenderer top;
	ModelRenderer side1;
	ModelRenderer side2;
	ModelRenderer side3;
	ModelRenderer side4;
	private int ticks;

	public ModelEgg() {
		ticks = 70;
		textureWidth = 64;
		textureHeight = 32;
		Bottom = new ModelRenderer(this, 3, 10);
		Bottom.addBox(-3F, 0F, -2F, 5, 7, 5);
		Bottom.setRotationPoint(0F, 17F, 0F);
		Bottom.setTextureSize(64, 32);
		Bottom.mirror = true;
		setRotation(Bottom, 0F, 0F, 0F);
		mid = new ModelRenderer(this, 1, 4);
		mid.addBox(-1F, 0.5F, -1F, 3, 1, 3);
		mid.setRotationPoint(-1F, 16F, 0F);
		mid.setTextureSize(64, 32);
		mid.mirror = true;
		setRotation(mid, 0F, 0F, 0F);
		top = new ModelRenderer(this, 1, 1);
		top.addBox(0F, 1F, 0F, 1, 1, 1);
		top.setRotationPoint(-1F, 15F, 0F);
		top.setTextureSize(64, 32);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		side1 = new ModelRenderer(this, 15, 0);
		side1.addBox(-2F, -2F, 0.5F, 3, 5, 1);
		side1.setRotationPoint(0F, 20F, -3F);
		side1.setTextureSize(64, 32);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		side2 = new ModelRenderer(this, 15, 0);
		side2.addBox(-0.5F, -2F, -2F, 1, 5, 3);
		side2.setRotationPoint(2F, 20F, 1F);
		side2.setTextureSize(64, 32);
		side2.mirror = true;
		setRotation(side2, 0F, 0F, 0F);
		side3 = new ModelRenderer(this, 15, 0);
		side3.addBox(0.5F, -2F, -1F, 1, 5, 3);
		side3.setRotationPoint(-4F, 20F, 0F);
		side3.setTextureSize(64, 32);
		side3.mirror = true;
		setRotation(side3, 0F, 0F, 0F);
		side4 = new ModelRenderer(this, 15, 0);
		side4.addBox(-1F, -2F, -0.5F, 3, 5, 1);
		side4.setRotationPoint(-1F, 20F, 3F);
		side4.setTextureSize(64, 32);
		side4.mirror = true;
		setRotation(side4, 0F, 0F, 0F);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Bottom.render(f5);
		mid.render(f5);
		top.render(f5);
		side1.render(f5);
		side2.render(f5);
		side3.render(f5);
		side4.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
		EntityEgg e = (EntityEgg)entity;


		/*Bottom.rotateAngleX = (float)Math.cos(par3 * -0.10F) * (float)Math.PI * 0.05F;
		mid.rotateAngleX = (float)Math.cos(par3 * -0.10F) * (float)Math.PI * 0.04F;
		top.rotateAngleX = (float)Math.cos(par3 * -0.10F) * (float)Math.PI * 0.02F;
		side1.rotateAngleX = (float)Math.cos(par3 * -0.10F) * (float)Math.PI * 0.04F;
		side2.rotateAngleX = (float)Math.cos(par3 * -0.10F) * (float)Math.PI * 0.04F;
		side3.rotateAngleX = (float)Math.cos(par3 * -0.10F) * (float)Math.PI * 0.05F;
		side4.rotateAngleX = (float)Math.cos(par3 * -0.10F) * (float)Math.PI * 0.04F;*/

	}
}