package com.theracraft.core.events;

import com.theracraft.core.Main;
import com.theracraft.core.data.data_handler;
import com.theracraft.core.data.rank_manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class player_join implements Listener {
    data_handler dh = new data_handler();
    rank_manager rm = Main.getInstance().getRankManager();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(!dh.hasPDCString(player, "rank")){
            rm.setRank(player, rm.getRank(player));
        }
    }
}
