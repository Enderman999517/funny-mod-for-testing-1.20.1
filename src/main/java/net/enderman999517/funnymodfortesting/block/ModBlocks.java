package net.enderman999517.funnymodfortesting.block;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.block.custom.*;
import net.enderman999517.funnymodfortesting.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
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

    public static final Block FAKE_WHITE_WOOL = registerBlock("fake_white_wool",
            new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)));

    //custom
    public static final Block LTF_BLOCK = registerBlock("ltf_block",
            new LtfBlock(FabricBlockSettings.copyOf(Blocks.STONE).sounds(ModSounds.LTF_BLOCK_SOUNDS)));

    public static final Block DEBUG_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.STONE));

    public static final Block HAPPY_MELON_BLOCK = registerBlock("happy_melon_block",
            new HappyMelonBlock(FabricBlockSettings.copyOf(Blocks.MELON)));

    //block entities
    public static final Block BRAINROTIFIER = registerBlock("brainrotifier",
            new BrainrotifierBlock(FabricBlockSettings.copyOf(Blocks.SLIME_BLOCK).nonOpaque()));
    public static final Block COMPACTOR = registerBlock("compactor",
            new CompactorBlock(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).nonOpaque()));
    public static final Block GONG = registerBlock("gong",
            new GongBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    //stairs + slabs
    public static final Block WHITE_WOOL_STAIRS = registerBlock("white_wool_stairs",
            new StairsBlock(Blocks.WHITE_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)));
    public static final Block LIGHT_GRAY_WOOL_STAIRS = registerBlock("light_gray_wool_stairs",
            new StairsBlock(Blocks.LIGHT_GRAY_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_WOOL)));
    public static final Block GRAY_WOOL_STAIRS = registerBlock("gray_wool_stairs",
            new StairsBlock(Blocks.GRAY_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.GRAY_WOOL)));
    public static final Block BLACK_WOOL_STAIRS = registerBlock("black_wool_stairs",
            new StairsBlock(Blocks.BLACK_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)));
    public static final Block BROWN_WOOL_STAIRS = registerBlock("brown_wool_stairs",
            new StairsBlock(Blocks.BROWN_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BROWN_WOOL)));
    public static final Block RED_WOOL_STAIRS = registerBlock("red_wool_stairs",
            new StairsBlock(Blocks.RED_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.RED_WOOL)));
    public static final Block ORANGE_WOOL_STAIRS = registerBlock("orange_wool_stairs",
            new StairsBlock(Blocks.ORANGE_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.ORANGE_WOOL)));
    public static final Block YELLOW_WOOL_STAIRS = registerBlock("yellow_wool_stairs",
            new StairsBlock(Blocks.YELLOW_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.YELLOW_WOOL)));
    public static final Block LIME_WOOL_STAIRS = registerBlock("lime_wool_stairs",
            new StairsBlock(Blocks.LIME_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.LIME_WOOL)));
    public static final Block GREEN_WOOL_STAIRS = registerBlock("green_wool_stairs",
            new StairsBlock(Blocks.GREEN_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.GREEN_WOOL)));
    public static final Block CYAN_WOOL_STAIRS = registerBlock("cyan_wool_stairs",
            new StairsBlock(Blocks.CYAN_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.CYAN_WOOL)));
    public static final Block LIGHT_BLUE_WOOL_STAIRS = registerBlock("light_blue_wool_stairs",
            new StairsBlock(Blocks.LIGHT_BLUE_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_WOOL)));
    public static final Block BLUE_WOOL_STAIRS = registerBlock("blue_wool_stairs",
            new StairsBlock(Blocks.BLUE_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BLUE_WOOL)));
    public static final Block PURPLE_WOOL_STAIRS = registerBlock("purple_wool_stairs",
            new StairsBlock(Blocks.PURPLE_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.PURPLE_WOOL)));
    public static final Block MAGENTA_WOOL_STAIRS = registerBlock("magenta_wool_stairs",
            new StairsBlock(Blocks.MAGENTA_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.MAGENTA_WOOL)));
    public static final Block PINK_WOOL_STAIRS = registerBlock("pink_wool_stairs",
            new StairsBlock(Blocks.PINK_WOOL.getDefaultState(), FabricBlockSettings.copyOf(Blocks.PINK_WOOL)));


    public static final Block WHITE_WOOL_SLAB = registerBlock("white_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)));
    public static final Block LIGHT_GRAY_WOOL_SLAB = registerBlock("light_gray_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_WOOL)));
    public static final Block GRAY_WOOL_SLAB = registerBlock("gray_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.GRAY_WOOL)));
    public static final Block BLACK_WOOL_SLAB = registerBlock("black_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)));
    public static final Block BROWN_WOOL_SLAB = registerBlock("brown_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.BROWN_WOOL)));
    public static final Block RED_WOOL_SLAB = registerBlock("red_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.RED_WOOL)));
    public static final Block ORANGE_WOOL_SLAB = registerBlock("orange_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_WOOL)));
    public static final Block YELLOW_WOOL_SLAB = registerBlock("yellow_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_WOOL)));
    public static final Block LIME_WOOL_SLAB = registerBlock("lime_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIME_WOOL)));
    public static final Block GREEN_WOOL_SLAB = registerBlock("green_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.GREEN_WOOL)));
    public static final Block CYAN_WOOL_SLAB = registerBlock("cyan_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.CYAN_WOOL)));
    public static final Block LIGHT_BLUE_WOOL_SLAB = registerBlock("light_blue_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_WOOL)));
    public static final Block BLUE_WOOL_SLAB = registerBlock("blue_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLUE_WOOL)));
    public static final Block PURPLE_WOOL_SLAB = registerBlock("purple_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_WOOL)));
    public static final Block MAGENTA_WOOL_SLAB = registerBlock("magenta_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_WOOL)));
    public static final Block PINK_WOOL_SLAB = registerBlock("pink_wool_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.PINK_WOOL)));


    public static final Block WHITE_WOOL_FENCE = registerBlock("white_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)));
    public static final Block LIGHT_GRAY_WOOL_FENCE = registerBlock("light_gray_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_WOOL)));
    public static final Block GRAY_WOOL_FENCE = registerBlock("gray_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.GRAY_WOOL)));
    public static final Block BLACK_WOOL_FENCE = registerBlock("black_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)));
    public static final Block BROWN_WOOL_FENCE = registerBlock("brown_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.BROWN_WOOL)));
    public static final Block RED_WOOL_FENCE = registerBlock("red_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.RED_WOOL)));
    public static final Block ORANGE_WOOL_FENCE = registerBlock("orange_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_WOOL)));
    public static final Block YELLOW_WOOL_FENCE = registerBlock("yellow_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_WOOL)));
    public static final Block LIME_WOOL_FENCE = registerBlock("lime_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.LIME_WOOL)));
    public static final Block GREEN_WOOL_FENCE = registerBlock("green_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.GREEN_WOOL)));
    public static final Block CYAN_WOOL_FENCE = registerBlock("cyan_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.CYAN_WOOL)));
    public static final Block LIGHT_BLUE_WOOL_FENCE = registerBlock("light_blue_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_WOOL)));
    public static final Block BLUE_WOOL_FENCE = registerBlock("blue_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.BLUE_WOOL)));
    public static final Block PURPLE_WOOL_FENCE = registerBlock("purple_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_WOOL)));
    public static final Block MAGENTA_WOOL_FENCE = registerBlock("magenta_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_WOOL)));
    public static final Block PINK_WOOL_FENCE = registerBlock("pink_wool_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.PINK_WOOL)));


    public static final Block WHITE_WOOL_FENCE_GATE = registerBlock("white_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL), WoodType.OAK));
    public static final Block LIGHT_GRAY_WOOL_FENCE_GATE = registerBlock("light_gray_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_WOOL), WoodType.OAK));
    public static final Block GRAY_WOOL_FENCE_GATE = registerBlock("gray_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.GRAY_WOOL), WoodType.OAK));
    public static final Block BLACK_WOOL_FENCE_GATE = registerBlock("black_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL), WoodType.OAK));
    public static final Block BROWN_WOOL_FENCE_GATE = registerBlock("brown_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.BROWN_WOOL), WoodType.OAK));
    public static final Block RED_WOOL_FENCE_GATE = registerBlock("red_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.RED_WOOL), WoodType.OAK));
    public static final Block ORANGE_WOOL_FENCE_GATE = registerBlock("orange_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_WOOL), WoodType.OAK));
    public static final Block YELLOW_WOOL_FENCE_GATE = registerBlock("yellow_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_WOOL), WoodType.OAK));
    public static final Block LIME_WOOL_FENCE_GATE = registerBlock("lime_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.LIME_WOOL), WoodType.OAK));
    public static final Block GREEN_WOOL_FENCE_GATE = registerBlock("green_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.GREEN_WOOL), WoodType.OAK));
    public static final Block CYAN_WOOL_FENCE_GATE = registerBlock("cyan_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.CYAN_WOOL), WoodType.OAK));
    public static final Block LIGHT_BLUE_WOOL_FENCE_GATE = registerBlock("light_blue_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_WOOL), WoodType.OAK));
    public static final Block BLUE_WOOL_FENCE_GATE = registerBlock("blue_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.BLUE_WOOL), WoodType.OAK));
    public static final Block PURPLE_WOOL_FENCE_GATE = registerBlock("purple_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_WOOL), WoodType.OAK));
    public static final Block MAGENTA_WOOL_FENCE_GATE = registerBlock("magenta_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_WOOL), WoodType.OAK));
    public static final Block PINK_WOOL_FENCE_GATE = registerBlock("pink_wool_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.PINK_WOOL), WoodType.OAK));


    public static final Block WHITE_WOOL_DOOR = registerBlock("white_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block LIGHT_GRAY_WOOL_DOOR = registerBlock("light_gray_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block GRAY_WOOL_DOOR = registerBlock("gray_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.GRAY_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block BLACK_WOOL_DOOR = registerBlock("black_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block BROWN_WOOL_DOOR = registerBlock("brown_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.BROWN_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block RED_WOOL_DOOR = registerBlock("red_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.RED_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block ORANGE_WOOL_DOOR = registerBlock("orange_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block YELLOW_WOOL_DOOR = registerBlock("yellow_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block LIME_WOOL_DOOR = registerBlock("lime_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.LIME_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block GREEN_WOOL_DOOR = registerBlock("green_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.GREEN_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block CYAN_WOOL_DOOR = registerBlock("cyan_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.CYAN_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block LIGHT_BLUE_WOOL_DOOR = registerBlock("light_blue_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block BLUE_WOOL_DOOR = registerBlock("blue_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.BLUE_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block PURPLE_WOOL_DOOR = registerBlock("purple_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block MAGENTA_WOOL_DOOR = registerBlock("magenta_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block PINK_WOOL_DOOR = registerBlock("pink_wool_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.PINK_WOOL).nonOpaque(), BlockSetType.OAK));


    public static final Block WHITE_WOOL_TRAPDOOR = registerBlock("white_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block LIGHT_GRAY_WOOL_TRAPDOOR = registerBlock("light_gray_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block GRAY_WOOL_TRAPDOOR = registerBlock("gray_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.GRAY_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block BLACK_WOOL_TRAPDOOR = registerBlock("black_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block BROWN_WOOL_TRAPDOOR = registerBlock("brown_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.BROWN_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block RED_WOOL_TRAPDOOR = registerBlock("red_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.RED_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block ORANGE_WOOL_TRAPDOOR = registerBlock("orange_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block YELLOW_WOOL_TRAPDOOR = registerBlock("yellow_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block LIME_WOOL_TRAPDOOR = registerBlock("lime_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.LIME_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block GREEN_WOOL_TRAPDOOR = registerBlock("green_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.GREEN_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block CYAN_WOOL_TRAPDOOR = registerBlock("cyan_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.CYAN_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block LIGHT_BLUE_WOOL_TRAPDOOR = registerBlock("light_blue_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block BLUE_WOOL_TRAPDOOR = registerBlock("blue_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.BLUE_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block PURPLE_WOOL_TRAPDOOR = registerBlock("purple_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block MAGENTA_WOOL_TRAPDOOR = registerBlock("magenta_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_WOOL).nonOpaque(), BlockSetType.OAK));
    public static final Block PINK_WOOL_TRAPDOOR = registerBlock("pink_wool_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.PINK_WOOL).nonOpaque(), BlockSetType.OAK));


    public static final Block WHITE_WOOL_BUTTON = registerBlock("white_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL), BlockSetType.OAK, 10, true));
    public static final Block LIGHT_GRAY_WOOL_BUTTON = registerBlock("light_gray_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_WOOL), BlockSetType.OAK, 10, true));
    public static final Block GRAY_WOOL_BUTTON = registerBlock("gray_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.GRAY_WOOL), BlockSetType.OAK, 10, true));
    public static final Block BLACK_WOOL_BUTTON = registerBlock("black_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL), BlockSetType.OAK, 10, true));
    public static final Block BROWN_WOOL_BUTTON = registerBlock("brown_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.BROWN_WOOL), BlockSetType.OAK, 10, true));
    public static final Block RED_WOOL_BUTTON = registerBlock("red_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.RED_WOOL), BlockSetType.OAK, 10, true));
    public static final Block ORANGE_WOOL_BUTTON = registerBlock("orange_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_WOOL), BlockSetType.OAK, 10, true));
    public static final Block YELLOW_WOOL_BUTTON = registerBlock("yellow_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_WOOL), BlockSetType.OAK, 10, true));
    public static final Block LIME_WOOL_BUTTON = registerBlock("lime_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.LIME_WOOL), BlockSetType.OAK, 10, true));
    public static final Block GREEN_WOOL_BUTTON = registerBlock("green_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.GREEN_WOOL), BlockSetType.OAK, 10, true));
    public static final Block CYAN_WOOL_BUTTON = registerBlock("cyan_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.CYAN_WOOL), BlockSetType.OAK, 10, true));
    public static final Block LIGHT_BLUE_WOOL_BUTTON = registerBlock("light_blue_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_WOOL), BlockSetType.OAK, 10, true));
    public static final Block BLUE_WOOL_BUTTON = registerBlock("blue_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.BLUE_WOOL), BlockSetType.OAK, 10, true));
    public static final Block PURPLE_WOOL_BUTTON = registerBlock("purple_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_WOOL), BlockSetType.OAK, 10, true));
    public static final Block MAGENTA_WOOL_BUTTON = registerBlock("magenta_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_WOOL), BlockSetType.OAK, 10, true));
    public static final Block PINK_WOOL_BUTTON = registerBlock("pink_wool_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.PINK_WOOL), BlockSetType.OAK, 10, true));



    public static final Block WHITE_WOOL_PRESSURE_PLATE = registerBlock("white_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.WHITE_WOOL), BlockSetType.OAK));
    public static final Block LIGHT_GRAY_WOOL_PRESSURE_PLATE = registerBlock("light_gray_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_WOOL), BlockSetType.OAK));
    public static final Block GRAY_WOOL_PRESSURE_PLATE = registerBlock("gray_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.GRAY_WOOL), BlockSetType.OAK));
    public static final Block BLACK_WOOL_PRESSURE_PLATE = registerBlock("black_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.BLACK_WOOL), BlockSetType.OAK));
    public static final Block BROWN_WOOL_PRESSURE_PLATE = registerBlock("brown_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.BROWN_WOOL), BlockSetType.OAK));
    public static final Block RED_WOOL_PRESSURE_PLATE = registerBlock("red_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.RED_WOOL), BlockSetType.OAK));
    public static final Block ORANGE_WOOL_PRESSURE_PLATE = registerBlock("orange_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.ORANGE_WOOL), BlockSetType.OAK));
    public static final Block YELLOW_WOOL_PRESSURE_PLATE = registerBlock("yellow_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.YELLOW_WOOL), BlockSetType.OAK));
    public static final Block LIME_WOOL_PRESSURE_PLATE = registerBlock("lime_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.LIME_WOOL), BlockSetType.OAK));
    public static final Block GREEN_WOOL_PRESSURE_PLATE = registerBlock("green_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.GREEN_WOOL), BlockSetType.OAK));
    public static final Block CYAN_WOOL_PRESSURE_PLATE = registerBlock("cyan_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.CYAN_WOOL), BlockSetType.OAK));
    public static final Block LIGHT_BLUE_WOOL_PRESSURE_PLATE = registerBlock("light_blue_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_WOOL), BlockSetType.OAK));
    public static final Block BLUE_WOOL_PRESSURE_PLATE = registerBlock("blue_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.BLUE_WOOL), BlockSetType.OAK));
    public static final Block PURPLE_WOOL_PRESSURE_PLATE = registerBlock("purple_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.PURPLE_WOOL), BlockSetType.OAK));
    public static final Block MAGENTA_WOOL_PRESSURE_PLATE = registerBlock("magenta_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.MAGENTA_WOOL), BlockSetType.OAK));
    public static final Block PINK_WOOL_PRESSURE_PLATE = registerBlock("pink_wool_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.PINK_WOOL), BlockSetType.OAK));






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
