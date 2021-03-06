package com.teamcqr.chocolatequestrepoured.client.models.armor;

import net.minecraft.client.model.ModelRenderer;

public class ModelArmorBull extends ModelCustomArmorBase {

    public ModelRenderer pauldronR1;
    public ModelRenderer pauldronR2;
    public ModelRenderer shoulderHornR1;
    public ModelRenderer shoulderHornR2;
    public ModelRenderer shoulderHornR3;
    public ModelRenderer hornL1;
    public ModelRenderer hornR1;
    public ModelRenderer hornL2;
    public ModelRenderer hornL3;
    public ModelRenderer hornR2;
    public ModelRenderer hornR3;
    public ModelRenderer pauldronL1;
    public ModelRenderer pauldronL2;
    public ModelRenderer shoulderHornL1;
    public ModelRenderer shoulderHornL2;
    public ModelRenderer shoulderHornL3;

    public ModelArmorBull(float scaleIn) {
    	super(scaleIn, 128, 128);
    	float scale = scaleIn * 0.75F;
        this.pauldronL1 = new ModelRenderer(this, 64, 0);
        this.pauldronL1.setRotationPoint(2.5F, 1.0F, 0.0F);
        this.pauldronL1.addBox(0.0F, -1.0F, -2.0F, 3, 2, 4, scale);
        this.setRotateAngle(pauldronL1, 0.0F, 0.0F, 0.39269908169872414F);
        this.hornL2 = new ModelRenderer(this, 0, 70);
        this.hornL2.mirror = true;
        this.hornL2.setRotationPoint(0.0F, -3.0F, 0.6F);
        this.hornL2.addBox(-1.0F, -1.0F, -1.0F, 2, 3, 2,scale);
        this.setRotateAngle(hornL2, -0.3839724354387525F, 0.0F, 0.0F);
        this.hornR3 = new ModelRenderer(this, 0, 75);
        this.hornR3.setRotationPoint(0.0F, -0.5F, -0.2F);
        this.hornR3.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, scale);
        this.setRotateAngle(hornR3, -0.3839724354387525F, 0.0F, 0.0F);
        this.pauldronR1 = new ModelRenderer(this, 64, 0);
        this.pauldronR1.setRotationPoint(-2.5F, 1.0F, 0.0F);
        this.pauldronR1.addBox(-3.0F, -1.0F, -2.0F, 3, 2, 4, scale);
        this.setRotateAngle(pauldronR1, 0.0F, 0.0F, -0.39269908169872414F);
        this.hornR2 = new ModelRenderer(this, 0, 70);
        this.hornR2.setRotationPoint(0.0F, -3.0F, 0.6F);
        this.hornR2.addBox(-1.0F, -1.0F, -1.0F, 2, 3, 2, scale);
        this.setRotateAngle(hornR2, -0.3839724354387525F, 0.0F, 0.0F);
        this.shoulderHornR3 = new ModelRenderer(this, 64, 22);
        this.shoulderHornR3.setRotationPoint(-0.1F, -2.8F, 0.1F);
        this.shoulderHornR3.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, scale);
        this.setRotateAngle(shoulderHornR3, 0.0F, 0.0F, 0.3839724354387525F);
        this.hornL1 = new ModelRenderer(this, 0, 64);
        this.hornL1.mirror = true;
        this.hornL1.setRotationPoint(3.2F, -7.5F, -3.2F);
        this.hornL1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, scale);
        this.setRotateAngle(hornL1, 1.1780972450961724F, -0.7853981633974483F, 0.0F);
        this.shoulderHornL1 = new ModelRenderer(this, 64, 11);
        this.shoulderHornL1.mirror = true;
        this.shoulderHornL1.setRotationPoint(-0.8F, -1.6F, 0.0F);
        this.shoulderHornL1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, scale);
        this.setRotateAngle(shoulderHornL1, 0.0F, 0.0F, 0.19198621771937624F);
        this.pauldronR2 = new ModelRenderer(this, 64, 6);
        this.pauldronR2.setRotationPoint(-0.5F, -1.5F, 0.0F);
        this.pauldronR2.addBox(-1.0F, -0.5F, -2.0F, 2, 1, 4, scale);
        this.hornL3 = new ModelRenderer(this, 0, 75);
        this.hornL3.mirror = true;
        this.hornL3.setRotationPoint(0.0F, -0.5F, -0.2F);
        this.hornL3.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, scale);
        this.setRotateAngle(hornL3, -0.3839724354387525F, 0.0F, 0.0F);
        this.hornR1 = new ModelRenderer(this, 0, 64);
        this.hornR1.setRotationPoint(-3.2F, -7.5F, -3.2F);
        this.hornR1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, scale);
        this.setRotateAngle(hornR1, 1.1780972450961724F, 0.7853981633974483F, 0.0F);
        this.shoulderHornR1 = new ModelRenderer(this, 64, 11);
        this.shoulderHornR1.setRotationPoint(2.0F, -1.6F, 0.0F);
        this.shoulderHornR1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, scale);
        this.setRotateAngle(shoulderHornR1, 0.0F, 0.0F, -0.19198621771937624F);
        this.pauldronL2 = new ModelRenderer(this, 64, 6);
        this.pauldronL2.setRotationPoint(-0.5F, -1.5F, 0.0F);
        this.pauldronL2.addBox(0.0F, -0.5F, -2.0F, 2, 1, 4, scale);
        this.shoulderHornL2 = new ModelRenderer(this, 64, 17);
        this.shoulderHornL2.mirror = true;
        this.shoulderHornL2.setRotationPoint(0.2F, -1.0F, 0.0F);
        this.shoulderHornL2.addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2, scale);
        this.setRotateAngle(shoulderHornL2, 0.0F, 0.0F, -0.3839724354387525F);
        this.shoulderHornL3 = new ModelRenderer(this, 64, 22);
        this.shoulderHornL3.mirror = true;
        this.shoulderHornL3.setRotationPoint(0.1F, -2.8F, 0.1F);
        this.shoulderHornL3.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, scale);
        this.setRotateAngle(shoulderHornL3, 0.0F, 0.0F, -0.3839724354387525F);
        this.shoulderHornR2 = new ModelRenderer(this, 64, 17);
        this.shoulderHornR2.setRotationPoint(-0.2F, -1.0F, 0.0F);
        this.shoulderHornR2.addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2, scale);
        this.setRotateAngle(shoulderHornR2, 0.0F, 0.0F, 0.3839724354387525F);
        
        this.bipedLeftArm.addChild(this.pauldronL1);
        this.hornL1.addChild(this.hornL2);
        this.hornR2.addChild(this.hornR3);
        this.bipedRightArm.addChild(this.pauldronR1);
        this.hornR1.addChild(this.hornR2);
        this.shoulderHornR2.addChild(this.shoulderHornR3);
        this.bipedHead.addChild(this.hornL1);
        this.pauldronL2.addChild(this.shoulderHornL1);
        this.pauldronR1.addChild(this.pauldronR2);
        this.hornL2.addChild(this.hornL3);
        this.bipedHead.addChild(this.hornR1);
        this.pauldronR2.addChild(this.shoulderHornR1);
        this.pauldronL1.addChild(this.pauldronL2);
        this.shoulderHornL1.addChild(this.shoulderHornL2);
        this.shoulderHornL2.addChild(this.shoulderHornL3);
        this.shoulderHornR1.addChild(this.shoulderHornR2);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
