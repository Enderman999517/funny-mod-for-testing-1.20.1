package net.enderman999517.funnymodfortesting.entity.client;

import net.minecraft.client.model.*;

public class ModModels {
    public static TexturedModelData getGongexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        root.addChild("gong_base", ModelPartBuilder.create().uv(0,0).cuboid(0,0,6,16,17,4), ModelTransform.NONE);
        root.addChild("gong_body", ModelPartBuilder.create().uv(0,32).cuboid(3, 3, 7.5F, 9, 9, 1), ModelTransform.NONE);

        return TexturedModelData.of(modelData, 64, 64);
    }
}
