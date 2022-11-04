package com.theracraft.core.misc;

import com.theracraft.core.data.data_handler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class util {

    static data_handler dh = new data_handler();
    public static void removeItem(Player player, Material material, int amount){
        ItemStack itemStack = new ItemStack(material);
        Inventory i = player.getInventory();
        for(ItemStack x : i.getContents()) {
            if (i.contains(itemStack)) {
                if (x != null) {
                    if (x == itemStack) {
                        if (dh.hasPDCInteger(player, material.toString())) {
                            int currentAmount = x.getAmount();
                            if (currentAmount > amount) {
                                x.setAmount(currentAmount - amount);
                                dh.setPDCInteger(player, material.toString(), 0);
                            } else {
                                x.setAmount(0);
                                amount = amount - currentAmount;
                                dh.setPDCInteger(player, material.toString(), amount);
                            }
                        }
                    }
                    player.sendMessage("Removed " + amount + " " + x + " from your inventory.");
                }
            }
        }

    }
}
