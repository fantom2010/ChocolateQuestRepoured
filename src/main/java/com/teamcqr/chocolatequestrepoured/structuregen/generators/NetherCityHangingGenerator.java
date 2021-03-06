package com.teamcqr.chocolatequestrepoured.structuregen.generators;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.teamcqr.chocolatequestrepoured.structuregen.PlateauBuilder;
import com.teamcqr.chocolatequestrepoured.structuregen.WorldDungeonGenerator;
import com.teamcqr.chocolatequestrepoured.structuregen.dungeons.FloatingNetherCity;
import com.teamcqr.chocolatequestrepoured.structuregen.generation.IStructure;
import com.teamcqr.chocolatequestrepoured.structuregen.structurefile.CQStructure;
import com.teamcqr.chocolatequestrepoured.structuregen.structurefile.EPosType;
import com.teamcqr.chocolatequestrepoured.util.DungeonGenUtils;
import com.teamcqr.chocolatequestrepoured.util.ThreadingUtil;
import com.teamcqr.chocolatequestrepoured.util.VectorUtil;

import net.minecraft.init.Blocks;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.structure.template.PlacementSettings;

public class NetherCityHangingGenerator implements IDungeonGenerator {

	//TODO: Air bubble around the whole thing
	
	private FloatingNetherCity dungeon;
	private int islandCount = 1;
	private int islandDistance = 1;
	private HashMap<BlockPos, File> structureMap = new HashMap<BlockPos, File>();

	// This needs to calculate async (island blocks, chain blocks, air blocks)

	public NetherCityHangingGenerator(FloatingNetherCity generator) {
		this.dungeon = generator;
		this.islandCount = this.dungeon.getBuildingCount(new Random());
		this.islandDistance = this.dungeon.getIslandDistance();
	}

	@Override
	public void preProcess(World world, Chunk chunk, int x, int y, int z, List<List<? extends IStructure>> lists) {
		Random rdm = new Random();
		long seed = WorldDungeonGenerator.getSeed(world, chunk.x + y * x - z, chunk.z + y * z + x);
		rdm.setSeed(seed);
		this.islandCount = this.dungeon.getBuildingCount(rdm);
		// Calculates the positions and creates the island objects
		// positions are the !!CENTERS!! of the platforms, the structures positions are calculated by the platforms themselves
		// Radius = sqrt(((Longer side of building) / 2)^2 *2) +5
		// Chain start pos: diagonal go (radius / 3) * 2 -1 blocks, here start building up the chains...
		BlockPos nextIslandPos = new BlockPos(x, y, z);
		BlockPos center = new BlockPos(x, y, z);

		rdm = new Random();

		// DONE: Carve out cave -> Need to specify the height in the dungeon
		int distMax = new Double((this.islandDistance * 1.5D) * (this.islandCount / 10D + 2D)).intValue();
		BlockPos lower = new BlockPos(x - distMax, y - this.dungeon.getYFactorHeight(), z - distMax);
		BlockPos upper = new BlockPos(x + distMax, y + (this.dungeon.getYFactorHeight() * 1.5D), z + distMax);
		if (this.dungeon.digAirCave()) {
			PlateauBuilder pb = new PlateauBuilder();
			// pb.createCave(rdm, lower, upper, WorldDungeonGenerator.getSeed(world, x - y, z + y), world);
			lists.add(pb.makeRandomBlobList(rdm, Blocks.AIR, lower, upper, 4, WorldDungeonGenerator.getSeed(world, x - y, z + y)));
		}

		for (int i = 0; i < this.islandCount; i++) {
			nextIslandPos = this.getNextIslandPos(center, i);

			File sf = this.dungeon.pickStructure();
			if (sf != null) {
				this.structureMap.put(nextIslandPos, sf);
			}
		}
	}

	@Override
	public void buildStructure(World world, Chunk chunk, int x, int y, int z, List<List<? extends IStructure>> lists) {
		// Builds the platforms
		// Builds the chains
		// TODO: Methods to get central buildings
		BlockPos center = new BlockPos(x, y, z);
		CQStructure censtruct = new CQStructure(this.dungeon.pickCentralStructure());
		center = new BlockPos(center.getX() - censtruct.getSize().getX(), y, center.getZ() - censtruct.getSize().getZ());
		this.buildBuilding(censtruct, center, world, world.getChunkFromBlockCoords(center), lists);

		for (BlockPos bp : this.structureMap.keySet()) {
			CQStructure structure = new CQStructure(this.structureMap.get(bp));
			BlockPos pastePos = bp.subtract(structure.getSize());
			pastePos = new BlockPos(pastePos.getX(), y, pastePos.getZ());

			this.buildBuilding(structure, pastePos, world, world.getChunkFromBlockCoords(bp), lists);
		}
	}

	@Override
	public void postProcess(World world, Chunk chunk, int x, int y, int z, List<List<? extends IStructure>> lists) {
		// Builds the structures

	}

	@Override
	public void fillChests(World world, Chunk chunk, int x, int y, int z, List<List<? extends IStructure>> lists) {
		// Unused

	}

	@Override
	public void placeSpawners(World world, Chunk chunk, int x, int y, int z, List<List<? extends IStructure>> lists) {
		// Unused

	}

	@Override
	public void placeCoverBlocks(World world, Chunk chunk, int x, int y, int z, List<List<? extends IStructure>> lists) {
		// Unused or maybe later implemented

	}

	// calculates a fitting position for the next island
	private BlockPos getNextIslandPos(BlockPos centerPos, int islandIndex) {
		// BlockPos retPos = new BlockPos(prevIslandPos);
		/*
		 * BlockPos retPos = prevIslandPos.add(VectorUtil.rotateVectorAroundY(new Vec3i(0, 0, this.dungeon.getIslandDistance() * 1.5), (360D / this.islandCount) * islandIndex));
		 * 
		 * while(!structureMap.isEmpty() || !structureMap.containsKey(retPos) || locIsNotFine(retPos)) {
		 * //DONE: Calculate new position
		 * retPos = getNextIslandPos(retPos, islandIndex++);
		 * }
		 */
		BlockPos retPos = new BlockPos(centerPos);

		Vec3i vector = new Vec3i(0, 0, (this.islandDistance * 3D) * ((islandIndex) / 10 + 1));

		int degreeMultiplier = islandIndex;// (Math.floorDiv(islandIndex, 10) *10);
		if (this.islandCount > 10) {
			degreeMultiplier -= (((islandIndex) / 10) * 10);
		}
		double angle = this.islandCount >= 10 ? 36D : 360D / this.islandCount;
		retPos = retPos.add(VectorUtil.rotateVectorAroundY(vector, degreeMultiplier * angle));

		return retPos;
	}
	/*
	 * private boolean locIsNotFine(BlockPos pos) {
	 * for(BlockPos p : structureMap.keySet()) {
	 * double dist = pos.getDistance(p.getX(), pos.getY(), p.getZ());
	 * dist = Math.abs(dist);
	 * if(dist < dungeon.getMinIslandDistance() || dist > dungeon.getMaxIslandDistance() || p.equals(pos)) {
	 * return true;
	 * }
	 * }
	 * return false;
	 * }
	 */

	// Constructs an Island in this shape:
	/*
	 * Dec Rad
	 * # # # # # # # # # # # # # # # # # # # # 0 10
	 * # # # # # # # # # # # # # # # # # # 1 9
	 * # # # # # # # # # # # # # # 2 7
	 * # # # # # # # # 3 4
	 * 
	 */
	private void buildBuilding(CQStructure structure, BlockPos pos, World world, Chunk chunk, List<List<? extends IStructure>> lists) {
		int radius = structure.getSize().getX() > structure.getSize().getZ() ? structure.getSize().getX() : structure.getSize().getZ();

		// r = sqrt(((Longer side of building) / 2)^2 *2) +5
		radius = new Double(Math.sqrt(Math.pow((double) radius / 2.0D, 2.0D) * 2.0D) + 5).intValue();

		BlockPos center = pos.add(-radius, 0, -radius);
		center = center.add(0, this.dungeon.getRandomHeightVariation(), 0);

		this.buildPlatform(center, radius, world);

		// DONE: Dig out cave
		// DONE: Not single caverns but one large cavern for everything ?
		/*
		 * PlateauBuilder builder = new PlateauBuilder();
		 * builder.createCave(new Random(), pos, pos.add(structure.getSizeX(), structure.getSizeY(), structure.getSizeZ()), world.getSeed(), world);
		 */

		PlacementSettings settings = new PlacementSettings();
		settings.setMirror(Mirror.NONE);
		settings.setRotation(Rotation.NONE);
		settings.setReplacedBlock(Blocks.STRUCTURE_VOID);
		settings.setIntegrity(1.0F);

		center = center.add(0, 1, 0);

		for (List<? extends IStructure> list : structure.addBlocksToWorld(world, center, settings, EPosType.CENTER_XZ_LAYER, this.dungeon, chunk.x, chunk.z))
			lists.add(list);

		/*
		 * CQDungeonStructureGenerateEvent event = new CQDungeonStructureGenerateEvent(this.dungeon, pos, new BlockPos(structure.getSizeAsVec()), world);
		 * if(structure != null && structure.getShieldCorePosition() != null) {
		 * event.setShieldCorePosition(structure.getShieldCorePosition());
		 * }
		 * MinecraftForge.EVENT_BUS.post(event);
		 */
	}

	private void buildPlatform(BlockPos center, int radius, World world) {
		List<BlockPos> blocks = new ArrayList<>();
		int decrementor = 0;
		int rad = (new Double(radius * 1.5D)).intValue();
		while (decrementor < (rad / 2)) {
			rad -= decrementor;

			for (int iX = -rad; iX <= rad; iX++) {
				for (int iZ = -rad; iZ <= rad; iZ++) {
					if (DungeonGenUtils.isInsideCircle(iX, iZ, rad, center)) {
						blocks.add(center.add(iX, -decrementor, iZ));
					}
				}
			}

			decrementor++;
		}

		if (this.dungeon.doBuildChains()) {
			this.buildChain(center.add(radius * 0.9, -2, radius * 0.9), world, 0);
			this.buildChain(center.add(-radius * 0.9, -2, -radius * 0.9), world, 0);
			this.buildChain(center.add(-radius * 0.9, -2, radius * 0.9), world, 1);
			this.buildChain(center.add(radius * 0.9, -2, -radius * 0.9), world, 1);
		}

		ThreadingUtil.passListWithBlocksToThreads(blocks, this.dungeon.getIslandBlock(), world, 100, true);
	}

	private void buildChain(BlockPos pos, World world, int iOffset) {
		/*
		 * Chain from side:
		 * #
		 * # # #
		 * # # #
		 * # #
		 * # #
		 * # #
		 * # # #
		 * # # #
		 * #
		 */
		int deltaYPerChainSegment = 5;

		int chainCount = (255 - pos.getY()) / 7;
		for (int i = 0; i < chainCount; i++) {
			// Check the direction of the chain
			int yOffset = i * deltaYPerChainSegment;
			BlockPos startPos = pos.add(0, yOffset, 0);
			if ((i + iOffset) % 2 > 0) {
				this.buildChainSegment(startPos, startPos.north(), startPos.south(), startPos.north(2).up(), startPos.south(2).up(), world);
			} else {
				this.buildChainSegment(startPos, startPos.east(), startPos.west(), startPos.east(2).up(), startPos.west(2).up(), world);
			}
		}
	}

	private void buildChainSegment(BlockPos lowerCenter, BlockPos lowerLeft, BlockPos lowerRight, BlockPos lowerBoundL, BlockPos lowerBoundR, World world) {
		world.setBlockState(lowerCenter, this.dungeon.getChainBlock().getDefaultState());
		world.setBlockState(lowerCenter.add(0, 6, 0), this.dungeon.getChainBlock().getDefaultState());

		world.setBlockState(lowerLeft, this.dungeon.getChainBlock().getDefaultState());
		world.setBlockState(lowerLeft.add(0, 6, 0), this.dungeon.getChainBlock().getDefaultState());

		world.setBlockState(lowerRight, this.dungeon.getChainBlock().getDefaultState());
		world.setBlockState(lowerRight.add(0, 6, 0), this.dungeon.getChainBlock().getDefaultState());

		for (int i = 0; i < 5; i++) {
			world.setBlockState(lowerBoundL.add(0, i, 0), this.dungeon.getChainBlock().getDefaultState());
			world.setBlockState(lowerBoundR.add(0, i, 0), this.dungeon.getChainBlock().getDefaultState());
		}
	}

}
