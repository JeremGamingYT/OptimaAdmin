package com.jeremgaming.optimaadmin;

import com.jeremgaming.optimaadmin.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Envoyer un message dans la console pour dire que le plugin est bien démarrer
        getServer().getConsoleSender().sendMessage("OPTIMA-ADMIN WAS SUCCESSFULLY LOADED!");

        // Enregistrement des commandes
        getCommand("ban").setExecutor(new Ban());
        getCommand("tempban").setExecutor(new Tempban());
        getCommand("unban").setExecutor(new UnBan());
        getCommand("kick").setExecutor(new Kick());
        getCommand("unmute").setExecutor(new UnMute());
        getCommand("teleport").setExecutor(new Teleport());
        getCommand("teleportall").setExecutor(new TeleportAll());
        getCommand("teleporthere").setExecutor(new TeleportHere());
        getCommand("playerkill").setExecutor(new PlayerKill());
        getCommand("heal").setExecutor(new Heal());
        getCommand("feed").setExecutor(new Feed());
        getCommand("day").setExecutor(new Day());
        getCommand("night").setExecutor(new Night());
        getCommand("sun").setExecutor(new Sun());
        getCommand("gamemode").setExecutor(new Gamemode());
        // Invsee
        Invsee invseeCommand = new Invsee();
        getCommand("invsee").setExecutor(invseeCommand);
        getServer().getPluginManager().registerEvents(invseeCommand, this);
        // Mute
        Mute muteCommand = new Mute();
        getCommand("mute").setExecutor(muteCommand);
        getServer().getPluginManager().registerEvents(muteCommand, this);
    }

    @Override
    public void onDisable() {
        // Quand le plugin est désactivé
        // Envoyer un message dans la console pour dire que le plugin est bien arrêter
        getServer().getConsoleSender().sendMessage("OPTIMA-ADMIN WAS SUCCESSFULLY UNLOADED!");
    }
}
