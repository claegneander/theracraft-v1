package com.theracraft.core.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class excalibur {
    public static ItemStack get(){
        ItemStack itemStack = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta != null) {
            itemMeta.setDisplayName(ChatColor.DARK_RED + "Excalibur");

            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "The legendary sword of King Arthur.");
            itemMeta.setLore(lore);

            itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 100, true);
            itemMeta.addEnchant(Enchantment.SWEEPING_EDGE, 100, true);
            itemMeta.addEnchant(Enchantment.DURABILITY, 100, true);

            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }
}
