package net.enderman999517.funnymodfortesting.render;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.entity.client.ModModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ChargedPlayerRenderFeature extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    private static final Identifier SKIN = new Identifier("funnymodfortesting:textures/entity/player/player_charge.png");
    private final PlayerEntityModel<AbstractClientPlayerEntity> chargedModel;

    public ChargedPlayerRenderFeature(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context, EntityModelLoader loader) {
        super(context);
        boolean slim = context.getModel().equals("slim");
        this.chargedModel = new PlayerEntityModel<>(loader.getModelPart(ModModelLayers.PLAYER_CHARGED), slim);
    }

    @Override
    public void render(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            AbstractClientPlayerEntity entity,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            float animationProgress,
            float headYaw,
            float headPitch
    ) {
        if (this.shouldRenderOverlay(entity)) {
            float partialAge = (float)entity.age + tickDelta;
            this.chargedModel.animateModel(entity, limbAngle, limbDistance, tickDelta);
            this.getContextModel().copyStateTo(this.chargedModel);
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(
                    RenderLayer.getEnergySwirl(SKIN, this.getEnergySwirlX(partialAge) % 1.0F, partialAge * 0.01F % 1.0F)
            );
            this.chargedModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            this.chargedModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, -getNormalHealth(entity) + 1, 0.5F * (getNormalHealth(entity)), getNormalHealth(entity), 1.0F);
        }
    }

    private boolean shouldRenderOverlay(AbstractClientPlayerEntity player) {
        if (player instanceof ModEntityData modEntityData) {
            return modEntityData.isRenderingOverlay();
        } else return false;
    }

    private float getNormalHealth(AbstractClientPlayerEntity entity) {
        return (entity.getHealth() - 1)/ 20;
    }

    protected float getEnergySwirlX(float partialAge) {
        return partialAge * 0.01f;
    }
}
