package com.teamcqr.chocolatequestrepoured.client.models.armor;

import net.minecraft.client.model.ModelRenderer;

public class ModelArmorSpider extends ModelCustomArmorBase {

	public ModelRenderer armLarm;
    public ModelRenderer helmL;
    public ModelRenderer helmR;
    public ModelRenderer arm1part1;
    public ModelRenderer arm2part1;
    public ModelRenderer arm3part1;
    public ModelRenderer arm4part1;
    public ModelRenderer arm5part1;
    public ModelRenderer arm6part1;
    public ModelRenderer bipedBody_1;
    public ModelRenderer arm1part2;
    public ModelRenderer arm2part2;
    public ModelRenderer arm3part2;
    public ModelRenderer arm4part2;
    public ModelRenderer arm5part2;
    public ModelRenderer arm6part2;
    public ModelRenderer armLarm_1;
	
	public ModelArmorSpider(float scale) {
		this(scale /2F, 128, 128);
	}
	
	public ModelArmorSpider(float scale, int textureWidth, int textureHeight) {
		super(scale, textureWidth, textureHeight);
		
        this.arm2part1 = new ModelRenderer(this, 64, 64);
        this.arm2part1.setRotationPoint(-2.0F, 2.0F, 2.0F);
        this.arm2part1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(arm2part1, 2.530727415391778F, 0.0F, -0.7853981633974483F);
        this.helmL = new ModelRenderer(this, 0, 64);
        this.helmL.mirror = true;
        this.helmL.setRotationPoint(4.0F, -2.0F, -4.0F);
        this.helmL.addBox(-1.0F, -1.0F, -2.0F, 1, 4, 4, scale *2F /3F);
        this.setRotateAngle(helmL, 0.0F, 0.7853981633974483F, 0.0F);
        this.helmR = new ModelRenderer(this, 0, 64);
        this.helmR.setRotationPoint(-4.0F, -2.0F, -4.0F);
        this.helmR.addBox(0.0F, -1.0F, -2.0F, 1, 4, 4, scale *2F /3F);
        this.setRotateAngle(helmR, 0.0F, -0.7853981633974483F, 0.0F);
        this.arm4part1 = new ModelRenderer(this, 64, 64);
        this.arm4part1.setRotationPoint(-2.0F, 5.0F, 2.0F);
        this.arm4part1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(arm4part1, 1.5707963267948966F, -0.7853981633974483F, 0.0F);
        this.arm5part2 = new ModelRenderer(this, 64, 74);
        this.arm5part2.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.arm5part2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(arm5part2, 0.0F, 0.0F, -1.5707963267948966F);
        this.arm5part1 = new ModelRenderer(this, 64, 64);
        this.arm5part1.setRotationPoint(2.0F, 8.0F, 2.0F);
        this.arm5part1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(arm5part1, 0.7853981633974483F, 0.7853981633974483F, 0.0F);
        this.armLarm = new ModelRenderer(this, 64, 0);
        this.armLarm.mirror = true;
        this.armLarm.setRotationPoint(-0.25F, 0.0F, 0.75F);
        this.armLarm.addBox(-1.0F, -4.0F, -5.0F, 2, 6, 6, 0.0F);
        this.setRotateAngle(armLarm, 0.0F, 0.7853981633974483F, 0.0F);
        this.armLarm_1 = new ModelRenderer(this, 64, 0);
        this.armLarm_1.mirror = true;
        this.armLarm_1.setRotationPoint(0.25F, 0.0F, 0.75F);
        this.armLarm_1.addBox(-1.0F, -4.0F, -5.0F, 2, 6, 6, 0.0F);
        this.setRotateAngle(armLarm_1, 0.0F, -0.7853981633974483F, 0.0F);
        this.arm1part1 = new ModelRenderer(this, 64, 64);
        this.arm1part1.setRotationPoint(2.0F, 2.0F, 2.0F);
        this.arm1part1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(arm1part1, 2.530727415391778F, 0.0F, 0.7853981633974483F);
        this.arm2part2 = new ModelRenderer(this, 64, 74);
        this.arm2part2.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.arm2part2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(arm2part2, 1.5707963267948966F, 0.0F, 0.0F);
        this.arm4part2 = new ModelRenderer(this, 64, 74);
        this.arm4part2.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.arm4part2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(arm4part2, 0.0F, 0.0F, 1.5707963267948966F);
        this.bipedBody_1 = new ModelRenderer(this, 64, 84);
        this.bipedBody_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody_1.addBox(-3.0F, 2.0F, 0.0F, 6, 8, 4, scale);
        this.setRotateAngle(bipedBody_1, 0.2617993877991494F, 0.0F, 0.0F);
        this.arm6part1 = new ModelRenderer(this, 64, 64);
        this.arm6part1.setRotationPoint(-2.0F, 8.0F, 2.0F);
        this.arm6part1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(arm6part1, 0.7853981633974483F, -0.7853981633974483F, 0.0F);
        this.arm3part1 = new ModelRenderer(this, 64, 64);
        this.arm3part1.setRotationPoint(2.0F, 5.0F, 2.0F);
        this.arm3part1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(arm3part1, 1.5707963267948966F, 0.7853981633974483F, 0.0F);
        this.arm1part2 = new ModelRenderer(this, 64, 74);
        this.arm1part2.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.arm1part2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(arm1part2, 1.5707963267948966F, 0.0F, 0.0F);
        this.arm3part2 = new ModelRenderer(this, 64, 74);
        this.arm3part2.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.arm3part2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(arm3part2, 0.0F, 0.0F, -1.5707963267948966F);
        this.arm6part2 = new ModelRenderer(this, 64, 74);
        this.arm6part2.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.arm6part2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(arm6part2, 0.0F, 0.0F, 1.5707963267948966F);
        
        this.bipedBody.addChild(this.arm2part1);
        this.bipedHead.addChild(this.helmL);
        this.bipedHead.addChild(this.helmR);
        this.bipedBody.addChild(this.arm4part1);
        this.arm5part1.addChild(this.arm5part2);
        this.bipedBody.addChild(this.arm5part1);
        this.bipedRightArm.addChild(this.armLarm);
        this.bipedLeftArm.addChild(this.armLarm_1);
        this.bipedBody.addChild(this.arm1part1);
        this.arm2part1.addChild(this.arm2part2);
        this.arm4part1.addChild(this.arm4part2);
        this.bipedBody.addChild(this.bipedBody_1);
        this.bipedBody.addChild(this.arm6part1);
        this.bipedBody.addChild(this.arm3part1);
        this.arm1part1.addChild(this.arm1part2);
        this.arm3part1.addChild(this.arm3part2);
        this.arm6part1.addChild(this.arm6part2);
	}
	
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
