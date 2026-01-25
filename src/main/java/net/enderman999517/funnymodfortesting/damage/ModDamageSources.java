package net.enderman999517.funnymodfortesting.damage;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModDamageSources {
    public static final RegistryKey<DamageType> SCYTHE_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(FunnyModForTesting.MOD_ID, "scythe"));
    public static final RegistryKey<DamageType> STIM_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(FunnyModForTesting.MOD_ID, "stim"));
    public static final RegistryKey<DamageType> IMPERSONATE_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(FunnyModForTesting.MOD_ID, "impersonate"));

    public static void registerDamageSources() {
        FunnyModForTesting.LOGGER.info("Registering Damage Sources for " + FunnyModForTesting.MOD_ID);
    }
}
