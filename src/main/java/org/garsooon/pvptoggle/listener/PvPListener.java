package org.garsooon.pvptoggle.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.garsooon.pvptoggle.data.PluginConfig;
import org.garsooon.pvptoggle.data.PvPDataManager;

public class PvPListener implements Listener {
    private final PvPDataManager dataManager;
    private final PluginConfig config;

    public PvPListener(PvPDataManager dataManager, PluginConfig config) {
        this.dataManager = dataManager;
        this.config = config;
    }

    @EventHandler
    public void onAnyDamage(EntityDamageEvent event) {
        Entity victimEntity = event.getEntity();

        if (!(victimEntity instanceof Player)) return;
        Player victim = (Player) victimEntity;

        Entity damagerEntity = null;

        if (event instanceof org.bukkit.event.entity.EntityDamageByEntityEvent) {
            damagerEntity = ((org.bukkit.event.entity.EntityDamageByEntityEvent) event).getDamager();
        }

        if (damagerEntity == null) return;

        Player attacker = null;

        if (damagerEntity instanceof Player) {
            attacker = (Player) damagerEntity;
        } else if (damagerEntity instanceof Projectile) {
            Projectile projectile = (Projectile) damagerEntity;
            if (projectile.getShooter() instanceof Player) {
                attacker = (Player) projectile.getShooter();
            }
        }

        if (attacker == null) return;

        if (config.getValues().alwaysPVPWorlds.contains(victim.getWorld().getName())) return;

        if (!dataManager.isPvPEnabled(attacker.getUniqueId()) || !dataManager.isPvPEnabled(victim.getUniqueId())) {
            event.setCancelled(true);
            attacker.sendMessage(ChatColor.RED + "PvP is disabled for you or the target.");
        }
    }
}
