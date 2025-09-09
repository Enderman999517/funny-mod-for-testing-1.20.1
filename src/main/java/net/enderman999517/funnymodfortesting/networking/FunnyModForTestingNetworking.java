package net.enderman999517.funnymodfortesting.networking;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class FunnyModForTestingNetworking {
    public static final Identifier ENTITY_HIDDEN_SYNC = new Identifier(FunnyModForTesting.MOD_ID, "entity_hidden_sync");

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
    }
}
