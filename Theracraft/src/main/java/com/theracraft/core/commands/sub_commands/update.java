package com.theracraft.core.commands.sub_commands;

import com.theracraft.core.Main;
import com.theracraft.core.data.rank_manager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class update implements command{
    rank_manager rm = Main.getInstance().getRankManager();
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission(getPermission())){
                rm.submitMaterials(player);
                rm.updateRank(player);
            }else{
                player.sendMessage(ChatColor.DARK_RED + "Missing permission:" + getPermission());
            }
        }
    }

    @Override
    public String getPermission() {
        return "theracraft.commands.update";
    }

    @Override
    public String getUsage() {
        return "/theracraft update";
    }
}
