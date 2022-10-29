package com.theracraft.core.data;

import com.theracraft.core.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class data_handler {
    /*
    This class is purely to make handling and accessing the stored data within entities easier.
    Use the command "/data get entity [PLAYER]" to check PDC in-game.
     */
    private final Main instance = Main.getInstance();

    //Integers
    public boolean hasPDCInteger(Player player, String key) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        if (pdc.get(new NamespacedKey(instance, key), PersistentDataType.INTEGER) != null) {
            return pdc.has(new NamespacedKey(instance, key));
        }
        return false;
    }
    public Integer getPDCInteger(Player player, String key){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        return pdc.get(new NamespacedKey(instance, key), PersistentDataType.INTEGER);
    }
    public void setPDCInteger(Player player, String key, Integer value){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(new NamespacedKey(instance, key), PersistentDataType.INTEGER, value);
    }

    //Longs
    public boolean hasPDCLong(Player player, String key){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        if(pdc.get(new NamespacedKey(instance, key), PersistentDataType.LONG) != null){
            return pdc.has(new NamespacedKey(instance, key));
        }
        return false;
    }
    public Long getPDCLong(Player player, String key){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        return pdc.get(new NamespacedKey(instance, key), PersistentDataType.LONG);
    }
    public void setPDCLong(Player player, String key, Long value){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(new NamespacedKey(instance, key), PersistentDataType.LONG, value);
    }

    //Strings
    public boolean hasPDCString(Player player, String key){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        if(pdc.get(new NamespacedKey(instance, key), PersistentDataType.STRING) != null){
            return pdc.has(new NamespacedKey(instance, key));
        }
        return false;
    }
    public String getPDCString(Player player, String key){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        return pdc.get(new NamespacedKey(instance, key), PersistentDataType.STRING);
    }
    public void setPDCString(Player player, String key, String value){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(new NamespacedKey(instance, key), PersistentDataType.STRING, value);
    }

    //Remove
    public void removePDC(Player player, String key){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.remove(new NamespacedKey(Main.getInstance(), key));
    }
}
