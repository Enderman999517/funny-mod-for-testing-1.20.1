package net.enderman999517.funnymodfortesting.render;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Environment(EnvType.CLIENT)
public class ModEntityModels {
    public static Map<EntityModelLayer, TexturedModelData> getModels() {
        ImmutableMap.Builder<EntityModelLayer, TexturedModelData> builder = ImmutableMap.builder();
        builder.put(ModEntityModelLayers.PLAYER_CHARGED, TexturedModelData.of(PlayerEntityModel.getTexturedModelData(new Dilation(2f), false), 64, 64));


        ImmutableMap<EntityModelLayer, TexturedModelData> immutableMap = builder.build();
        List<EntityModelLayer> list = (List<EntityModelLayer>) EntityModelLayers.getLayers()
                .filter(layer -> !immutableMap.containsKey(layer))
                .collect(Collectors.toList());
        if (!list.isEmpty()) {
            throw new IllegalStateException("Missing layer definitions: " + list);
        } else {
            return immutableMap;
        }
    }

}
