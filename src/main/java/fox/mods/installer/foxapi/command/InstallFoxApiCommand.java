package fox.mods.installer.foxapi.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import fox.mods.installer.foxapi.InstallFoxApi;
import fox.mods.installer.rtp.InstallRtp;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class InstallFoxApiCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("install-foxapi")
                .requires(source -> source.hasPermission(4))
                .executes(InstallFoxApiCommand::downloadMod));
    }

    private static int downloadMod(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSuccess(() -> net.minecraft.network.chat.Component.literal("§6Starting download..."), false);

        InstallFoxApi.downloadMod(source);
        return 1;
    }
}
