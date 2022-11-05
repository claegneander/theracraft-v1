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

    public String formatTime(long time) {
        time = time / 20;
        long days = TimeUnit.SECONDS.toDays(time);
        long hours = TimeUnit.SECONDS.toHours(time) - TimeUnit.DAYS.toHours(days);
        long minutes = TimeUnit.SECONDS.toMinutes(time) - TimeUnit.DAYS.toMinutes(days) + TimeUnit.HOURS.toMinutes(hours);

        StringBuilder builder = new StringBuilder();
        if (days == 1L) {
            builder.append("1 day, ");
        } else if (days != 0L) {
            builder.append(days).append(" days, ");
        }
        if (hours == 1L) {
            builder.append("1 hour and ");
        } else {
            builder.append(hours).append(" hours and ");
        }
        if (minutes != 1L && (minutes != 0L || hours != 0L || days != 0L)) {
            builder.append(minutes).append(" minutes");
        } else {
            builder.append("1 minute");
        }
        return builder.toString();
    }
}
