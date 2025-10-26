package net.enderman999517.funnymodfortesting.block.entity.renderer;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.block.entity.GongBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class GongBlockEntityRenderer  implements BlockEntityRenderer<GongBlockEntity> {
    public GongBlockEntityRenderer(BlockEntityRendererFactory.Context context) {}

    @Override
    public void render(GongBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        float angle = entity.getSwingAngle(tickDelta);
        FunnyModForTesting.LOGGER.error("angle: " + angle);
        FunnyModForTesting.LOGGER.error("tickdelta: " + tickDelta);
        matrices.push();

        matrices.translate(0.5,0.5,0.5);
        matrices.multiply(RotationAxis.NEGATIVE_X.rotation(angle));
        matrices.translate(-0.5,-0.5,-0.5);

        MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(
                entity.getCachedState(), matrices, vertexConsumers, light, overlay
        );

        matrices.pop();
    }
}
