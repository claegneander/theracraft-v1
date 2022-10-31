package com.theracraft.core.commands.sub_commands;

import com.theracraft.core.commands.theracraft;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class help implements command{

    Map<String, String> commandUsages;
    @Override
    public void execute(CommandSender sender, String[] args) {
        theracraft t = new theracraft();
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission(getPermission())){
                commandUsages = t.getUsages();
                for(String s: commandUsages.keySet()){
                    if(player.hasPermission("theracraft.commands." + s)){
                        player.sendMessage(commandUsages.get(s));
                    }
                }
            }else{
                player.sendMessage("Missing permission: " + getPermission());
            }
        }
        t = null;
    }

    @Override
    public String getPermission() {
        return "theracraft.commands.help";
    }

    @Override
    public String getUsage() {
        return "/theracraft help";
    }
}
