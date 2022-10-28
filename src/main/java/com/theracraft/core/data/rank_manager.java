package com.theracraft.core.data;

import com.theracraft.core.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class rank_manager {

    setup s = Main.getInstance().getSetup();
    data_handler dh = new data_handler();

    public rank_manager() {

    }

    public String getRank(Player player) {
        String rank = s.getRanks().get(0);
        if (dh.hasPDCString(player, "rank")) {
            rank = dh.getPDCString(player, "rank");
        } else {
            for (int i = 0; i < s.getRanks().size(); i++) {
                if (player.hasPermission("theracraft.ranks." + s.getRanks().get(i))) {
                    rank = s.getRanks().get(i);
                }
            }
        }
        Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.GREEN + rank);
        return rank;
    }

    public void setRank(Player player, String rank) {
        dh.setPDCString(player, "rank", rank);
        Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "" + player.getName() + " has been given the rank: " + rank + "!");
    }

    public void updateRank(Player player) {
        String rank = getRank(player);
        setRank(player, rank);
        String display = s.getRankDisplayNames().get(rank);
        long time = s.getRankPlayTimeRequirements().get(rank);

        dh.setPDCString(player, "rank_display", display);
        dh.setPDCLong(player, "rank_time_requirement", time);

    }

    public void removeRank(Player player) {
        String rank = getRank(player);
        String display = s.getRankDisplayNames().get(rank);
        long time = s.getRankPlayTimeRequirements().get(rank);
        if (dh.hasPDCString(player, "rank")) {
            if (Objects.equals(dh.getPDCString(player, "rank"), rank)) {
                dh.removePDC(player, "rank");
            } else {
                Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error code: R001. Data mismatch.");
            }
        }
        if (dh.hasPDCString(player, "rank_display")) {
            if (Objects.equals(dh.getPDCString(player, "rank_display"), display)) {
                dh.removePDC(player, "rank_display");
            } else {
                Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error code: R001. Data mismatch.");
            }
        }
        if (dh.hasPDCLong(player, "rank_time_requirement")) {
            if (dh.getPDCLong(player, "rank_time_requirement") == time) {
                dh.removePDC(player, "rank_time_requirement");
            } else {
                Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error code: R001. Data mismatch.");
            }
        }
        //Need to also add materials for the rank they are on.
    }
}

