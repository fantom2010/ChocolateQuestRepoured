package com.teamcqr.chocolatequestrepoured.objects.entity.ai.spells;

import com.teamcqr.chocolatequestrepoured.objects.entity.bases.AbstractEntityCQR;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIExplosionRay extends AbstractEntityAIUseSpell {

	public EntityAIExplosionRay(AbstractEntityCQR entity) {
		super(entity);
	}

	@Override
	protected void castSpell() {
		int explosionCount = 1;
		Vec3d v = new Vec3d(this.entity.getAttackTarget().getPosition().subtract(this.entity.getPosition()));
		explosionCount = new Double(v.lengthVector()).intValue() / 2;
		v = v.normalize();
		BlockPos start = this.entity.getPosition();
		BlockPos[] positions = new BlockPos[explosionCount];
		for (int i = 1; i <= explosionCount; i++) {
			BlockPos p = start.add(v.x * i + 4 * Math.sin((i - 1) * 2), v.y * i, v.z * i + 4 * Math.sin((i - 1) * 2));
			positions[i - 1] = p;
		}

		for (BlockPos p : positions) {
			this.entity.world.newExplosion(this.entity, p.getX(), p.getY(), p.getZ(), 0.5F, this.entity.getRNG().nextBoolean(), true);
		}
	}

	@Override
	protected int getCastingTime() {
		return 100;
	}

	@Override
	protected int getCastingInterval() {
		return 160;
	}

	@Override
	protected int getCastWarmupTime() {
		return 60;
	}

	@Override
	protected SoundEvent getSpellPrepareSound() {
		return SoundEvents.ENTITY_CREEPER_PRIMED;
	}

	@Override
	protected ESpellType getSpellType() {
		return ESpellType.SUMMON_EXPLOSION_RAY;
	}

}
