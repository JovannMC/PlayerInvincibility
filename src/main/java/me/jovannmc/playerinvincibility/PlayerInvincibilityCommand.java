package me.jovannmc.playerinvincibility;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PlayerInvincibilityCommand implements CommandExecutor {
    PlayerInvincibility plugin = PlayerInvincibility.getPlugin(PlayerInvincibility.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            // Info command
            if (sender.hasPermission("playerinvincibility.info")) {
                sender.sendMessage("§6§lPlayerInvincibility §7- §eInfo");
                sender.sendMessage("§7Version: §e" + plugin.getDescription().getVersion());
                sender.sendMessage("§7Author: §e" + plugin.getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
                sender.sendMessage("§7Description: §e" + plugin.getDescription().getDescription());
                sender.sendMessage("§7Source code: §ehttps://github.com/JovannMC/PlayerInvincibility");
            } else {
                sender.sendMessage("§6§lPlayerInvincibility §7- §cYou don't have permission to do that!");
            }
        } else if (args.length >= 1) {
            // Reload command
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("playerinvincibility.reload")) {
                    plugin.reloadConfig();
                    sender.sendMessage("§6§lPlayerInvincibility §7- §eConfig reloaded!");
                } else {
                    sender.sendMessage("§6§lPlayerInvincibility §7- §cYou don't have permission to do that!");
                }
            } else if (args[0].equalsIgnoreCase("add")) {
                // Add command
                if (sender.hasPermission("playerinvincibility.add")) {
                    if (!plugin.getConfig().getStringList("invincible").contains(args[1])) {
                        plugin.getConfig().getStringList("invincible").add(args[1]);
                        plugin.saveConfig();
                        sender.sendMessage("§6§lPlayerInvincibility §7- §ePlayer " + args[1] + " is now invincible!");
                    } else {
                        sender.sendMessage("§6§lPlayerInvincibility §7- §ePlayer " + args[1] + " is already invincible!");
                    }
                } else {
                    sender.sendMessage("§6§lPlayerInvincibility §7- §cYou don't have permission to do that!");
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                // Remove command
                if (sender.hasPermission("playerinvincibility.remove")) {
                    if (plugin.getConfig().getStringList("invincible").contains(args[1])) {
                        plugin.getConfig().getStringList("invincible").remove(args[1]);
                        plugin.saveConfig();
                        sender.sendMessage("§6§lPlayerInvincibility §7- §ePlayer " + args[1] + " is no longer invincible!");
                    } else {
                        sender.sendMessage("§6§lPlayerInvincibility §7- §ePlayer " + args[1] + " is not invincible!");
                    }
                } else {
                    sender.sendMessage("§6§lPlayerInvincibility §7- §cYou don't have permission to do that!");
                }
            }
        }
        return false;
    }
}
