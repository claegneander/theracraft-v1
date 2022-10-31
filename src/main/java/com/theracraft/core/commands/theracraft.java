package com.theracraft.core.commands;

import com.theracraft.core.commands.sub_commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class theracraft implements CommandExecutor {

    public final Map<String, command> commands = new HashMap<>();
    public final Map<String, String> usages;
    public theracraft() {
        commands.put("check", new check());
        commands.put("help", new help());
        commands.put("removerank", new remove_rank());
        commands.put("setrank", new set_rank());
        commands.put("submit", new submit());
        commands.put("update", new update());
        usages = new HashMap<>();
        for(String s: commands.keySet()){
            usages.put(s, commands.get(s).getUsage());
        }
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("theracraft.commands.theracraft")) {
                if (args.length >= 1) {
                    try {
                        command c = commands.get(args[0].toLowerCase());
                        if (c != null) {
                            c.execute(sender, args);
                        } else {
                            player.sendMessage(ChatColor.DARK_RED + "Error: Invalid subcommand.");
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }
    public Map<String, String> getUsages(){
        return this.usages;
    }
}