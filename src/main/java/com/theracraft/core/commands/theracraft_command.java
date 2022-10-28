package com.theracraft.core.commands;

import com.theracraft.core.Main;
import com.theracraft.core.data.rank_manager;
import com.theracraft.core.data.setup;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class theracraft_command implements CommandExecutor{
    setup s = Main.getInstance().getSetup();
    rank_manager rm = new rank_manager();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("theracraft.commands.auto")) {
                if (args.length == 1 && args[0].equals("auto")) {
                    //Toggled the auto feature.
                }else{
                    player.sendMessage(ChatColor.DARK_RED +"Invalid syntax.");
                }
            }else {
                player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.auto");
            }
            // "/theracraft check (PLAYER)"
            if(player.hasPermission("theracraft.commands.check")){
                if (args[0].equals("check")) {
                    if(args.length == 1){
                        //This will check the player using the command.
                    }else if(args.length == 2){
                        if(player.hasPermission("theracraft.commands.check-other")) {
                            for (Player target : Bukkit.getOnlinePlayers()) {
                                if (args[1].equals(target.getName())) {
                                    //This will check the target player.
                                }else{
                                    player.sendMessage(ChatColor.DARK_RED +"No player found with that name.");
                                }
                            }
                        }else{
                            player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.check-other");
                        }
                    }else{
                        player.sendMessage(ChatColor.DARK_RED +"Invalid syntax.");
                    }
                }
            }else{
                player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.check");
            }
            if(player.hasPermission("theracraft.commands.help")){
                if (args.length == 1 && args[0].equals("help")) {

                }
            }else{
                player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.help");
            }
            // "/theracraft playtime"
            if(player.hasPermission("theracraft.commands.playtime")){
                if (args.length == 1 && args[0].equals("playtime")) {

                }
            }else{
                player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.playtime");
            }
            // "/theracraft rankup"
            if(player.hasPermission("theracraft.commands.rankup")){
                if (args.length == 1 && args[0].equals("rankup")) {

                }
            }else{
                player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.rankup");
            }
            // "/theracraft remove-rank [PLAYER]"
            if(player.hasPermission("theracraft.commands.remove-rank")){
                if (args[0].equals("remove-rank")) {
                    if(args.length == 2){
                        for(Player target : Bukkit.getOnlinePlayers()) {
                            if (args[1].equals(target.getName())) {
                                rm.removeRank(player);
                            }else{
                                player.sendMessage(ChatColor.DARK_RED +"No player found with that name.");
                            }
                        }
                    }else{
                        player.sendMessage(ChatColor.DARK_RED +"Invalid syntax.");
                    }
                }
            }else{
                player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.remove-rank");
            }
            // "/theracraft set-rank [PLAYER] [RANK]"
            if(player.hasPermission("theracraft.commands.set-rank")){
                if (args[0].equals("set-rank")) {
                    if(args.length == 3){
                        for(Player target : Bukkit.getOnlinePlayers()) {
                            if (args[1].equals(target.getName())) {
                                for(String rank: s.getRanks()) {
                                    if (args[2].equals(rank)) {
                                        rm.setRank(player, rank);
                                    }else{
                                        player.sendMessage(ChatColor.DARK_RED +"No rank found with that name.");
                                    }
                                }
                            }else{
                                player.sendMessage(ChatColor.DARK_RED +"No player found with that name.");
                            }
                        }
                    }else{
                        player.sendMessage(ChatColor.DARK_RED +"Invalid syntax.");
                    }
                }
            }else{
                player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.set-rank");
            }
        }else{
            System.out.println("Hi console.");
        }
        return true;
    }
}
