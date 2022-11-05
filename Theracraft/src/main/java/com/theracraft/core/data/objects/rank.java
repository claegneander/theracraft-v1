package com.theracraft.core.data.objects;

import org.bukkit.Material;

import java.util.HashMap;

public class rank {

    public String rank;
    public String display;
    long playtime;
    HashMap<Material, Integer> map;
    public rank(String r, String d, long p, HashMap<Material, Integer> m){
        this.rank = r;
        this.display = d;
        this.playtime = p;
        this.map = m;
    }
    public String getRank(){
        return this.rank;
    }
    public void setRank(String r){
        this.rank = r;
    }
    public String getDisplay(){
        return this.display;
    }
    public void setDisplay(String d){
        this.display = d;
    }
    public long getPlaytime(){
        return this.playtime;
    }
    public void setPlaytime(long p){
        this.playtime = p;
    }
    public HashMap<Material, Integer> getMap(){
        return this.map;
    }
    public void setMap(HashMap<Material, Integer> m){
        this.map = m;
    }
}
