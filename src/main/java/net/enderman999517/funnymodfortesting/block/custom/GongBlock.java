package net.enderman999517.funnymodfortesting.block.custom;

import net.enderman999517.funnymodfortesting.block.entity.GongBlockEntity;
import net.enderman999517.funnymodfortesting.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Attachment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GongBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final VoxelShape SHAPE = Block.createCuboidShape(0,0,6, 16,17,10);

    public GongBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GongBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof GongBlockEntity gong) {
                gong.incrementRings(world, pos, player);
                gong.startSwing();
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.GONG_BLOCK_ENTITY, GongBlockEntity::tick);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {

        Direction direction = ctx.getSide();
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        Direction.Axis axis = direction.getAxis();
        if (axis == Direction.Axis.Y) {
            BlockState blockState = this.getDefaultState()
                    .with(FACING, ctx.getHorizontalPlayerFacing());
            if (blockState.canPlaceAt(ctx.getWorld(), blockPos)) {
                return blockState;
            }
        } else {
            boolean bl = axis == Direction.Axis.X
                    && world.getBlockState(blockPos.west()).isSideSolidFullSquare(world, blockPos.west(), Direction.EAST)
                    && world.getBlockState(blockPos.east()).isSideSolidFullSquare(world, blockPos.east(), Direction.WEST)
                    || axis == Direction.Axis.Z
                    && world.getBlockState(blockPos.north()).isSideSolidFullSquare(world, blockPos.north(), Direction.SOUTH)
                    && world.getBlockState(blockPos.south()).isSideSolidFullSquare(world, blockPos.south(), Direction.NORTH);
            BlockState blockState = this.getDefaultState().with(FACING, direction.getOpposite());
            if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                return blockState;
            }

            if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                return blockState;
            }
        }
        return null;
    }
}
