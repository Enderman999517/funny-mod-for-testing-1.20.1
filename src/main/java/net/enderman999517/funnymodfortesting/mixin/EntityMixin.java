package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.damage.ModDamageSources;
import net.enderman999517.funnymodfortesting.networking.EntityMovementTracker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Entity.class)
public abstract class EntityMixin {
    Entity self = (Entity)(Object)this;


    @Shadow
    public abstract boolean collidesWith(Entity other);
    @Shadow
    public abstract boolean isCollidable();
    @Shadow
    public abstract boolean damage(net.minecraft.entity.damage.DamageSource source, float amount);

    @Inject(
            method = "move",
            at = @At(
                    value = "TAIL"
            )
    )
    private void onMove(MovementType type, Vec3d movement, CallbackInfo ci) {
        Entity self = (Entity) (Object) this;

        if (!self.getWorld().isClient) {
            List<Entity> nearby = self.getWorld().getOtherEntities(
                    self,
                    self.getBoundingBox().expand(0.1),
                    e -> e != self && e.isCollidable()
            );

            for (Entity other : nearby) {
                if (self.collidesWith(other)) {
                    
                }
            }
        }
    }

}
