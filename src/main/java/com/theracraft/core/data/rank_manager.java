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
        } else {
            for(int i = 0; i < s.getRanks().size(); i++){
                if(player.hasPermission("theracraft.ranks." + s.getRanks().get(i))){
                    rank = s.getRanks().get(i);
                    setRank(player, rank);
                }
            }
        }
        return rank;
    }
    public void setRank(Player player, String rank){
        dh.setPDCString(player, "rank", rank);
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
