package com.theracraft.core;

import com.theracraft.core.commands.repair;
import com.theracraft.core.commands.repair_all;
import com.theracraft.core.commands.theracraft;
import com.theracraft.core.data.setup;
import com.theracraft.core.events.player_join;
import com.theracraft.core.events.player_quit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static Main instance;
    public static Logger l;
    public setup s;
    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        /* Creates an instance of the logger in order to always use the same one. */
        l = getLogger();
        s = new setup(this);
        /*
        for(int i = 0; i < s.getRankMap().size(); i++) {
            String rank = s.getRankMap().get(i).getRank();
            String display = s.getRankMap().get(i).getDisplay();
            long playtime = s.getRankMap().get(i).getPlaytime();
            HashMap<Material, Integer> map = s.getRankMap().get(i).getMap();
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Rank: " + rank);
            getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "Display: " + display);
            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Playtime: " + playtime);
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "Materials + Values: " + map);

        }
        */
        /* Make sure our commands and events are registered when the plugin is enabled. */
        registerCommands();
        registerEvents();

        l.info(ChatColor.GREEN + "Theracraft enabled.");
    }
    @Override
    public void onDisable() {
        l.info(ChatColor.DARK_RED + "Theracraft disabled.");
        s = null;
        l = null;
        instance = null;
    }
    @SuppressWarnings("All")
    public void registerCommands(){
        try {
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
        pm.registerEvents(new player_quit(), this);
        l.info("Events have been registered.");
    }
    public static Main getInstance(){
        return instance;
    }
    public setup getSetup(){
        return s;
    }
}
