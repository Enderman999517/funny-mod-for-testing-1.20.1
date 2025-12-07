package net.enderman999517.funnymodfortesting.block.entity.renderer;

import net.enderman999517.funnymodfortesting.block.custom.GongBlock;
import net.enderman999517.funnymodfortesting.block.entity.GongBlockEntity;
import net.enderman999517.funnymodfortesting.entity.client.GongModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class GongBlockEntityRenderer  implements BlockEntityRenderer<GongBlockEntity> {
    public static final SpriteIdentifier GONG_BODY_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("entity/gong/gong"));
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

        matrices.push();
        matrices.translate(1,1.5,1);
        matrices.multiply(RotationAxis.POSITIVE_X.rotation(MathHelper.PI));
        model.getBase().render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(GongModel.TEXTURE)), light, overlay);
        matrices.pop();

        matrices.push();
        matrices.translate(1, 1.5, 1);
        matrices.multiply(RotationAxis.POSITIVE_X.rotation(MathHelper.PI));

        float pitch = 0f;
        float roll = 0f;

        if (entity.swingTicks > 0) {
            float angle = MathHelper.sin(ticks / (float) Math.PI) / (4.0F + ticks / 3.0F);
            if (entity.lastSideHit == Direction.NORTH) {
                matrices.translate(0.5,0.5,0.5);
                matrices.multiply(RotationAxis.POSITIVE_X.rotation(angle));
                matrices.translate(-0.5,-0.5,-0.5);

            } else if (entity.lastSideHit == Direction.SOUTH) {
                matrices.translate(0.5,0.5,0.5);
                matrices.multiply(RotationAxis.NEGATIVE_X.rotation(angle));
                matrices.translate(-0.5,-0.5,-0.5);

            } else if (entity.lastSideHit == Direction.EAST) {
                matrices.translate(0.5,0.5,0.5);
                matrices.multiply(RotationAxis.NEGATIVE_Z.rotation(angle));
                matrices.translate(-0.5,-0.5,-0.5);

            } else if (entity.lastSideHit == Direction.WEST) {
                matrices.translate(0.5,0.5,0.5);
                matrices.multiply(RotationAxis.POSITIVE_Z.rotation(angle));
                matrices.translate(-0.5,-0.5,-0.5);
            }
        }

        this.gongBody.pitch = pitch;
        this.gongBody.roll = roll;
        VertexConsumer vertexConsumer = GONG_BODY_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntitySolid);

        model.getSwing().render(matrices, vertexConsumer, light, overlay);
        matrices.pop();
    }
}
