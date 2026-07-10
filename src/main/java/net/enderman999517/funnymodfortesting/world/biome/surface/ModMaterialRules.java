package net.enderman999517.funnymodfortesting.world.biome.surface;

import net.enderman999517.funnymodfortesting.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

//public class ModMaterialRules {
//    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
//    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
//    private static final MaterialRules.MaterialRule WATER = makeStateRule(Blocks.WATER);
//
//    public static MaterialRules.MaterialRule makeRules() {
//        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);
//
//        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, WATER), WATER);
//
//        return MaterialRules.sequence(
//                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.OCEAN_DIM_BIOME),
//                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, WATER)),
//                        MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, WATER)),
//
//                // Default to a grass and dirt surface
//                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)
//        );
//    }
//
//    private static MaterialRules.MaterialRule makeStateRule(Block block) {
//        return MaterialRules.block(block.getDefaultState());
//    }
//
//}
