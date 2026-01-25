package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.damage.ModDamageTypes;
import net.enderman999517.funnymodfortesting.entity.custom.HiddenEntity;
import net.enderman999517.funnymodfortesting.networking.EntityInventoryTracker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.NetworkSyncedItem;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void handleDamageWithImpersonate(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.isOf(ModDamageTypes.IMPERSONATE_DAMAGE)) {
            FunnyModForTesting.LOGGER.error("source=imp");
            if (amount >= serverPlayerEntity.getHealth()) {
                FunnyModForTesting.LOGGER.error("damage>health");
                cir.setReturnValue(false);

                FunnyModForTesting.LOGGER.error("att: {}", serverPlayerEntity.getAttacker());
                FunnyModForTesting.LOGGER.error("!isalive: {}", !serverPlayerEntity.isAlive());
                FunnyModForTesting.LOGGER.error("a-glat: {}", serverPlayerEntity.age - serverPlayerEntity.getLastAttackedTime());
                if (serverPlayerEntity.getAttacker() instanceof ServerPlayerEntity attacker) {
                    //serverPlayerEntity.setInvisible(true);
                    //serverPlayerEntity.setInvulnerable(true);
                    //serverPlayerEntity.setPos(attacker.getPos().x, attacker.getPos().y, attacker.getPos().z);
                    //serverPlayerEntity.setHealth(attacker.getHealth());
                    //serverPlayerEntity.getHungerManager().setFoodLevel(attacker.getHungerManager().getFoodLevel());
                    //serverPlayerEntity.getHungerManager().setSaturationLevel(attacker.getHungerManager().getSaturationLevel());
                    //serverPlayerEntity.getHungerManager().setExhaustion(attacker.getHungerManager().getExhaustion());

                    serverPlayerEntity.changeGameMode(GameMode.SPECTATOR);
                    serverPlayerEntity.setCameraEntity(attacker);

                    //saves my (killed w/ sword) inv to tracker so can be put onto attacker's inv
                    PlayerInventory targetInv = serverPlayerEntity.getInventory();
                    EntityInventoryTracker.putInvToList(serverPlayerEntity.getUuid(), targetInv);

                    //saves attacker inv to tracker so can be recalled when attacker dies
                    PlayerInventory attackerInv = attacker.getInventory();
                    EntityInventoryTracker.putInvToList(attacker.getUuid(), attackerInv);

                    FunnyModForTesting.LOGGER.error("at io spe");


                    //sets attacker's inv the same as target's inv
                    for (int i = 0; i < attacker.getInventory().size(); i++) {
                        StackReference attackerStackReference = attacker.getStackReference(i);
                        StackReference targetStackReference = serverPlayerEntity.getStackReference(i);

                        attackerStackReference.set(targetStackReference.get());

                        if (attackerStackReference.get().getItem().isNetworkSynced()) {
                            Packet<?> packet = ((NetworkSyncedItem)attackerStackReference.get().getItem()).createSyncPacket(attackerStackReference.get(), attacker.getWorld(), attacker);
                            if (packet != null) {
                                attacker.networkHandler.sendPacket(packet);
                            }
                        }
                    }
                }
            }
        }
    }

    //@Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    //public void handleDeathWithImpersonate(DamageSource damageSource, CallbackInfo ci) {
    //    //World world = damageSource.getSource().getWorld();
    //    //PlayerInventory targetInv = serverPlayerEntity.getInventory();
    //    //EntityInventoryTracker.putInvToList(serverPlayerEntity.getUuid(), targetInv);
//
    //    //if killed with sword
    //    if(damageSource.isOf(ModDamageTypes.IMPERSONATE_DAMAGE)) {
    //        if (serverPlayerEntity.getAttacker() instanceof ServerPlayerEntity attacker) {
    //            //saves my (killed w/ sword) inv to tracker so can be put onto attacker's inv
    //            PlayerInventory targetInv = serverPlayerEntity.getInventory();
    //            EntityInventoryTracker.putInvToList(serverPlayerEntity.getUuid(), targetInv);
    //
    //            //saves attacker inv to tracker so can be recalled when attacker dies
    //            PlayerInventory attackerInv = attacker.getInventory();
    //            EntityInventoryTracker.putInvToList(attacker.getUuid(), attackerInv);
//
    //            FunnyModForTesting.LOGGER.error("killed w/ sword");
    //            //stops death message + stats + item drops from occurring
    //            ci.cancel();
    //        }
    //    }





        // attacker is not me
        // serverPlayerEntity is me so i die
        //Entity attacker = damageSource.getAttacker();
        //if(attacker != serverPlayerEntity && attacker instanceof ServerPlayerEntity serverAttacker) {
//
        //    PlayerInventory targetInv = serverPlayerEntity.getInventory();
        //    EntityInventoryTracker.putInvToList(serverPlayerEntity.getUuid(), targetInv);
        //    //gets attacker inventory and adds to the tracker //works
        //    PlayerInventory attackerInv = serverAttacker.getInventory();
        //    EntityInventoryTracker.putInvToList(attacker.getUuid(), attackerInv );
//
//
        //    //for each slot, set the attacker's slot the same as the target's slot //works
        //    for (int i = 0; i < serverAttacker.getInventory().size(); i++) {
        //        StackReference attackerStackReference = serverAttacker.getStackReference(i);
        //        StackReference targetStackReference = serverPlayerEntity.getStackReference(i);
//
        //        attackerStackReference.set(targetStackReference.get());
//
        //        if (attackerStackReference.get().getItem().isNetworkSynced()) {
        //            Packet<?> packet = ((NetworkSyncedItem)attackerStackReference.get().getItem()).createSyncPacket(attackerStackReference.get(), serverAttacker.getWorld(), serverAttacker);
        //            if (packet != null) {
        //                serverAttacker.networkHandler.sendPacket(packet);
        //            }
        //        }
        //    }
//
        //    //stop death message and other stuff from broadcasting if the player is killed with the sword //works except for item scatterer
        //    attacker.getHandItems().forEach(stack -> {
        //        if(stack.isOf(ModItems.IMPERSONATE_SWORD)) {
        //            ci.cancel();
        //         }
        //    });
//
        //    //make attacker impersonate the killed player //works
        //    //if(attacker instanceof ServerPlayerEntity) {
        //        Impersonator.get((PlayerEntity) attacker).impersonate(FunnyModForTesting.IMPERSONATION_KEY, serverPlayerEntity.getGameProfile());
        //    //} else Impersonator.get((PlayerEntity) attacker).stopImpersonation(FunnyModForTesting.IMPERSONATION_KEY);
//
        //    /*1: get attacker inventory and save
        //    2: get target inventory and save
        //    3: replace attacker inventory with target inventory
        //    */
        //}
//
        ////we dont care about attacker
        ////serverPlayerEntity is me so i die
//
        ////if the impersonator dies:
        //if(Impersonator.get(serverPlayerEntity).isImpersonating()) {
        //    //stop impersonation //works
        //    Impersonator.get(serverPlayerEntity).stopImpersonation(FunnyModForTesting.IMPERSONATION_KEY);
//
//
        //    //reset my (attacker inventory from last step) inventory to before any impersonation and clear both lists
        //    PlayerInventory originalInv =  EntityInventoryTracker.getInvList(serverPlayerEntity.getUuid()).get(0);
        //    if (originalInv != null) {
        //        for (int i = 0; i < originalInv.size(); i++) {
        //            ItemStack originalInvStack = originalInv.getStack(i);
        //            StackReference targetStackReference = serverPlayerEntity.getStackReference(i);
        //            FunnyModForTesting.LOGGER.error("tsr.g: {}", targetStackReference.get());
        //            FunnyModForTesting.LOGGER.error("ois: {}", originalInvStack);
//
         //            targetStackReference.set(originalInvStack);
//
        //            if (originalInvStack.getItem().isNetworkSynced()) {
        //                Packet<?> packet = ((NetworkSyncedItem)originalInvStack.getItem()).createSyncPacket(originalInvStack, serverPlayerEntity.getWorld(), serverPlayerEntity);
        //                if (packet != null) {
        //                    serverPlayerEntity.networkHandler.sendPacket(packet);
        //                }
        //            }
        //        }
        //        EntityInventoryTracker.getInvList(serverPlayerEntity.getUuid()).clear();
        //    }
        //}
    //}

}
