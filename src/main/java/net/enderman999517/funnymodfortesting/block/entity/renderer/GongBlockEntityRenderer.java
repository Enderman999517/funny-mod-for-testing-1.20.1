package net.enderman999517.funnymodfortesting.block.entity.renderer;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.block.entity.GongBlockEntity;
import net.enderman999517.funnymodfortesting.entity.client.ModModelLayers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class GongBlockEntityRenderer  implements BlockEntityRenderer<GongBlockEntity> {
    private static final String GONG_BODY = "gong_body";
    private final ModelPart gongBody;

    public GongBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        ModelPart part = context.getLayerModelPart(ModModelLayers.GONG);
        this.gongBody = part.getChild("gong_body");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData2 = modelPartData.addChild(
                "gong_body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -3.0F, -7.0F, 10.0F, 13.0F, 2.0F), ModelTransform.pivot(7.5F, 15.0F, 7.5F)
        );
        modelPartData2.addChild(
                "bell_base", ModelPartBuilder.create().uv(0, 13).cuboid(4.0F, 4.0F, 4.0F, 8.0F, 2.0F, 8.0F), ModelTransform.pivot(-8.0F, -12.0F, -8.0F)
        );
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void render(GongBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        float angle = entity.getSwingAngle(tickDelta, entity);
        FunnyModForTesting.LOGGER.error("angle: " + angle);
        //FunnyModForTesting.LOGGER.error("tickdelta: " + tickDelta);
        matrices.push();

        matrices.translate(0.5, 1.0, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(angle));
        matrices.translate(-0.5, -1.0, -0.5);

        MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(
                entity.getCachedState(), matrices, vertexConsumers, light, overlay
        );

        matrices.pop();
    }
}
