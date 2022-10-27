package com.theracraft.core.data;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class time_manager {

    //This will return their online time in ticks; use the format method to make it more readable.
    public long getPlayedTime(Player player){
        return player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
    }
    //This will check if a player has played longer than the specified target.
    public boolean checkTime(Player player, long target){
        long time = getPlayedTime(player);
        return time >= target;
    }
    //Our method for formatting the long value into a readable String.
    public String formatTime(long time){
        long days = TimeUnit.SECONDS.toDays(time);
        long hours = TimeUnit.SECONDS.toHours(time) - (days * 24);
        long minutes = TimeUnit.SECONDS.toMinutes(time) - (hours * 60);
        long seconds = TimeUnit.SECONDS.toSeconds(time) - (minutes * 60);
        return ("Days: " + days + " Hours: " + hours + " Minutes: " + minutes + " Seconds: " + seconds);
    }
}
