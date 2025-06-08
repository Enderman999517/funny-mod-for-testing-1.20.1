package net.enderman999517.funnymodfortesting.datagen;

import net.enderman999517.funnymodfortesting.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {


    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        // RUN DATATGEN AFTER CHANGES!!!!!!!!!
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMOGH, 1)
                .pattern("AAA")
                .pattern("AGA")
                .pattern("AAA")
                .input('A', ModItems.AMOGH_ESSENCE)
                .input('G', Items.GUNPOWDER)
                .criterion(hasItem(ModItems.AMOGH_ESSENCE), conditionsFromItem(ModItems.AMOGH_ESSENCE))
                .criterion(hasItem(ModItems.AMOGH), conditionsFromItem(ModItems.AMOGH))
                .showNotification(true)
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AMOGH)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NVG_GOGGLES, 1)
                .pattern("ISI")
                .pattern("EAE")
                .pattern("   ")
                .input('I', Items.IRON_INGOT)
                .input('S', Items.STRING)
                .input('E', Items.ENDER_EYE)
                .input('A', Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.ENDER_EYE), conditionsFromItem(Items.ENDER_EYE))
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .showNotification(true)
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NVG_GOGGLES)));
    }
}
