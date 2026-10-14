package net.enderman999517.funnymodfortesting.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.networking.ModSync;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Collection;

public class SetShaderCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(
                CommandManager.literal("setshader")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(
                                CommandManager.argument("targets", EntityArgumentType.entities())
                                        .then(
                                                CommandManager.argument("shader", StringArgumentType.greedyString())
                                                        .executes(
                                                                commandContext -> execute(
                                                                        commandContext.getSource(),
                                                                        EntityArgumentType.getEntities(commandContext, "targets"),
                                                                        StringArgumentType.getString(commandContext, "shader")
                                                                )
                                                        )
                                        )
                        )
        );
    }


    private static int execute(ServerCommandSource source, Collection<? extends Entity> targets, String shader) {

        int validTargets = 0;
        for (Entity entity : targets) {
            if (entity instanceof PlayerEntity) {
                ModSync.syncCurrentAppliedShaderFlag(entity, shader, false);
                validTargets++;
            }
        }

        if (targets.size() == 1) {
            source.sendFeedback(
                    () -> Text.translatable("commands.funnymodfortesting.setshader.success.single", shader, (targets.iterator().next()).getDisplayName()), true);
        } else {
            int finalValidTargets = validTargets;
            source.sendFeedback(() -> Text.translatable("commands.funnymodfortesting.setshader.success.multiple", shader, finalValidTargets), true);
        }
        return validTargets;
    }
}
