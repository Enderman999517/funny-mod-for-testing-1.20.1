package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.ModEntityData;
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
    protected abstract boolean isWalking();

    @Shadow
    protected abstract boolean canSprint();

    @Shadow
    protected abstract boolean canVehicleSprint(Entity vehicle);

    @Shadow protected abstract void sendMovementPackets();

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

    @Inject(method = "tickMovement", at = @At("HEAD"), cancellable = true)
    private void cancelMovementIfBeingImpersonated(CallbackInfo ci) {
        if (entity instanceof ModEntityData modEntityData && modEntityData.isBeingImpersonated()) {
            ci.cancel();
        }
    }

    //@Inject(method = "init", at = @At("HEAD"))
    //private void resyncCameraMovementIfBeingImpersonated(CallbackInfo ci) {
    //    if (entity instanceof ModEntityData modEntityData && modEntityData.isBeingImpersonated()) {
    //        ClientTickEvents.END_CLIENT_TICK.register(client -> {
    //            //System.out.println("ASDFKLH");
    //            //this.sendMovementPackets();
    //            client.worldRenderer.
    //        });
    //    }
    //}

}
