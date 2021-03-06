package com.teamcqr.chocolatequestrepoured.structuregen;

import java.util.Random;

import javax.annotation.Nullable;

import com.teamcqr.chocolatequestrepoured.objects.banners.EBanners;
import com.teamcqr.chocolatequestrepoured.util.CQRConfig;
import com.teamcqr.chocolatequestrepoured.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public enum EDungeonMobType {

	DEFAULT(null, null, null),
	// DONT_REPLACE(null),
	DWARF(new ResourceLocation(Reference.MODID, "dwarf"), null, null),
	SKELETON(new ResourceLocation(Reference.MODID, "skeleton"), new ResourceLocation(Reference.MODID, "necromancer"), EBanners.SKELETON_BANNER),
	ZOMBIE(new ResourceLocation(Reference.MODID, "zombie"), new ResourceLocation(Reference.MODID, "lich"), null),
	PIRATE(new ResourceLocation(Reference.MODID, "pirate"), null, EBanners.PIRATE_BANNER),
	ILLAGER(new ResourceLocation(Reference.MODID, "illager"), null, EBanners.ILLAGER_BANNER),
	WALKER(new ResourceLocation(Reference.MODID, "walker"), null, EBanners.WALKER_ORDO),
	SPECTER(new ResourceLocation(Reference.MODID, "spectre"), null, null),
	ENDERMAN(new ResourceLocation(Reference.MODID, "enderman"), null, EBanners.ENDERMEN_BANNER),
	BOARMAN(new ResourceLocation(Reference.MODID, "boarman"), new ResourceLocation(Reference.MODID, "boar_mage"), EBanners.PIGMAN_BANNER),
	MINOTAUR(new ResourceLocation(Reference.MODID, "minotaur"), null, null),
	ORC(new ResourceLocation(Reference.MODID, "orc"), null, null),
	GOLEM(new ResourceLocation(Reference.MODID, "golem"), null, null),
	GOBLIN(new ResourceLocation(Reference.MODID, "goblin"), null, null),
	MUMMY(new ResourceLocation(Reference.MODID, "mummy"), null, null),
	OGRE(new ResourceLocation(Reference.MODID, "ogre"), null, null),
	GREMLIN(new ResourceLocation(Reference.MODID, "gremlin"), null, EBanners.GREMLIN_BANNER),
	TRITON(new ResourceLocation(Reference.MODID, "triton"), null, null);

	private static final Random RAND = new Random();
	private static final EDungeonMobType[][] MOB_WHEEL = new EDungeonMobType[][] {
			new EDungeonMobType[] { SKELETON },
			new EDungeonMobType[] { ZOMBIE, MUMMY },
			new EDungeonMobType[] { ILLAGER },
			// new EDungeonMobType[] {GOBLIN, ORC, OGRE},
			new EDungeonMobType[] { SPECTER },
			new EDungeonMobType[] { MINOTAUR },
			new EDungeonMobType[] { ENDERMAN }, };

	private ResourceLocation resLoc;
	private ResourceLocation bossResLoc;
	private EBanners banner;

	private EDungeonMobType(ResourceLocation resLoc, ResourceLocation bossResLoc, EBanners banner) {
		this.resLoc = resLoc;
		this.banner = banner;
		this.bossResLoc = bossResLoc;
	}

	public ResourceLocation getEntityResourceLocation() {
		return this.resLoc;
	}

	public ResourceLocation getBossResourceLocation() {
		return this.bossResLoc;
	}

	public EBanners getBanner() {
		return this.banner;
	}

	/**
	 * The parameters x and z are block coordinates!<br>
	 * <br>
	 * Mob spawns depending on distance: Raising from near to far:<br>
	 * Skeleton<br>
	 * Zombie/Mummy<br>
	 * Illager<br>
	 * (Goblin/Orc/Ogre currently disabled)<br>
	 * Specter<br>
	 * Minotaur<br>
	 * Endermen<br>
	 */
	public static EDungeonMobType getMobTypeDependingOnDistance(World world, int x, int z) {
		BlockPos spawnPoint = world.getSpawnPoint();
		int x1 = x - spawnPoint.getX();
		int z1 = z - spawnPoint.getZ();
		int distToSpawn = (int) Math.sqrt((double) (x1 * x1 + z1 * z1));
		int index = distToSpawn / CQRConfig.mobs.mobTypeChangeDistance;

		if (index >= MOB_WHEEL.length) {
			index = RAND.nextInt(MOB_WHEEL.length);
		}

		return MOB_WHEEL[index][RAND.nextInt(MOB_WHEEL[index].length)];
	}

	@Nullable
	public static EDungeonMobType byString(String name) {
		for (EDungeonMobType e : EDungeonMobType.values()) {
			if (e.name().equalsIgnoreCase(name)) {
				return e;
			}
		}
		return null;
	}

}
