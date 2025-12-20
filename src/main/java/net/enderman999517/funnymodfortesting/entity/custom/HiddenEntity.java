package net.enderman999517.funnymodfortesting.entity.custom;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.networking.ModSync;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class HiddenEntity extends HostileEntity {
    private static final TrackedData<Boolean> HIDDEN = DataTracker.registerData(HiddenEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public HiddenEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createHiddenAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1);
    }

    @Override
    public void tick() {
        if (this instanceof ModEntityData modEntityData) {
            FunnyModForTesting.LOGGER.error("hidden: {}", modEntityData.isHidden());
            if (!modEntityData.isHidden()) {
                modEntityData.setHidden(true);
                FunnyModForTesting.LOGGER.error("asjkg");
            }
            ModSync.syncHiddenFlag(this, modEntityData.isHidden());
        }
        super.tick();
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(HIDDEN, true);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.dataTracker.get(HIDDEN)) {
            nbt.putBoolean("hidden", true);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.dataTracker.set(HIDDEN, nbt.getBoolean("hidden"));
    }
}
