package net.enderman999517.funnymodfortesting.networking;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

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
                   FunnyModForTesting.LOGGER.error("client " + shouldRenderOverlay);
                   modEntityData.setRenderingOverlay(shouldRenderOverlay);
               }
            });
        });
    }
}
