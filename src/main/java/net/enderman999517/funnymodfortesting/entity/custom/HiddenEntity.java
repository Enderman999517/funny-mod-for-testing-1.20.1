package net.enderman999517.funnymodfortesting.entity.custom;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class HiddenEntity extends HostileEntity {
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
            if (!modEntityData.isHidden()) {
                modEntityData.setHidden(true);
                FunnyModForTesting.LOGGER.error("asjkg");
            }
        }
        super.tick();
    }
}
