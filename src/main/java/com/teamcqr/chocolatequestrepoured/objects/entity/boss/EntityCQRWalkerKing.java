package com.teamcqr.chocolatequestrepoured.objects.entity.boss;

import com.teamcqr.chocolatequestrepoured.factions.EDefaultFaction;
import com.teamcqr.chocolatequestrepoured.init.ModItems;
import com.teamcqr.chocolatequestrepoured.objects.entity.Capes;
import com.teamcqr.chocolatequestrepoured.objects.entity.EBaseHealths;
import com.teamcqr.chocolatequestrepoured.objects.entity.ELootTablesBoss;
import com.teamcqr.chocolatequestrepoured.objects.entity.EntityEquipmentExtraSlot;
import com.teamcqr.chocolatequestrepoured.objects.entity.bases.AbstractEntityCQRBoss;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.BossInfo.Overlay;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityCQRWalkerKing extends AbstractEntityCQRBoss {
	
	private int lightningTick = 0;
	private int borderLightning = 20;
	private boolean active = false;
	private int activationCooldown = 80;

	public EntityCQRWalkerKing(World world) {
		this(world, 1);
	}
	
	public EntityCQRWalkerKing(World worldIn, int size) {
		super(worldIn, size);
		
		this.bossInfoServer.setColor(Color.PURPLE);
		this.bossInfoServer.setCreateFog(true);
		this.bossInfoServer.setDarkenSky(true);
		this.bossInfoServer.setOverlay(Overlay.PROGRESS);
		this.bossInfoServer.setPlayEndBossMusic(true);
	}

	
	@Override
	public void onLivingUpdate() {
		if(fallDistance > 12 && !world.isRemote) {
			BlockPos teleportPos = null;
			boolean teleport = getAttackTarget() != null || getHomePositionCQR() != null;
			if(getAttackTarget() != null) {
				Vec3d v = getPositionVector().subtract(getAttackTarget().getPositionVector());
				v = v.normalize();
				v = v.subtract(0, v.y, 0);
				v = v.scale(2);
				teleportPos = new BlockPos(getAttackTarget().getPositionVector().add(v));
			} else if(getHomePositionCQR() != null) {
				teleportPos = getHomePositionCQR();
			}
			if(teleport) {
				//spawn cloud
				for(int ix = -1; ix <= 1; ix++) {
					for(int iz = -1; iz <= 1; iz++) {
						((WorldServer)world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX + ix, posY +2, posZ +iz, 10, 0, 0, 0, 0.25, 0, 0, 0);
					}
				}
				world.playSound(posX, posY, posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.AMBIENT, 1, 1, true);
				attemptTeleport(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ());
			}
		}
		if(active) {
			if(getAttackTarget() == null && !world.isRemote) {
				activationCooldown--;
				if(activationCooldown < 0) {
					active = false;
					world.getWorldInfo().setThundering(false);
					activationCooldown = 80;
				}
			} 
			lightningTick++;
			if(lightningTick > borderLightning) {
				// strike lightning
				lightningTick = 0;
				borderLightning = 50;
				int x = -20 + getRNG().nextInt(41);
				int z = -15 + getRNG().nextInt(41);
				int y = -10 + getRNG().nextInt(21);
				
				EntityLightningBolt entitybolt = new EntityLightningBolt(world, posX +x, posY +y, posZ +z, false);
				world.spawnEntity(entitybolt);
			}
		}
		super.onLivingUpdate();
	}
	
	@Override
	public void onStruckByLightning(EntityLightningBolt lightningBolt) {
		this.heal(20F);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(source.getImmediateSource() != null && source.getImmediateSource() instanceof EntitySpectralArrow) {
			amount *= 2;
		}
		return super.attackEntityFrom(source, amount);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount, boolean sentFromPart) {
		if(source == DamageSource.WITHER) {
			this.heal(amount /2);
			return true;
		}
		if(source == DamageSource.FALL) {
			return true;
		}
		active = true;
		activationCooldown = 80;
		if(!world.isRemote && !world.getWorldInfo().isThundering()) {
			world.getWorldInfo().setCleanWeatherTime(0);
			world.getWorldInfo().setRainTime(400);
			world.getWorldInfo().setThunderTime(200);
			world.getWorldInfo().setRaining(true);
			world.getWorldInfo().setThundering(true);
		}
		return super.attackEntityFrom(source, amount, sentFromPart);
	}
	
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}
	
	@Override
	public boolean hasCape() {
		return this.deathTicks <= 0;
	}
	
	@Override
	public ResourceLocation getResourceLocationOfCape() {
		return Capes.CAPE_WALKER;
	}

	@Override
	protected ResourceLocation getLootTable() {
		return ELootTablesBoss.BOSS_WALKER_KING.getLootTable();
	}

	@Override
	public float getBaseHealth() {
		return EBaseHealths.WALKER_KING.getValue();
	}

	@Override
	public EDefaultFaction getDefaultFaction() {
		return EDefaultFaction.WALKERS;
	}

	@Override
	public int getTextureCount() {
		return 1;
	}

	@Override
	public boolean canRide() {
		return true;
	}

	@Override
	public boolean canOpenDoors() {
		return true;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_WITHER_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_WITHER_HURT;
	}
	
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_WITHER_DEATH;
	};
	
	@Override
	protected SoundEvent getFinalDeathSound() {
		return SoundEvents.ENTITY_ENDERMEN_DEATH;
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, getSword());
		this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(ModItems.SHIELD_WALKER_KING, 1));
		this.setItemStackToExtraSlot(EntityEquipmentExtraSlot.POTION, new ItemStack(ModItems.POTION_HEALING, 3));
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	private ItemStack getSword() {
		ItemStack sword = new ItemStack(ModItems.SWORD_WALKER, 1);
		
		for(int i = 0; i < 1 + getRNG().nextInt(3 * (world.getDifficulty().ordinal() +1)); i++) {
			sword = EnchantmentHelper.addRandomEnchantment(getRNG(), sword, 20 + getRNG().nextInt(41), true);
		}
		
		return sword;
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		world.getWorldInfo().setThundering(false);
		super.onDeath(cause);
	}
	
	@Override
	protected boolean usesEnderDragonDeath() {
		return true;
	}

	@Override
	protected boolean doesExplodeOnDeath() {
		return true;
	}
	
	@Override
	protected EnumParticleTypes getDeathAnimParticles() {
		return EnumParticleTypes.EXPLOSION_HUGE;
	}
	
}
