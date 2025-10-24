package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "Lnet/minecraft/entity/LivingEntity;canTarget(Lnet/minecraft/entity/LivingEntity;)Z", at = @At("HEAD"), cancellable = true)
    private void cancelTargetIfPlayerInvis(LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity)(Object)this;
        World world = entity.getWorld();
        if (!world.isClient) {
            if (target instanceof ModEntityData modEntityDataT) {
                if (entity instanceof  ModEntityData modEntityDataE) {
                    if ((modEntityDataE.isHidden() && !modEntityDataT.isHidden()) || (!modEntityDataE.isHidden() && modEntityDataT.isHidden())) {
                        cir.setReturnValue(false);
                    }
                }
            }
        }
    }
}
