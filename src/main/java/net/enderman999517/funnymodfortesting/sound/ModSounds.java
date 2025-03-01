package net.enderman999517.funnymodfortesting.sound;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.block.custom.LtfBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;


public class ModSounds {

    public static final SoundEvent LTF_BLOCK_BREAK = registerSoundEvent("ltf_block_break");
    public static final SoundEvent LTF_BLOCK_STEP = registerSoundEvent("ltf_block_step");
    public static final SoundEvent LTF_BLOCK_PLACE = registerSoundEvent("ltf_block_place");
    public static final SoundEvent LTF_BLOCK_HIT = registerSoundEvent("ltf_block_hit");
    public static final SoundEvent LTF_BLOCK_FALL = registerSoundEvent("ltf_block_fall");


    public static final BlockSoundGroup LTF_BLOCK_SOUNDS = new BlockSoundGroup(-2f, LtfBlock.getRandomPitch(),
            ModSounds.LTF_BLOCK_BREAK, ModSounds.LTF_BLOCK_STEP, ModSounds.LTF_BLOCK_PLACE,
            ModSounds.LTF_BLOCK_HIT, ModSounds.LTF_BLOCK_FALL);



    public static final SoundEvent LTF_INTERACT = registerSoundEvent("ltf_interact");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(FunnyModForTesting.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        FunnyModForTesting.LOGGER.info("Registering Sounds for " + FunnyModForTesting.MOD_ID);
    }
}
