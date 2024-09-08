package fox.mods.installer.pvpadditions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import fox.mods.installer.pvpadditions.InstallPvpAdditions;
import fox.mods.installer.tpa.InstallTpa;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class InstallPvpAdditionsCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("install-pvp_additions")
                .requires(source -> source.hasPermission(4))
                .executes(InstallPvpAdditionsCommand::downloadMod));
    }

    private static int downloadMod(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSuccess(() -> net.minecraft.network.chat.Component.literal("§6Starting download..."), false);

        InstallPvpAdditions.downloadMod(source);
        return 1;
    }
}
