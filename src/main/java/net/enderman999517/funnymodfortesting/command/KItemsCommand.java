package net.enderman999517.funnymodfortesting.command;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Collection;

public class KItemsCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("kitems")
                .executes(context -> run(context.getSource(), ImmutableList.of(context.getSource().getEntityOrThrow())))
                //.then(
                //        CommandManager.argument("targets", EntityArgumentType.entities())
                //                .executes(context -> run(context.getSource(), EntityArgumentType.getEntities(context, "targets")))
                //)
        );
    }

    private static int run(ServerCommandSource source, Collection<? extends Entity> targets) throws CommandSyntaxException {

        if (targets.getClass().getPermittedSubclasses() == new Class[]{ItemStack.class}) {
            for (Entity entity : targets) {
                entity.kill();
//
                if (targets.size() == 1) {
                    source.sendFeedback(() -> Text.translatable("commands.funnymodfortesting.kill.success.single", ((Entity)targets.iterator().next()).getDisplayName()), true);
                } else {
                    source.sendFeedback(() -> Text.translatable("commands.funnymodfortesting.kill.success.multiple", targets.size()), true);
                }
            }
        }

        return targets.size();
    }
}
