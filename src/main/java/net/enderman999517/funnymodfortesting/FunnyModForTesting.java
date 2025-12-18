package net.enderman999517.funnymodfortesting;

import net.enderman999517.funnymodfortesting.block.ModBlocks;
import net.enderman999517.funnymodfortesting.block.entity.ModBlockEntities;
import net.enderman999517.funnymodfortesting.command.ModCommands;
import net.enderman999517.funnymodfortesting.damage.ModDamageSources;
import net.enderman999517.funnymodfortesting.enchantment.ModEnchantments;
import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.enderman999517.funnymodfortesting.entity.custom.AmoghEntity;
import net.enderman999517.funnymodfortesting.entity.effect.ModStatusEffects;
import net.enderman999517.funnymodfortesting.item.ModItemGroups;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.enderman999517.funnymodfortesting.networking.EntityMovementTracker;
import net.enderman999517.funnymodfortesting.networking.ModSync;
import net.enderman999517.funnymodfortesting.recipe.ModRecipes;
import net.enderman999517.funnymodfortesting.screen.ModScreenHandlers;
import net.enderman999517.funnymodfortesting.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunnyModForTesting implements ModInitializer {
	public static final String MOD_ID = "funnymodfortesting";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	// || g


	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModEntities.registerModEntities();
		ModBlockEntities.registerBlockEntities();
		FabricDefaultAttributeRegistry.register(ModEntities.AMOGH, AmoghEntity.createAmoghAttributes());
		ModSounds.registerSounds();
		ModStatusEffects.registerModStatusEffects();
		ModScreenHandlers.registerScreenHandlers();
		ModRecipes.registerRecipes();
		ModCommands.registerCommands();
		ModDamageSources.registerDamageSources();
		ModEnchantments.registerModEnchantments();
		ModSync.init();
		EntityMovementTracker.register();

	}
}