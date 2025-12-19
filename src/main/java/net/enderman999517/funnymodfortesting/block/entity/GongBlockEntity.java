package net.enderman999517.funnymodfortesting.block.entity;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;

public class GongBlockEntity extends BlockEntity {
    private final int MAX_RING_TIMES = 3;
    private final HashMap<UUID, Integer> playerRingTimes = new HashMap<>();
    public int swingTicks;
    public static final int MAX_SWING_TICKS = 30;
    public Direction dir;
    public boolean swinging;

    public GongBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GONG_BLOCK_ENTITY, pos, state);
    }
    
    public void incrementRings(World world, BlockPos pos, PlayerEntity player) {
        UUID id = player.getUuid();
        int count = playerRingTimes.getOrDefault(id, 0) +1;

        if (count < MAX_RING_TIMES) {
            playerRingTimes.put(id, count);
            world.playSound(null, pos, SoundEvents.ENTITY_EVOKER_PREPARE_WOLOLO, SoundCategory.BLOCKS,1f,1f);
        } else {
            triggerEvent(world, pos, player);
            playerRingTimes.put(id, 0);
        }
    }

    public int getSwingTicks() {
        return this.swingTicks;
    }

    public Direction getDir() {
        return this.dir;
    }

    public void startSwing(Direction lastSideHit) {
        BlockPos blockPos = this.getPos();
        this.dir = lastSideHit;
        if (!this.swinging) {
            this.swinging = true;
        }
        this.world.addSyncedBlockEvent(blockPos, this.getCachedState().getBlock(), 1, dir.getId());
    }

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        if (type == 1 && !this.swinging) {
            this.dir = Direction.byId(data);
            this.swingTicks = 0;
            this.swinging = true;
            return true;
        } else {
            return super.onSyncedBlockEvent(type, data);
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, GongBlockEntity blockEntity) {
        if (blockEntity.swinging  || blockEntity.swingTicks > 0) {
            blockEntity.swingTicks++;
        }

        if (blockEntity.swingTicks >= MAX_SWING_TICKS) {
            blockEntity.swinging = false;
            blockEntity.swingTicks = 0;
        }
        blockEntity.sync();
    }


    private void triggerEvent(World world, BlockPos pos, PlayerEntity player) {
        world.playSound(null, pos, SoundEvents.ENTITY_EVOKER_CELEBRATE, SoundCategory.BLOCKS,1f,1f);
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
        nbt.putInt("ticks", this.swingTicks);
        nbt.putBoolean("swinging", this.swinging);
        if (this.dir != null) {
            nbt.putString("dir", this.dir.asString());
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.swingTicks = nbt.getInt("ticks");
        if (nbt.contains("dir")) {
            dir = Direction.byName(nbt.getString("dir"));
        }
        this.swinging = nbt.getBoolean("swinging");
    }

    public void sync() {
        if (world != null && !world.isClient) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }
}
