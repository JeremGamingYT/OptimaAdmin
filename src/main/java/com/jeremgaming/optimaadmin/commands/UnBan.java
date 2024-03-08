package com.jeremgaming.optimaadmin.commands;

import org.bukkit.BanList;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class UnBan implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("§cUtilisation: /unban <joueur>");
            return true;
        }

        String targetPlayerName = args[0];
        OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(targetPlayerName);

        if (!targetPlayer.isBanned()) {
            sender.sendMessage("§7Le/La joueur(se) §e" + targetPlayerName + " §7n'est pas banni(e).");
            return true;
        }

        Bukkit.getBanList(BanList.Type.NAME).pardon(targetPlayerName);
        sender.sendMessage("§7Le/La joueur(se) §e" + targetPlayerName + " §7a été débanni(e).");

        if (targetPlayer.isOnline()) {
            Player onlinePlayer = (Player) targetPlayer;
            onlinePlayer.sendMessage("§6Vous avez été débanni du serveur.");
        }

        return true;
    }
}
