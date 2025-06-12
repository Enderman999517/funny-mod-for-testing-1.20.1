package net.enderman999517.funnymodfortesting.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.Direction;

public class HappyMelonBlock extends Block {
    public HappyMelonBlock(Settings settings) {
        super(settings);
    }

    public static DirectionProperty FACING = DirectionProperty.of("number",
            Direction.NORTH,
            Direction.EAST);

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
