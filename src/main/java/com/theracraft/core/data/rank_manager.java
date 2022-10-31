package com.theracraft.core.data;

import com.theracraft.core.Main;
import com.theracraft.core.misc.util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class rank_manager {
    private final setup s = Main.getInstance().getSetup();
    private final data_handler dh = new data_handler();
    private final time_manager tm = new time_manager();

    public String getRank(Player player){
        String rank = s.getRankMap().get(0).getRank();
        if(dh.hasPDCString(player, "rank")){
            rank = dh.getPDCString(player, "rank");
        }else {
            boolean isOP = player.isOp();
            if(isOP){
                player.setOp(false);
            }
            try {
                for (int i = 0; i < s.getRankMap().size(); i++) {
                    if (player.hasPermission("theracraft.ranks." + s.getRankMap().get(i).rank)) {
                        rank = s.getRankMap().get(i).getRank();
                    }
                }
            } catch (Exception e) {
                Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error 001: Rank not found.");
            }
            if(isOP){
                player.setOp(true);
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
        if(dh.hasPDCString(player, "rank")) {
            dh.removePDC(player, "rank");
            removeMaterialData(player);
        }
    }
    //This method will check if there is a rank available above the player's rank, then promote them if possible.
    public void updateRank(Player player) {
        String currentRank = getRank(player);
        String newRank = currentRank;
        for(String rank : s.getRankNames()){
            if(rank.equals(currentRank)){
                int x = s.getRankNames().indexOf(rank) + 1;
                if(x < s.getRankNames().size()){
                    newRank = s.getRankNames().get(x);
                }
            }
        }
        if(!Objects.equals(newRank, currentRank)) {
            if(isPromotable(player)){
                try {
                    removeRank(player);
                    setRank(player, newRank);
                    removeMaterialData(player);
                    addMaterialData(player);
                } catch (Exception e) {
                    e.printStackTrace();
                }
           }else {
                player.sendMessage(ChatColor.DARK_RED + "You do not meet all the requirements to rank up.");
            }
        }else{
            player.sendMessage(ChatColor.DARK_RED + "You are already at the highest rank.");
        }
    }
    public void checkRank(Player player){
        String currentRank = getRank(player);
        String newRank = currentRank;
        for(String rank : s.getRankNames()){
            if(rank.equals(currentRank)){
                int x = s.getRankNames().indexOf(rank) + 1;
                if(x < s.getRankNames().size()){
                    newRank = s.getRankNames().get(x);
                }
            }
        }
        long playedTime = tm.getPlayedTime(player);
        long requiredTime = 0L;
        for(int i = 0; i < s.getRankMap().size(); i++){
            if(currentRank.equals(s.getRankMap().get(i).getRank())) {
                requiredTime = s.getRankMap().get(i).getPlaytime();
            }
        }
        HashMap<Material, Integer> map = new HashMap<>();
        Material material;
        int value;
        for(int i = 0; i < s.getRankMap().size(); i++) {
            for (Material m : s.getRankMap().get(i).getMap().keySet()){
                if(dh.hasPDCInteger(player, String.valueOf(m))){
                    material = m;
                    value = s.getRankMap().get(i).getMap().get(m);
                    map.put(material, value);
                }
            }
        }
        player.sendMessage(ChatColor.BLUE + "--------------------");
        player.sendMessage(ChatColor.LIGHT_PURPLE + "You are viewing the path to " + ChatColor.DARK_PURPLE + newRank +
                ChatColor.LIGHT_PURPLE + " for " + player.getName() + ".");
        player.sendMessage(ChatColor.BLUE + "--------------------");
        player.sendMessage(ChatColor.GRAY + "Requirements:");

        float timePercentage = (float) (playedTime * 100)/requiredTime;
        int rounded = (int) timePercentage;
        if(tm.checkTime(player, requiredTime)){
            player.sendMessage(ChatColor.AQUA + "Play for " + tm.formatTime(requiredTime) + ": §a✓");
        }else{
            player.sendMessage(ChatColor.AQUA + "Play for " + tm.formatTime(requiredTime) + ":" + ChatColor.BLUE + " (" + rounded + "%)");
        }

        for(Material m : map.keySet()){
            int count = dh.getPDCInteger(player, m.toString());
            if(count == 0) {
                player.sendMessage(ChatColor.AQUA + "Submit " + m + ": " + ChatColor.BLUE + count + " §a✓");
            }else{
                player.sendMessage(ChatColor.AQUA + "Submit " + m + ": " + ChatColor.BLUE + count + " §c✗");
            }
        }
        player.sendMessage(ChatColor.BLUE + "--------------------");
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
    public void submitMaterials(Player player){
        String rank = getRank(player);
        player.sendMessage("1");
        for(int i = 0; i < s.getRankMap().size(); i++){
            if(s.getRankMap().get(i).getRank().equals(rank)){
                player.sendMessage("2");
                Map<Material, Integer> map = s.getRankMap().get(i).getMap();
                for(Material m: map.keySet()){
                    ItemStack itemStack = new ItemStack(m);
                    if(dh.hasPDCInteger(player, m.toString())){
                        player.sendMessage(String.valueOf(m));
                        if(player.getInventory().contains(itemStack)){
                            player.sendMessage("4");
                            int count = map.get(m);
                            int removedAmount = 0;
                            while(count != 0 && player.getInventory().contains(itemStack)){
                                count--;
                                removedAmount++;
                                util.removeItem(player, itemStack, 1);
                            }
                            dh.setPDCInteger(player, m.toString(), count);
                            player.sendMessage("Removed " + removedAmount + " " + itemStack.displayName() + " from inventory.");
                        }
                    }
                }
            }
        }

    }
    public boolean isPromotable(Player player){
        String rank = getRank(player);
        long playedTime = tm.getPlayedTime(player);
        long playtime_req = 0L;
        boolean playTimeMet = false;
        boolean materialReqMet = false;
        int value;
        int count = 0;
        for(int i = 0; i < s.getRankMap().size(); i++){
            if(rank.equals(s.getRankMap().get(i).getRank())) {
                playtime_req = s.getRankMap().get(i).getPlaytime();
            }
        }
        for(int i = 0; i < s.getRankMap().size(); i++) {
            for (Material m : s.getRankMap().get(i).getMap().keySet()){
                if(dh.hasPDCInteger(player, String.valueOf(m))){
                    value = dh.getPDCInteger(player, m.toString());
                    count += value;
                }
            }
        }
        if(count == 0){
            materialReqMet = true;
        }
        if(playedTime > playtime_req){
            playTimeMet = true;
        }
        return playTimeMet && materialReqMet;
    }

}

