package net.enderman999517.funnymodfortesting.entity.effect;

import net.enderman999517.funnymodfortesting.item.DebugCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.ArrayList;
import java.util.List;

public class FlashbangStatusEffect extends StatusEffect {
    private int debugMode;
    private final List<DebugCallback> callbacks = new ArrayList<>();

    protected FlashbangStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
    }
}
