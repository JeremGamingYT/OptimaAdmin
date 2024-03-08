package com.jeremgaming.optimaadmin.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Day implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;
        World world = player.getWorld();

        world.setTime(0); // 0 ticks = jour
        player.sendMessage("§7Le jour s'est levé dans le monde §e" + world.getName() + "§7.");

        return true;
    }
}
