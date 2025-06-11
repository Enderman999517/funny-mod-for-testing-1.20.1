package net.enderman999517.funnymodfortesting.datagen;

import net.enderman999517.funnymodfortesting.block.ModBlocks;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
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

        //random
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LTF_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SHOWCASE_BLOCK_W);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SHOWCASE_BLOCK_B);

        //block entities
        blockStateModelGenerator.registerSimpleState(ModBlocks.BRAINROTIFIER);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //custom
        itemModelGenerator.register(ModItems.OVERPOWERED, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMOGH, Models.GENERATED);
        itemModelGenerator.register(ModItems.FLASHBANG, Models.GENERATED);

        //items
        itemModelGenerator.register(ModItems.AMOGH_ESSENCE, Models.GENERATED);

        ////armor
        //itemModelGenerator.registerArmor(((ArmorItem) ModItems.LTF_HAIR));

        //spawn eggs
        itemModelGenerator.register(ModItems.AMOGH_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
    }

}
