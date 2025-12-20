package net.enderman999517.funnymodfortesting.entity.client;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.entity.custom.HiddenEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class HiddenRenderer extends MobEntityRenderer<HiddenEntity, HiddenModel<HiddenEntity>> {

    private static final Identifier TEXTURE = new Identifier(FunnyModForTesting.MOD_ID, "textures/entity/hidden.png");

    public HiddenRenderer(EntityRendererFactory.Context context) {
        super(context, new HiddenModel<>(context.getPart(ModModelLayers.HIDDEN)), 0);
    }

    @Override
    public Identifier getTexture(HiddenEntity entity) {
        return TEXTURE;
    }
}
