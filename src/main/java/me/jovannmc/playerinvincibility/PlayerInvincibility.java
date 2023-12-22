package me.jovannmc.playerinvincibility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerInvincibility extends JavaPlugin implements Listener {
    // TODO: maybe add code to check for other damage causes, especially if caused by other players (eg lava placed by player to bypass invincibility)
    // TODO: use arraylists to reduce disk I/O

    @Override
    public void onEnable() {
        saveDefaultConfig();
        if (getConfig().getInt("configVersion") != 1) {
            getLogger().warning("Outdated config version! Please delete your config.yml and restart the server!");
            getServer().getPluginManager().disablePlugin(this);
        }

        getServer().getPluginCommand("playerinvincibility").setExecutor(new PlayerInvincibilityCommand());
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("PlayerInvincibility has been enabled!");
        getLogger().info("Invincible players: " + getConfig().getStringList("invincible").toString());
    }

    @Override
    public void onDisable() {
        getLogger().info("PlayerInvincibility has been disabled!");
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        // Check config if player is in "invincible", if on the list and player was damaged by another player, cancel the e
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player && getConfig().getStringList("invincible").contains(e.getEntity().getName())) {
            Player damaged = (Player) e.getEntity();
            Player damager = (Player) e.getDamager();

            if (damaged.hasPermission("playerinvincibility.bypass") || damager.hasPermission("playerinvincibility.bypass")) return;

            if (getConfig().getBoolean("logInvincibleMessage")) {
                getLogger().info("Player " + damaged.getName() + " was attempted to be damaged by " + damager.getName() + " but was invincible!");
            }
            if (getConfig().getBoolean("sendInvincibleMessage")) {
                damaged.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("invincibleMessage")).replace("%damager%", e.getDamager().getName()));
                damager.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("invincibleMessageDamager")).replace("%damanged%", e.getEntity().getName()));
            }
            if (getConfig().getBoolean("broadcastInvincibleMessage")) {
                getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("invincibleMessageBroadcast")).replace("%damaged%", damaged.getName()).replace("%damager%", damager.getName()));
            }
            e.setCancelled(true);
        }
    }
}
