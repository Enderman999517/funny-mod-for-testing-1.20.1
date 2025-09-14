package net.enderman999517.funnymodfortesting.render;

import com.google.common.collect.Sets;
import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import java.util.Set;

public class ModEntityModelLayers {
    private static final Set<EntityModelLayer> LAYERS = Sets.<EntityModelLayer>newHashSet();

    public static final EntityModelLayer PLAYER_CHARGED = register("player", "charge");

    private static EntityModelLayer register(String id, String layer) {
        EntityModelLayer entityModelLayer = create(id, layer);
        if (!LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + entityModelLayer);
        } else {
            return entityModelLayer;
        }
    }

    private static EntityModelLayer create(String id, String layer) {
        return new EntityModelLayer(new Identifier(FunnyModForTesting.MOD_ID, id), layer);
    }
}
