package net.enderman999517.funnymodfortesting.recipe;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(FunnyModForTesting.MOD_ID, BrainrottingRecipe.Serializer.ID),
                BrainrottingRecipe.Serializer.INSTANCE);

        Registry.register(Registries.RECIPE_TYPE, new Identifier(FunnyModForTesting.MOD_ID, BrainrottingRecipe.Type.ID),
                BrainrottingRecipe.Type.INSTANCE);
    }
}
