package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.enderman999517.funnymodfortesting.networking.ModSync;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityNbtMixin implements ModEntityData {
    @Unique
    Entity entity = (Entity)(Object)this;

    @Unique
    private boolean hidden = false;
    @Unique
    private boolean renderingOverlay = false;
    @Unique
    private boolean beingImpersonated = false;

    @Inject(method = "writeNbt(Lnet/minecraft/nbt/NbtCompound;)Lnet/minecraft/nbt/NbtCompound;", at = @At("RETURN"))
    private void writeModData(NbtCompound nbt, CallbackInfoReturnable<CallbackInfo> cir) {
        nbt.putBoolean("hidden", hidden);
        nbt.putBoolean("renderingOverlay", renderingOverlay);
        nbt.putBoolean("beingImpersonated", beingImpersonated);
    }

    @Inject(method = "readNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("RETURN"))
    private void readModData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("hidden")) {
            hidden = nbt.getBoolean("hidden");
        }
        if (nbt.contains("renderingOverlay")) {
            renderingOverlay = nbt.getBoolean("renderingOverlay");
        }
        if (nbt.contains("beingImpersonated")) {
            beingImpersonated = nbt.getBoolean("beingImpersonated");
        }
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
        if (!entity.getWorld().isClient) {
            ModSync.syncHiddenFlag(entity);
        }
    }

    @Override
    public boolean isRenderingOverlay() {
        return renderingOverlay;
    }

    @Override
    public void setRenderingOverlay(boolean renderingOverlay) {
        this.renderingOverlay = renderingOverlay;
        if (!entity.getWorld().isClient) {
            ModSync.syncRenderingOverlayFlag(entity, renderingOverlay);
        }
    }

    @Override
    public boolean hasRingInOffhand() {
        if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
            return serverPlayerEntity.getStackInHand(Hand.OFF_HAND).isOf(ModItems.RING);
        } else return false;
    }

    @Override
    public boolean isBeingImpersonated() {
        return beingImpersonated;
    }

    public void setBeingImpersonated(boolean beingImpersonated) {
        this.beingImpersonated = beingImpersonated;
        if (!entity.getWorld().isClient) {
            ModSync.syncBeingImpersonatedFlag(entity, beingImpersonated);
        }
    }
}
