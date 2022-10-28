package com.theracraft.core.events;

import com.theracraft.core.data.rank_manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class player_join implements Listener {

    rank_manager rm = new rank_manager();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        rm.updateRank(player);
    }
}
