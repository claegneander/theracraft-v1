package com.theracraft.core.data;

import com.theracraft.core.Main;
import com.theracraft.core.data.objects.rank;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class setup {
    FileConfiguration fc;
    private final List<rank> rankMap;

    //Our setup instance will create a rank object of for each rank, which we should be able to access.
    public setup(Main plugin){
        fc = Main.getInstance().getConfig();
        rankMap = new ArrayList<>();
        List<String> ranks = new ArrayList<>(Objects.requireNonNull(fc.getConfigurationSection("ranks")).getKeys(false));
        for(String s : ranks){
            String display = fc.getString("ranks." + s + ".display");
            long playTime = fc.getLong("ranks." + s + ".playtime");
            HashMap<Material, Integer> map = new HashMap<>();
            try{
                Material tempMaterial;
                int tempInt;
                for(String m : Objects.requireNonNull(fc.getConfigurationSection("ranks." + s + ".materials")).getKeys(false)){
                    if(m != null) {
                        tempMaterial = Material.matchMaterial(m);
                        tempInt = fc.getInt("ranks." + s + ".materials." + m);
                        if(tempMaterial != null) {
                            map.put(tempMaterial, tempInt);
                        }
                    }else{
                        Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "WARN: Material type == null; in config.yml.");
                    }
                }
            }catch(Exception e){
                Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Missing material data in config.yml for: " + ChatColor.RED + s);
            }
            rank r = new rank(s, display, playTime, map);
            rankMap.add(r);
        }

    }
    public List<rank> getRankMap(){
        return this.rankMap;
    }
}
