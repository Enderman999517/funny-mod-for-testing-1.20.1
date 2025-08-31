package net.enderman999517.funnymodfortesting;

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
import net.enderman999517.funnymodfortesting.item.custom.ScytheItem;
import net.enderman999517.funnymodfortesting.screen.BrainrottingScreen;
import net.enderman999517.funnymodfortesting.screen.CompactingScreen;
import net.enderman999517.funnymodfortesting.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class FunnyModForTestingClient implements ClientModInitializer {

    public static final KeyBinding NVG_TOGGLE = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.funnymodfortesting.nvg",
            GLFW.GLFW_KEY_N,
            "category.funnymodfortesting"
    ));
    public static final KeyBinding FISH = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.funnymodfortesting.fish",
            GLFW.GLFW_KEY_F,
            "category.funnymodfortesting"
    ));

    public boolean renderingBlit = false;
    private static final ManagedShaderEffect testShader = ShaderEffectManager.getInstance().manage(new Identifier(FunnyModForTesting.MOD_ID, "shaders/post/blit.json"));
    private static final Uniform4f color = testShader.findUniform4f("ColorModulate");

    public void registerModelPredicateProviders() {
        ModelPredicateProviderRegistry.register(ModItems.SCYTHE, new Identifier("cast"), (itemStack, clientWorld, livingEntity, seed) -> {
            //if (itemStack.getItem() instanceof ScytheItem scytheItem) {
                //boolean hasEffects = !ScytheItem.isItemListEmpty(itemStack);

                if (livingEntity == null) {
                    return ScytheItem.isItemListEmpty(itemStack) ? 0.0f : 1.0f;
                } else {
                    return livingEntity instanceof PlayerEntity && !ScytheItem.isItemListEmpty(itemStack) ? 1.0F : 0.0F;
                }
            //}
            //return 0.0f;
        });
    }

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.EXPLOSIVE_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.STATUS_EFFECT_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BLOCK_PLACING_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.AMOGH, AmoghModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.AMOGH, AmoghRenderer::new);
        HandledScreens.register(ModScreenHandlers.BRAINROTTING_SCREEN_HANDLER, BrainrottingScreen::new);
        HandledScreens.register(ModScreenHandlers.COMPACTING_SCREEN_HANDLER, CompactingScreen::new);

        registerModelPredicateProviders();

        //ClientTickEvents.END_CLIENT_TICK.register(DepthFx.INSTANCE);
        //ShaderEffectRenderCallback.EVENT.register(DepthFx.INSTANCE);
        //PostWorldRenderCallback.EVENT.register(DepthFx.INSTANCE);
        //ModItems.DEBUG_ITEM.registerDebugCallback((world, player, hand) -> {
        //    //if (world.isClient) {
        //        DepthFx.INSTANCE.testShader.release();
        //    //}
        //});



        ShaderEffectRenderCallback.EVENT.register(tickDelta -> {
            if (renderingBlit) {
                testShader.render(tickDelta);
            }
        });
        ModItems.DEBUG_ITEM.registerDebugCallback((world, player, hand) -> {
                renderingBlit = !renderingBlit;
                color.set(0.0f,1.0f,0.4f,0.0f);
        });

        ShaderEffectRenderCallback.EVENT.register(tickDelta -> {
            if (renderingBlit) {
                testShader.render(tickDelta);
            }
        });
        ModItems.NVG_GOGGLES.registerDebugCallback((stack, world, entity, slot,selected) -> {
                renderingBlit = !renderingBlit;
                color.set(0.2f,1.0f,0.5f,1.0f);
        });

        ModStatusEffects.FLASHBANG.registerDebugCallback((entity, attributes, amplifier) -> {
            // i know i probably shouldnt do this but i couldnt find another way that worked

            //try {
            //int duration;
            //for (duration = entity.getStatusEffect(ModStatusEffects.FLASHBANG).getDuration(); duration > 0; duration--) {
            //    duration = entity.getStatusEffect(ModStatusEffects.FLASHBANG).getDuration();
            renderingBlit = !renderingBlit;
            color.set(1.0f, 1.0f, 1.0f, 1.0f);

            //}
            //duration = entity.getStatusEffect(ModStatusEffects.FLASHBANG).getDuration();


            //} catch (NullPointerException ignored) {

            //}
        });

    }
}
