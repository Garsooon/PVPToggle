package org.garsooon.pvptoggle.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.garsooon.pvptoggle.data.PluginConfig;
import org.garsooon.pvptoggle.data.PvPDataManager;

public class JoinListener implements Listener {
    private final PvPDataManager dataManager;
    private final PluginConfig config;

    public JoinListener(PvPDataManager dataManager, PluginConfig config) {
        this.dataManager = dataManager;
        this.config = config;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (config.getValues().resetOnRelog) {
            dataManager.reset(event.getPlayer().getUniqueId());
        }
    }
}
