package com.jeremgaming.optimaadmin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("§cUtilisation: /kick <joueur> <raison>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§cLe joueur spécifié n'est pas en ligne.");
            return true;
        }

        if (target.equals(sender)) {
            sender.sendMessage("§cVous ne pouvez pas vous expulser avec cette commande.");
            return true;
        }

        StringBuilder reason = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            reason.append(args[i]).append(" ");
        }

        target.kickPlayer("§cVous avez été expulsé: §e" + reason.toString());
        sender.sendMessage("§7Le joueur §e" + target.getName() + " §7a été expulsé pour la raison: §c" + reason.toString());
        return true;
    }
}