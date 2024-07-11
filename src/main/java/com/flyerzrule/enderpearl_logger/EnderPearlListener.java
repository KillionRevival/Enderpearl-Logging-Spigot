package com.flyerzrule.enderpearl_logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class EnderPearlListener implements Listener {

    private final EnderPearlLogger plugin;
    private final File logFile;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public EnderPearlListener(EnderPearlLogger plugin) {
        this.plugin = plugin;
        this.logFile = new File(plugin.getDataFolder(), "enderpearl_log.txt");
        createLogFile();
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            Location from = event.getFrom();
            Location to = event.getTo();
            double distance = from.distance(to);

            String timestamp = LocalDateTime.now().format(dateTimeFormatter);
            String logMessage = String.format("[%s] %s(%s) traveled %.2f blocks with an enderpearl. Start: %s, End: %s",
                    timestamp, event.getPlayer().getName(), event.getPlayer().getUniqueId().toString(), distance,
                    formatLocation(from), formatLocation(to));
            logToFile(logMessage);
        }
    }

    private String formatLocation(Location location) {
        return String.format("World: %s, X: %.2f, Y: %.2f, Z: %.2f", location.getWorld().getName(), location.getX(),
                location.getY(), location.getZ());
    }

    private void createLogFile() {
        try {
            if (!logFile.exists()) {
                logFile.getParentFile().mkdirs();
                logFile.createNewFile();
            }
        } catch (IOException e) {
            plugin.getLogger().severe("Could not create log file: " + e.getMessage());
        }
    }

    private void logToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            plugin.getLogger().severe("Could not write to log file: " + e.getMessage());
        }
    }
}
