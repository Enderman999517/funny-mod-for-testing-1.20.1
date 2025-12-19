package net.enderman999517.funnymodfortesting.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModCommands {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(KItemsCommand::register);
        CommandRegistrationCallback.EVENT.register(ToggleHiddenCommand::register);
    }
}
