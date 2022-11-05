package com.theracraft.core.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class excalibur {
    public static ItemStack get(){
        ItemStack itemStack = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta != null) {
            itemMeta.displayName(Component.text("Excalibur")
                    .color(NamedTextColor.DARK_RED));


            List<Component> lore = new ArrayList<>();
            lore.add(Component.text("The legendary sword of King Arthur.")
                    .color(NamedTextColor.GOLD));
            itemMeta.lore(lore);

            itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 100, true);
            itemMeta.addEnchant(Enchantment.SWEEPING_EDGE, 100, true);
            itemMeta.addEnchant(Enchantment.DURABILITY, 100, true);

            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }
}
