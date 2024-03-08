package com.jeremgaming.optimaadmin.commands;

import org.bukkit.BanList;
import org.bukkit.command.CommandExecutor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Tempban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        if (args.length < 4) {
            sender.sendMessage("§cUtilisation: §7/tempban <§ejoueur§7> <§ddurée§7> (d/w/m/y) <§craison§7>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§cLe joueur spécifié n'est pas en ligne.");
            return true;
        }

        long duration;
        String durationUnit = args[2].toLowerCase();
        try {
            duration = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("§cDurée invalide.");
            return true;
        }

        long durationMillis;
        switch (durationUnit) {
            case "d":
                durationMillis = TimeUnit.DAYS.toMillis(duration);
                break;
            case "w":
                durationMillis = TimeUnit.DAYS.toMillis(duration * 7);
                break;
            case "m":
                durationMillis = TimeUnit.DAYS.toMillis(duration * 30);
                break;
            case "y":
                durationMillis = TimeUnit.DAYS.toMillis(duration * 365);
                break;
            default:
                sender.sendMessage("§cUnité de temps invalide. Utilisez §7d§c, §7w§c, §7m §cou §7y§c.");
                return true;
        }

        Date expiryDate = new Date(System.currentTimeMillis() + durationMillis);

        StringBuilder reason = new StringBuilder();
        for (int i = 3; i < args.length; i++) {
            reason.append(args[i]).append(" ");
        }

        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), reason.toString(), expiryDate, null);
        target.kickPlayer("§cVous avez été temporairement banni pour: §e" + reason.toString() + " §cjusqu'au §d" + expiryDate);
        sender.sendMessage("§7Le joueur §e" + target.getName() + " §7a été temporairement banni pour la raison: §e" + reason.toString() + " §7jusqu'au: §d" + expiryDate);
        return true;
    }
}
