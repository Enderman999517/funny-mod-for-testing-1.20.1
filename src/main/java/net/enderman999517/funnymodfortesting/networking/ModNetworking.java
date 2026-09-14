package net.enderman999517.funnymodfortesting.networking;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.FunnyModForTestingClient;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.irisshaders.iris.Iris;
import net.irisshaders.iris.api.v0.IrisApi;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ModNetworking {
    /**
     * How to do networking
     * <li>For basic networking, add an identifier below
     * and register a global reciever in the {@linkplain ModNetworking#register() register} method.
     * Then add behaviour in {@linkplain ModSync#init() init} method and add the relevant methods in {@link ModSync} of the format <pre>
     *     {@code
     *         public static void sync<DATA>Flag(Entity entity, <DATATYPE> <DATA>) {
     *              handleSimple<DATATYPE>(entity, <DATA>, ModNetworking.<IDENTIFIER>);
     *         }
     *
     *         public static void sync<DATA>Flag(Entity entity, <DATATYPE> <DATA>, ServerPlayerEntity target) {
     *              syncSimple<DATATYPE>(entity, target, <DATA>, ModNetworking.<IDENTIFIER>);
     *         }
     *     }
     * </pre>
     * Note: handle methods send to all players but sync methods send to a specific target only, so if global sync is not needed, don't add the handleSimpleDATATYPE method</li>
     * <li>For more complex interactions, don't use handleSimpleDATATYPE, use a custom method</li>
     * <li>If data storage is needed, add the relevant getters and setters in {@link ModEntityData} and see the comment in {@link net.enderman999517.funnymodfortesting.mixin.EntityNbtMixin}</li>
     */
    public static final Identifier ENTITY_HIDDEN_SYNC = new Identifier(FunnyModForTesting.MOD_ID, "entity_hidden_sync");
    public static final Identifier DISPLAY_OVERLAY_SYNC = new Identifier(FunnyModForTesting.MOD_ID, "display_overlay_sync");
    public static final Identifier BEING_IMPERSONATED_SYNC = new Identifier(FunnyModForTesting.MOD_ID, "being_impersonated_sync");
    public static final Identifier IMPERSONATING_SYNC = new Identifier(FunnyModForTesting.MOD_ID, "impersonating_sync");
    public static final Identifier CAMERA_TARGET_ENTITY_UUID_SYNC = new Identifier(FunnyModForTesting.MOD_ID, "camera_target_entity_uuid_sync");
    public static final Identifier RENDERING_SHADER_SYNC = new Identifier(FunnyModForTesting.MOD_ID, "rendering_shader_sync");


    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(ENTITY_HIDDEN_SYNC, (client, handler, buf, responseSender) -> {
            int entityId = buf.readVarInt();
            boolean isHidden = buf.readBoolean();

            client.execute(() -> {
                Entity entity = client.world.getEntityById(entityId);
                if (entity == null) {
                    return;
                }
                if (entity instanceof ModEntityData modData) {
                    modData.setHidden(isHidden);
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(DISPLAY_OVERLAY_SYNC, (client, handler, buf, responseSender) -> {
            int entityId = buf.readVarInt();
            boolean shouldRenderOverlay = buf.readBoolean();

            client.execute(() -> {
               Entity entity = client.world.getEntityById(entityId);
               if (entity == null) {
                   return;
               }
               if (entity instanceof ModEntityData modEntityData) {
                   modEntityData.setRenderingOverlay(shouldRenderOverlay);
               }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(BEING_IMPERSONATED_SYNC, (client, handler, buf, responseSender) ->  {
            int entityId = buf.readVarInt();
            boolean beingImpersonated = buf.readBoolean();

            client.execute(() -> {
                Entity entity = client.world.getEntityById(entityId);
                if (entity == null) {
                    return;
                }
                if (entity instanceof ModEntityData modEntityData) {
                    modEntityData.setBeingImpersonated(beingImpersonated);
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(IMPERSONATING_SYNC, (client, handler, buf, responseSender) ->  {
            int entityId = buf.readVarInt();
            boolean isImpersonating = buf.readBoolean();

            client.execute(() -> {
                Entity entity = client.world.getEntityById(entityId);
                if (entity == null) {
                    return;
                }
                if (entity instanceof ModEntityData modEntityData) {
                    modEntityData.setImpersonating(isImpersonating);
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(CAMERA_TARGET_ENTITY_UUID_SYNC, (client, handler, buf, responseSender) ->  {
            int entityId = buf.readVarInt();
            String cameraTargetEntityUuid = buf.readString();

            client.execute(() -> {
                Entity entity = client.world.getEntityById(entityId);
                if (entity == null) {
                    return;
                }
                if (entity instanceof ModEntityData modEntityData) {
                    modEntityData.setCameraTargetEntityUuid(cameraTargetEntityUuid);
                }
            });
        });


        ClientPlayNetworking.registerGlobalReceiver(RENDERING_SHADER_SYNC, (client, handler, buf, responseSender) ->  {
            int entityId = buf.readVarInt();

            client.execute(() -> {
                Entity entity = client.world.getEntityById(entityId);
                if (entity == null) {
                    return;
                }

                Iris.getIrisConfig().setShaderPackName(FunnyModForTestingClient.oceandimShader);
                IrisApi.getInstance().getConfig().setShadersEnabledAndApply(true);
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
                            data.setBeingImpersonated(data.isBeingImpersonated());
                            data.setImpersonating(data.isImpersonating());
                            data.setCameraTargetEntityUuid(data.getCameraTargetEntityUuid());
                        }
                    }
                }
            }
        });
    }

    public static void lerpTp(ServerPlayerEntity target) {
        WorldRenderEvents.END.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null) return;
            float tickDelta = context.tickDelta();

            Vec3d lastTickPos = EntityMovementTracker.getLastTickPos(target.getUuid());
            client.player.setPos(
                    MathHelper.lerp(tickDelta, lastTickPos.getX(), target.getX()),
                    MathHelper.lerp(tickDelta, lastTickPos.getY(), target.getY()),
                    MathHelper.lerp(tickDelta, lastTickPos.getZ(), target.getZ()));
        });
    }
}
