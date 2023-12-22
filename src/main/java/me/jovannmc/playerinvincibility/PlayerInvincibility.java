package me.jovannmc.playerinvincibility;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerInvincibility extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
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
            getLogger().info("Player " + event.getEntity().getName() + " was attempted to be damaged by " + event.getDamager().getName() + " but was invincible!");
            event.getEntity().sendMessage("You were attempted to be damaged by " + event.getDamager().getName() + " but you are invincible!");
        }
    }
}
