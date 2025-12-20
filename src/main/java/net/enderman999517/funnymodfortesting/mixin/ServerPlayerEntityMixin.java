package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin implements ModEntityData {
    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)(Object)this;
    boolean wasInOffhandPrevTick = false;

    @Override
    public boolean hasRingInOffhand() {
        return serverPlayerEntity.getStackInHand(Hand.OFF_HAND).isOf(ModItems.RING);
    }

    @Inject(method = "playerTick", at = @At("HEAD"))
    public void setHiddenForRing(CallbackInfo ci) {
        if (serverPlayerEntity instanceof ModEntityData modEntityData) {
            if(wasInOffhandPrevTick) {
                if(!modEntityData.hasRingInOffhand()) {
                    modEntityData.setHidden(false);
                    modEntityData.setRenderingOverlay(false);
                    wasInOffhandPrevTick = false;
                }
            } else if (modEntityData.hasRingInOffhand()) {
                modEntityData.setHidden(true);
                modEntityData.setRenderingOverlay(true);
                wasInOffhandPrevTick = true;
            }
        }
    }
}
