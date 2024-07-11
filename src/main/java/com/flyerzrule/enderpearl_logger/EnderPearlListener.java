package com.flyerzrule.enderpearl_logger;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class EnderPearlListener implements Listener {

    private final EnderPearlLogger plugin;

    public EnderPearlListener(EnderPearlLogger plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            Location from = event.getFrom();
            Location to = event.getTo();
            double distance = from.distance(to);

            String logMessage = String.format("%s(%s) traveled %.2f blocks with an enderpearl. Start: %s, End: %s",
                    event.getPlayer().getName(), event.getPlayer().getUniqueId().toString(), distance,
                    formatLocation(from), formatLocation(to));
            plugin.getLogger().info(logMessage);
        }
    }

    private String formatLocation(Location location) {
        return String.format("World: %s, X: %.2f, Y: %.2f, Z: %.2f", location.getWorld().getName(), location.getX(),
                location.getY(), location.getZ());
    }
}
