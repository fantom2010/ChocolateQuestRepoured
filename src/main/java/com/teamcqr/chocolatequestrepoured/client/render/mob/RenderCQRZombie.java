package com.teamcqr.chocolatequestrepoured.client.render.mob;

import com.teamcqr.chocolatequestrepoured.objects.entity.mobs.EntityCQRZombie;
import com.teamcqr.chocolatequestrepoured.util.Reference;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCQRZombie extends RenderLiving<EntityCQRZombie>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation((Reference.MODID + ":textures/entity/entity_mob_cqrzombie.png"));

    public RenderCQRZombie(RenderManager manager)
    {
        //Using the vanilla model - if we wanted to use a custom model this is where we could insert that instead
        super(manager, new ModelZombie(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCQRZombie entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityCQRZombie entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}