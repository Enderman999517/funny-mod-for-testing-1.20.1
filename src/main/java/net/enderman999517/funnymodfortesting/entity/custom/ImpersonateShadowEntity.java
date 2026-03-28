package net.enderman999517.funnymodfortesting.entity.custom;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
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

    private boolean tryDiscard = false;

    public void setTryDiscard(boolean tryDiscard) {
        this.tryDiscard = tryDiscard;
    }


    public ImpersonateShadowEntity(EntityType<? extends ImpersonateShadowEntity> entityType, World world) {
        //disabled rendering in renderDispatcherMixin
        super(entityType, world);
        this.noClip = true;
        this.setInvulnerable(true);
        this.setNoGravity(true);

        //sets owner null if owner dc
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            if (this.owner == handler.player) {
                this.setOwner(null);
            }
        });
    }



    @Override
    public void tick() {
        if (this.owner == null && tryDiscard) {
            FunnyModForTesting.LOGGER.info("Discarded shadow entity with uuid {} and position {}", this.getUuid(), this.getPos());
            this.discard();
        } else if (tryDiscard) {
            this.teleport(owner.getServer().getWorld(owner.getWorld().getRegistryKey()), owner.getX(), owner.getY(), owner.getZ(), Set.of(), owner.getYaw(), owner.getPitch());
            FunnyModForTesting.LOGGER.error("aslifyduiosdgkwey df");
        }

        super.tick();

    }

    @Override
    protected void initDataTracker() {
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

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        if (this.ownerUuid != null) {
            nbt.putUuid("Owner", this.ownerUuid);
        }
    }

    protected boolean isOwner(Entity entity) {
        return entity.getUuid().equals(this.ownerUuid);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.containsUuid("Owner")) {
            this.ownerUuid = nbt.getUuid("Owner");
            this.owner = null;
        }
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        Entity entity = this.getWorld().getEntityById(packet.getEntityData());
        if (entity != null) {
            this.setOwner((LivingEntity) entity);
        }
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        Entity entity = this.getOwner();
        return new EntitySpawnS2CPacket(this, entity == null ? 0 : entity.getId());
    }
}
