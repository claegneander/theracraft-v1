package com.github.agnostos.commands;

import com.github.agnostos.commands.sub_commands.help;
import com.github.agnostos.commands.sub_commands.sub_command;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class agnostos implements CommandExecutor {
    public final Map<String, sub_command> commands = new HashMap<>();
    public final Map<String, String> descriptions;
    public final Map<String, String> usages;

    public agnostos() {
        commands.put("help", new help());
        descriptions = new HashMap<>();
        usages = new HashMap<>();
        for(String s: commands.keySet()){
            descriptions.put(s, commands.get(s).getDescription());
            usages.put(s, commands.get(s).getUsage());
        }
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("agnostos.commands.agnostos")) {
                if (args.length >= 1) {
                    try {
                        sub_command c = commands.get(args[0].toLowerCase());
                        if (c != null) {
                            c.onCommand(sender, args);
                        } else {
                            player.sendMessage(ChatColor.DARK_RED + "Error C001: Invalid sub-command.");
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }else{
                player.sendMessage(ChatColor.DARK_RED + "Error P001: Missing permission: agnostos.commands.agnostos");
            }
        }
        return true;
    }
    public Map<String, String> getDescriptions(){
        return this.descriptions;
    }
    public Map<String, String> getUsages(){
        return this.usages;
    }
}
