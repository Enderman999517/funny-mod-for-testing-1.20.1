package net.enderman999517.funnymodfortesting.entity.ai;

import net.enderman999517.funnymodfortesting.entity.custom.AmoghEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;

public class AmoghAttackGoal extends MeleeAttackGoal {
    private final AmoghEntity entity;

    public AmoghAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        entity = ((AmoghEntity) mob);
    }

    @Override
    protected void attack(LivingEntity target, double squaredDistance) {
        if (isEnemyWithinAttackDistance(target)) {
                performAttack(target);
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy) {
        return this.entity.distanceTo(pEnemy) <= 5f;
    }

    protected void performAttack(LivingEntity target) {
    this.entity.ignite();
    }
}
