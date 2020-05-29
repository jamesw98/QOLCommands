package com.jombles.qol;

import java.util.HashMap;

public class CooldownManager {

    private long DEFAULT_COOLDOWN;
    private HashMap<String, Long> playersOnCooldown;

    public CooldownManager(long dc){
        DEFAULT_COOLDOWN = dc;
        playersOnCooldown = new HashMap<>();
    }

    public void addPlayer(String name, Long cooldownStart){
        playersOnCooldown.put(name, cooldownStart);
    }

    public long checkPlayer(String name){
        if (playersOnCooldown.containsKey(name)){
            return playersOnCooldown.get(name);
        }
        else{
            return -1;
        }
    }

    public void removePlayer(String name){
        playersOnCooldown.remove(name);
    }

    public boolean isInCooldown(String name){
        return playersOnCooldown.containsKey(name);
    }

    public long getDef(){
        return DEFAULT_COOLDOWN;
    }
}
