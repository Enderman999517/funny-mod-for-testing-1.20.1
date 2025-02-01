package net.enderman999517.funnymodfortesting.entity.client;

import net.enderman999517.funnymodfortesting.entity.custom.AmoghEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class AmoghModel<T extends AmoghEntity> extends SinglePartEntityModel<T> {
	private final ModelPart bone;

	public AmoghModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 0)
				.cuboid(-47.0F, -48.0F, -1.0F, 48.0F, 48.0F, 0.0F,
						new Dilation(0.0F)), ModelTransform.pivot(23.0F, 24.0F, 1.0F));
		return TexturedModelData.of(modelData, 48, 48);
	}
	@Override
	public void setAngles(AmoghEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return bone;
	}
}