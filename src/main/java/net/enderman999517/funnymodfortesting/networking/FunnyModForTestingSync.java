package net.enderman999517.funnymodfortesting.networking;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.ThreadedAnvilChunkStorage;

public class FunnyModForTestingSync {

    public static void syncHiddenFlag(Entity entity, boolean hidden) {
        if (!(entity.getWorld() instanceof ServerWorld serverWorld)) return;
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeVarInt(entity.getId());
        buf.writeBoolean(hidden);

        CustomPayloadS2CPacket packet = new CustomPayloadS2CPacket(FunnyModForTestingNetworking.ENTITY_HIDDEN_SYNC, buf);

        ThreadedAnvilChunkStorage storage = serverWorld.getChunkManager().threadedAnvilChunkStorage;

        storage.getPlayersWatchingChunk(entity.getChunkPos(), false).forEach(player ->  {
            if (player != null) {
                player.networkHandler.sendPacket(packet);
            }
        });
    }

    public static void syncHiddenFlagToPlayer(Entity entity, ServerPlayerEntity player, boolean hidden) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeVarInt(entity.getId());
        buf.writeBoolean(hidden);

        CustomPayloadS2CPacket packet = new CustomPayloadS2CPacket(FunnyModForTestingNetworking.ENTITY_HIDDEN_SYNC, buf);
        player.networkHandler.sendPacket(packet);
    }
}
