package net.enderman999517.funnymodfortesting.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ModCommands {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(KItemsCommand::register);
        CommandRegistrationCallback.EVENT.register(ToggleHiddenCommand::register);

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(ClientCommandManager.literal("dlshader")
                        .then(ClientCommandManager.argument("url", StringArgumentType.greedyString())
                                .executes(context ->
                                        {
                                            try {
                                                return DownloadShaderCommand.downloadShader(context.getSource(), StringArgumentType.getString(context, "url"));
                                            } catch (IOException | InterruptedException | TimeoutException e) {
                                                context.getSource().sendError(Text.translatable("commands.funnymodfortesting.dlshader.error"));
                                            }
                                            return 0;
                                        }
                                )
                        )
                )
        );
    }
}
