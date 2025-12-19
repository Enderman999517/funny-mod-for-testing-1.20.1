package net.enderman999517.funnymodfortesting.block.entity.renderer;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.block.custom.GongBlock;
import net.enderman999517.funnymodfortesting.block.entity.GongBlockEntity;
import net.enderman999517.funnymodfortesting.entity.client.GongModel;
import net.enderman999517.funnymodfortesting.entity.client.ModModelLayers;
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


    public GongBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.model = new GongModel(context.getLayerModelPart(ModModelLayers.GONG));
    }

    @Override
    public void render(GongBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        float ticks = (float) entity.getSwingTicks() + tickDelta;
        float pitch = 0.0f;
        float roll = 0.0f;
        Direction dir = entity.getDir();
        Direction cachedDir = entity.getCachedState().get(GongBlock.FACING);
        ModelPart swing = this.model.getSwing();

        matrices.push();
        matrices.translate(0.5, 1.5, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_X.rotation(MathHelper.PI));

        if (cachedDir == Direction.EAST || cachedDir == Direction.WEST) {
            matrices.multiply(RotationAxis.NEGATIVE_Y.rotation(MathHelper.PI/2));
        }

        if (entity.swinging) {
            float angle = MathHelper.sin(ticks / MathHelper.PI) / (4.0F + ticks / 3.0F);
            //FunnyModForTesting.LOGGER.error("angle: {}", angle);
            //FunnyModForTesting.LOGGER.error("tickDelta: {}", tickDelta);
            //FunnyModForTesting.LOGGER.error("tick: {}", entity.swingTicks);
            if (dir == Direction.NORTH) {
                pitch = angle;
            } else if (dir == Direction.SOUTH) {
                pitch = -angle;
            } else if (dir == Direction.EAST) {
                pitch = -angle;
            } else if (dir == Direction.WEST) {
                pitch = angle;
            }
        }

        swing.pitch = pitch;
        swing.roll = roll;
        this.model.getMain().render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(GongModel.TEXTURE)), light, overlay);
        matrices.pop();
    }
}
