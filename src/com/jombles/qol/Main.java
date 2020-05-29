package com.jombles.qol;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable(){

        this.instance = this;

        CooldownManager cm = new CooldownManager(this.getConfig().getLong("cooldown"));

        this.saveDefaultConfig();

        boolean apEnabled = this.getConfig().getBoolean("enableAtPlayer");
        boolean linksEnabled = this.getConfig().getBoolean("enableLinks");

        this.getCommand("poke").setExecutor(new CommandAt(apEnabled, cm, getInstance()));
        this.getCommand("links").setExecutor(new CommandLinks(linksEnabled, getInstance()));
        this.getCommand("qol").setExecutor(new CommandHelp());

        System.out.println("[QOLCommands] QOL Commands enabled");
    }

    @Override
    public void onDisable(){
        System.out.println("[QOLCommands] QOL Commands disabled, goodbye!");
    }

    public Main getInstance(){
        return instance;
    }
}
