package com.theracraft.core.commands.sub_commands;

import com.theracraft.core.Main;
import com.theracraft.core.data.data_handler;
import com.theracraft.core.data.rank_manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class remove_rank implements command{
    rank_manager rm = Main.getInstance().getRankManager();
    data_handler dh = new data_handler();
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission(getPermission())){
                if(args.length == 2){
                    for(Player target : Bukkit.getOnlinePlayers()){
                        if(args[1].equals(target.getName())){
                            if(dh.hasPDCString(target, "rank")) {
                                player.sendMessage(ChatColor.GREEN + "Removed rank " + ChatColor.BLUE + rm.getRank(target) +
                                        ChatColor.GREEN + " from player " + ChatColor.BLUE + target.getName() + "!");
                                rm.removeRank(target);
                            }else{
                                player.sendMessage(ChatColor.DARK_RED + "That player does not have a rank.");
                            }
                        }
                    }
                }else{
                    player.sendMessage(ChatColor.BLUE + getUsage());
                }
            }else{
                player.sendMessage(ChatColor.BLUE + "Missing permission: " + getPermission());
            }
        }
    }
    @Override
    public String getPermission() {
        return "theracraft.commands.remove-rank";
    }

    @Override
    public String getUsage() {
        return "/theracraft removerank [PLAYER]";
    }
}
