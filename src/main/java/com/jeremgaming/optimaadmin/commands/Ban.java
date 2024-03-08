package com.jeremgaming.optimaadmin.commands;

import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.command.CommandExecutor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("§cUtilisation: §7/ban <§ejoueur§7> <§craison§7>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§cLe joueur spécifié n'est pas en ligne.");
            return true;
        }

        if (target.equals(sender)) {
            sender.sendMessage("§cVous ne pouvez pas vous bannir avec cette commande.");
            return true;
        }

        StringBuilder reason = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            reason.append(args[i]).append(" ");
        }

        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), reason.toString(), null, null);
        target.kickPlayer("§c§lVous avez été banni(e) pour la raison: §d" + reason.toString());
        sender.sendMessage("§7Le/La joueur(se) §e" + target.getName() + " §7a été banni(e) pour la raison: §c" + reason.toString() + " §7à vie.");
        return true;
    }
}
