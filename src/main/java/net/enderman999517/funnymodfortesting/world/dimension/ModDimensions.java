package net.enderman999517.funnymodfortesting.world.dimension;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.densityfunction.DensityFunction;

import java.util.OptionalLong;

public class ModDimensions {

    public static final RegistryKey<DimensionOptions> OCEANDIM_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(FunnyModForTesting.MOD_ID, "oceandim"));
    public static final RegistryKey<World> OCEANDIM_LEVEL = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(FunnyModForTesting.MOD_ID, "oceandim"));
    public static final RegistryKey<DimensionType> OCEANDIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(FunnyModForTesting.MOD_ID, "oceandim_type"));
    public static final RegistryKey<DensityFunction> OCEANDIM_DENSITY_FUNCTION = RegistryKey.of(RegistryKeys.DENSITY_FUNCTION,
            Identifier.of(FunnyModForTesting.MOD_ID, "oceandim_density_function"));
    public static final RegistryKey<ChunkGeneratorSettings> OCEANDIM_CHUNK_GENERATOR_SETTINGS = RegistryKey.of(RegistryKeys.CHUNK_GENERATOR_SETTINGS,
            Identifier.of(FunnyModForTesting.MOD_ID, "oceandim_chunk_generator_settings"));


    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(ModDimensions.OCEANDIM_TYPE,
                new DimensionType(
                        OptionalLong.of(18000),
                        true,
                        false,
                        false,
                        false,
                        1.0,
                        false,
                        false,
                        0,
                        320,
                        320,
                        BlockTags.INFINIBURN_OVERWORLD,
                        DimensionTypes.OVERWORLD_ID,
                        0.0F,
                        new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)
                )
        );
    }


    //public static void bootstrapChunkGeneratorSettings(Registerable<ChunkGeneratorSettings> context) {
    //    GenerationShapeConfig generationShapeConfig = GenerationShapeConfig.create(0, 320, 1, 1);
    //    NoiseRouter noiseRouter = ModNoiseRouters.testNoiseRouter();
//
    //    context.register(ModDimensions.OCEANDIM_CHUNK_GENERATOR_SETTINGS,
    //            new ChunkGeneratorSettings(
    //                    generationShapeConfig, Blocks.DIAMOND_BLOCK.getDefaultState(),Blocks.WATER.getDefaultState(), noiseRouter,
    //                    ModMaterialRules.makeRules(), new ArrayList<>(),128,true,false,false,false
    //            )
    //    );
    //}
}
