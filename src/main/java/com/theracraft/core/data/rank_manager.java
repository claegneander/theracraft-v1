package com.theracraft.core.data;

import com.theracraft.core.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Map;

public class rank_manager {

    private final setup s = Main.getInstance().getSetup();
    private final data_handler dh = new data_handler();

    public String getRank(Player player){
        String rank = s.getRankMap().get(0).getRank();
        if(dh.hasPDCString(player, "rank")){
            rank = dh.getPDCString(player, "rank");
        }else{
            try{
                for(int i = 0; i < s.getRankMap().size(); i++){
                    if(player.hasPermission("theracraft.ranks." + s.getRankMap().get(i).rank)){
                        rank = s.getRankMap().get(i).getRank();
                    }
                }
            }catch(Exception e){
                Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error 001: Rank not found.");
            }
        }
        return rank;
    }
    public void setRank(Player player, String s){
        try{
            dh.setPDCString(player, "rank", s);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void removeRank(Player player){
        if(dh.hasPDCString(player, "rank")){
            dh.removePDC(player, "rank");
        }
    }
    public void addMaterialData(Player player){
        String rank = getRank(player);
        for(int i = 0; i < s.getRankMap().size(); i++){
            if(s.getRankMap().get(i).getRank().equals(rank)){
                Map<Material, Integer> map = s.getRankMap().get(i).getMap();
                for(Material m: map.keySet()){
                    if(!dh.hasPDCInteger(player, String.valueOf(m))) {
                        dh.setPDCInteger(player, String.valueOf(m), map.get(m));
                    }
                }
            }
        }
    }
    public void removeMaterialData(Player player){
        for(Material material : Material.values()){
            if(dh.hasPDCInteger(player, String.valueOf(material))){
                dh.removePDC(player, String.valueOf(material));
            }
        }
    }
    //This method will check if there is a rank available above the player's rank, then promote them if possible.
    public void updateRank(Player player) {
        String currentRank = getRank(player);
        String newRank;
        for(int i = 0; i < s.getRankMap().size(); i++){
            if(currentRank.equals(s.getRankMap().get(i).getRank())){

            }
        }

    }
}

