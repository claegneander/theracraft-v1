package com.theracraft.core.data;

import com.theracraft.core.Main;
import org.bukkit.entity.Player;

public class rank_manager {

    setup s = Main.getInstance().getSetup();
    data_handler dh = new data_handler();
    time_manager tm = new time_manager();

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
        return rank;
    }
    public void setRank(Player player, String rank) {
        dh.setPDCString(player, "rank", rank);
    }

    public void updateRank(Player player) {
        String rank = getRank(player);
        setRank(player, rank);
        //Need to add in a material loop for setting PDC data.
    }
    public void removeRank(Player player) {
        String rank = getRank(player);
    }
    public boolean checkRank(Player player){
        String rank = getRank(player);
        if(tm.getPlayedTime(player) >= s.getRankPlayTimeRequirements().get(rank)){
            //Do a material check for a player's rank and what they have turned in. The data is stored in PDC.
            return true;
        }
        return false;
    }
}

