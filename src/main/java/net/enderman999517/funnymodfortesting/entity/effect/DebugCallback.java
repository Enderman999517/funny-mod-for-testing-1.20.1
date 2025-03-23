package net.enderman999517.funnymodfortesting.entity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;

@FunctionalInterface
public interface DebugCallback {
    void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier);
}