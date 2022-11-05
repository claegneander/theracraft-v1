package com.theracraft.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class repair_all implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("kazanjima.commands.repair-all")) {
                for (int i = 0; i <= player.getInventory().getSize(); i++) {
                    if (player.getInventory().getItem(i) != null) {
                        ItemStack currentItem = player.getInventory().getItem(i);
                        if (currentItem != null) {
                            int currentDurability = currentItem.getDurability();
                            int maxDurability = player.getActiveItem().getMaxItemUseDuration();
                            if (currentDurability > maxDurability) {
                                currentItem.setDurability((short) maxDurability);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}