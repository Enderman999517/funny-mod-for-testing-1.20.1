package net.enderman999517.funnymodfortesting.networking;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.block.entity.GongBlockEntity;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ModNetworking {
    public static final Identifier ENTITY_HIDDEN_SYNC = new Identifier(FunnyModForTesting.MOD_ID, "entity_hidden_sync");
    public static final Identifier DISPLAY_OVERLAY_SYNC = new Identifier(FunnyModForTesting.MOD_ID, "display_overlay_sync");
    //public static final Identifier GONG_SYNC = new Identifier(FunnyModForTesting.MOD_ID, "gong_sync");

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
                            data.setRenderingOverlay(data.isRenderingOverlay());
                        }
                    }
                }
            }
        });


        //ClientPlayNetworking.registerGlobalReceiver(GONG_SYNC, (minecraftClient, clientPlayNetworkHandler, packetByteBuf, packetSender) -> {
        //    int ticks = packetByteBuf.readVarInt();
        //    Vector3f vector3f = packetByteBuf.readVector3f();
        //    Vec3i pos = new Vec3i((int) vector3f.x, (int) vector3f.y, (int) vector3f.z);
        //    Direction dir = Direction.valueOf(packetByteBuf.readString());
        //
        //    minecraftClient.execute(() -> {
        //        BlockEntity entity = minecraftClient.world.getBlockEntity(new BlockPos(pos));
        //        if (entity instanceof GongBlockEntity gongBlockEntity) {
        //            gongBlockEntity.setSwingTicks(ticks);
        //            gongBlockEntity.setDir(dir);
        //        }
        //    });
        //});
    }
}
