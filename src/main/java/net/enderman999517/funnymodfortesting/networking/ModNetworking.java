package net.enderman999517.funnymodfortesting.networking;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ModNetworking {
    public static final Identifier ENTITY_HIDDEN_SYNC = new Identifier(FunnyModForTesting.MOD_ID, "entity_hidden_sync");
    public static final Identifier DISPLAY_OVERLAY_SYNC = new Identifier(FunnyModForTesting.MOD_ID, "display_overlay_sync");

    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(ENTITY_HIDDEN_SYNC, (minecraftClient, clientPlayNetworkHandler, packetByteBuf, packetSender) -> {
            int entityId = packetByteBuf.readVarInt();
            boolean isHidden = packetByteBuf.readBoolean();

            minecraftClient.execute(() -> {
                Entity entity = minecraftClient.world.getEntityById(entityId);
                if (entity == null) {
                    return;
                }
                if (entity instanceof ModEntityData modData) {
                    modData.setHidden(isHidden);
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(DISPLAY_OVERLAY_SYNC, (minecraftClient, clientPlayNetworkHandler, packetByteBuf, packetSender) -> {
            int entityId = packetByteBuf.readVarInt();
            boolean shouldRenderOverlay = packetByteBuf.readBoolean();

            minecraftClient.execute(() -> {
               Entity entity = minecraftClient.world.getEntityById(entityId);
               if (entity == null) {
                   return;
               }
               if (entity instanceof ModEntityData modEntityData) {
                   modEntityData.setRenderingOverlay(shouldRenderOverlay);
               }
            });
        });

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            client.execute(() -> {
                for (Entity entity : client.world.getEntities()) {
                    if (entity instanceof ModEntityData modEntityData) {
                        modEntityData.setHidden(modEntityData.isHidden());
                        FunnyModForTesting.LOGGER.error(entity.getName().getString() + " hidden: " + modEntityData.isHidden());
                    }
                }
            });
        });

        AtomicInteger tickCounter = new AtomicInteger();
        AtomicBoolean awaitingResync = new AtomicBoolean(false);


        ClientPlayConnectionEvents.JOIN.register((handler, sender,client) -> {
            tickCounter.set(0);
            awaitingResync.set(true);
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (awaitingResync.get() && client.world != null) {
                tickCounter.getAndIncrement();

                if (tickCounter.get() >= 20) {
                    awaitingResync.set(false);

                    for (Entity entity : client.world.getEntities()) {
                        if (entity instanceof ModEntityData data) {
                            data.setHidden(data.isHidden());
                        }
                    }
                }
            }
        });
    }
}
