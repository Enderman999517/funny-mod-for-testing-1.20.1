package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.enderman999517.funnymodfortesting.entity.custom.ImpersonateShadowEntity;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.enderman999517.funnymodfortesting.networking.ModSync;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(Entity.class)
public abstract class EntityNbtMixin implements ModEntityData {

    @Shadow public abstract World getWorld();

    @Shadow public abstract @Nullable MinecraftServer getServer();

    @Unique
    Entity entity = (Entity)(Object)this;

    @Unique
    private boolean hidden = false;
    @Unique
    private boolean renderingOverlay = false;
    @Unique
    private boolean beingImpersonated = false;
    @Unique
    private boolean impersonating = false;

    @Inject(method = "writeNbt(Lnet/minecraft/nbt/NbtCompound;)Lnet/minecraft/nbt/NbtCompound;", at = @At("RETURN"))
    private void writeModData(NbtCompound nbt, CallbackInfoReturnable<CallbackInfo> cir) {
        nbt.putBoolean("hidden", hidden);
        nbt.putBoolean("renderingOverlay", renderingOverlay);
        nbt.putBoolean("beingImpersonated", beingImpersonated);
        nbt.putBoolean("impersonating", impersonating);
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
        if (nbt.contains("impersonating")) {
            impersonating = nbt.getBoolean("impersonating");
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

    @Override
    public void setBeingImpersonated(boolean beingImpersonated) {
        this.beingImpersonated = beingImpersonated;
        if (!entity.getWorld().isClient) {
            ModSync.syncBeingImpersonatedFlag(entity, beingImpersonated);
        }
    }

    @Override
    public boolean isImpersonating() {
        return impersonating;
    }

    @Override
    public void setImpersonating(boolean impersonating) {

        if (this.getWorld().getServer() != null) {
            ServerWorld serverWorld = this.getWorld().getServer().getWorld(this.getWorld().getRegistryKey());

            //if going from not impersonating to impersonating
            if ((!this.isImpersonating() && impersonating)) {
                ImpersonateShadowEntity impersonateShadowEntity = new ImpersonateShadowEntity(ModEntities.IMPERSONATE_SHADOW, this.getWorld());
                if (entity instanceof LivingEntity le && !entity.getWorld().isClient) {
                    impersonateShadowEntity.setTryDiscard(false);
                    impersonateShadowEntity.setOwner(le);
                    impersonateShadowEntity.setPos(le.getX(), le.getY(), le.getZ());
                    serverWorld.spawnEntityAndPassengers(impersonateShadowEntity);
                    impersonateShadowEntity.setOwner(le);
                    impersonateShadowEntity.setTryDiscard(true);

                    FunnyModForTesting.LOGGER.error("not impersonating to impersonating");
                    FunnyModForTesting.LOGGER.error("owner: " + impersonateShadowEntity.getOwner() + ", id: " + impersonateShadowEntity.getId());
                }
            } else {
                //adds specific shadow to be removed
                Box box = new Box(entity.getX() - 1, entity.getY() - 1, entity.getZ() - 1, entity.getX() + 1, entity.getY() + 1, entity.getZ() + 1);
                //List<ImpersonateShadowEntity> entityList = new ArrayList<>();
                serverWorld.getOtherEntities(entity, box).forEach(entity1 -> {
                    //if ((entity1.getDisplayName().getString().equals(entity.getUuid().toString())) && entity1 instanceof ImpersonateShadowEntity) {
                    //    entityList.add((ImpersonateShadowEntity) entity1);
                    //}

                    FunnyModForTesting.LOGGER.error("set owner null in enbtmxn");
                    if (entity1 instanceof ImpersonateShadowEntity impersonateShadowEntity && impersonateShadowEntity.getOwner() == entity) {
                        impersonateShadowEntity.setOwner(null);
                    }
                });

                //if (!entityList.isEmpty()) {
                //    ImpersonateShadowEntity impersonateShadowEntity = entityList.get(entityList.size() - 1);
                //    impersonateShadowEntity.setOwner(null);
                //}
            }
        }

        this.impersonating = impersonating;
        if (!entity.getWorld().isClient) {
            ModSync.syncImpersonatingFlag(entity, impersonating);
        }
    }
}
