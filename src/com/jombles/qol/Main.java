package com.jombles.qol;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable(){

        CooldownManager cm = new CooldownManager(this.getConfig().getLong("cooldown"));

        boolean apEnabled = this.getConfig().getBoolean("enableAtPlayer");
        boolean linksEnabled = this.getConfig().getBoolean("enableLinks");

        String disc = this.getConfig().getString("linkValues.discord");
        String web = this.getConfig().getString("linkValues.website");
        String donate = this.getConfig().getString("linkValues.donate");

        this.getCommand("ap").setExecutor(new CommandAt(apEnabled, cm));
        this.getCommand("links").setExecutor(new CommandLinks(linksEnabled, disc, web, donate));

        System.out.println("[QOLCommands] QOL Commands enabled");
    }

    @Override
    public void onDisable(){
        System.out.println("[QOLCommands] QOL Commands disabled, goodbye!");
    }
}
