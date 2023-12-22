package me.jovannmc.playerinvincibility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerInvincibility extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        if (getConfig().getInt("configVersion") != 1) {
            getLogger().warning("Outdated config version! Please delete your config.yml and restart the server!");
            getServer().getPluginManager().disablePlugin(this);
        }

        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("PlayerInvincibility has been enabled!");
        getLogger().info("Invincible players: " + getConfig().getStringList("invincible").toString());
    }

    @Override
    public void onDisable() {
        getLogger().info("PlayerInvincibility has been disabled!");
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        // Check config if player is in "invincible", if on the list and player was damaged by another player, cancel the event
        if (event.getEntity() instanceof Player && getConfig().getStringList("invincible").contains(event.getEntity().getName()) && event.getCause() == EntityDamageByEntityEvent.DamageCause.ENTITY_ATTACK && event.getDamager() instanceof Player) {
            event.setCancelled(true);
            if (getConfig().getBoolean("logInvincibleMessage")) {
                getLogger().info("Player " + event.getEntity().getName() + " was attempted to be damaged by " + event.getDamager().getName() + " but was invincible!");
            }

            if (getConfig().getBoolean("sendInvincibleMessage")) {
                event.getEntity().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("invincibleMessage")).replace("%player%", event.getDamager().getName()));
                event.getDamager().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("invincibleMessageDamager")).replace("%player%", event.getEntity().getName()));
            }

            if (getConfig().getBoolean("broadcastInvincibleMessage")) {
                getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("invincibleMessageBroadcast")).replace("%player%", event.getEntity().getName()).replace("%damager%", event.getDamager().getName()));
            }
        }
    }
}
