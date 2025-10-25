package net.enderman999517.funnymodfortesting.block.entity;

import com.mojang.serialization.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.SculkShriekerBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.Vibrations;
import org.jetbrains.annotations.Nullable;

public class GongBlockEntity extends BlockEntity {
    private static final int MAX_RINGING_TICKS = 50;
    private static final int MAX_RESONATING_TICKS = 40;
    private static final int MAX_BELL_HEARING_DISTANCE = 32;
    private long lastRingTime;
    public int ringTicks;
    public boolean ringing;
    public Direction lastSideHit;
    private boolean resonating;
    private int resonateTime;
    private final int MAX_RING_TIMES = 3;
    private int ringTimes = 0;

    public GongBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GONG_BLOCK_ENTITY, pos, state);
    }

    //@Override
    //public boolean onSyncedBlockEvent(int type, int data) {
    //    if (type == 1) {
    //        this.resonateTime = 0;
    //        this.lastSideHit = Direction.byId(data);
    //        this.ringTicks = 0;
    //        this.ringing = true;
    //        return true;
    //    } else {
    //        return super.onSyncedBlockEvent(type, data);
    //    }
    //}
//
    //private static void tick(World world, BlockPos pos, BlockState state, GongBlockEntity blockEntity) {
    //    if (blockEntity.ringing) {
    //        blockEntity.ringTicks++;
    //    }
//
    //    if (blockEntity.ringTicks >= MAX_RINGING_TICKS) {
    //        blockEntity.ringing = false;
    //        blockEntity.ringTicks = 0;
    //    }
//
    //    if (blockEntity.ringTicks >= 5 && blockEntity.resonateTime == 0) {
    //        blockEntity.resonating = true;
    //        world.playSound(null, pos, SoundEvents.BLOCK_BELL_RESONATE, SoundCategory.BLOCKS, 1.0F, 1.0F);
    //    }
//
    //    if (blockEntity.resonating) {
    //        if (blockEntity.resonateTime < 40) {
    //            blockEntity.resonateTime++;
    //        } else {
    //            blockEntity.resonating = false;
    //        }
    //    }
    //}
//
    //public static void clientTick(World world, BlockPos pos, BlockState state, GongBlockEntity blockEntity) {
    //    tick(world, pos, state, blockEntity);
    //}
//
    //public static void serverTick(World world, BlockPos pos, BlockState state, GongBlockEntity blockEntity) {
    //    tick(world, pos, state, blockEntity);
    //}
//
    //@Override
    //public void readNbt(NbtCompound nbt) {
    //    super.readNbt(nbt);
    //    if (nbt.contains("gong_ring_times", NbtElement.NUMBER_TYPE)) {
    //        this.ringTimes = nbt.getInt("gong_ring_times");
    //    }
    //}
//
    //@Override
    //protected void writeNbt(NbtCompound nbt) {
    //    super.writeNbt(nbt);
    //    nbt.putInt("gong_ring_times", this.ringTimes);
    //}
//
    //public void activateGongOnPlayer(ServerWorld world, @Nullable ServerPlayerEntity player) {
    //    if (player != null) {
    //        BlockState blockState = this.getCachedState();
    //        if (!(Boolean)blockState.get(SculkShriekerBlock.SHRIEKING)) {
    //            this.ringTimes = 0;
    //            if (this.trySyncWarningLevel(world, player)) {
    //                this.shriek(world, (Entity)player);
    //            }
    //        }
    //    }
    //}

    public void incrementRings(World world, BlockPos pos, PlayerEntity player) {
        ringTimes++;
        if (ringTimes <= 2) {
            world.playSound(null, pos, SoundEvents.ENTITY_EVOKER_CAST_SPELL, SoundCategory.BLOCKS);
        }
        if (ringTimes >= MAX_RING_TIMES) {
            triggerEvent(world, pos, player);
            ringTimes = 0;
        }
        markDirty();
    }

    private void triggerEvent(World world, BlockPos pos, PlayerEntity player) {
        world.playSound(null, pos, SoundEvents.ITEM_TRIDENT_THUNDER, SoundCategory.BLOCKS);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("ringTimes", ringTimes);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        ringTimes = nbt.getInt("ringTimes");
    }
}
