package com.flyerzrule.enderpearl_logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class EnderPearlListener implements Listener {

    private final EnderPearlLogger plugin;

    public EnderPearlListener(EnderPearlLogger plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerUseEnderPearl(PlayerInteractEvent event) {
        // Make sure the pearl is in the main hand and a right click
        if (event.getHand() == EquipmentSlot.HAND &&
            event.getAction().name() == "RIGHT_CLICK_AIR"
        ) {
            ItemStack item = event.getItem();
            if (item != null && item.getType() == Material.ENDER_PEARL) {
                plugin.getLogger().info(event.getPlayer().getName() + " used an Enderpearl at " + formatLocation(event.getPlayer().getLocation()));
            }
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            Location from = event.getFrom();
            Location to = event.getTo();
            double distance = from.distance(to);

            plugin.getLogger().info(event.getPlayer().getName() + " traveled " + distance + " blocks with an Enderpearl. " +
                    "Start: " + formatLocation(from) + ", End: " + formatLocation(to));
        }
    }

    private String formatLocation(Location location) {
        return "World: " + location.getWorld().getName() +
               ", X: " + location.getX() +
               ", Y: " + location.getY() +
               ", Z: " + location.getZ();
    }
}
