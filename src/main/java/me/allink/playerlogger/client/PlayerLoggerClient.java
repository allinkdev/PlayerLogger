package me.allink.playerlogger.client;

import com.google.gson.Gson;
import me.allink.playerlogger.client.log.Log;
import me.allink.playerlogger.client.property.SkinProperty;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayNetworkHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class PlayerLoggerClient implements ClientModInitializer {
    static Log LOG;
    static String workingDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
    static File logFile = new File(String.valueOf(Paths.get(workingDirectory, "log.json")));
    static Path logPath = logFile.toPath();

    static void saveLog(Log log) {
        LOG = log;
        try {
            String json = new Gson().toJson(LOG);
            Files.writeString(logPath, json);
        } catch (IOException e) {
            System.err.println("Couldn't save log file.");
            e.printStackTrace();
        }
    }

    public static void addUsername(String username) {
        if(LOG.getUsernames().contains(username)) return;
        LOG.addUsername(username);
        saveLog(LOG);
    }

    public static void addSkinProperty(SkinProperty property) {
        if(LOG.getSkinProperties().contains(property)) return;
        LOG.addSkinProperty(property);
        saveLog(LOG);
    }

    @Override
    public void onInitializeClient() {
        if (!Files.exists(logPath)) {
            try {
                LOG = new Log();
                String json = new Gson().toJson(LOG);
                Files.writeString(logPath, json);
            } catch (IOException e) {
                System.err.println("Couldn't create log file.");
                e.printStackTrace();
            }
        } else {
            try {
                String json = Files.readString(logPath);
                LOG = new Gson().fromJson(json, Log.class);
            } catch (IOException e) {
                System.err.println("Couldn't read log file.");
                e.printStackTrace();
            }
        }
    }
}
