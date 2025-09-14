package net.enderman999517.funnymodfortesting.render;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.item.custom.RingItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class LightningOverlayFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    public LightningOverlayFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
        super(context);
    }

    private static final Identifier LIGHTNING_OVERLAY = new Identifier("funnymodfortesting:textures/entity/player/player_charge.png");

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity,
                       float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (shouldRenderLightning(entity)) {
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(LIGHTNING_OVERLAY));

            float red = 1.0F;
            float green = 1.0F;
            float blue = 1.0F;
            float alpha = 1.0F;

            this.getContextModel().head.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, red, green, blue, alpha);
            this.getContextModel().body.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, red, green, blue, alpha);
        }
    }

    private boolean shouldRenderLightning(AbstractClientPlayerEntity player) {
        //if (player instanceof ModEntityData modEntityData) {
        //    return modEntityData.isHidden();
        //}
        return RingItem.shouldRender;
    }
}
