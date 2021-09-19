package me.electrum.crash;

import me.electrum.crash.commands.CrashPlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void onEnable() {
        getLogger().info("Crashplayer v1.0 has started.");
        getCommand("crash").setExecutor((CommandExecutor)new CrashPlayer());
    }

    public void onDisable() {
        getLogger().info("Crashplayer v1.0 has disabled.");
    }
}
