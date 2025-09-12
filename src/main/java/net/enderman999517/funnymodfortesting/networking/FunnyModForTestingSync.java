package net.enderman999517.funnymodfortesting.networking;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.ThreadedAnvilChunkStorage;

import java.util.ArrayList;
import java.util.List;

public class FunnyModForTestingSync {

    public static void syncHiddenFlag(Entity entity, boolean hidden) {
        if (!(entity.getWorld() instanceof ServerWorld serverWorld)) return;
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeVarInt(entity.getId());
        buf.writeBoolean(hidden);

        PacketByteBuf bufH = PacketByteBufs.create();
        bufH.writeVarInt(entity.getId());
        bufH.writeBoolean(false);

        PacketByteBuf bufU = PacketByteBufs.create();
        bufU.writeVarInt(entity.getId());
        bufU.writeBoolean(true);

        CustomPayloadS2CPacket packet = new CustomPayloadS2CPacket(FunnyModForTestingNetworking.ENTITY_HIDDEN_SYNC, buf);
        CustomPayloadS2CPacket packetH = new CustomPayloadS2CPacket(FunnyModForTestingNetworking.ENTITY_HIDDEN_SYNC, bufH);
        CustomPayloadS2CPacket packetU = new CustomPayloadS2CPacket(FunnyModForTestingNetworking.ENTITY_HIDDEN_SYNC, bufU);

        ThreadedAnvilChunkStorage storage = serverWorld.getChunkManager().threadedAnvilChunkStorage;
        List<ServerPlayerEntity> playerEntities = new ArrayList<>(storage.getPlayersWatchingChunk(entity.getChunkPos(), false));

        playerEntities.forEach(player ->  {
            if (player instanceof ModEntityData modEntityDataP) {
                if (entity instanceof ModEntityData modEntityDataE) {
                    //half worked
                    //if (!modEntityData.isHidden()) {
                    //    player.networkHandler.sendPacket(packet);
                    //    FunnyModForTesting.LOGGER.error("sent hidden packet");

                    if (!modEntityDataE.isHidden()) {
                        player.networkHandler.sendPacket(packet);
                    } else if (modEntityDataP.isHidden()) {
                        player.networkHandler.sendPacket(packetU);
                    }
                }
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
