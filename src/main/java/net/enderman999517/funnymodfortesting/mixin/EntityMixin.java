package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.damage.ModDamageSources;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.enderman999517.funnymodfortesting.networking.EntityMovementTracker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Entity.class)
public abstract class EntityMixin {
    Entity self = (Entity)(Object)this;

    @Inject(
            method = "move",
            at = @At(
                    value = "TAIL"
            )
    )
    private void onMove(MovementType type, Vec3d movement, CallbackInfo ci) {

        if (!self.getWorld().isClient) {
            List<Entity> nearby = self.getWorld().getOtherEntities(
                    self,
                    self.getBoundingBox().expand(0.1),
                    e -> e != self && e !=self.getControllingVehicle()
            );
            for (Entity other : nearby) {
                if (self instanceof LivingEntity selfL && other instanceof LivingEntity otherL) {
                    if (selfL.isUsingItem() && selfL.getStackInHand(selfL.getActiveHand()).isOf(ModItems.SPEAR)) {
                        double hSpeed = EntityMovementTracker.getHSpeed(selfL.getUuid()) - EntityMovementTracker.getHSpeed(otherL.getUuid());
                        double closingX = EntityMovementTracker.getDx(selfL.getUuid()) - EntityMovementTracker.getDx(otherL.getUuid());
                        double closingZ = EntityMovementTracker.getDz(selfL.getUuid()) - EntityMovementTracker.getDz(otherL.getUuid());
                        float extra = (float) Math.min(100, hSpeed * 23);
                        if (extra > 5f) {
                            DamageSource source = new DamageSource(
                                    selfL.getWorld().getRegistryManager()
                                            .get(RegistryKeys.DAMAGE_TYPE)
                                            .entryOf(DamageTypes.PLAYER_ATTACK));
                            otherL.damage(source, extra);
                            otherL.addVelocity(closingX * 0.75, selfL.getVelocity().y, closingZ * 0.75);
                        }
                    }
                }
            }
        }
    }
}
