package net.enderman999517.funnymodfortesting.entity.custom;

import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ExplosiveProjectileEntity extends ThrownItemEntity {
    public ExplosiveProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ExplosiveProjectileEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.EXPLOSIVE_PROJECTILE, livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.OVERPOWERED;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 10, World.ExplosionSourceType.MOB);
        }
        this.discard();
        super.onBlockHit(blockHitResult);
    }

    //@Override
    //protected void onEntityHit(EntityHitResult entityHitResult) {
    //    if (!this.getWorld().isClient) {
    //        if (!entityHitResult.getEntity().isPlayer()) {
    //            this.getWorld().sendEntityStatus(this, (byte)3);
    //            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 10, World.ExplosionSourceType.MOB);
    //        }
    //    }
    //    this.discard();
    //    super.onEntityHit(entityHitResult);
    //}
}
