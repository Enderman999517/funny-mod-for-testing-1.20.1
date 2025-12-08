package net.enderman999517.funnymodfortesting.entity.client;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.minecraft.client.model.*;
import net.minecraft.util.Identifier;

public class GongModel {
    public static final Identifier TEXTURE = new Identifier(FunnyModForTesting.MOD_ID, "textures/block/gong.png");
    public static final Identifier SWING_TEXTURE = new Identifier(FunnyModForTesting.MOD_ID, "textures/block/gong_swing.png");
    public static final Identifier BASE_TEXTURE = new Identifier(FunnyModForTesting.MOD_ID, "textures/block/gong_base.png");

    private final ModelPart root;
    private final ModelPart base;
    private final ModelPart swing;

    public GongModel(ModelPart root) {
        this.root = root;
        this.base = root.getChild("base");
        this.swing = root.getChild("swing");
    }

    public ModelPart getBase() {return base;}
    public ModelPart getSwing() {return swing;}

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData swing = modelPartData.addChild("swing", ModelPartBuilder.create().uv(0, 3).cuboid(-1.0F, 2.0F, 0.5F, 10.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 25).cuboid(7.0F, 4.0F, 0.5F, 2.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 9).cuboid(1.0F, 4.0F, 0.75F, 6.0F, 6.0F, 0.5F, new Dilation(0.0F))
                .uv(6, 25).cuboid(-1.0F, 4.0F, 0.5F, 2.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 6).cuboid(-1.0F, 10.0F, 0.5F, 10.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(22, 3).cuboid(7.0F, -1.0F, 0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(26, 3).cuboid(0.0F, -1.0F, 0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 10.0F, -1.0F));

        ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create().uv(0, 9).cuboid(-2.0F, -15.0F, 0.0F, 1.0F, 14.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-16.0F, -16.0F, 0.0F, 16.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(6, 9).cuboid(-15.0F, -15.0F, 0.0F, 1.0F, 14.0F, 2.0F, new Dilation(0.0F))
                .uv(12, 16).cuboid(-16.0F, -1.0F, -1.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(12, 21).cuboid(-3.0F, -1.0F, -1.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 24.0F, -1.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
}
