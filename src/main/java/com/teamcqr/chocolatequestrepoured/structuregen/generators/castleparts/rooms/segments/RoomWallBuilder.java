package com.teamcqr.chocolatequestrepoured.structuregen.generators.castleparts.rooms.segments;

import com.teamcqr.chocolatequestrepoured.structuregen.dungeons.CastleDungeon;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class RoomWallBuilder
{
    protected BlockPos wallStart;
    protected WallOptions options;
    protected int doorStart = 0;
    protected int doorWidth = 0;
    protected int length;
    protected int height;
    protected EnumFacing side;
    protected Random random;

    public RoomWallBuilder(BlockPos wallStart, int height, int length, WallOptions options, EnumFacing side)
    {
        this.height = height;
        this.length = length;
        this.options = options;
        this.side = side;

        this.wallStart = wallStart;

        if (options.hasDoor())
        {
            this.doorStart = options.getDoor().getOffset();
            this.doorWidth = options.getDoor().getWidth();
        }
    }

    public void generate(World world, CastleDungeon dungeon)
    {
        BlockPos pos;
        IBlockState blockToBuild;

        EnumFacing iterDirection;

        if (side.getAxis() == EnumFacing.Axis.X)
        {
            iterDirection = EnumFacing.SOUTH;
        }
        else
        {
            iterDirection = EnumFacing.EAST;
        }

        for (int i = 0; i < length; i++)
        {
            for (int y = 0; y < height; y++)
            {
                pos = wallStart.offset(iterDirection, i).offset(EnumFacing.UP, y);
                blockToBuild = getBlockToBuild(pos, dungeon);
                world.setBlockState(pos, blockToBuild);
            }
        }
    }

    protected IBlockState getBlockToBuild(BlockPos pos, CastleDungeon dungeon)
    {
        if (options.hasWindow())
        {
            return getBlockBasicGlass(pos, dungeon);
        }
        else if (options.hasDoor())
        {
            return getBlockDoor(pos, dungeon);
        }
        else
        {
            return dungeon.getWallBlock().getDefaultState();
        }
    }

    protected IBlockState getBlockDoor(BlockPos pos, CastleDungeon dungeon)
    {
        IBlockState blockToBuild = dungeon.getWallBlock().getDefaultState();
        int y = pos.getY() - wallStart.getY();
        int dist = getLengthPoint(pos);

        if (withinDoorWidth(dist))
        {
            if (y == 0)
            {
                blockToBuild = dungeon.getWallBlock().getDefaultState();
            }
            else if (y < DoorPlacement.DEFAULT_HEIGHT)
            {
                blockToBuild = Blocks.AIR.getDefaultState();
            }
        }

        return blockToBuild;
    }

    private IBlockState getBlockBasicGlass(BlockPos pos, CastleDungeon dungeon)
    {
        int y = pos.getY() - wallStart.getY();
        int dist = getLengthPoint(pos);

        if ((y == 3 || y == 4) && (dist == length / 2))
        {
            return Blocks.GLASS_PANE.getDefaultState();
        } else
        {
            return dungeon.getWallBlock().getDefaultState();
        }
    }

    private IBlockState getBlockBasicBars(BlockPos pos, CastleDungeon dungeon)
    {
        int y = pos.getY();
        int dist = getLengthPoint(pos);

        if ((y == 3 || y == 4) && (dist == length / 2))
        {
            return Blocks.IRON_BARS.getDefaultState();
        } else
        {
            return dungeon.getWallBlock().getDefaultState();
        }
    }

    /*
     * Whether to build a window is usually determined by how far along the wall we are.
     * This function gets the relevant length along the wall based on if we are a horizontal
     * wall or a vertical wall.
     */
    protected int getLengthPoint(BlockPos pos)
    {
        if (side.getAxis() == EnumFacing.Axis.X)
        {
            return pos.getZ() - wallStart.getZ();
        } else
        {
            return pos.getX() - wallStart.getX();
        }
    }

    protected boolean withinDoorWidth(int value)
    {
        int relativeToDoor = value - doorStart;
        return (relativeToDoor >= 0 && relativeToDoor < doorWidth);
    }
}
