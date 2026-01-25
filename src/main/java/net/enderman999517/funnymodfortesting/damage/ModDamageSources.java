package net.enderman999517.funnymodfortesting.damage;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModDamageSources {

    public static DamageSource of(World world, RegistryKey<DamageType> key, @Nullable Entity attacker) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key), attacker);
    }

    public static void registerDamageSources() {
        FunnyModForTesting.LOGGER.info("Registering Damage Sources for " + FunnyModForTesting.MOD_ID);
    }
}
