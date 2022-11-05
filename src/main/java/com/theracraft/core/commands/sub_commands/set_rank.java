package com.theracraft.core.commands.sub_commands;

import com.theracraft.core.Main;
import com.theracraft.core.data.rank_manager;
import com.theracraft.core.data.setup;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class set_rank implements command{
    rank_manager rm = Main.getInstance().getRankManager();
    setup s = Main.instance.getSetup();
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission(getPermission())){
                if(args.length == 3){
                    for(Player target : Bukkit.getOnlinePlayers()){
                        if(args[1].equals(target.getName())){
                            for(String rank: s.getRankNames()){
                                if(args[2].equals(rank)){
                                    rm.removeRank(target);
                                    rm.setRank(target, rank);
                                    player.sendMessage(ChatColor.GREEN + "Set rank " + ChatColor.BLUE + rm.getRank(target) +
                                            ChatColor.GREEN + " for player " + ChatColor.BLUE + target.getName() + "!");
                                    break;
                                }else{
                                    player.sendMessage(ChatColor.DARK_RED + "Error: Invalid rank.");
                                }
                            }
                        }else{
                            player.sendMessage(ChatColor.DARK_RED + "Error: No player found.");
                        }
                    }
                }else{
                    player.sendMessage(ChatColor.BLUE + getUsage());
                }
            }else{
                player.sendMessage(ChatColor.DARK_RED + "Missing permission: " + getPermission());
            }
        }
    }
    @Override
    public String getPermission() {
        return "theracraft.commands.set-rank";
    }

    @Override
    public String getUsage() {
        return "/theracraft setrank [PLAYER] [RANK]";
    }
}
