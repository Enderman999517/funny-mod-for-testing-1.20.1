package net.enderman999517.funnymodfortesting;

import ladysnake.satin.api.event.PostWorldRenderCallback;
import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.enderman999517.funnymodfortesting.entity.client.AmoghModel;
import net.enderman999517.funnymodfortesting.entity.client.AmoghRenderer;
import net.enderman999517.funnymodfortesting.entity.client.ModModelLayers;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class FunnyModForTestingClient implements ClientModInitializer {

    //public static final KeyBinding KEY_BINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding(
    //        "key.funnymodfortesting.showinfo",
    //        GLFW.GLFW_KEY_LEFT_SHIFT,
    //        "category.funnymodfortesting"
    //));

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.EXPLOSIVE_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.AMOGH, AmoghModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.AMOGH, AmoghRenderer::new);

        ClientTickEvents.END_CLIENT_TICK.register(DepthFx.INSTANCE);
        ShaderEffectRenderCallback.EVENT.register(DepthFx.INSTANCE);
        PostWorldRenderCallback.EVENT.register(DepthFx.INSTANCE);
        ModItems.DEBUG_ITEM.registerDebugCallback((world, player, hand) -> {
            if (world.isClient) {
                DepthFx.INSTANCE.testShader.release();
            }
        });
    }
}
