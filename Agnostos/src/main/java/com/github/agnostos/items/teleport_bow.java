package com.github.agnostos.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class teleport_bow {

    public ItemStack get(){
        ItemStack itemStack = new ItemStack(Material.BOW);

        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta != null) {
            itemMeta.displayName(Component.text("Teleport Bow")
                    .color(NamedTextColor.LIGHT_PURPLE));

            List<Component> lore = new ArrayList<>();
            lore.add(Component.text("Teleports the user to the location of the arrow.")
                    .color(NamedTextColor.DARK_PURPLE));
            itemMeta.lore(lore);

            itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            itemMeta.addEnchant(Enchantment.DURABILITY, 3, true);

            itemStack.setItemMeta(itemMeta);
        }

        return itemStack;
    }
}
