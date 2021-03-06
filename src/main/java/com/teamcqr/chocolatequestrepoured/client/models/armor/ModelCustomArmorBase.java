package com.teamcqr.chocolatequestrepoured.client.models.armor;

import java.util.Deque;
import java.util.LinkedList;

import com.teamcqr.chocolatequestrepoured.client.render.entity.layers.LayerCQREntityArmor;
import com.teamcqr.chocolatequestrepoured.objects.entity.mobs.EntityCQREnderman;
import com.teamcqr.chocolatequestrepoured.objects.entity.mobs.EntityCQRGolem;
import com.teamcqr.chocolatequestrepoured.objects.entity.mobs.EntityCQRGremlin;
import com.teamcqr.chocolatequestrepoured.objects.entity.mobs.EntityCQRIllager;
import com.teamcqr.chocolatequestrepoured.objects.entity.mobs.EntityCQRMandril;
import com.teamcqr.chocolatequestrepoured.objects.entity.mobs.EntityCQRTriton;
import com.teamcqr.chocolatequestrepoured.objects.items.ItemPotionHealing;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCustomArmorBase extends ModelBiped {

	public ModelCustomArmorBase(float scale, int textureWidth, int textureHeight) {
		super(scale, 0.0F, textureWidth, textureHeight);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		if (entityIn instanceof EntityArmorStand) {
			this.rotationAnglesArmorStand(entityIn);
		} else if (entityIn instanceof EntityCQREnderman) {
			super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
			this.rotationAnglesEnderman(entityIn);
		} else if (entityIn instanceof EntityCQRGolem) {
			super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
			this.bipedRightLeg.offsetX -= 3F / 16F;
			this.bipedLeftLeg.offsetX -= 1F / 16F;
		} else if (entityIn instanceof EntityCQRGremlin) {
			this.isRiding = false;
			super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
			this.rotationAnglesGremlin(limbSwing, limbSwingAmount, entityIn);
		} else if (entityIn instanceof EntityCQRIllager) {
			this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;
			this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
			EntityCQRIllager ent = (EntityCQRIllager) entityIn;
			this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
			this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
			this.bipedRightLeg.rotateAngleY = 0.0F;
			this.bipedLeftLeg.rotateAngleY = 0.0F;

			super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

			if (!ent.isAggressive() && !(ent.getHeldItemMainhand().getItem() instanceof ItemPotionHealing)) {
				float angle = new Float(Math.toRadians(-42.97));
				ModelCustomArmorBase.setRotationAngle(this.bipedLeftArm, angle, 0, 0);
				ModelCustomArmorBase.setRotationAngle(this.bipedRightArm, angle, 0, 0);
			}

			if (this.isRiding) {
				this.bipedRightLeg.rotateAngleX = -1.4137167F;
				this.bipedRightLeg.rotateAngleY = ((float) Math.PI / 10F);
				this.bipedRightLeg.rotateAngleZ = 0.07853982F;
				this.bipedLeftLeg.rotateAngleX = -1.4137167F;
				this.bipedLeftLeg.rotateAngleY = -((float) Math.PI / 10F);
				this.bipedLeftLeg.rotateAngleZ = -0.07853982F;
			}

			AbstractIllager.IllagerArmPose abstractillager$illagerarmpose = ((EntityCQRIllager) entityIn).getIllagerArmPose();

			if (ent.isAggressive() || ent.getHeldItemMainhand().getItem() instanceof ItemPotionHealing) {
				if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.ATTACKING) {
					float f = MathHelper.sin(this.swingProgress * (float) Math.PI);
					float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
					this.bipedRightArm.rotateAngleZ = 0.0F;
					this.bipedLeftArm.rotateAngleZ = 0.0F;
					this.bipedRightArm.rotateAngleY = 0.15707964F;
					this.bipedLeftArm.rotateAngleY = -0.15707964F;

					if (((EntityLivingBase) entityIn).getPrimaryHand() == EnumHandSide.RIGHT) {
						this.bipedRightArm.rotateAngleX = -1.8849558F + MathHelper.cos(ageInTicks * 0.09F) * 0.15F;
						this.bipedLeftArm.rotateAngleX = -0.0F + MathHelper.cos(ageInTicks * 0.19F) * 0.5F;
						this.bipedRightArm.rotateAngleX += f * 2.2F - f1 * 0.4F;
						this.bipedLeftArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
					} else {
						this.bipedRightArm.rotateAngleX = -0.0F + MathHelper.cos(ageInTicks * 0.19F) * 0.5F;
						this.bipedLeftArm.rotateAngleX = -1.8849558F + MathHelper.cos(ageInTicks * 0.09F) * 0.15F;
						this.bipedRightArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
						this.bipedLeftArm.rotateAngleX += f * 2.2F - f1 * 0.4F;
					}

					this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
					this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
					this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
					this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
				} else if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.BOW_AND_ARROW) {
					this.bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
					this.bipedRightArm.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
					this.bipedLeftArm.rotateAngleX = -0.9424779F + this.bipedHead.rotateAngleX;
					this.bipedLeftArm.rotateAngleY = this.bipedHead.rotateAngleY - 0.4F;
					this.bipedLeftArm.rotateAngleZ = ((float) Math.PI / 2F);
				}
			}
		} else if (entityIn instanceof EntityCQRMandril) {
			super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
			this.rotationAnglesMandril(entityIn);
		} else {
			super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		}
	}

	private void rotationAnglesMandril(Entity entityIn) {
		ModelCustomArmorBase.setRotationAngle(this.bipedBody, 0.39269908169872414F, 0.0F, 0.0F);

		this.bipedRightArm.setRotationPoint(-5.0F, 1.5F, -3.0F);
		this.bipedHead.setRotationPoint(0.0F, -6.0F, -5.0F);
		this.bipedLeftArm.setRotationPoint(5.0F, 1.5F, -3.0F);
	}

	private void rotationAnglesGremlin(float limbSwing, float limbSwingAmount, Entity entityIn) {
		this.bipedLeftArm.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
		this.bipedRightArm.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;

		this.bipedBody.setRotationPoint(0.0F, 13.0F, -4.0F);
		this.bipedRightArm.setRotationPoint(-4.0F, 14.5F, -2.5F);
		this.bipedLeftArm.setRotationPoint(4.0F, 14.5F, -1.5F);
		this.bipedRightLeg.setRotationPoint(-2.0F, 18.0F, 6.0F);
		this.bipedLeftLeg.setRotationPoint(2.0F, 18.0F, 6.0F);
		this.bipedHead.setRotationPoint(0.0F, 10.0F, -4.0F);
		this.bipedHeadwear.setRotationPoint(0.0F, 10.0F, -4.0F);
		ModelCustomArmorBase.setRotationAngle(this.bipedBody, 1.0471975511965976F, 0.0F, 0.0F);

		copyModelAngles(this.bipedHead, this.bipedHeadwear);
	}

	private void rotationAnglesEnderman(Entity entityIn) {
		this.bipedBody.rotateAngleX = 0.0F;
		this.bipedBody.rotationPointY = -14.0F;
		this.bipedBody.rotationPointZ = -0.0F;
		this.bipedRightLeg.rotateAngleX -= 0.0F;
		this.bipedLeftLeg.rotateAngleX -= 0.0F;
		this.bipedRightArm.rotateAngleX = (float) ((double) this.bipedRightArm.rotateAngleX * 0.5D);
		this.bipedLeftArm.rotateAngleX = (float) ((double) this.bipedLeftArm.rotateAngleX * 0.5D);
		this.bipedRightLeg.rotateAngleX = (float) ((double) this.bipedRightLeg.rotateAngleX * 0.5D);
		this.bipedLeftLeg.rotateAngleX = (float) ((double) this.bipedLeftLeg.rotateAngleX * 0.5D);

		if (this.bipedRightArm.rotateAngleX > 0.4F) {
			this.bipedRightArm.rotateAngleX = 0.4F;
		}

		if (this.bipedLeftArm.rotateAngleX > 0.4F) {
			this.bipedLeftArm.rotateAngleX = 0.4F;
		}

		if (this.bipedRightArm.rotateAngleX < -0.4F) {
			this.bipedRightArm.rotateAngleX = -0.4F;
		}

		if (this.bipedLeftArm.rotateAngleX < -0.4F) {
			this.bipedLeftArm.rotateAngleX = -0.4F;
		}

		if (this.bipedRightLeg.rotateAngleX > 0.4F) {
			this.bipedRightLeg.rotateAngleX = 0.4F;
		}

		if (this.bipedLeftLeg.rotateAngleX > 0.4F) {
			this.bipedLeftLeg.rotateAngleX = 0.4F;
		}

		if (this.bipedRightLeg.rotateAngleX < -0.4F) {
			this.bipedRightLeg.rotateAngleX = -0.4F;
		}

		if (this.bipedLeftLeg.rotateAngleX < -0.4F) {
			this.bipedLeftLeg.rotateAngleX = -0.4F;
		}

		this.bipedRightArm.rotationPointZ = 0.0F;
		this.bipedLeftArm.rotationPointZ = 0.0F;
		this.bipedRightLeg.rotationPointZ = 0.0F;
		this.bipedLeftLeg.rotationPointZ = 0.0F;
		this.bipedRightLeg.rotationPointY = -5.0F;
		this.bipedLeftLeg.rotationPointY = -5.0F;
		this.bipedHead.rotationPointZ = -0.0F;
		this.bipedHead.rotationPointY = -13.0F;
		this.bipedHeadwear.rotationPointX = this.bipedHead.rotationPointX;
		this.bipedHeadwear.rotationPointY = this.bipedHead.rotationPointY;
		this.bipedHeadwear.rotationPointZ = this.bipedHead.rotationPointZ;
		this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
		this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
		this.bipedHeadwear.rotateAngleZ = this.bipedHead.rotateAngleZ;
	}

	private void rotationAnglesArmorStand(Entity entityIn) {
		EntityArmorStand entityarmorstand = (EntityArmorStand) entityIn;
		this.bipedHead.rotateAngleX = 0.017453292F * entityarmorstand.getHeadRotation().getX();
		this.bipedHead.rotateAngleY = 0.017453292F * entityarmorstand.getHeadRotation().getY();
		this.bipedHead.rotateAngleZ = 0.017453292F * entityarmorstand.getHeadRotation().getZ();
		this.bipedHead.setRotationPoint(0.0F, 1.0F, 0.0F);
		this.bipedBody.rotateAngleX = 0.017453292F * entityarmorstand.getBodyRotation().getX();
		this.bipedBody.rotateAngleY = 0.017453292F * entityarmorstand.getBodyRotation().getY();
		this.bipedBody.rotateAngleZ = 0.017453292F * entityarmorstand.getBodyRotation().getZ();
		this.bipedLeftArm.rotateAngleX = 0.017453292F * entityarmorstand.getLeftArmRotation().getX();
		this.bipedLeftArm.rotateAngleY = 0.017453292F * entityarmorstand.getLeftArmRotation().getY();
		this.bipedLeftArm.rotateAngleZ = 0.017453292F * entityarmorstand.getLeftArmRotation().getZ();
		this.bipedRightArm.rotateAngleX = 0.017453292F * entityarmorstand.getRightArmRotation().getX();
		this.bipedRightArm.rotateAngleY = 0.017453292F * entityarmorstand.getRightArmRotation().getY();
		this.bipedRightArm.rotateAngleZ = 0.017453292F * entityarmorstand.getRightArmRotation().getZ();
		this.bipedLeftLeg.rotateAngleX = 0.017453292F * entityarmorstand.getLeftLegRotation().getX();
		this.bipedLeftLeg.rotateAngleY = 0.017453292F * entityarmorstand.getLeftLegRotation().getY();
		this.bipedLeftLeg.rotateAngleZ = 0.017453292F * entityarmorstand.getLeftLegRotation().getZ();
		this.bipedLeftLeg.setRotationPoint(1.9F, 11.0F, 0.0F);
		this.bipedRightLeg.rotateAngleX = 0.017453292F * entityarmorstand.getRightLegRotation().getX();
		this.bipedRightLeg.rotateAngleY = 0.017453292F * entityarmorstand.getRightLegRotation().getY();
		this.bipedRightLeg.rotateAngleZ = 0.017453292F * entityarmorstand.getRightLegRotation().getZ();
		this.bipedRightLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
		copyModelAngles(this.bipedHead, this.bipedHeadwear);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (entityIn instanceof EntityCQRGremlin) {
			this.isRiding = false;
		}
		if (entityIn instanceof EntityCQRTriton) {
			this.bipedLeftLeg.showModel = false;
			this.bipedRightLeg.showModel = false;
		}
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

	private Deque<Float> rotations = new LinkedList<>();

	public void render(Entity entity, float scale, LayerCQREntityArmor layer, ModelBiped model, EntityEquipmentSlot slot) {
		this.applyRotations(model);

		GlStateManager.pushMatrix();

		if (this.isChild) {
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);

			GlStateManager.pushMatrix();
			layer.setupHeadOffsets(this.bipedHead, slot);
			this.bipedHead.render(scale);
			GlStateManager.popMatrix();

			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);

			GlStateManager.pushMatrix();
			layer.setupBodyOffsets(this.bipedBody, slot);
			this.bipedBody.render(scale);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			layer.setupRightArmOffsets(this.bipedRightArm, slot);
			this.bipedRightArm.render(scale);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			layer.setupLeftArmOffsets(this.bipedLeftArm, slot);
			this.bipedLeftArm.render(scale);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			layer.setupRightLegOffsets(this.bipedRightLeg, slot);
			this.bipedRightLeg.render(scale);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			layer.setupLeftLegOffsets(this.bipedLeftLeg, slot);
			this.bipedLeftLeg.render(scale);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			layer.setupHeadwearOffsets(this.bipedHeadwear, slot);
			this.bipedHeadwear.render(scale);
			GlStateManager.popMatrix();
		} else {
			if (entity.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}

			GlStateManager.pushMatrix();
			layer.setupHeadOffsets(this.bipedHead, slot);
			this.bipedHead.render(scale);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			layer.setupBodyOffsets(this.bipedBody, slot);
			this.bipedBody.render(scale);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			layer.setupRightArmOffsets(this.bipedRightArm, slot);
			this.bipedRightArm.render(scale);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			layer.setupLeftArmOffsets(this.bipedLeftArm, slot);
			this.bipedLeftArm.render(scale);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			layer.setupRightLegOffsets(this.bipedRightLeg, slot);
			this.bipedRightLeg.render(scale);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			layer.setupLeftLegOffsets(this.bipedLeftLeg, slot);
			this.bipedLeftLeg.render(scale);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			layer.setupHeadwearOffsets(this.bipedHeadwear, slot);
			this.bipedHeadwear.render(scale);
			GlStateManager.popMatrix();
		}

		GlStateManager.popMatrix();

		this.resetRotations();
	}

	public void applyRotations(ModelBiped model) {
		this.applyRotations(this.bipedHeadwear, model.bipedHeadwear);
		this.applyRotations(this.bipedHead, model.bipedHead);
		this.applyRotations(this.bipedBody, model.bipedBody);
		this.applyRotations(this.bipedLeftArm, model.bipedLeftArm);
		this.applyRotations(this.bipedRightArm, model.bipedRightArm);
		this.applyRotations(this.bipedLeftLeg, model.bipedLeftLeg);
		this.applyRotations(this.bipedRightLeg, model.bipedRightLeg);
	}

	public void applyRotations(ModelRenderer target, ModelRenderer source) {
		this.rotations.push(target.offsetX);
		this.rotations.push(target.offsetY);
		this.rotations.push(target.offsetZ);
		this.rotations.push(target.rotateAngleX);
		this.rotations.push(target.rotateAngleY);
		this.rotations.push(target.rotateAngleZ);
		this.rotations.push(target.rotationPointX);
		this.rotations.push(target.rotationPointY);
		this.rotations.push(target.rotationPointZ);

		target.offsetX = source.offsetX;
		target.offsetY = source.offsetY;
		target.offsetZ = source.offsetZ;
		target.rotateAngleX = source.rotateAngleX;
		target.rotateAngleY = source.rotateAngleY;
		target.rotateAngleZ = source.rotateAngleZ;
		target.rotationPointX = source.rotationPointX;
		target.rotationPointY = source.rotationPointY;
		target.rotationPointZ = source.rotationPointZ;
	}

	public void resetRotations() {
		this.resetRotations(this.bipedHeadwear);
		this.resetRotations(this.bipedHead);
		this.resetRotations(this.bipedBody);
		this.resetRotations(this.bipedLeftArm);
		this.resetRotations(this.bipedRightArm);
		this.resetRotations(this.bipedLeftLeg);
		this.resetRotations(this.bipedRightLeg);
	}

	public void resetRotations(ModelRenderer target) {
		target.offsetX = this.rotations.pop();
		target.offsetY = this.rotations.pop();
		target.offsetZ = this.rotations.pop();
		target.rotateAngleX = this.rotations.pop();
		target.rotateAngleY = this.rotations.pop();
		target.rotateAngleZ = this.rotations.pop();
		target.rotationPointX = this.rotations.pop();
		target.rotationPointY = this.rotations.pop();
		target.rotationPointZ = this.rotations.pop();
	}

	public static void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}
