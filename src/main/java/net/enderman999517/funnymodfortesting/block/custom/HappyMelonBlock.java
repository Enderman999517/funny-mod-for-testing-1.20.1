package net.enderman999517.funnymodfortesting.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class HappyMelonBlock extends HorizontalFacingBlock {
    public HappyMelonBlock(Settings settings) {
        super(settings);
    }

    public static DirectionProperty FACING = DirectionProperty.of("facing",
            Direction.NORTH,
            Direction.EAST,
            Direction.SOUTH,
            Direction.WEST);

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }


    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
