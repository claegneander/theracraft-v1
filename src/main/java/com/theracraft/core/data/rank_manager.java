package com.theracraft.core.data;

import com.theracraft.core.Main;
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
            //Add a for loop to check for each possible rank and its permission, and if they have it, give them that rank.
        } else {
            for(int i = 0; i < s.getRanks().size(); i++){
                if(player.hasPermission("theracraft.ranks." + s.getRanks().get(i))){
                    rank = s.getRanks().get(i);
                    dh.setPDCString(player, "rank", rank);
                }
            }

        }
        return rank;
    }
    //This will be used to make sure everyone has the correct data for that rank. i.e. put in pdc for playtime and stuff.
    public void updateRank(Player player){

    }
    //Method for confirming they have all the requirements to rank up.
    public boolean checkRankUpStatus(Player player){
        return true;
    }
}
//A way for us to calculate ticks to time values.
//https://mapmaking.fr/tick/
