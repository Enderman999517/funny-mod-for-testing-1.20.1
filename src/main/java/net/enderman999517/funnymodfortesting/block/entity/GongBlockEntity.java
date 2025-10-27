package net.enderman999517.funnymodfortesting.block.entity;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
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
    private int swingTicks = 0;
    private boolean swinging = false;
    private static final int MAX_SWING_TICKS = 40;

    public GongBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GONG_BLOCK_ENTITY, pos, state);
    }

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

    public void startSwing() {
        swinging = true;
        swingTicks = 0;
    }

    public static void tick(World world, BlockPos pos, BlockState state, GongBlockEntity be) {
        if (be.swinging) {
            //FunnyModForTesting.LOGGER.error("swingticks: " + be.swingTicks);
            be.swingTicks ++;
            if (be.swingTicks > MAX_SWING_TICKS) {
                be.swinging = false;
                be.swingTicks = 0;
            }
        }
    }

    private void triggerEvent(World world, BlockPos pos, PlayerEntity player) {
        //player.playSound(SoundEvents.ITEM_TRIDENT_THUNDER, SoundCategory.BLOCKS, 1f, 1f);
        if (player instanceof ModEntityData modEntityData) {
            modEntityData.setHidden(true);
            modEntityData.setRenderingOverlay(true);
        }
    }

    public float getSwingAngle(float tickDelta, GongBlockEntity entity) {
        //if (!entity.swinging) return 0f;
        FunnyModForTesting.LOGGER.error("swingTicks + tickdelta : " + entity.swingTicks);
        return (float) Math.sin((entity.swingTicks + tickDelta) / 4.0) * 20.0f;
    }
}
