package com.theracraft.core.data;

import com.theracraft.core.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class data_handler {
    /*
    This class is purely to make handling and accessing the stored data within entities easier.
     */
    private final Main instance = Main.getInstance();

    public void setPDCInteger(Player player, String key, Integer value){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(new NamespacedKey(instance, key), PersistentDataType.INTEGER, value);
    }
    public void setPDCInteger(Entity entity, String key, Integer value){
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        pdc.set(new NamespacedKey(instance, key), PersistentDataType.INTEGER, value);
    }

    public void setPDCString(Player player, String key, String value){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(new NamespacedKey(instance, key), PersistentDataType.STRING, value);
    }

    public Integer getPDCInteger(Player player, String key){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        return pdc.get(new NamespacedKey(instance, key), PersistentDataType.INTEGER);
    }
    public Integer getPDCInteger(Entity entity, String key){
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        return pdc.get(new NamespacedKey(instance, key), PersistentDataType.INTEGER);
    }
    public String getPDCString(Player player, String key){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        return pdc.get(new NamespacedKey(instance, key), PersistentDataType.STRING);
    }

    public boolean hasPDCInteger(Player player, String key){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        if(pdc.get(new NamespacedKey(instance, key), PersistentDataType.INTEGER) != null){
            return pdc.has(new NamespacedKey(instance, key));
        }
        return false;
    }
    public boolean hasPDCInteger(Entity entity, String key){
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        if(pdc.get(new NamespacedKey(instance, key), PersistentDataType.INTEGER) != null){
            return pdc.has(new NamespacedKey(instance, key));
        }
        return false;
    }
    public boolean hasPDCString(Player player, String key){
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        if(pdc.get(new NamespacedKey(instance, key), PersistentDataType.STRING) != null){
            return pdc.has(new NamespacedKey(instance, key));
        }
        return false;
    }
}
