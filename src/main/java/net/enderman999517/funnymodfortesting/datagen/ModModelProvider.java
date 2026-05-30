package net.enderman999517.funnymodfortesting.datagen;

import net.enderman999517.funnymodfortesting.block.ModBlocks;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool whiteWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.WHITE_WOOL);
        BlockStateModelGenerator.BlockTexturePool lightGrayWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.LIGHT_GRAY_WOOL);
        BlockStateModelGenerator.BlockTexturePool grayWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.GRAY_WOOL);
        BlockStateModelGenerator.BlockTexturePool blackWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BLACK_WOOL);
        BlockStateModelGenerator.BlockTexturePool brownWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BROWN_WOOL);
        BlockStateModelGenerator.BlockTexturePool redWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.RED_WOOL);
        BlockStateModelGenerator.BlockTexturePool orangeWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.ORANGE_WOOL);
        BlockStateModelGenerator.BlockTexturePool yellowWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.YELLOW_WOOL);
        BlockStateModelGenerator.BlockTexturePool limeWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.LIME_WOOL);
        BlockStateModelGenerator.BlockTexturePool greenWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.GREEN_WOOL);
        BlockStateModelGenerator.BlockTexturePool cyanWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CYAN_WOOL);
        BlockStateModelGenerator.BlockTexturePool lightBlueWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.LIGHT_BLUE_WOOL);
        BlockStateModelGenerator.BlockTexturePool blueWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BLUE_WOOL);
        BlockStateModelGenerator.BlockTexturePool purpleWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PURPLE_WOOL);
        BlockStateModelGenerator.BlockTexturePool magentaWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.MAGENTA_WOOL);
        BlockStateModelGenerator.BlockTexturePool pinkWoolPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PINK_WOOL);

        //RUN DATAGEN

        //stairs etc
        whiteWoolPool.stairs(ModBlocks.WHITE_WOOL_STAIRS);
        lightGrayWoolPool.stairs(ModBlocks.LIGHT_GRAY_WOOL_STAIRS);
        grayWoolPool.stairs(ModBlocks.GRAY_WOOL_STAIRS);
        blackWoolPool.stairs(ModBlocks.BLACK_WOOL_STAIRS);
        brownWoolPool.stairs(ModBlocks.BROWN_WOOL_STAIRS);
        redWoolPool.stairs(ModBlocks.RED_WOOL_STAIRS);
        orangeWoolPool.stairs(ModBlocks.ORANGE_WOOL_STAIRS);
        yellowWoolPool.stairs(ModBlocks.YELLOW_WOOL_STAIRS);
        limeWoolPool.stairs(ModBlocks.LIME_WOOL_STAIRS);
        greenWoolPool.stairs(ModBlocks.GREEN_WOOL_STAIRS);
        cyanWoolPool.stairs(ModBlocks.CYAN_WOOL_STAIRS);
        lightBlueWoolPool.stairs(ModBlocks.LIGHT_BLUE_WOOL_STAIRS);
        blueWoolPool.stairs(ModBlocks.BLUE_WOOL_STAIRS);
        purpleWoolPool.stairs(ModBlocks.PURPLE_WOOL_STAIRS);
        magentaWoolPool.stairs(ModBlocks.MAGENTA_WOOL_STAIRS);
        pinkWoolPool.stairs(ModBlocks.PINK_WOOL_STAIRS);

        whiteWoolPool.slab(ModBlocks.WHITE_WOOL_SLAB);
        lightGrayWoolPool.slab(ModBlocks.LIGHT_GRAY_WOOL_SLAB);
        grayWoolPool.slab(ModBlocks.GRAY_WOOL_SLAB);
        blackWoolPool.slab(ModBlocks.BLACK_WOOL_SLAB);
        brownWoolPool.slab(ModBlocks.BROWN_WOOL_SLAB);
        redWoolPool.slab(ModBlocks.RED_WOOL_SLAB);
        orangeWoolPool.slab(ModBlocks.ORANGE_WOOL_SLAB);
        yellowWoolPool.slab(ModBlocks.YELLOW_WOOL_SLAB);
        limeWoolPool.slab(ModBlocks.LIME_WOOL_SLAB);
        greenWoolPool.slab(ModBlocks.GREEN_WOOL_SLAB);
        cyanWoolPool.slab(ModBlocks.CYAN_WOOL_SLAB);
        lightBlueWoolPool.slab(ModBlocks.LIGHT_BLUE_WOOL_SLAB);
        blueWoolPool.slab(ModBlocks.BLUE_WOOL_SLAB);
        purpleWoolPool.slab(ModBlocks.PURPLE_WOOL_SLAB);
        magentaWoolPool.slab(ModBlocks.MAGENTA_WOOL_SLAB);
        pinkWoolPool.slab(ModBlocks.PINK_WOOL_SLAB);

        whiteWoolPool.fence(ModBlocks.WHITE_WOOL_FENCE);
        lightGrayWoolPool.fence(ModBlocks.LIGHT_GRAY_WOOL_FENCE);
        grayWoolPool.fence(ModBlocks.GRAY_WOOL_FENCE);
        blackWoolPool.fence(ModBlocks.BLACK_WOOL_FENCE);
        brownWoolPool.fence(ModBlocks.BROWN_WOOL_FENCE);
        redWoolPool.fence(ModBlocks.RED_WOOL_FENCE);
        orangeWoolPool.fence(ModBlocks.ORANGE_WOOL_FENCE);
        yellowWoolPool.fence(ModBlocks.YELLOW_WOOL_FENCE);
        limeWoolPool.fence(ModBlocks.LIME_WOOL_FENCE);
        greenWoolPool.fence(ModBlocks.GREEN_WOOL_FENCE);
        cyanWoolPool.fence(ModBlocks.CYAN_WOOL_FENCE);
        lightBlueWoolPool.fence(ModBlocks.LIGHT_BLUE_WOOL_FENCE);
        blueWoolPool.fence(ModBlocks.BLUE_WOOL_FENCE);
        purpleWoolPool.fence(ModBlocks.PURPLE_WOOL_FENCE);
        magentaWoolPool.fence(ModBlocks.MAGENTA_WOOL_FENCE);
        pinkWoolPool.fence(ModBlocks.PINK_WOOL_FENCE);

        whiteWoolPool.fenceGate(ModBlocks.WHITE_WOOL_FENCE_GATE);
        lightGrayWoolPool.fenceGate(ModBlocks.LIGHT_GRAY_WOOL_FENCE_GATE);
        grayWoolPool.fenceGate(ModBlocks.GRAY_WOOL_FENCE_GATE);
        blackWoolPool.fenceGate(ModBlocks.BLACK_WOOL_FENCE_GATE);
        brownWoolPool.fenceGate(ModBlocks.BROWN_WOOL_FENCE_GATE);
        redWoolPool.fenceGate(ModBlocks.RED_WOOL_FENCE_GATE);
        orangeWoolPool.fenceGate(ModBlocks.ORANGE_WOOL_FENCE_GATE);
        yellowWoolPool.fenceGate(ModBlocks.YELLOW_WOOL_FENCE_GATE);
        limeWoolPool.fenceGate(ModBlocks.LIME_WOOL_FENCE_GATE);
        greenWoolPool.fenceGate(ModBlocks.GREEN_WOOL_FENCE_GATE);
        cyanWoolPool.fenceGate(ModBlocks.CYAN_WOOL_FENCE_GATE);
        lightBlueWoolPool.fenceGate(ModBlocks.LIGHT_BLUE_WOOL_FENCE_GATE);
        blueWoolPool.fenceGate(ModBlocks.BLUE_WOOL_FENCE_GATE);
        purpleWoolPool.fenceGate(ModBlocks.PURPLE_WOOL_FENCE_GATE);
        magentaWoolPool.fenceGate(ModBlocks.MAGENTA_WOOL_FENCE_GATE);
        pinkWoolPool.fenceGate(ModBlocks.PINK_WOOL_FENCE_GATE);

        blockStateModelGenerator.registerDoor(ModBlocks.WHITE_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.LIGHT_GRAY_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.GRAY_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.BLACK_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.BROWN_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.RED_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.ORANGE_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.YELLOW_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.LIME_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.GREEN_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.CYAN_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.LIGHT_BLUE_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.BLUE_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.PURPLE_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.MAGENTA_WOOL_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.PINK_WOOL_DOOR);

        blockStateModelGenerator.registerTrapdoor(ModBlocks.WHITE_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.LIGHT_GRAY_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.GRAY_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.BLACK_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.BROWN_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.RED_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.ORANGE_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.YELLOW_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.LIME_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.GREEN_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.CYAN_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.LIGHT_BLUE_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.BLUE_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.PURPLE_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.MAGENTA_WOOL_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.PINK_WOOL_TRAPDOOR);

        whiteWoolPool.button(ModBlocks.WHITE_WOOL_BUTTON);
        lightGrayWoolPool.button(ModBlocks.LIGHT_GRAY_WOOL_BUTTON);
        grayWoolPool.button(ModBlocks.GRAY_WOOL_BUTTON);
        blackWoolPool.button(ModBlocks.BLACK_WOOL_BUTTON);
        brownWoolPool.button(ModBlocks.BROWN_WOOL_BUTTON);
        redWoolPool.button(ModBlocks.RED_WOOL_BUTTON);
        orangeWoolPool.button(ModBlocks.ORANGE_WOOL_BUTTON);
        yellowWoolPool.button(ModBlocks.YELLOW_WOOL_BUTTON);
        limeWoolPool.button(ModBlocks.LIME_WOOL_BUTTON);
        greenWoolPool.button(ModBlocks.GREEN_WOOL_BUTTON);
        cyanWoolPool.button(ModBlocks.CYAN_WOOL_BUTTON);
        lightBlueWoolPool.button(ModBlocks.LIGHT_BLUE_WOOL_BUTTON);
        blueWoolPool.button(ModBlocks.BLUE_WOOL_BUTTON);
        purpleWoolPool.button(ModBlocks.PURPLE_WOOL_BUTTON);
        magentaWoolPool.button(ModBlocks.MAGENTA_WOOL_BUTTON);
        pinkWoolPool.button(ModBlocks.PINK_WOOL_BUTTON);

        whiteWoolPool.pressurePlate(ModBlocks.WHITE_WOOL_PRESSURE_PLATE);
        lightGrayWoolPool.pressurePlate(ModBlocks.LIGHT_GRAY_WOOL_PRESSURE_PLATE);
        grayWoolPool.pressurePlate(ModBlocks.GRAY_WOOL_PRESSURE_PLATE);
        blackWoolPool.pressurePlate(ModBlocks.BLACK_WOOL_PRESSURE_PLATE);
        brownWoolPool.pressurePlate(ModBlocks.BROWN_WOOL_PRESSURE_PLATE);
        redWoolPool.pressurePlate(ModBlocks.RED_WOOL_PRESSURE_PLATE);
        orangeWoolPool.pressurePlate(ModBlocks.ORANGE_WOOL_PRESSURE_PLATE);
        yellowWoolPool.pressurePlate(ModBlocks.YELLOW_WOOL_PRESSURE_PLATE);
        limeWoolPool.pressurePlate(ModBlocks.LIME_WOOL_PRESSURE_PLATE);
        greenWoolPool.pressurePlate(ModBlocks.GREEN_WOOL_PRESSURE_PLATE);
        cyanWoolPool.pressurePlate(ModBlocks.CYAN_WOOL_PRESSURE_PLATE);
        lightBlueWoolPool.pressurePlate(ModBlocks.LIGHT_BLUE_WOOL_PRESSURE_PLATE);
        blueWoolPool.pressurePlate(ModBlocks.BLUE_WOOL_PRESSURE_PLATE);
        purpleWoolPool.pressurePlate(ModBlocks.PURPLE_WOOL_PRESSURE_PLATE);
        magentaWoolPool.pressurePlate(ModBlocks.MAGENTA_WOOL_PRESSURE_PLATE);
        pinkWoolPool.pressurePlate(ModBlocks.PINK_WOOL_PRESSURE_PLATE);






        //random
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LTF_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SHOWCASE_BLOCK_W);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SHOWCASE_BLOCK_B);

        //block entities
        blockStateModelGenerator.registerSimpleState(ModBlocks.BRAINROTIFIER);
        blockStateModelGenerator.registerSimpleState(ModBlocks.COMPACTOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //RUN DATAGEN
        //custom
        itemModelGenerator.register(ModItems.OVERPOWERED, Models.GENERATED);
        itemModelGenerator.register(ModItems.TORCH_GUN, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMOGH, Models.GENERATED);
        itemModelGenerator.register(ModItems.FLASHBANG, Models.GENERATED);
        itemModelGenerator.register(ModItems.STIM, Models.GENERATED);
        itemModelGenerator.register(ModItems.SYRINGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RING, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPEAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.IMPERSONATE_SWORD, Models.GENERATED);

        //items
        itemModelGenerator.register(ModItems.AMOGH_ESSENCE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HAPPY_MELON_SLICE, Models.GENERATED);

        ////armor
        //itemModelGenerator.registerArmor(((ArmorItem) ModItems.LTF_HAIR));

        //spawn eggs
        itemModelGenerator.register(ModItems.AMOGH_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
    }

}
