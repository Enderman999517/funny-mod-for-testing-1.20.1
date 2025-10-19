package net.enderman999517.funnymodfortesting.entity.ai;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;

public class CancelTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {

    public CancelTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility) {
        super(mob, targetClass, checkVisibility);
    }

    @Override
    public boolean canStart() {
        if (super.canStart()) {
            T target = (T) this.target;
            if (target instanceof ModEntityData modEntityData) {
                if (modEntityData.isHidden()) {
                    return false;
                }
            } return true;
        }
        return false;
    }
}
