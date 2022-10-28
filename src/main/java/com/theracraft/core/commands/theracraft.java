package com.theracraft.core.commands;

import com.theracraft.core.data.rank_manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class theracraft implements CommandExecutor {
    rank_manager rm = new rank_manager();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            // "/theracraft"
            if(args.length == 0){
                Bukkit.dispatchCommand(player, "theracraft help");
            }else{
                switch (args[0]){
                    // "/theracraft auto"
                    case ("auto"):
                        if(player.hasPermission("theracraft.commands.auto")){

                        }else{
                            player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.auto");
                        }
                    // "/theracraft check"
                    case ("check"):
                        if(player.hasPermission("theracraft.commands.check")){
                            if(args.length == 1){
                                rm.checkRank(player);
                            }else if(args.length == 2){
                                if(player.hasPermission("theracraft.commands.check-other")) {
                                    for (Player target : Bukkit.getOnlinePlayers()) {
                                        if (args[1].equals(target.getName())) {
                                            rm.checkRank(target);
                                        }
                                    }
                                }else{
                                    player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.check-other");
                                }
                            }
                        }else{
                            player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.check");
                        }
                    // "/theracraft help"
                    case ("help"):
                        if(player.hasPermission("theracraft.commands.help")){

                        }else{
                            player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.help");
                        }
                    // "/theracraft playtime"
                    case ("playtime"):
                        if(player.hasPermission("theracraft.commands.playtime")){

                        }else{
                            player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.playtime");
                        }
                    // "/theracraft rankup"
                    case ("rankup"):
                        if(player.hasPermission("theracraft.commands.rankup")){

                        }else{
                            player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.rankup");
                        }
                    // "/theracraft remove-rank"
                    case ("remove-rank"):
                        if(player.hasPermission("theracraft.commands.remove-rank")){
                            rm.removeRank(player);
                        }else{
                            player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.remove-rank");
                        }
                    // "/theracraft set-rank"
                    case ("set-rank"):
                        if(player.hasPermission("theracraft.commands.set-rank")){

                        }else{
                            player.sendMessage(ChatColor.DARK_RED + "Missing permission: theracraft.commands.set-rank");
                        }
                }
            }
        }
        return false;
    }
}
