package net.enderman999517.funnymodfortesting.block.entity;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;

public class GongBlockEntity extends BlockEntity {
    private final int MAX_RING_TIMES = 3;
    private final HashMap<UUID, Integer> ringTimes = new HashMap<>();
    public int swingTicks = 0;
    public static final int MAX_SWING_TICKS = 50;
    public Direction swingDir = Direction.NORTH;
    private boolean swinging = false;

    public GongBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GONG_BLOCK_ENTITY, pos, state);
    }

    public void incrementRings(World world, BlockPos pos, PlayerEntity player) {
        UUID id = player.getUuid();
        int count = ringTimes.getOrDefault(id, 0) +1;

        if (count < MAX_RING_TIMES) {
            ringTimes.put(id, count);
        } else {
            triggerEvent(world, pos, player);
            ringTimes.put(id, 0);
        }
    }

    public void startSwing() {
        this.swingTicks = 0;
        this.swinging = true;
        markDirty();
    }

    public static void tick(World world, BlockPos pos, BlockState state, GongBlockEntity blockEntity) {
        if (blockEntity.swinging) {
            blockEntity.swingTicks++;
        }

        if (blockEntity.swingTicks >= MAX_SWING_TICKS) {
            blockEntity.swinging = false;
            blockEntity.swingTicks = 0;
        }
        blockEntity.sync();
    }


    private void triggerEvent(World world, BlockPos pos, PlayerEntity player) {
        //player.playSound(SoundEvents.ITEM_TRIDENT_THUNDER, SoundCategory.BLOCKS, 1f, 1f);
        if (player instanceof ModEntityData modEntityData) {
            modEntityData.setHidden(true);
            modEntityData.setRenderingOverlay(true);
        }
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("Ticks", swingTicks);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        swingTicks = nbt.getInt("Ticks");
    }

    public void sync() {
        if (world != null && !world.isClient) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }
}
