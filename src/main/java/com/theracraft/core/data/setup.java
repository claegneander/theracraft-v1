package com.theracraft.core.data;

import com.theracraft.core.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class setup {
    //The purpose of this class is to read the config file and load the settings into memory, so they can easily be accessed by the plugin.
    Main instance = Main.getInstance();

    private final List<String> ranks;
    private final HashMap<String, String> rank_display_names;
    private final HashMap<String, Long> rank_play_time_requirements;
    private final HashMap<String, Map<Material, Integer>> rank_material_requirements;


    public setup(){
        ranks = new ArrayList<>();
        rank_display_names = new HashMap<>();
        rank_play_time_requirements = new HashMap<>();
        rank_material_requirements = new HashMap<>();
        getRanksFromConfig();
        getDisplayNamesFromRanks();
        getPlayTimeFromRanks();
        getMaterialsRequirementsFromRanks();
    }
    private void getRanksFromConfig(){
        FileConfiguration fc = instance.getConfig();
        ranks.addAll(Objects.requireNonNull(fc.getConfigurationSection("ranks")).getKeys(false));
    }
    private void getDisplayNamesFromRanks(){
        FileConfiguration fc = instance.getConfig();
        for (String rank : ranks) {
            rank_display_names.put(rank, Objects.requireNonNull(fc.getString("ranks." + rank + ".display")));
        }
    }
    private void getPlayTimeFromRanks(){
        FileConfiguration fc = instance.getConfig();
        for (String rank : ranks) {
            rank_play_time_requirements.put(rank, fc.getLong("ranks." + rank + ".playtime"));
        }
    }
    private void getMaterialsRequirementsFromRanks() {
        FileConfiguration fc = instance.getConfig();
        for (String rank : ranks) {
            try {
                Map<Material, Integer> tempMap = new HashMap<>();
                Material tempMaterial;
                int tempInt;
                for (String material : Objects.requireNonNull(fc.getConfigurationSection("ranks." + rank + ".materials")).getKeys(false)) {
                    tempMaterial = Material.matchMaterial(material);
                    tempInt = fc.getInt("ranks." + rank + ".materials." + material);
                    if(tempMaterial != null) {
                        tempMap.put(tempMaterial, tempInt);
                        rank_material_requirements.put(rank, tempMap);
                    }else{
                        instance.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "WARN: Material type == null; in config.yml.");
                    }
                }
            } catch (NullPointerException e) {

            }
        }
    }
    public List<String> getRanks(){
        return ranks;
    }
    public HashMap<String, String> getRankDisplayNames(){
        return rank_display_names;
    }
    public HashMap<String, Long> getRankPlayTimeRequirements(){
        return rank_play_time_requirements;
    }
    public HashMap<String, Map<Material, Integer>> getRankMaterialRequirements(){
        return rank_material_requirements;
    }
}
