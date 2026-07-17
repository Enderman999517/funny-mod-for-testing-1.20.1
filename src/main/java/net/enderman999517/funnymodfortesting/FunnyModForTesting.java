package net.enderman999517.funnymodfortesting;

import net.enderman999517.funnymodfortesting.block.ModBlocks;
import net.enderman999517.funnymodfortesting.block.entity.ModBlockEntities;
import net.enderman999517.funnymodfortesting.command.ModCommands;
import net.enderman999517.funnymodfortesting.damage.ModDamageSources;
import net.enderman999517.funnymodfortesting.enchantment.ModEnchantments;
import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.enderman999517.funnymodfortesting.entity.custom.AmoghEntity;
import net.enderman999517.funnymodfortesting.entity.custom.HiddenEntity;
import net.enderman999517.funnymodfortesting.entity.effect.ModStatusEffects;
import net.enderman999517.funnymodfortesting.item.ModItemGroups;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.enderman999517.funnymodfortesting.networking.EntityMovementTracker;
import net.enderman999517.funnymodfortesting.networking.ModSync;
import net.enderman999517.funnymodfortesting.recipe.ModRecipes;
import net.enderman999517.funnymodfortesting.screen.ModScreenHandlers;
import net.enderman999517.funnymodfortesting.sound.ModSounds;
import net.enderman999517.funnymodfortesting.world.dimension.ModDimensions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunnyModForTesting implements ModInitializer {
	public static final String MOD_ID = "funnymodfortesting";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Identifier IMPERSONATION_KEY = new Identifier(FunnyModForTesting.MOD_ID, "impersonatesword");
	// || g


	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModEntities.registerModEntities();
		ModBlockEntities.registerBlockEntities();
		FabricDefaultAttributeRegistry.register(ModEntities.AMOGH, AmoghEntity.createAmoghAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.HIDDEN, HiddenEntity.createHiddenAttributes());
		ModSounds.registerSounds();
		ModStatusEffects.registerModStatusEffects();
		ModScreenHandlers.registerScreenHandlers();
		ModRecipes.registerRecipes();
		ModCommands.registerCommands();
		ModDamageSources.registerDamageSources();
		ModEnchantments.registerModEnchantments();
		ModSync.init();
		EntityMovementTracker.register();

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(fabricItemGroupEntries -> {

			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PINK_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.MAGENTA_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PURPLE_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLUE_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_BLUE_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.CYAN_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GREEN_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIME_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.YELLOW_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.ORANGE_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.RED_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BROWN_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLACK_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GRAY_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_GRAY_WOOL_PRESSURE_PLATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.WHITE_WOOL_PRESSURE_PLATE);

			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PINK_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.MAGENTA_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PURPLE_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLUE_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_BLUE_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.CYAN_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GREEN_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIME_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.YELLOW_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.ORANGE_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.RED_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BROWN_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLACK_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GRAY_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_GRAY_WOOL_BUTTON);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.WHITE_WOOL_BUTTON);

			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PINK_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.MAGENTA_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PURPLE_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLUE_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_BLUE_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.CYAN_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GREEN_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIME_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.YELLOW_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.ORANGE_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.RED_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BROWN_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLACK_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GRAY_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_GRAY_WOOL_TRAPDOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.WHITE_WOOL_TRAPDOOR);

			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PINK_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.MAGENTA_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PURPLE_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLUE_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_BLUE_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.CYAN_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GREEN_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIME_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.YELLOW_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.ORANGE_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.RED_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BROWN_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLACK_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GRAY_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_GRAY_WOOL_DOOR);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.WHITE_WOOL_DOOR);

			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PINK_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.MAGENTA_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PURPLE_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLUE_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_BLUE_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.CYAN_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GREEN_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIME_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.YELLOW_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.ORANGE_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.RED_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BROWN_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLACK_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GRAY_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_GRAY_WOOL_FENCE_GATE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.WHITE_WOOL_FENCE_GATE);

			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PINK_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.MAGENTA_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PURPLE_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLUE_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_BLUE_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.CYAN_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GREEN_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIME_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.YELLOW_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.ORANGE_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.RED_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BROWN_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLACK_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GRAY_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_GRAY_WOOL_FENCE);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.WHITE_WOOL_FENCE);

			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PINK_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.MAGENTA_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PURPLE_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLUE_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_BLUE_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.CYAN_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GREEN_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIME_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.YELLOW_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.ORANGE_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.RED_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BROWN_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLACK_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GRAY_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_GRAY_WOOL_SLAB);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.WHITE_WOOL_SLAB);

			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PINK_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.MAGENTA_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.PURPLE_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLUE_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_BLUE_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.CYAN_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GREEN_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIME_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.YELLOW_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.ORANGE_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.RED_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BROWN_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.BLACK_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.GRAY_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.LIGHT_GRAY_WOOL_STAIRS);
			fabricItemGroupEntries.addAfter(Items.PINK_CARPET, ModBlocks.WHITE_WOOL_STAIRS);
		});


		CustomPortalBuilder.beginPortal()
				.frameBlock(Blocks.GOLD_BLOCK)
				.lightWithWater()
				.destDimID(new Identifier(FunnyModForTesting.MOD_ID, "oceandim"))
				.flatPortal()
				.tintColor(0xffffff)
				.forcedSize(2,2)
				.onlyLightInOverworld()
				.registerPreIgniteEvent((player, world, portalPos, framePos, portalIgnitionSource) -> ! (world.getDimensionKey() == ModDimensions.OCEANDIM_TYPE))
				.registerPortal();

	}
}