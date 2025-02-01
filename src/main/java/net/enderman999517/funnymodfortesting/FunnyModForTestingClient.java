package net.enderman999517.funnymodfortesting;

import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.enderman999517.funnymodfortesting.entity.client.AmoghModel;
import net.enderman999517.funnymodfortesting.entity.client.AmoghRenderer;
import net.enderman999517.funnymodfortesting.entity.client.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class FunnyModForTestingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.EXPLOSIVE_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.AMOGH, AmoghModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.AMOGH, AmoghRenderer::new);
    }
}
