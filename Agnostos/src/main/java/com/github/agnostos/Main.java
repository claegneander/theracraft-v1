package com.github.agnostos;

import com.github.agnostos.commands.agnostos;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    public static Main instance;
    public static ConsoleCommandSender console;
    public void onEnable(){
        instance = this;
        console = instance.getServer().getConsoleSender();

        saveDefaultConfig();

        registerCommands();

        console.sendMessage(ChatColor.GREEN + "Agnostos successfully enabled.");
    }
    public void onDisable(){
        console = null;
        instance = null;
    }

    public static Main getInstance(){
        return instance;
    }
    public ConsoleCommandSender getConsole(){
        return console;
    }
    public void registerCommands(){
        try{
            Objects.requireNonNull(this.getCommand("agnostos")).setExecutor(new agnostos());
            console.sendMessage(ChatColor.GREEN + "Commands have been registered.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
