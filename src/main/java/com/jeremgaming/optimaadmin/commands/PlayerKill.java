package com.jeremgaming.optimaadmin.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class PlayerKill implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("§cUtilisation: §7/playerkill <§ejoueur§7>");
            return true;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            player.sendMessage("§cLe joueur spécifié n'est pas en ligne.");
            return true;
        }

        if (targetPlayer.equals(player)) {
            player.sendMessage("§cVous ne pouvez pas vous tuer vous-même avec cette commande.");
            return true;
        }

        PlayerInventory targetInventory = targetPlayer.getInventory();
        targetInventory.clear();
        targetPlayer.setHealth(0.0);
        player.sendMessage("§7Le joueur §e" + targetPlayer.getName() + " §7a été tué et son inventaire a été supprimé.");

        return true;
    }
}
