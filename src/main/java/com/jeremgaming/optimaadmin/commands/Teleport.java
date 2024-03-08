package com.jeremgaming.optimaadmin.commands;

import org.bukkit.command.CommandExecutor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("§cUtilisation: §7/teleport <§ejoueur§7> ou /tp <§dx§7> <§dy§7> <§dz§7>");
            return true;
        }

        if (args.length == 1) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                player.sendMessage("§cLe joueur spécifié n'est pas en ligne.");
                return true;
            }

            player.teleport(targetPlayer.getLocation());
            player.sendMessage("§7Vous avez été téléporté vers §e" + targetPlayer.getName() + "§7.");
        } else {
            World world = player.getWorld();
            double x, y, z;

            try {
                x = Double.parseDouble(args[0]);
                y = Double.parseDouble(args[1]);
                z = Double.parseDouble(args[2]);
            } catch (NumberFormatException e) {
                player.sendMessage("§cCoordonnées invalides.");
                return true;
            }

            Location targetLocation = new Location(world, x, y, z);
            player.teleport(targetLocation);
            player.sendMessage("§7Vous avez été téléporté aux coordonnées : §d" + x + "§7, §d" + y + "§7, §d" + z + " §7dans le monde §e" + world.getName() + "§7.");
        }

        return true;
    }
}
