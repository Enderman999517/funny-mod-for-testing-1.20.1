package net.enderman999517.funnymodfortesting.entity.effect;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {

    public static void registerModStatusEffects() {
        FunnyModForTesting.LOGGER.info("Registering Mod Status Effects for " + FunnyModForTesting.MOD_ID);

        Registry.register(Registries.STATUS_EFFECT,
                new Identifier(FunnyModForTesting.MOD_ID, "exp"), new ExpStatusEffect(StatusEffectCategory.BENEFICIAL, 0xB00B69));
        Registry.register(Registries.STATUS_EFFECT,
                new Identifier(FunnyModForTesting.MOD_ID, "flashbang"), new FlashbangStatusEffect(StatusEffectCategory.HARMFUL, 0xFFFFFF));

    }
}
