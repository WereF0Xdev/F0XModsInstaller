package fox.mods.installer;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fox.mods.installer.essentialscommands.command.InstallEssentialsCommandsCommand;
import fox.mods.installer.foxapi.command.InstallFoxApiCommand;
import fox.mods.installer.pvpadditions.InstallPvpAdditions;
import fox.mods.installer.pvpadditions.command.InstallPvpAdditionsCommand;
import fox.mods.installer.rtp.command.InstallRtpCommand;
import fox.mods.installer.tpa.command.InstallTpaCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Supplier;

@Mod("installer")
public class InstallerMod {
    public InstallerMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("installer")
                        .requires(source -> source.hasPermission(4))
                        .executes(this::sendInfo)
        );
    }

    private int sendInfo(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSuccess(() -> Component.literal("""
                §e§lF0X Mods Installer
                §6You can use the installer to easily download mods made from this author directly from in-game without having to go online and uploading them to the server.
                §6Here is a list of mods that are currently available with the installer and the command to download them:
                
                §eTPA §i- §p/install-tpa
                §eRTP §i- §p/install-rtp
                §eFoxAPI §i- §p/install-foxapi
                §eEssentials Commands §i- §p/install-essentials_commands
                §ePvP Additions §i- §p/install-pvp_additions
                
                §7More mods will be added to the future and the installer will be progressively updated to have the latest mod updates."""), false);
        return Command.SINGLE_SUCCESS;
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        InstallTpaCommand.register(event.getServer().getCommands().getDispatcher());
        InstallRtpCommand.register(event.getServer().getCommands().getDispatcher());
        InstallEssentialsCommandsCommand.register(event.getServer().getCommands().getDispatcher());
        InstallFoxApiCommand.register(event.getServer().getCommands().getDispatcher());
        InstallPvpAdditionsCommand.register(event.getServer().getCommands().getDispatcher());
    }
}

