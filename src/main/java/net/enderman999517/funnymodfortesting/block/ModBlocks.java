package net.enderman999517.funnymodfortesting.block;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.block.custom.BrainrotifierBlock;
import net.enderman999517.funnymodfortesting.block.custom.HappyMelonBlock;
import net.enderman999517.funnymodfortesting.block.custom.LtfBlock;
import net.enderman999517.funnymodfortesting.block.entity.BrainrotifierBlockEntity;
import net.enderman999517.funnymodfortesting.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block SHOWCASE_BLOCK_W = registerBlock("showcase_block_w",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block SHOWCASE_BLOCK_B = registerBlock("showcase_block_b",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));

    //custom
    public static final Block LTF_BLOCK = registerBlock("ltf_block",
            new LtfBlock(FabricBlockSettings.copyOf(Blocks.STONE).sounds(ModSounds.LTF_BLOCK_SOUNDS)));

    public static final Block DEBUG_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.STONE));

    //public static final Block HAPPY_MELON_BLOCK_1 = registerBlock("happy_melon_1",
    //        new HappyMelonBlock(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block HAPPY_MELON_BLOCK = Registry.register(Registries.BLOCK, new Identifier(FunnyModForTesting.MOD_ID, "happy_melon_block"),
            new HappyMelonBlock(FabricBlockSettings.copyOf(Blocks.STONE)));
    //public static final Block HAPPY_MELON_BLOCK_2 = registerBlock("happy_melon_2",
    //        new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    //public static final Block HAPPY_MELON_BLOCK_3 = registerBlock("happy_melon_3",
    //        new Block(FabricBlockSettings.copyOf(Blocks.STONE)));

    //block entities
    public static final Block BRAINROTIFIER = registerBlock("brainrotifier",
            new BrainrotifierBlock(FabricBlockSettings.copyOf(Blocks.SLIME_BLOCK).nonOpaque()));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(FunnyModForTesting.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(FunnyModForTesting.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Registry.register(Registries.BLOCK, new Identifier(FunnyModForTesting.MOD_ID, "debug_block"), DEBUG_BLOCK);
        FunnyModForTesting.LOGGER.info("Registering ModBlocks for " + FunnyModForTesting.MOD_ID);
    }
}
