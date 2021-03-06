package com.teamcqr.chocolatequestrepoured.objects.entity.ai.spells;

import com.teamcqr.chocolatequestrepoured.objects.entity.bases.AbstractEntityCQR;
import com.teamcqr.chocolatequestrepoured.objects.entity.misc.EntitySummoningCircle;
import com.teamcqr.chocolatequestrepoured.objects.entity.misc.EntitySummoningCircle.ECircleTexture;
import com.teamcqr.chocolatequestrepoured.util.DungeonGenUtils;
import com.teamcqr.chocolatequestrepoured.util.VectorUtil;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAISummonMeteors extends AbstractEntityAIUseSpell {

	protected static final int MIN_FIREBALLS_PER_CAST = 3;
	protected static final int MAX_FIREBALLS_PER_CAST = 8;

	public EntityAISummonMeteors(AbstractEntityCQR entity) {
		super(entity);
	}

	@Override
	protected void castSpell() {
		Vec3d vector = this.entity.getLookVec().normalize();
		vector = vector.add(vector).add(vector).add(vector).add(vector);

		int ballCount = DungeonGenUtils.getIntBetweenBorders(MIN_FIREBALLS_PER_CAST, MAX_FIREBALLS_PER_CAST, this.entity.getRNG());

		if (ballCount > 0) {
			double angle = /* 180D */360D / (double) ballCount;
			// vector = VectorUtil.rotateVectorAroundY(vector, 270 + (angle /2));
			BlockPos[] spawnPositions = new BlockPos[ballCount];
			BlockPos centeredPos = this.entity.getPosition();
			if (this.entity.getAttackTarget() != null && !this.entity.getAttackTarget().isDead) {
				Vec3d v = this.entity.getAttackTarget().getPositionVector().subtract(this.entity.getPositionVector());
				v = new Vec3d(v.x / 2, v.y / 2, v.z / 2);
				centeredPos = centeredPos.add(v.x, v.y, v.z);
			}
			for (int i = 0; i < ballCount; i++) {
				spawnPositions[i] = centeredPos.add(new BlockPos(VectorUtil.rotateVectorAroundY(vector, angle * i)));
			}
			for (BlockPos p : spawnPositions) {
				if (this.entity.getNavigator().getPathToPos(p) != null) {
					// System.out.println("Pos: " + p.toString());
					ResourceLocation summon = new ResourceLocation("minecraft", "fireball");
					ECircleTexture texture = ECircleTexture.METEOR;
					if (this.entity.getRNG().nextInt(4) == 3) {
						summon = new ResourceLocation("minecraft", "small_fireball");
					}
					EntitySummoningCircle circle = new EntitySummoningCircle(this.entity.world, summon, 1.1F, texture, null);
					circle.setSummon(summon);
					circle.setPosition(p.getX(), p.getY() + 10, p.getZ());
					circle.setVelocityForSummon(new Vec3d(0D, -1D, 0D));

					this.entity.world.spawnEntity(circle);
				}
			}
		}
	}

	@Override
	protected int getCastingTime() {
		return 60;
	}

	@Override
	protected int getCastWarmupTime() {
		return 100;
	}

	@Override
	protected int getCastingInterval() {
		return 1200;
	}

	@Override
	protected SoundEvent getSpellPrepareSound() {
		return SoundEvents.ENTITY_WITHER_SPAWN;
	}

	@Override
	protected ESpellType getSpellType() {
		return ESpellType.SUMMON_FALLING_FIREBALLS;
	}

}
