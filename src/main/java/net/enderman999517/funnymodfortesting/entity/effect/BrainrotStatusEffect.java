package net.enderman999517.funnymodfortesting.entity.effect;

import net.enderman999517.funnymodfortesting.sound.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class BrainrotStatusEffect extends StatusEffect {
    protected BrainrotStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            ((PlayerEntity) entity).playSound(ModSounds.LTF_BLOCK_PLACE, 1f, 1f);
        }
    }
}
