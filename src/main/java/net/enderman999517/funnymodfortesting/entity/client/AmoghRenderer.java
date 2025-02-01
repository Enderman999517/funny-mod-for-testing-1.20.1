package net.enderman999517.funnymodfortesting.entity.client;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.entity.custom.AmoghEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class AmoghRenderer extends MobEntityRenderer<AmoghEntity, AmoghModel<AmoghEntity>> {
    private static final Identifier TEXTURE = new Identifier(FunnyModForTesting.MOD_ID, "textures/entity/amogh.png");

    public AmoghRenderer(EntityRendererFactory.Context context) {
        super(context, new AmoghModel<>(context.getPart(ModModelLayers.AMOGH)), 0.0f);
    }

    @Override
    public Identifier getTexture(AmoghEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(AmoghEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if (mobEntity.isAttacking()) {
            matrixStack.scale(10, 10, 10);
        } else {
            matrixStack.scale(1, 1, 1);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
