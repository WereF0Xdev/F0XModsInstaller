package fox.mods.installer.pvpadditions;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InstallPvpAdditions {

    public static void downloadMod(CommandSourceStack source) {
        String url = "https://cdn.modrinth.com/data/Sny3T1aP/versions/2mD2bgNw/PvP%20Additions%201.0%20NeoForge%2C%20Forge%201.20.1.jar";
        String fileName = "pvp_additions-1.1.0.jar";
        String modsFolderPath = FMLPaths.GAMEDIR.get().resolve("mods").toString();

        new Thread(() -> {
            try {
                File modsFolder = new File(modsFolderPath);
                if (!modsFolder.exists() && !modsFolder.mkdirs()) {
                    throw new IOException("Could not create mods directory.");
                }

                File targetFile = new File(modsFolder, fileName);

                URL downloadUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
                connection.setRequestMethod("GET");

                try (InputStream inputStream = connection.getInputStream();
                     FileOutputStream outputStream = new FileOutputStream(targetFile)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    source.getServer().execute(() ->
                            source.sendSuccess(() -> Component.literal("§6Downloaded PvP Additions 1.1.0 for Forge and NeoForge 1.20.1 to mods folder: " + targetFile.getPath()), false)
                    );
                }
            } catch (IOException e) {
                source.getServer().execute(() ->
                        source.sendFailure(Component.literal("§6Failed to download mod: " + e.getMessage()))
                );
            }
        }).start();
    }
}
