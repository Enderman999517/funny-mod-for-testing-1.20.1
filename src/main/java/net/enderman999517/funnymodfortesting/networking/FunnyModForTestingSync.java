package net.enderman999517.funnymodfortesting.networking;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.List;

public class FunnyModForTestingSync {

    public static void syncHiddenFlag(Entity entity, boolean hidden) {
        if (!(entity.getWorld() instanceof ServerWorld serverWorld)) return;

        PacketByteBuf bufH = PacketByteBufs.create();
        bufH.writeVarInt(entity.getId());
        bufH.writeBoolean(true);

        PacketByteBuf bufU = PacketByteBufs.create();
        bufU.writeVarInt(entity.getId());
        bufU.writeBoolean(false);

        CustomPayloadS2CPacket packetH = new CustomPayloadS2CPacket(FunnyModForTestingNetworking.ENTITY_HIDDEN_SYNC, bufH);
        CustomPayloadS2CPacket packetU = new CustomPayloadS2CPacket(FunnyModForTestingNetworking.ENTITY_HIDDEN_SYNC, bufU);

        List<ServerPlayerEntity> playerEntities = serverWorld.getPlayers();

        playerEntities.forEach(player ->  {
            if (player instanceof ModEntityData modEntityDataP) {
                if (entity instanceof ModEntityData modEntityDataE) {
                    PacketByteBuf bufHp = PacketByteBufs.create();
                    bufHp.writeVarInt(player.getId());
                    bufHp.writeBoolean(true);

                    PacketByteBuf bufUp = PacketByteBufs.create();
                    bufUp.writeVarInt(player.getId());
                    bufUp.writeBoolean(false);

                    CustomPayloadS2CPacket packetHp = new CustomPayloadS2CPacket(FunnyModForTestingNetworking.ENTITY_HIDDEN_SYNC, bufHp);
                    CustomPayloadS2CPacket packetUp = new CustomPayloadS2CPacket(FunnyModForTestingNetworking.ENTITY_HIDDEN_SYNC, bufUp);
                    //checks if currently hidden ie should be unrendered
                    if (modEntityDataE.isHidden()) {
                        //both hidden
                        if (modEntityDataP.isHidden()) {
                            player.networkHandler.sendPacket(packetU);
                            ((ServerPlayerEntity) entity).networkHandler.sendPacket(packetUp);
                            //entity hidden, player not hidden
                        } else {
                            player.networkHandler.sendPacket(packetH);
                            ((ServerPlayerEntity) entity).networkHandler.sendPacket(packetUp);
                        }
                        //entity not hidden, player hidden
                    } else if (modEntityDataP.isHidden()) {
                        player.networkHandler.sendPacket(packetU);
                        ((ServerPlayerEntity) entity).networkHandler.sendPacket(packetHp);
                        //entity not hidden, player not hidden
                    } else {
                        player.networkHandler.sendPacket(packetU);
                        ((ServerPlayerEntity) entity).networkHandler.sendPacket(packetUp);
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
