package net.enderman999517.funnymodfortesting.entity.custom;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

public class ImpersonateShadowEntity extends Entity implements Ownable {
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUuid;

    public ImpersonateShadowEntity(EntityType<? extends ImpersonateShadowEntity> entityType, World world) {
        super(entityType, world);
    }

    public ImpersonateShadowEntity(LivingEntity owner, World world) {
        super(ModEntities.IMPERSONATE_SHADOW, owner, world);
    }


    @Override
    public void tick() {
        super.tick();

        if (this.owner == null) {
            FunnyModForTesting.LOGGER.info("Discarded shadow entity with id {} and position {}", this.getId(), this.getPos());
            this.discard();
        } else this.teleport(owner.getServer().getWorld(owner.getWorld().getRegistryKey()), owner.getX(), owner.getY(), owner.getZ(), Set.of(), 0f, 0f);

        //sets owner null if owner dc
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            if (this.owner == handler.player) {
                this.setOwner(null);
            }
        });


    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }


    public void setOwner(@Nullable LivingEntity owner) {
        this.owner = owner;
        this.ownerUuid = owner == null ? null : owner.getUuid();
    }

    @Override
    public @Nullable Entity getOwner() {
        if (this.owner == null && this.ownerUuid != null && this.getWorld() instanceof ServerWorld) {
            Entity entity = ((ServerWorld)this.getWorld()).getEntity(this.ownerUuid);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity)entity;
            }
        }
        return this.owner;
    }
}
