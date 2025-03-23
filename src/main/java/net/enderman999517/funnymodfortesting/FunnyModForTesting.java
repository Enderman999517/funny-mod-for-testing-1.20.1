package net.enderman999517.funnymodfortesting;

import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import ladysnake.satin.api.managed.uniform.Uniform4f;
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


	private boolean renderingBlit = false;
	private final ManagedShaderEffect testShader = ShaderEffectManager.getInstance().manage(new Identifier(MOD_ID, "shaders/post/blit.json"));
	private final Uniform4f color = testShader.findUniform4f("ColorModulate");


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


		ShaderEffectRenderCallback.EVENT.register(tickDelta -> {
			if (renderingBlit) {
				testShader.render(tickDelta);
			}
		});
		ModItems.DEBUG_ITEM.registerDebugCallback((world, player, hand) -> {
			if (world.isClient) {
				renderingBlit = !renderingBlit;
				color.set(5.0f,5.0f,5.0f,5.0f);
			}
		});

		ModStatusEffects.FLASHBANG.registerDebugCallback((entity, attributes, amplifier) -> {
			//if (entity.getWorld().isClient) {
			// i know i probably shouldnt do this but i couldnt find another way that worked

			try {
                //int duration;
                //for (duration = entity.getStatusEffect(ModStatusEffects.FLASHBANG).getDuration(); duration > 0; duration--) {
                //    duration = entity.getStatusEffect(ModStatusEffects.FLASHBANG).getDuration();
                    renderingBlit = !renderingBlit;
                    color.set(5.0f, 5.0f, 5.0f, 5f);

                //}
                //duration = entity.getStatusEffect(ModStatusEffects.FLASHBANG).getDuration();


            } catch (NullPointerException ignored) {

			}


			//}
		});

	}
}