package com.jeremgaming.optimaadmin.commands;

import org.bukkit.command.CommandExecutor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportAll implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("§cUtilisation: §7/teleportall §d/ §7/tpall");
            return true;
        }

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendMessage("§7Vous avez été téléporté");
            onlinePlayer.teleport(player.getLocation());
        }

        player.sendMessage("§7Tous les joueurs en ligne ont été téléportés à vous.");

        return true;
    }
}
