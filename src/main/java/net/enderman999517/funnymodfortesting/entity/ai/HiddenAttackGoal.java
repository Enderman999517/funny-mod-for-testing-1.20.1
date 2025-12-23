package net.enderman999517.funnymodfortesting.entity.ai;

import net.enderman999517.funnymodfortesting.entity.custom.HiddenEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;

public class HiddenAttackGoal extends MeleeAttackGoal {
    private final HiddenEntity entity;

    public HiddenAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        this.entity = ((HiddenEntity) mob);
    }


    //@Override
    //protected void attack(LivingEntity target, double squaredDistance) {
    //    //if (isEnemyWithinAttackDistance(target)) {
    //    //    performAttack(target);
    //    //}
    //    super.attack(isEnemyWithinAttackDistance(target), );
    //}
//
    //private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy) {
    //    return this.entity.distanceTo(pEnemy) <= 5f;
    //}

    //protected void performAttack(LivingEntity target) {
    //    target.;
    //}
}
