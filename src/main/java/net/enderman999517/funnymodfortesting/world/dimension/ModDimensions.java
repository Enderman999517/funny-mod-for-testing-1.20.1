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

import java.util.OptionalLong;

public class ModDimensions {

    public static final RegistryKey<DimensionOptions> OCEANDIM_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(FunnyModForTesting.MOD_ID, "oceandim"));
    public static final RegistryKey<World> OCEANDIM_LEVEL = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(FunnyModForTesting.MOD_ID, "oceandim"));
    public static final RegistryKey<DimensionType> OCEANDIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(FunnyModForTesting.MOD_ID, "oceandim_type"));


    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(ModDimensions.OCEANDIM_TYPE,
                new DimensionType(
                        OptionalLong.empty(),
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
}
