package com.github.agnostos.commands.sub_commands;

import org.bukkit.command.CommandSender;

public interface sub_command {
    void onCommand(CommandSender sender, String[] args);
    String getDescription();
    String getPermission();
    String getUsage();
}
