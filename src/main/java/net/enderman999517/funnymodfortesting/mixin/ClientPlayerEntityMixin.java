package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.item.ModItems;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffects;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
    ClientPlayerEntity entity = (ClientPlayerEntity)(Object) this;

    //@Inject(method = "tickMovement", at = @At("TAIL"))
    //private void resetEntityUseSpeed(CallbackInfo ci) {
    //    ClientPlayerEntity entity = (ClientPlayerEntity)(Object) this;
    //    if (entity.isUsingItem() && !entity.hasVehicle()) {
    //        if (entity.getStackInHand(entity.getActiveHand()).isOf(ModItems.SPEAR)) {
    //            float yaw = MathHelper.wrapDegrees(entity.getBodyYaw());
    //            float forward =  entity.input.movementForward;
    //            float side =  entity.input.movementSideways;
    //            float normalForward = (float) (forward * Math.cos(Math.toRadians(yaw)) + side * Math.sin(Math.toRadians(yaw)));
    //            float normalSide = (float) (forward * Math.sin(Math.toRadians(yaw)) + side * -Math.cos(Math.toRadians(yaw)));
    //            float x;
    //            float z;
    //            x = -normalSide;
    //            z = normalForward;
    //            entity.input.movementForward *= 5;
    //            entity.input.movementSideways *= 5;
    //            entity.setVelocity(entity.getVelocity().x, entity.getVelocity().y, entity.getVelocity().z);
    //            float f = MathHelper.clamp(0.3F + EnchantmentHelper.getSwiftSneakSpeedBoost(entity), 0.0F, 1.0F);
    //            entity.input.tick(false, f);
//
    //        }
    //    }
    //}

    @Inject(
            method = "tickMovement",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/input/Input;movementSideways:F",
                    opcode = Opcodes.PUTFIELD
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void onSTickMovement(CallbackInfo ci) {
        if (entity.getStackInHand(entity.getActiveHand()).isOf(ModItems.SPEAR)) {
            entity.input.movementSideways /= 0.2F;
            entity.input.movementForward /= 0.2F;
        }
    }
    @Inject(
            method = "tickMovement",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/input/Input;movementForward:F",
                    opcode = Opcodes.PUTFIELD
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void onFTickMovement(CallbackInfo ci) {
        ClientPlayerEntity entity = (ClientPlayerEntity)(Object) this;
        if (entity.getStackInHand(entity.getActiveHand()).isOf(ModItems.SPEAR)) {
            entity.input.movementSideways /= 0.2F;
            entity.input.movementForward /= 0.2F;
        }
    }

    @Shadow
    public abstract boolean isWalking();

    @Shadow
    public abstract boolean canSprint();

    @Shadow
    public abstract boolean canVehicleSprint(Entity vehicle);

    @Inject(method = "canStartSprinting", at = @At("RETURN"), cancellable = true)
    private void canSprintIfUsingSpear(CallbackInfoReturnable<Boolean> cir) {
        if (entity.getStackInHand(entity.getActiveHand()).isOf(ModItems.SPEAR)) {
            cir.setReturnValue(!entity.isSprinting()
                    && this.isWalking()
                    && this.canSprint()
                    && !entity.hasStatusEffect(StatusEffects.BLINDNESS)
                    && (!entity.hasVehicle() || this.canVehicleSprint(entity.getVehicle()))
                    && !entity.isFallFlying());
        }
    }
}
