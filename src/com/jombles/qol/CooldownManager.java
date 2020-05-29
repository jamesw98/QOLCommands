package com.jombles.qol;

import java.util.HashMap;

/**
 * cooldown manager for commandAt
 */
public class CooldownManager {

    private long DEFAULT_COOLDOWN;
    private HashMap<String, Long> playersOnCooldown;

    /**
     * constructor
     * @param dc default cooldown length
     */
    public CooldownManager(long dc){
        DEFAULT_COOLDOWN = dc;
        playersOnCooldown = new HashMap<>();
    }

    /**
     * gets the player of name to get their starting time
     * @param name the name of the player
     * @return their starting time for the cooldown
     */
    public long checkPlayer(String name){
        if (playersOnCooldown.containsKey(name)){
            return playersOnCooldown.get(name);
        }
        else{
            return -1;
        }
    }

    public void addPlayer(String name, Long cooldownStart){
        playersOnCooldown.put(name, cooldownStart);
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
