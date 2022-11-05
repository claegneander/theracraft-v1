package com.github.agnostos.commands.sub_commands;

import com.github.agnostos.Main;
import com.github.agnostos.commands.agnostos;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class help implements sub_command {
    Main instance = Main.getInstance();
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        agnostos mainCommand = new agnostos();
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(getPermission())) {
                Map<String, String> descriptions = mainCommand.getDescriptions();
                Map<String, String> usages = mainCommand.getUsages();
                for(String s : usages.keySet()){
                    if(player.hasPermission("agnostos.commands.agnostos." + s)){
                        player.sendMessage(descriptions.get(s));
                        player.sendMessage(usages.get(s));
                    }
                }
            }else{
                player.sendMessage(ChatColor.DARK_RED + "Error P002: Missing permission: " + getPermission());
            }
        }else{
            instance.getConsole().sendMessage(ChatColor.DARK_RED + "You must be a player to use this command.");
        }
        mainCommand = null;
    }

    @Override
    public String getDescription() {
        return "Provides information about the command: /agnostos";
    }

    @Override
    public String getPermission() {
        return "agnostos.commands.agnostos.help";
    }

    @Override
    public String getUsage() {
        return "/agnostos help";
    }
}
