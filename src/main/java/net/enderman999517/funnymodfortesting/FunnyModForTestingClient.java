package net.enderman999517.funnymodfortesting;

import ladysnake.satin.api.event.PostWorldRenderCallback;
import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import ladysnake.satin.api.managed.uniform.Uniform4f;
import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.enderman999517.funnymodfortesting.entity.client.AmoghModel;
import net.enderman999517.funnymodfortesting.entity.client.AmoghRenderer;
import net.enderman999517.funnymodfortesting.entity.client.ModModelLayers;
import net.enderman999517.funnymodfortesting.entity.effect.ModStatusEffects;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;

import static net.enderman999517.funnymodfortesting.FunnyModForTesting.renderingBlit;
import static net.enderman999517.funnymodfortesting.FunnyModForTesting.testShader;

public class FunnyModForTestingClient implements ClientModInitializer {

    //public static final KeyBinding KEY_BINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding(
    //        "key.funnymodfortesting.showinfo",
    //        GLFW.GLFW_KEY_LEFT_SHIFT,
    //        "category.funnymodfortesting"
    //));

    private final Uniform4f color = testShader.findUniform4f("ColorModulate");

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.EXPLOSIVE_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.STATUS_EFFECT_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.AMOGH, AmoghModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.AMOGH, AmoghRenderer::new);

        ClientTickEvents.END_CLIENT_TICK.register(DepthFx.INSTANCE);
        ShaderEffectRenderCallback.EVENT.register(DepthFx.INSTANCE);
        PostWorldRenderCallback.EVENT.register(DepthFx.INSTANCE);
        ModItems.DEBUG_ITEM.registerDebugCallback((world, player, hand) -> {
            //if (world.isClient) {
                DepthFx.INSTANCE.testShader.release();
            //}
        });


        ShaderEffectRenderCallback.EVENT.register(tickDelta -> {
            if (renderingBlit) {
                testShader.render(tickDelta);
            }
        });
        ModItems.DEBUG_ITEM.registerDebugCallback((world, player, hand) -> {
            //if (world.isClient) {
                renderingBlit = !renderingBlit;
                color.set(5.0f,5.0f,5.0f,5.0f);
            //}
        });

        ModStatusEffects.FLASHBANG.registerDebugCallback((entity, attributes, amplifier) -> {
            //if (entity.getWorld().isClient) {
            // i know i probably shouldnt do this but i couldnt find another way that worked

            try {
                //int duration;
                //for (duration = entity.getStatusEffect(ModStatusEffects.FLASHBANG).getDuration(); duration > 0; duration--) {
                //    duration = entity.getStatusEffect(ModStatusEffects.FLASHBANG).getDuration();
                renderingBlit = !renderingBlit;
                color.set(5.0f, 5.0f, 5.0f, 5f);

                //}
                //duration = entity.getStatusEffect(ModStatusEffects.FLASHBANG).getDuration();


            } catch (NullPointerException ignored) {

            }


            //}
        });
    }
}
