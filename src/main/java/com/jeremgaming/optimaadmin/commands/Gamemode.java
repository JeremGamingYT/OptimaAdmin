package com.jeremgaming.optimaadmin.commands;

import org.bukkit.command.CommandExecutor;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("§cUtilisation: §7/gamemode <§dmode§7> / [§ejoueur§7]");
            return true;
        }

        GameMode targetGameMode;
        try {
            targetGameMode = GameMode.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            player.sendMessage("§7Mode de jeu invalide. Utilisez : §asurvival§7, §dcreative§7, §eadventure §7ou spectator.");
            return true;
        }

        if (args.length == 1) {
            player.setGameMode(targetGameMode);
            player.sendMessage("§7Votre mode de jeu est maintenant : §e" + targetGameMode.toString().toLowerCase() + "§7.");
        } else {
            Player targetPlayer = player.getServer().getPlayer(args[1]);
            if (targetPlayer == null) {
                player.sendMessage("§cLe joueur spécifié n'est pas en ligne.");
                return true;
            }

            targetPlayer.setGameMode(targetGameMode);
            targetPlayer.sendMessage("§7Votre mode de jeu a été changé en : §e" + targetGameMode.toString().toLowerCase() + "§7.");
            player.sendMessage("§7Le mode de jeu de §e" + targetPlayer.getName() + " §7a été changé en : §e" + targetGameMode.toString().toLowerCase() + "§7.");
        }

        return true;
    }
}
