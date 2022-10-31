package com.theracraft.core.commands.sub_commands;

import org.bukkit.command.CommandSender;

public interface command {
    //This class is for sub-commands.
    void execute(CommandSender sender, String[] args);
    String getPermission();
    String getUsage();
}
