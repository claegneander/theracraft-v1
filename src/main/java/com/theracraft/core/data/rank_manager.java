package com.theracraft.core.data;

import com.theracraft.core.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class rank_manager {

    setup s = Main.getInstance().getSetup();
    data_handler dh = new data_handler();

    public rank_manager(){

    }
    public String getRank(Player player) {
        String rank = s.getRanks().get(0);
        if (dh.hasPDCInteger(player, "rank")) {
            rank = dh.getPDCString(player, "rank");
        } else {
            for(int i = 0; i < s.getRanks().size(); i++){
                if(player.hasPermission("theracraft.ranks." + s.getRanks().get(i))){
                    rank = s.getRanks().get(i);
                    setRank(player, rank);
                    updateRank(player);
                }
            }
        }
        return rank;
    }
    public void setRank(Player player, String rank){
        dh.setPDCString(player, "rank", rank);
    }
    //Used to set the initial values of someone's rank; do not call if they are the already were setup or you will erase progress.
    public void updateRank(Player player){
        String rank = getRank(player);
        String display = s.getRankDisplayNames().get(rank);
        long time = s.getRankPlayTimeRequirements().get(rank);

        dh.setPDCString(player, "rank_display", display);
        dh.setPDCLong(player, "rank_time_requirement", time);

        for(Material key: s.getRankMaterialRequirements().get(rank).keySet()){
            int value = s.getRankMaterialRequirements().get(rank).get(key);
            dh.setPDCInteger(player, String.valueOf(key), value);
            System.out.println("Set PDC data for " + player.getName() + " with the key " + key.toString() + " and value of " + value);
        }
    }
    //Method for confirming they have all the requirements to rank up.
    public boolean checkRankUpStatus(Player player){
        return true;
    }
}
//A way for us to calculate ticks to time values.
//https://mapmaking.fr/tick/
