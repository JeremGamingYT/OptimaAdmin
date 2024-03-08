package com.jeremgaming.optimaadmin.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;

public class Invsee implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lCette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("§cUtilisation: §7/invsee <§ejoueur§7>");
            return true;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            player.sendMessage("§cLe joueur spécifié n'est pas en ligne.");
            return true;
        }

        if (targetPlayer.equals(player)) {
            player.sendMessage("§cVous ne pouvez pas voir votre propre inventaire avec cette commande.");
            return true;
        }

        Inventory targetInventory = Bukkit.createInventory(null, targetPlayer.getInventory().getSize(), "§7Inventaire de : §e" + targetPlayer.getName());
        targetInventory.setContents(targetPlayer.getInventory().getContents());
        player.openInventory(targetInventory);

        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof Player) {
            event.setCancelled(true);
        }
    }
}
