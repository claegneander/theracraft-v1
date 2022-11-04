package com.theracraft.core.misc;

import com.theracraft.core.data.data_handler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class util {

    static data_handler dh = new data_handler();
    public static void removeItem(Player player, Material material, int amount) {
        ItemStack itemStack = new ItemStack(material);
        Inventory i = player.getInventory();
        boolean itemRemoved = false;
        int removedAmount = amount;
        if(amount != 0) {
            for (ItemStack x : i.getContents()) {
                if (x != null) {
                    if (x.isSimilar(itemStack)) {
                        if (dh.hasPDCInteger(player, material.toString())) {
                            int currentAmount = x.getAmount();
                            if (currentAmount > amount) {
                                x.setAmount(currentAmount - amount);
                                dh.setPDCInteger(player, material.toString(), 0);
                                amount = 0;
                            } else {
                                x.setAmount(0);
                                amount = amount - currentAmount;
                                removedAmount = currentAmount;
                                dh.setPDCInteger(player, material.toString(), amount);
                            }
                            itemRemoved = true;
                        }
                    }
                }
            }
        }
        if(itemRemoved) {
            //This needs to be formatted better.
            player.sendMessage(ChatColor.GREEN + "Removed " + removedAmount + " " + itemStack.getItemMeta().getDisplayName() + " from your inventory.");
        }
    }
}
