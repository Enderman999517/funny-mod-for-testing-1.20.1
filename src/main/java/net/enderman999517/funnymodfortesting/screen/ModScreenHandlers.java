package net.enderman999517.funnymodfortesting.screen;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<BrainrottingScreenHandler> BRAINROTTING_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(FunnyModForTesting.MOD_ID, "brainrotting"),
                    new ExtendedScreenHandlerType<>(BrainrottingScreenHandler::new));
    public static final ScreenHandlerType<CompactingScreenHandler> COMPACTING_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(FunnyModForTesting.MOD_ID, "compacting"),
                    new ExtendedScreenHandlerType<>(CompactingScreenHandler::new));


    public static void registerScreenHandlers() {
        FunnyModForTesting.LOGGER.info("Registering screen handlers for " + FunnyModForTesting.MOD_ID);
    }

}
