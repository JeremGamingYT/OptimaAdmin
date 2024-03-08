package com.jeremgaming.optimaadmin.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class UnMute implements CommandExecutor {
    private static final Mute.MuteData UNMUTED = new Mute.MuteData(null, null);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("§cUtilisation: §7/unmute <§ejoueur§7>");
            return true;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            player.sendMessage("§cLe joueur spécifié n'est pas en ligne.");
            return true;
        }

        if (targetPlayer.equals(player)) {
            player.sendMessage("§cVous ne pouvez pas vous unmuter vous-même.");
            return true;
        }

        if (!Mute.mutedPlayers.containsKey(targetPlayer)) {
            player.sendMessage("§7Le joueur §e" + targetPlayer.getName() + " §7n'est pas réduit au silence.");
            return true;
        }

        Mute.mutedPlayers.put(targetPlayer, UNMUTED);
        targetPlayer.sendMessage("§7Vous n'êtes plus réduit au silence.");
        player.sendMessage("§7Le joueur §e" + targetPlayer.getName() + " §7n'est plus réduit au silence.");

        return true;
    }
}
