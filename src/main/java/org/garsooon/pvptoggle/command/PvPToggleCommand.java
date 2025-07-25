package org.garsooon.pvptoggle.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.garsooon.pvptoggle.data.PvPDataManager;

public class PvPToggleCommand implements CommandExecutor {
    private final PvPDataManager dataManager;

    public PvPToggleCommand(PvPDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players may use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("pvptoggle.use")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        boolean enabled = !dataManager.isPvPEnabled(player.getUniqueId());
        dataManager.setPvPEnabled(player.getUniqueId(), enabled);
        player.sendMessage(ChatColor.RED + "PvP has been " + (enabled ? "enabled" : "disabled") + ".");
        return true;
    }
}
