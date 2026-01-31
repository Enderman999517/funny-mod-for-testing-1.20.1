package net.enderman999517.funnymodfortesting.networking;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.*;

public class ModSync {

    public static void init() {
        final Map<UUID, Integer> resyncPlayerInvisWaitTicks = new HashMap<>();

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            ServerPlayerEntity player = handler.getPlayer();

            if (player instanceof ModEntityData modEntityData) {
                modEntityData.setHidden(false);
                modEntityData.setRenderingOverlay(false);
            }
        });

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            resyncPlayerInvisWaitTicks.put(player.getUuid(), 0);
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            Iterator<Map.Entry<UUID, Integer>> iterator = resyncPlayerInvisWaitTicks.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<UUID, Integer> entry = iterator.next();
                UUID uuid = entry.getKey();
                int ticks = entry.getValue();

                if (ticks >= 0) {
                    ServerPlayerEntity joiningPlayer = server.getPlayerManager().getPlayer(uuid);
                    if (joiningPlayer != null) {
                        ModEntityData entityData = ((ModEntityData) joiningPlayer);

                        syncHiddenFlag(joiningPlayer, entityData.isHidden(), joiningPlayer);
                        syncOverlayFlag(joiningPlayer, entityData.isRenderingOverlay(), joiningPlayer);
                        syncBeingImpersonatedFlag(joiningPlayer, entityData.isBeingImpersonated(), joiningPlayer);

                        reSyncAllVisibilityFor(joiningPlayer);
                        reSyncAllOverlayFor(joiningPlayer);
                        reSyncAllImpersonatingFor(joiningPlayer);
                    }
                    iterator.remove();
                } else {
                    entry.setValue(ticks + 1);
                }
            }

            server.getPlayerManager().getPlayerList().forEach(player -> {
                List<Entity> entities = player.getServerWorld().getOtherEntities(player, player.getBoundingBox().expand(50,50,50));
                entities.forEach(entity -> {
                    ModEntityData modEntityData = ((ModEntityData) entity);

                    syncHiddenFlag(entity);
                    syncRenderingOverlayFlag(entity, modEntityData.isHidden());
                    syncBeingImpersonatedFlag(entity, modEntityData.isBeingImpersonated());
                });
            });
        });

        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
            if (player != null) {
                ModEntityData entityData = ((ModEntityData) player);

                syncHiddenFlag(player, entityData.isHidden(), player);
                syncOverlayFlag(player, entityData.isRenderingOverlay(), player);
                syncBeingImpersonatedFlag(player, entityData.isBeingImpersonated(), player);
            }
        });

        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            if (newPlayer != null) {
                ModEntityData oldData = ((ModEntityData) oldPlayer);
                ModEntityData newData = ((ModEntityData) newPlayer);

                newData.setHidden(oldData.isHidden());
                newData.setRenderingOverlay(oldData.isRenderingOverlay());
                newData.setBeingImpersonated(oldData.isBeingImpersonated());

                syncHiddenFlag(newPlayer, oldData.isHidden(), newPlayer);
                syncOverlayFlag(newPlayer, oldData.isRenderingOverlay(), newPlayer);
                syncBeingImpersonatedFlag(newPlayer, oldData.isBeingImpersonated(), newPlayer);
            }
        });
    }


    private static void reSyncAllVisibilityFor(ServerPlayerEntity joiningPlayer) {
        ServerWorld serverWorld = joiningPlayer.getServerWorld();
        List<ServerPlayerEntity> players = serverWorld.getPlayers();

        for (ServerPlayerEntity other : players) {
            if (other == joiningPlayer) continue;

            ModEntityData otherData = (ModEntityData) other;
            ModEntityData joiningData = (ModEntityData) joiningPlayer;

            syncHiddenFlag(other, otherData.isHidden(), joiningPlayer);
            syncHiddenFlag(joiningPlayer, joiningData.isHidden(), other);
        }
    }

    private static void reSyncAllOverlayFor(ServerPlayerEntity joiningPlayer) {
        ServerWorld serverWorld = joiningPlayer.getServerWorld();
        List<ServerPlayerEntity> players = serverWorld.getPlayers();

        for (ServerPlayerEntity other : players) {
            if (other == joiningPlayer) continue;

            ModEntityData otherData = (ModEntityData) other;
            ModEntityData joiningData = (ModEntityData) joiningPlayer;

            syncOverlayFlag(other, otherData.isRenderingOverlay(), joiningPlayer);
            syncOverlayFlag(joiningPlayer, joiningData.isRenderingOverlay(), other);
        }
    }

    private static void reSyncAllImpersonatingFor(ServerPlayerEntity joiningPlayer) {
        ServerWorld serverWorld = joiningPlayer.getServerWorld();
        List<ServerPlayerEntity> players = serverWorld.getPlayers();

        for (ServerPlayerEntity other : players) {
            if (other == joiningPlayer) continue;

            ModEntityData otherData = (ModEntityData) other;
            ModEntityData joiningData = (ModEntityData) joiningPlayer;

            syncBeingImpersonatedFlag(other, otherData.isBeingImpersonated(), joiningPlayer);
            syncBeingImpersonatedFlag(joiningPlayer, joiningData.isBeingImpersonated(), other);
        }
    }


    public static void syncHiddenFlag(Entity entity) {
        if (!(entity.getWorld() instanceof ServerWorld serverWorld)) return;

        PacketByteBuf bufH = PacketByteBufs.create();
        bufH.writeVarInt(entity.getId());
        bufH.writeBoolean(true);

        PacketByteBuf bufU = PacketByteBufs.create();
        bufU.writeVarInt(entity.getId());
        bufU.writeBoolean(false);

        CustomPayloadS2CPacket packetH = new CustomPayloadS2CPacket(ModNetworking.ENTITY_HIDDEN_SYNC, bufH);
        CustomPayloadS2CPacket packetU = new CustomPayloadS2CPacket(ModNetworking.ENTITY_HIDDEN_SYNC, bufU);

        List<ServerPlayerEntity> playerEntities = serverWorld.getPlayers();

        playerEntities.forEach(player -> {
            if (player instanceof ModEntityData modEntityDataP) {
                if (entity instanceof ModEntityData modEntityDataE) {
                    PacketByteBuf bufHp = PacketByteBufs.create();
                    bufHp.writeVarInt(player.getId());
                    bufHp.writeBoolean(true);

                    PacketByteBuf bufUp = PacketByteBufs.create();
                    bufUp.writeVarInt(player.getId());
                    bufUp.writeBoolean(false);

                    CustomPayloadS2CPacket packetHp = new CustomPayloadS2CPacket(ModNetworking.ENTITY_HIDDEN_SYNC, bufHp);
                    CustomPayloadS2CPacket packetUp = new CustomPayloadS2CPacket(ModNetworking.ENTITY_HIDDEN_SYNC, bufUp);
                    //checks if currently hidden ie should be unrendered
                    if (modEntityDataE.isHidden()) {
                        //both hidden
                        if (modEntityDataP.isHidden()) {
                            player.networkHandler.sendPacket(packetU);
                            if (entity instanceof PlayerEntity) {
                                ((ServerPlayerEntity) entity).networkHandler.sendPacket(packetUp);
                            }
                            //entity hidden, player not hidden
                        } else {
                            player.networkHandler.sendPacket(packetH);
                            if (entity instanceof PlayerEntity) {
                                ((ServerPlayerEntity) entity).networkHandler.sendPacket(packetUp);
                            }
                        }
                        //entity not hidden, player hidden
                    } else if (modEntityDataP.isHidden()) {
                        player.networkHandler.sendPacket(packetU);
                        if (entity instanceof PlayerEntity) {
                            ((ServerPlayerEntity) entity).networkHandler.sendPacket(packetHp);
                        }
                        //both not hidden
                    } else {
                        player.networkHandler.sendPacket(packetU);
                        if (entity instanceof PlayerEntity) {
                            ((ServerPlayerEntity) entity).networkHandler.sendPacket(packetUp);
                        }
                    }
                }
            }
        });
    }

    public static void syncRenderingOverlayFlag(Entity entity, boolean renderingOverlay) {
        handleSimpleBoolean(entity, renderingOverlay, ModNetworking.DISPLAY_OVERLAY_SYNC);
    }

    public static void syncBeingImpersonatedFlag(Entity entity, boolean beingImpersonated) {
        handleSimpleBoolean(entity, beingImpersonated, ModNetworking.BEING_IMPERSONATED_SYNC);
    }


    public static void syncHiddenFlag(Entity entity, boolean hidden, ServerPlayerEntity target) {
        syncSimpleBoolean(entity, target, hidden, ModNetworking.ENTITY_HIDDEN_SYNC);
    }

    public static void syncOverlayFlag(Entity entity, boolean renderingOverlay, ServerPlayerEntity target) {
        syncSimpleBoolean(entity, target, renderingOverlay, ModNetworking.DISPLAY_OVERLAY_SYNC);
    }

    public static void syncBeingImpersonatedFlag(Entity entity, boolean beingImpersonated, ServerPlayerEntity target) {
        syncSimpleBoolean(entity, target, beingImpersonated, ModNetworking.BEING_IMPERSONATED_SYNC);
    }


    private static void handleSimpleBoolean(Entity entity, boolean data, Identifier key) {
        if (!(entity.getWorld() instanceof ServerWorld serverWorld)) return;
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeVarInt(entity.getId());
        buf.writeBoolean(data);
        CustomPayloadS2CPacket packet = new CustomPayloadS2CPacket(key, buf);

        List<ServerPlayerEntity> playerEntities = serverWorld.getPlayers();
        playerEntities.forEach(player -> player.networkHandler.sendPacket(packet));
    }

    private static void syncSimpleBoolean(Entity entity, ServerPlayerEntity target, boolean data, Identifier key) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeVarInt(entity.getId());
        buf.writeBoolean(data);

        CustomPayloadS2CPacket packet = new CustomPayloadS2CPacket(key, buf);
        target.networkHandler.sendPacket(packet);
    }
}
