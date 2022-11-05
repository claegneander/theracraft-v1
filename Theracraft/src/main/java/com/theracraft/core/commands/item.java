package com.theracraft.core.commands;

import com.theracraft.core.items.excalibur;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class item implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("theracraft.commands.item")) {
                int amount = 1;
                /*
                If they specify a number of the item, it will be the amount. Otherwise, amount equals one.
                 */
                if(args.length > 1){
                    if (isInt(args[1])) {
                        amount = Integer.parseInt(args[1]);
                    }
                }
                try {
                    for (int x = 0; x < amount; x++) {
                        ArrayList<String> itemNames = getItemNames(getItems());
                        for (int i = 0; i < itemNames.size(); i++) {
                            if (args[0].equalsIgnoreCase(itemNames.get(i))) {
                                player.getInventory().addItem(getItems().get(i));
                            }
                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
    /*
    Be sure to add all custom items to the ArrayList.
     */
    public static ArrayList<ItemStack> getItems () {
        ArrayList<ItemStack> items = new ArrayList<>();
        items.add(excalibur.get());
        return items;
    }
    /*
    This lets us parse out the names as strings for the custom items for use in the command.
     */
    public static ArrayList<String> getItemNames (ArrayList<ItemStack> items) {
        ArrayList<String> itemNames = new ArrayList<>();
        for (ItemStack item : items) {
            String tempName = item.getItemMeta().getDisplayName();
            tempName = ChatColor.stripColor(tempName);
            tempName = tempName.replace(" ", "_");
            itemNames.add(tempName);
        }
        return itemNames;
    }
    /*
    This is a simple check to see if the input is an Integer.
     */
    public static boolean isInt(String string){
        try{
            Integer.parseInt(string);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
}
