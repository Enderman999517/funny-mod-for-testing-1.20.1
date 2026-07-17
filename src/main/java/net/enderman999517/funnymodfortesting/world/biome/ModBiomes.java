package net.enderman999517.funnymodfortesting.world.biome;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

public class ModBiomes {
    public static final RegistryKey<Biome> OCEAN_DIM_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(FunnyModForTesting.MOD_ID, "ocean_dim_biome"));

    public static float waterStart = 20;
    public static float waterEnd = 30;

    public static void bootstrap(Registerable<Biome> context) {
        context.register(OCEAN_DIM_BIOME, oceanDimBiome(context));
    }

    public static Biome oceanDimBiome(Registerable<Biome> context) {

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        return new Biome.Builder()
                .precipitation(false)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x000000)
                        .waterFogColor(0xffffff)
                        .skyColor(0x30c918)
                        .grassColor(0x7f03fc)
                        .foliageColor(0xd203fc)
                        .fogColor(0x000000)
                        .build())
                .build();
    }
}
