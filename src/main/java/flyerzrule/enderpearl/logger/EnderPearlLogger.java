package com.flyerzrule.enderpearl.logger;

import org.bukkit.plugin.java.JavaPlugin;

public class EnderPearlLogger extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("EnderPearlLogger has been enabled!");
        getServer().getPluginManager().registerEvents(new EnderPearlListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("EnderPearlLogger has been disabled!");
    }
}
