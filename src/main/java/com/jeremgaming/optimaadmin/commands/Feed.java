package com.jeremgaming.optimaadmin.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length > 0) {
            Player targetPlayer = player.getServer().getPlayer(args[0]);
            if (targetPlayer == null) {
                player.sendMessage("§cLe joueur spécifié n'est pas en ligne.");
                return true;
            }

            targetPlayer.setFoodLevel(20);
            targetPlayer.sendMessage("§7Vous avez été nourri.");
            player.sendMessage("§7Le joueur §e" + targetPlayer.getName() + " §7a été nourri.");
        } else {
            player.setFoodLevel(20);
            player.sendMessage("§7Vous avez été nourri.");
        }

        return true;
    }
}
