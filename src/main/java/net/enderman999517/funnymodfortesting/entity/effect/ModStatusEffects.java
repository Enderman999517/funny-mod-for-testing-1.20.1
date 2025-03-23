package net.enderman999517.funnymodfortesting.entity.effect;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {

    public static final StatusEffect EXP = registerStatusEffect("exp",
            new ExpStatusEffect(StatusEffectCategory.BENEFICIAL, 0xB00B69));

    private static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(FunnyModForTesting.MOD_ID, name), statusEffect);
    }

    public static final FlashbangStatusEffect FLASHBANG = new FlashbangStatusEffect(StatusEffectCategory.HARMFUL, 0xFFFFFF);

    public static void registerModStatusEffects() {
        FunnyModForTesting.LOGGER.info("Registering Mod Status Effects for " + FunnyModForTesting.MOD_ID);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(FunnyModForTesting.MOD_ID, "flashbang"), FLASHBANG);
    }
}
