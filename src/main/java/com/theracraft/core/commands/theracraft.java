package com.theracraft.core.commands;

import com.theracraft.core.data.rank_manager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class theracraft implements CommandExecutor {
    rank_manager rm = new rank_manager();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0){
                player.sendMessage("/theracraft help");
            }
            if(args[0].equals("rr")){
                rm.removeRank(player);
            }else if(args[0].equals("update")){
                rm.updateRank(player);
            }
        }
        return true;
    }
}
