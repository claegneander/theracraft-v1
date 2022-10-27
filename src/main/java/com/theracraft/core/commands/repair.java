package com.theracraft.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class repair implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("theracraft.commands.repair")) {
                ItemStack itemStack = player.getItemInHand();
                int currentDurability = itemStack.getDurability();
                int maxDurability = player.getActiveItem().getMaxItemUseDuration();
                if (currentDurability != maxDurability) {
                    player.getItemInHand().setDurability((short) maxDurability);
                    return true;
                }
            } else {
                player.sendMessage("Missing permission: theracraft.commands.repair");
            }
        }
        return true;
    }
}