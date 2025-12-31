package net.enderman999517.funnymodfortesting.mixin;

import io.github.ladysnake.impersonate.Impersonator;
import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.entity.custom.HiddenEntity;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.enderman999517.funnymodfortesting.networking.EntityInventoryTracker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.NetworkSyncedItem;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @Unique
    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)(Object)this;
    @Unique
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

    @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    public void handleDeathWithImpersonate(DamageSource damageSource, CallbackInfo ci) {
        Entity attacker = damageSource.getAttacker();
        if(attacker != serverPlayerEntity && attacker instanceof ServerPlayerEntity serverAttacker) {
            //gets both inventories and adds to the tracker
             PlayerInventory targetInv = serverPlayerEntity.getInventory();
             PlayerInventory attackerInv = serverAttacker.getInventory();
             EntityInventoryTracker.putInvToList(serverPlayerEntity.getUuid(), targetInv);
             EntityInventoryTracker.putInvToList(attacker.getUuid(), attackerInv);


             //for each slot, set the attacker's slot the same as the target's slot
            for (int i = 0; i < serverAttacker.getInventory().size(); i++) {
                StackReference attackerStackReference = serverAttacker.getStackReference(i);
                StackReference targetStackReference = serverPlayerEntity.getStackReference(i);

                attackerStackReference.set(targetStackReference.get());

                if (attackerStackReference.get().getItem().isNetworkSynced()) {
                    Packet<?> packet = ((NetworkSyncedItem)attackerStackReference.get().getItem()).createSyncPacket(attackerStackReference.get(), serverAttacker.getWorld(), serverAttacker);
                    if (packet != null) {
                        serverAttacker.networkHandler.sendPacket(packet);
                    }
                }
            }

            //stop death message and other stuff from broadcasting if the player is killed with the sword
            attacker.getHandItems().forEach(stack -> {
                if(stack.isOf(ModItems.IMPERSONATE_SWORD)) {
                    ci.cancel();
                }
            });

            //make attacker impersonate the killed player
            //if(attacker instanceof ServerPlayerEntity) {
                Impersonator.get((PlayerEntity) attacker).impersonate(FunnyModForTesting.IMPERSONATION_KEY, serverPlayerEntity.getGameProfile());
            //} else Impersonator.get((PlayerEntity) attacker).stopImpersonation(FunnyModForTesting.IMPERSONATION_KEY);

            /*1: get attacker inventory and save
            2: get target inventory and save
            3: replace attacker inventory with target inventory
            */
        }

        //stop impersonation if the impersonator dies
        if(Impersonator.get(serverPlayerEntity).isImpersonating()) {
            Impersonator.get(serverPlayerEntity).stopImpersonation(FunnyModForTesting.IMPERSONATION_KEY);

        }
    }

}
