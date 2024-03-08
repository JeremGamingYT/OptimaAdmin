package com.jeremgaming.optimaadmin.commands;

import org.bukkit.command.CommandExecutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportHere implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("§cUtilisation: §7/teleporthere <§ejoueur§7> §d/ §7tphere <§ejoueur§7>");
            return true;
        }

        Player targetPlayer = player.getServer().getPlayer(args[0]);
        if (targetPlayer == null) {
            player.sendMessage("§cLe joueur spécifié n'est pas en ligne.");
            return true;
        }

        targetPlayer.teleport(player.getLocation());
        targetPlayer.sendMessage("§7Vous avez été téléporté vers §e" + player.getName() + "§7.");
        player.sendMessage(targetPlayer.getName() + " §7a été téléporté vers vous.");

        return true;
    }
}
