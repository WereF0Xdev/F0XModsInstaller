package fox.mods.installer.rtp.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import fox.mods.installer.rtp.InstallRtp;
import fox.mods.installer.tpa.InstallTpa;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class InstallRtpCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("install-rtp")
                .requires(source -> source.hasPermission(4))
                .executes(InstallRtpCommand::downloadMod));
    }

    private static int downloadMod(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSuccess(() -> net.minecraft.network.chat.Component.literal("§6Starting download..."), false);

        InstallRtp.downloadMod(source);
        return 1;
    }
}
