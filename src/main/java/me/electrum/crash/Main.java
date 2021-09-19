package me.electrum.crash;

import me.electrum.crash.commands.CrashCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void onEnable() {
        getLogger().info("Crash v1.0 has started.");
        getCommand("crash").setExecutor((CommandExecutor)new CrashCommand());
    }

    public void onDisable() {
        getLogger().info("Crash v1.0 has disabled.");
    }
}
