package net.enderman999517.funnymodfortesting.entity.custom;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.enderman999517.funnymodfortesting.entity.effect.ModStatusEffects;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatusEffectProjectileEntity extends ThrownItemEntity {
    private StatusEffect statusEffect;
    private double radius;
    //private Potion potion = Potions.EMPTY;
    //private final Map<Entity, Integer> affectedEntities = Maps.<Entity, Integer>newHashMap();

    public StatusEffect setEffect(StatusEffect statusEffect) {
        this.statusEffect = statusEffect;
        return statusEffect;
    }
    public double setRadius(double radius) {
        this.radius = radius;
        return radius;
    }

    public StatusEffectProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public StatusEffectProjectileEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.STATUS_EFFECT_PROJECTILE, livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, (byte) 3);
            //this.getWorld().getClosestPlayer(this, 10).addStatusEffect(new StatusEffectInstance(statusEffect));
            //if (this.getWorld().isPlayerInRange(this.getX(), this.getY(), this.getZ(), 5)) {

            //}

            //List<LivingEntity> entitiesInRange = new ArrayList<LivingEntity>(this.getWorld().getOtherEntities(null, new Box(
            //        this.getX() - radius,
            //        this.getY() - radius,
            //        this.getZ() - radius,
            //        this.getX() + radius,
            //        this.getY() + radius,
            //        this.getZ() + radius)));

            List<LivingEntity> entitiesInRange = this.getWorld().getNonSpectatingEntities(LivingEntity.class, new Box(
                    this.getX() - radius,
                    this.getY() - radius,
                    this.getZ() - radius,
                    this.getX() + radius,
                    this.getY() + radius,
                    this.getZ() + radius));

            for (LivingEntity livingEntity : entitiesInRange) {
                //this.affectedEntities.entrySet();
                //List<StatusEffectInstance> list = Lists.<StatusEffectInstance>newArrayList();
                livingEntity.addStatusEffect(new StatusEffectInstance(statusEffect, 200, -1, false, false, false));

                //for (StatusEffectInstance statusEffectInstance : this.potion.getEffects()) {
                //    list.add(
                //            new StatusEffectInstance(
                //                    statusEffectInstance.getEffectType(),
                //                    statusEffectInstance.mapDuration(i -> i / 4),
                //                    statusEffectInstance.getAmplifier(),
                //                    statusEffectInstance.isAmbient(),
                //                    statusEffectInstance.shouldShowParticles()
                //            )
                //    );
                //}
            }



            //for (this.getWorld().getOtherEntities(null, new Box(
            //        this.getX() - radius,
            //        this.getY() - radius,
            //        this.getZ() - radius,
            //        this.getX() + radius,
            //        this.getY() + radius,
            //        this.getZ() + radius));;) {
//
            //    setEffect(statusEffect);
            //    return;
            //}
        }
        this.discard();
        super.onBlockHit(blockHitResult);
    }
}
