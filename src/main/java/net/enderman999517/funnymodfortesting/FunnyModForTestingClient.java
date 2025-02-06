package net.enderman999517.funnymodfortesting;

import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.enderman999517.funnymodfortesting.entity.client.AmoghModel;
import net.enderman999517.funnymodfortesting.entity.client.AmoghRenderer;
import net.enderman999517.funnymodfortesting.entity.client.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
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
    }


}
