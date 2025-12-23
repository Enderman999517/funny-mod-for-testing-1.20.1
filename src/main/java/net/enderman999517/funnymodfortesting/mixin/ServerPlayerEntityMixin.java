package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.entity.custom.HiddenEntity;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)(Object)this;
    boolean wasInOffhandPrevTick = false;

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

            Box checkBox = new Box(
                    serverPlayerEntity.getX() - 100, serverPlayerEntity.getY() - 100, serverPlayerEntity.getZ() - 100,
                    serverPlayerEntity.getX() + 100, serverPlayerEntity.getY() + 100, serverPlayerEntity.getZ() + 100);

            List<Entity> entities = serverPlayerEntity.getServerWorld().getOtherEntities(serverPlayerEntity, checkBox);
            entities.forEach(entity -> {
                if (entity instanceof HiddenEntity hiddenEntity) {
                    if (modEntityData.isHidden() && hiddenEntity.getTracking()) {
                        hiddenEntity.getBossBar().addPlayer(serverPlayerEntity);
                    } else if (hiddenEntity.getBossBar().getPlayers().contains(serverPlayerEntity) && !modEntityData.hasRingInOffhand() || !hiddenEntity.getTracking()) {
                        hiddenEntity.getBossBar().removePlayer(serverPlayerEntity);
                    }
                }
            });
        }
    }
}
