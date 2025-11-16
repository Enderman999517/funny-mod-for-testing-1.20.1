package net.enderman999517.funnymodfortesting.block.entity.renderer;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.block.entity.GongBlockEntity;
import net.enderman999517.funnymodfortesting.entity.client.ModModelLayers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class GongBlockEntityRenderer  implements BlockEntityRenderer<GongBlockEntity> {
    private static final String GONG_BODY = "gong_body";
    private static final String GONG_BASE = "gong_base";
    private final ModelPart gongBody;
    private final ModelPart gongBase;
    private static final Identifier TEXTURE = new Identifier(FunnyModForTesting.MOD_ID, "textures/block/gong.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityCutout(TEXTURE);


    public GongBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        ModelPart root = context.getLayerModelPart(ModModelLayers.GONG);
        this.gongBody = root.getChild(GONG_BODY);
        this.gongBase = root.getChild(GONG_BASE);
    }

    @Override
    public void render(GongBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        MinecraftClient client = MinecraftClient.getInstance();

        BakedModel model = client.getBakedModelManager().getModel(new ModelIdentifier(new Identifier(FunnyModForTesting.MOD_ID), "block/gong_body"));
        if (model == null) return;

        int ticks = entity.swingTicks;
        VertexConsumer vc = vertexConsumers.getBuffer(LAYER);

        float swingProgress = 0;
        //if (ticks > 0) {
            swingProgress = MathHelper.sin((ticks + tickDelta) / 30f * MathHelper.PI) * 0.5f;
        //}

        matrices.push();
        gongBase.render(matrices, vc, light, overlay);
        matrices.push();
        matrices.translate(0.5, 0.5, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_X.rotation(swingProgress));
        matrices.translate(-0.5, -0.5, -0.5);

        //Direction dir = entity.swingDir;
        //if (dir == Direction.NORTH || dir == Direction.SOUTH) {
        //    matrices.multiply(RotationAxis.POSITIVE_X.rotation(swingProgress));
        //} else matrices.multiply(RotationAxis.POSITIVE_Z.rotation(swingProgress));
//
        //MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(
        //        entity.getCachedState(), matrices, vertexConsumers, light, overlay
        //);

        gongBody.render(matrices, vc, light, overlay);
        matrices.pop();
        matrices.pop();
        //}
    }
}
