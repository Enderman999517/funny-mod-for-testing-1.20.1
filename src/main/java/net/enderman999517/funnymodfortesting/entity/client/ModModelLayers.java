package net.enderman999517.funnymodfortesting.entity.client;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer AMOGH =
            new EntityModelLayer(new Identifier(FunnyModForTesting.MOD_ID, "amogh"), "main");
    public static final EntityModelLayer PLAYER_CHARGED =
            new EntityModelLayer(new Identifier(FunnyModForTesting.MOD_ID, "player_charged"), "main");

    public static void registerModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(PLAYER_CHARGED, () ->
                TexturedModelData.of(PlayerEntityModel.getTexturedModelData(new Dilation(2f), false), 64, 64)
        );
    }
}
