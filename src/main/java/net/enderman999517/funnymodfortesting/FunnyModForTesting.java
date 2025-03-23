package net.enderman999517.funnymodfortesting;

import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import net.enderman999517.funnymodfortesting.block.ModBlocks;
import net.enderman999517.funnymodfortesting.block.entity.ModBlockEntities;
import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.enderman999517.funnymodfortesting.entity.custom.AmoghEntity;
import net.enderman999517.funnymodfortesting.entity.effect.ModStatusEffects;
import net.enderman999517.funnymodfortesting.item.ModItemGroups;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.enderman999517.funnymodfortesting.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunnyModForTesting implements ModInitializer {
	public static final String MOD_ID = "funnymodfortesting";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public static final ManagedShaderEffect testShader = ShaderEffectManager.getInstance().manage(new Identifier(FunnyModForTesting.MOD_ID, "shaders/post/blit.json"));

	public static boolean renderingBlit = false;


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModEntities.registerModEntities();
		ModBlockEntities.registerBlockEntities();
		FabricDefaultAttributeRegistry.register(ModEntities.AMOGH, AmoghEntity.createAmoghAttributes());
		ModSounds.registerSounds();
		ModStatusEffects.registerModStatusEffects();


	}
}