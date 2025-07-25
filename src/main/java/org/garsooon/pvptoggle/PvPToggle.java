package org.garsooon.pvptoggle;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.garsooon.pvptoggle.command.PvPToggleCommand;
import org.garsooon.pvptoggle.data.PluginConfig;
import org.garsooon.pvptoggle.listener.PvPListener;
import org.garsooon.pvptoggle.listener.JoinListener;
import org.garsooon.pvptoggle.data.PvPDataManager;

public class PvPToggle extends JavaPlugin {
    private static PvPToggle instance;
    private PluginConfig pluginConfig;
    private PvPDataManager dataManager;

    @Override
    public void onEnable() {
        instance = this;
        pluginConfig = new PluginConfig(this);
        pluginConfig.load();
        dataManager = new PvPDataManager(pluginConfig);

        // Register commands
        PvPToggleCommand commandHandler = new PvPToggleCommand(dataManager);
        getCommand("pvptoggle").setExecutor(commandHandler);

        // Register listeners
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new PvPListener(dataManager, pluginConfig), this);
        pm.registerEvents(new JoinListener(dataManager, pluginConfig), this);

        System.out.println("[PvPToggle] Plugin enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println("[PvPToggle] Plugin disabled.");
    }

    public static PvPToggle getInstance() {
        return instance;
    }
}
