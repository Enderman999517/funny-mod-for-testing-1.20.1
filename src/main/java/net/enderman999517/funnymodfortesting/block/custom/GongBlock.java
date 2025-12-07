package net.enderman999517.funnymodfortesting.block.custom;

import net.enderman999517.funnymodfortesting.block.entity.GongBlockEntity;
import net.enderman999517.funnymodfortesting.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
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
    private static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0,0,6, 16,17,10);
    private static final VoxelShape EAST_SHAPE = Block.createCuboidShape(6,0,0, 9,17,16);

    public GongBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        return switch (direction) {
            case EAST, WEST -> EAST_SHAPE;
            default -> NORTH_SHAPE;
        };
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
        return this.ring(world, state, hit, player, pos) ? ActionResult.success(world.isClient) : ActionResult.PASS;
    }

    private boolean ring(World world, BlockState state, BlockHitResult hitResult, PlayerEntity player, BlockPos pos) {
        Direction hitSide = hitResult.getSide();
        Direction facing = state.get(FACING);
        Direction facingOpposite = state.get(FACING).getOpposite();

        if (hitSide == facing || hitSide == facingOpposite) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof GongBlockEntity gong) {
                gong.incrementRings(world, pos, player);
                gong.startSwing(hitSide.getOpposite());
            }
            return true;
        } else return false;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.GONG_BLOCK_ENTITY, GongBlockEntity::tick);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {

        Direction direction = ctx.getSide();
        BlockPos blockPos = ctx.getBlockPos();
        Direction.Axis axis = direction.getAxis();
        if (axis == Direction.Axis.Y) {
            BlockState blockState = this.getDefaultState()
                    .with(FACING, ctx.getHorizontalPlayerFacing());
            if (blockState.canPlaceAt(ctx.getWorld(), blockPos)) {
                return blockState;
            }
        } else {
            BlockState blockState = this.getDefaultState().with(FACING, direction.getOpposite());
            if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                return blockState;
            }
        }
        //return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
