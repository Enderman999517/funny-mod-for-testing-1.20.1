package net.enderman999517.funnymodfortesting.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.enderman999517.funnymodfortesting.FunnyModForTestingClient;
import net.enderman999517.funnymodfortesting.world.dimension.ModDimensions;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {

    @Inject(at = @At("TAIL"), method = "applyFog(Lnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/BackgroundRenderer$FogType;FZF)V")
    private static void changeWaterFogLevel(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {

        float fogStart = viewDistance * FunnyModForTestingClient.waterStart * 0.01f;
        float fogEnd = viewDistance * FunnyModForTestingClient.waterEnd * 0.01f;
        Entity entity = camera.getFocusedEntity();

        if (camera.getSubmersionType().equals(CameraSubmersionType.WATER) && entity.getWorld().getDimensionKey().equals(ModDimensions.OCEANDIM_TYPE)) {

            if (entity instanceof ClientPlayerEntity clientPlayerEntity) {
                fogEnd *= Math.max(0.25f, clientPlayerEntity.getUnderwaterVisibility());
            }

            RenderSystem.setShaderFogStart(fogStart);
            RenderSystem.setShaderFogEnd(fogEnd);
        }
    }
}
