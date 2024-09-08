package fox.mods.installer.essentialscommands.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import fox.mods.installer.essentialscommands.InstallEssentialsCommands;
import fox.mods.installer.rtp.InstallRtp;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class InstallEssentialsCommandsCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("install-essentials_commands")
                .requires(source -> source.hasPermission(4))
                .executes(InstallEssentialsCommandsCommand::downloadMod));
    }

    private static int downloadMod(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSuccess(() -> net.minecraft.network.chat.Component.literal("§6Starting download..."), false);

        InstallEssentialsCommands.downloadMod(source);
        return 1;
    }
}
