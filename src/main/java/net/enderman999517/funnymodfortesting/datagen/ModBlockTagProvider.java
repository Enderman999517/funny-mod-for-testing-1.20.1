package net.enderman999517.funnymodfortesting.datagen;

import net.enderman999517.funnymodfortesting.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        //RUN DATAGEN OR ELSE

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.WHITE_WOOL_FENCE)
                .add(ModBlocks.LIGHT_GRAY_WOOL_FENCE)
                .add(ModBlocks.GRAY_WOOL_FENCE)
                .add(ModBlocks.BLACK_WOOL_FENCE)
                .add(ModBlocks.BROWN_WOOL_FENCE)
                .add(ModBlocks.RED_WOOL_FENCE)
                .add(ModBlocks.ORANGE_WOOL_FENCE)
                .add(ModBlocks.YELLOW_WOOL_FENCE)
                .add(ModBlocks.LIME_WOOL_FENCE)
                .add(ModBlocks.GREEN_WOOL_FENCE)
                .add(ModBlocks.CYAN_WOOL_FENCE)
                .add(ModBlocks.LIGHT_BLUE_WOOL_FENCE)
                .add(ModBlocks.BLUE_WOOL_FENCE)
                .add(ModBlocks.PURPLE_WOOL_FENCE)
                .add(ModBlocks.MAGENTA_WOOL_FENCE)
                .add(ModBlocks.PINK_WOOL_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.WHITE_WOOL_FENCE_GATE)
                .add(ModBlocks.LIGHT_GRAY_WOOL_FENCE_GATE)
                .add(ModBlocks.GRAY_WOOL_FENCE_GATE)
                .add(ModBlocks.BLACK_WOOL_FENCE_GATE)
                .add(ModBlocks.BROWN_WOOL_FENCE_GATE)
                .add(ModBlocks.RED_WOOL_FENCE_GATE)
                .add(ModBlocks.ORANGE_WOOL_FENCE_GATE)
                .add(ModBlocks.YELLOW_WOOL_FENCE_GATE)
                .add(ModBlocks.LIME_WOOL_FENCE_GATE)
                .add(ModBlocks.GREEN_WOOL_FENCE_GATE)
                .add(ModBlocks.CYAN_WOOL_FENCE_GATE)
                .add(ModBlocks.LIGHT_BLUE_WOOL_FENCE_GATE)
                .add(ModBlocks.BLUE_WOOL_FENCE_GATE)
                .add(ModBlocks.PURPLE_WOOL_FENCE_GATE)
                .add(ModBlocks.MAGENTA_WOOL_FENCE_GATE)
                .add(ModBlocks.PINK_WOOL_FENCE_GATE);
    }
}
