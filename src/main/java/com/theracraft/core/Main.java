package com.theracraft.core;

import com.theracraft.core.commands.item;
import com.theracraft.core.commands.repair;
import com.theracraft.core.commands.repair_all;
import com.theracraft.core.commands.theracraft;
import com.theracraft.core.data.rank_manager;
import com.theracraft.core.data.setup;
import com.theracraft.core.events.player_join;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    /*
    TODO: Fix the time formatting.
    TODO: Add in a command to ban Olivia forever!
    TODO: Figure out a way to use a weighted list to randomize the items for each rank.
     */

    public static Main instance;
    public static Logger l;
    public setup s;
    public rank_manager rm;
    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        /* Creates an instance of the logger in order to always use the same one. */
        l = getLogger();
        s = new setup(this);
        rm = new rank_manager();
        /* Make sure our commands and events are registered when the plugin is enabled. */
        registerCommands();
        registerEvents();

        l.info(ChatColor.GREEN + "Theracraft enabled.");
    }
    @Override
    public void onDisable() {
        l.info(ChatColor.DARK_RED + "Theracraft disabled.");
        s = null;
        rm = null;
        l = null;
        instance = null;
    }
    @SuppressWarnings("All")
    public void registerCommands(){
        try {
            theracraft t = new theracraft();
            this.getCommand("item").setExecutor(new item());
            this.getCommand("repair").setExecutor(new repair());
            this.getCommand("repair-all").setExecutor(new repair_all());
            this.getCommand("theracraft").setExecutor(new theracraft());
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        l.info("Commands have been registered.");
    }
    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new player_join(), this);
        l.info("Events have been registered.");
    }
    public static Main getInstance(){
        return instance;
    }
    public setup getSetup(){
        return s;
    }
    public rank_manager getRankManager(){
        return rm;
    }
}
