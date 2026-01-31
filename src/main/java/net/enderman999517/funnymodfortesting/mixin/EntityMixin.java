package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "pushAwayFrom", at = @At("HEAD"), cancellable = true)
    private void stopPushIfBeingImpersonated(Entity entity, CallbackInfo ci) {
        if (entity instanceof ModEntityData modEntityData && modEntityData.isBeingImpersonated()) {
            ci.cancel();
        }
    }
}
