package net.enderman999517.funnymodfortesting.entity.client;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GongModel extends Model {
    public static final Identifier TEXTURE = new Identifier(FunnyModForTesting.MOD_ID, "textures/block/gong.png");

    private final ModelPart main;
    private final ModelPart swing;

    public GongModel(ModelPart root) {
        super(RenderLayer::getEntityCutout);
        this.main = root.getChild("main");
        this.swing = this.main.getChild("swing");
    }

    public ModelPart getMain() {return main;}
    public ModelPart getSwing() {return this.swing;}

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 9).cuboid(6.0F, -8.0F, -1.0F, 1.0F, 14.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-8.0F, -9.0F, -1.0F, 16.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(6, 9).cuboid(-7.0F, -8.0F, -1.0F, 1.0F, 14.0F, 2.0F, new Dilation(0.0F))
                .uv(12, 16).cuboid(-8.0F, 6.0F, -2.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(12, 21).cuboid(5.0F, 6.0F, -2.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 17.0F, 0.0F));

        ModelPartData swing = main.addChild("swing", ModelPartBuilder.create().uv(0, 3).cuboid(-5.0F, 3.0F, -0.5F, 10.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 25).cuboid(3.0F, 5.0F, -0.5F, 2.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 9).cuboid(-3.0F, 5.0F, -0.25F, 6.0F, 6.0F, 0.5F, new Dilation(0.0F))
                .uv(6, 25).cuboid(-5.0F, 5.0F, -0.5F, 2.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 6).cuboid(-5.0F, 11.0F, -0.5F, 10.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(22, 3).cuboid(3.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(26, 3).cuboid(-4.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}
