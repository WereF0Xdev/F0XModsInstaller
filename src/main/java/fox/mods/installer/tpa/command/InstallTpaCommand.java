package fox.mods.installer.tpa.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import fox.mods.installer.tpa.InstallTpa;

public class InstallTpaCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("install-tpa")
                .requires(source -> source.hasPermission(4))
                .executes(InstallTpaCommand::downloadMod));
    }

    private static int downloadMod(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSuccess(() -> net.minecraft.network.chat.Component.literal("Starting download..."), false);

        InstallTpa.downloadMod(source);
        return 1;
    }
}
