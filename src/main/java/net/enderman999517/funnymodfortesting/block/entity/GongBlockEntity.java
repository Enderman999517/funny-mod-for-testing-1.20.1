package net.enderman999517.funnymodfortesting.block.entity;

import net.enderman999517.funnymodfortesting.FunnyModForTestingClient;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.UUID;

public class GongBlockEntity extends BlockEntity {
    private final int MAX_RING_TIMES = 3;
    private final HashMap<UUID, Integer> ringTimes = new HashMap<>();

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
        UUID id = player.getUuid();
        int count = ringTimes.getOrDefault(id, 0) +1;

        if (count < MAX_RING_TIMES) {
            ringTimes.put(id, count);
            player.sendMessage(Text.literal("a"));
        } else {
            triggerEvent(world, pos, player);
            ringTimes.put(id, 0);
        }
    }

    private void triggerEvent(World world, BlockPos pos, PlayerEntity player) {
        //player.playSound(SoundEvents.ITEM_TRIDENT_THUNDER, SoundCategory.BLOCKS, 1f, 1f);
        if (player instanceof ModEntityData modEntityData) {
            modEntityData.setHidden(true);
            modEntityData.setRenderingOverlay(true);
        }
    }
}
