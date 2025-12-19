package net.enderman999517.funnymodfortesting.command;

import com.mojang.brigadier.CommandDispatcher;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class ToggleHiddenCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("togglehidden").requires(source -> source.hasPermissionLevel(4)).executes(commandContext -> {
            if (commandContext.getSource().getPlayer() instanceof ModEntityData modEntityData) {
                modEntityData.setHidden(!modEntityData.isHidden());
                modEntityData.setRenderingOverlay(!modEntityData.isRenderingOverlay());
                return 1;
            } else return 0;
        }));
    }
}
