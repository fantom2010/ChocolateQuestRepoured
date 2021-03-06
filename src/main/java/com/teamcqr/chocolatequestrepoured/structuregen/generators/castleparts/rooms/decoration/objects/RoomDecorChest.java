package com.teamcqr.chocolatequestrepoured.structuregen.generators.castleparts.rooms.decoration.objects;

import java.util.HashSet;

import com.teamcqr.chocolatequestrepoured.structuregen.WorldDungeonGenerator;
import com.teamcqr.chocolatequestrepoured.structuregen.dungeons.CastleDungeon;
import com.teamcqr.chocolatequestrepoured.structuregen.generators.castleparts.rooms.CastleRoomBase;
import com.teamcqr.chocolatequestrepoured.structuregen.lootchests.ELootTable;

import net.minecraft.block.BlockChest;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RoomDecorChest extends RoomDecorBlocksBase {
	public RoomDecorChest() {
		super();
	}

	@Override
	protected void makeSchematic() {
		this.schematic.add(new DecoBlockRotating(0, 0, 0, Blocks.CHEST.getDefaultState(), BlockChest.FACING, EnumFacing.SOUTH));
	}

	@Override
	public void build(World world, CastleRoomBase room, CastleDungeon dungeon, BlockPos start, EnumFacing side, HashSet<BlockPos> decoMap) {
		super.build(world, room, dungeon, start, side, decoMap);

		int[] chestIDs = room.getChestIDs();
		if (chestIDs != null) {
			TileEntityChest chest = (TileEntityChest) world.getTileEntity(start);
			if (chest != null) {
				int eltID = chestIDs[dungeon.getRandom().nextInt(chestIDs.length)];
				ResourceLocation resLoc = null;
				try {
					resLoc = ELootTable.values()[eltID].getResourceLocation();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				if (resLoc != null) {
					long seed = WorldDungeonGenerator.getSeed(world, start.getX() + start.getY(), start.getZ() + start.getY());
					chest.setLootTable(resLoc, seed);
				}
			}
		} else {
			System.out.format("Placed a chest but could not find a loot table for Room Type {%s}", room.getRoomType().toString());
		}
	}
}
