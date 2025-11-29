package net.enderman999517.funnymodfortesting.block.entity.renderer;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.block.entity.GongBlockEntity;
import net.enderman999517.funnymodfortesting.entity.client.GongModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class GongBlockEntityRenderer  implements BlockEntityRenderer<GongBlockEntity> {
    private final GongModel model;


    public GongBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.model = new GongModel(GongModel.getTexturedModelData().createModel());
    }

    @Override
    public void render(GongBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        float ticks = (float) entity.swingTicks + tickDelta/2;
        float angle = MathHelper.sin(ticks / (float) Math.PI) / (4f + ticks / 0.3f);
        if (model == null) return;

        matrices.push();

        model.getBase().render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(GongModel.TEXTURE)), light, overlay);

        matrices.push();
        if (entity.swingTicks > 0) {
            //matrices.translate(0.5, 0.5, 0.5);
            matrices.multiply(RotationAxis.NEGATIVE_X.rotation(angle));
            //matrices.translate(-0.5, -0.5, -0.5);
        }

        model.getSwing().render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(GongModel.TEXTURE)), light, overlay);
        matrices.pop();
        matrices.pop();
    }
}
