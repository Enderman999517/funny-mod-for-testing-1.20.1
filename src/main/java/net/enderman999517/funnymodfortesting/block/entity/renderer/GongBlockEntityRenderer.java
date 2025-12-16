package net.enderman999517.funnymodfortesting.block.entity.renderer;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.block.custom.GongBlock;
import net.enderman999517.funnymodfortesting.block.entity.GongBlockEntity;
import net.enderman999517.funnymodfortesting.entity.client.GongModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class GongBlockEntityRenderer  implements BlockEntityRenderer<GongBlockEntity> {
    private final GongModel model;
    private final ModelPart gongBody;


    public GongBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.model = new GongModel(GongModel.getTexturedModelData().createModel());
        ModelPart modelPart = GongModel.getTexturedModelData().createModel();
        this.gongBody = modelPart.getChild("swing");
    }

    @Override
    public void render(GongBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        float ticks = (float) entity.swingTicks + tickDelta;
        Direction dir = entity.getDir();
        Direction cachedDir = entity.getCachedState().get(GongBlock.FACING);
        //position
        matrices.push();
        //if (entity.swingTicks < 1) {
        //    ticks = (1+tickDelta) * MathHelper.lerp(tickDelta, 0, 1);
        //}

        if (cachedDir == Direction.EAST || cachedDir == Direction.WEST) {
            matrices.multiply(RotationAxis.NEGATIVE_Y.rotation(MathHelper.PI/2));
            matrices.translate(0,0,-1);
        }

        matrices.translate(0.5, 1.5, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_X.rotation(MathHelper.PI));
        model.getBase().render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(GongModel.TEXTURE)), light, overlay);
        matrices.pop();

        matrices.push();
        matrices.translate(0.5, 1.5, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_X.rotation(MathHelper.PI));

        if (cachedDir == Direction.EAST || cachedDir == Direction.WEST) {
            matrices.multiply(RotationAxis.NEGATIVE_Y.rotation(MathHelper.PI/2));
        }

        //swing
        if (entity.swingTicks > 0) {
            float angle = MathHelper.sin(ticks / MathHelper.PI) / (4.0F + ticks / 3.0F);
            FunnyModForTesting.LOGGER.error("angle: {}", angle);
            FunnyModForTesting.LOGGER.error("tickDelta: {}", tickDelta);
            FunnyModForTesting.LOGGER.error("tick: {}", entity.swingTicks);
            if (dir == Direction.NORTH) {
                matrices.translate(0,0.5,0);
                matrices.multiply(RotationAxis.POSITIVE_X.rotation(angle));
                matrices.translate(-0,-0.5,-0);

            } else if (dir == Direction.SOUTH) {
                matrices.translate(0,0.5,0);
                matrices.multiply(RotationAxis.NEGATIVE_X.rotation(angle));
                matrices.translate(-0,-0.5,-0);

            } else if (dir == Direction.EAST) {
                matrices.translate(0,0.5,0);
                matrices.multiply(RotationAxis.NEGATIVE_X.rotation(angle));
                matrices.translate(-0,-0.5,-0);

            } else if (dir == Direction.WEST) {
                matrices.translate(0,0.5,0);
                matrices.multiply(RotationAxis.POSITIVE_X.rotation(angle));
                matrices.translate(-0,-0.5,-0);
            }
        }

        model.getSwing().render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(GongModel.TEXTURE)), light, overlay);
        matrices.pop();
    }
}
