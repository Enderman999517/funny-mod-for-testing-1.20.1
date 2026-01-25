package net.enderman999517.funnymodfortesting.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.enderman999517.funnymodfortesting.damage.ModDamageSources;
import net.enderman999517.funnymodfortesting.damage.ModDamageTypes;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @WrapOperation(
            method = "attack",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private boolean useDifferentAttackDamage(Entity instance, DamageSource source, float amount, Operation<Boolean> original) {
        if (source.getAttacker() instanceof PlayerEntity player) {
            if (player.getStackInHand(player.getActiveHand()).isOf(ModItems.IMPERSONATE_SWORD)) {
                return instance.damage(ModDamageSources.of(instance.getWorld(), ModDamageTypes.IMPERSONATE_DAMAGE, source.getAttacker()), amount);
            } else return original.call(instance, source, amount);
        } else return original.call(instance, source, amount);
    }
}
