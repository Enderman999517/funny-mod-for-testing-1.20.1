package net.enderman999517.funnymodfortesting.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Collection;

public class ToggleHiddenCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(
                CommandManager.literal("sethidden")
                        .requires(source -> source.hasPermissionLevel(2))
                                .then(
                                        CommandManager.argument("targets", EntityArgumentType.entities())
                                                .then(
                                                    CommandManager.argument("hidden", BoolArgumentType.bool())
                                                    .executes(
                                                            commandContext -> execute(
                                                                commandContext.getSource(),
                                                                EntityArgumentType.getEntities(commandContext, "targets"),
                                                                BoolArgumentType.getBool(commandContext, "hidden")
                                                            )
                                                    )
                                                )
                                )
        );
    }

    private static int execute(ServerCommandSource source, Collection<? extends Entity> targets, boolean hidden) {

        int validTargets = 0;
        for (Entity entity : targets) {
            if (entity instanceof ModEntityData modEntityData) {
                modEntityData.setHidden(hidden);
                modEntityData.setRenderingOverlay(hidden);
                validTargets++;
            }
        }

        if (targets.size() == 1) {
            source.sendFeedback(
                    () -> Text.translatable("commands.hidden.success.single", hidden, (targets.iterator().next()).getDisplayName()), true);
        } else {
            source.sendFeedback(() -> Text.translatable("commands.hidden.success.multiple", hidden, targets.size()), true);
        }
        return validTargets;
    }
}
