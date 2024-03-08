package com.jeremgaming.optimaadmin.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Mute implements CommandExecutor, Listener {
    public static final Map<Player, MuteData> mutedPlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage("§cUtilisation: §7/mute <§ejoueur§7> <§adurée§7> [m/h/d/w] [§craison§7]");
            return true;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            player.sendMessage("§cLe joueur spécifié n'est pas en ligne.");
            return true;
        }

        if (targetPlayer.equals(player)) {
            player.sendMessage("§cVous ne pouvez pas vous muter vous-même.");
            return true;
        }

        long duration;
        try {
            duration = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage("§cDurée invalide.");
            return true;
        }

        TimeUnit unit = TimeUnit.MINUTES;
        if (args.length >= 3) {
            switch (args[2].toLowerCase()) {
                case "m":
                    unit = TimeUnit.MINUTES;
                    break;
                case "h":
                    unit = TimeUnit.HOURS;
                    break;
                case "d":
                    unit = TimeUnit.DAYS;
                    break;
                case "w":
                    unit = TimeUnit.DAYS;
                    duration *= 7;
                    break;
                default:
                    player.sendMessage("§cUnité de temps invalide. Utilisez §7m§c, §7h§c, §7d §cou §7w§c.");
                    return true;
            }
        }

        long expirationTime = System.currentTimeMillis() + unit.toMillis(duration);
        Date expirationDate = new Date(expirationTime);

        StringBuilder reason = new StringBuilder();
        if (args.length >= 4) {
            for (int i = 3; i < args.length; i++) {
                reason.append(args[i]).append(" ");
            }
        } else {
            reason.append("§cAucune raison spécifiée.");
        }

        mutedPlayers.put(targetPlayer, new MuteData(expirationDate, reason.toString()));
        targetPlayer.sendMessage("§cVous avez été réduit au silence jusqu'au §d" + expirationDate + " §cpour la raison suivante : §e" + reason);
        player.sendMessage("§7Le joueur §e" + targetPlayer.getName() + " §7a été réduit au silence jusqu'au §d" + expirationDate + " §cpour la raison suivante : §e" + reason);

        return true;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (mutedPlayers.containsKey(player)) {
            MuteData muteData = mutedPlayers.get(player);
            if (muteData.expirationDate != null && System.currentTimeMillis() < muteData.expirationDate.getTime()) {
                event.setCancelled(true);
                player.sendMessage("§cVous êtes actuellement réduit au silence jusqu'au §d" + muteData.expirationDate + " §cpour la raison suivante : §e" + muteData.reason);
            } else {
                mutedPlayers.remove(player);
            }
        }
    }

    public static class MuteData {
        private final Date expirationDate;
        private final String reason;

        public MuteData(Date expirationDate, String reason) {
            this.expirationDate = expirationDate;
            this.reason = reason;
        }
    }
}
