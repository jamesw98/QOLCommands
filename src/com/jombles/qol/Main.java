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
        boolean staffEnabled = this.getConfig().getBoolean("enableStaff");

        this.getCommand("poke").setExecutor(new CommandAt(apEnabled, cm, getInstance()));
        this.getCommand("links").setExecutor(new CommandLinks(linksEnabled, getInstance()));
        this.getCommand("qol").setExecutor(new CommandHelp());
        this.getCommand("staff").setExecutor(new CommandStaff(staffEnabled, getInstance()));

        this.getCommand("discord").setExecutor(new CommandInfo("Discord Link: ", this.getConfig().getString("linkValues.discord")));
        this.getCommand("website").setExecutor(new CommandInfo("Website Link: ", this.getConfig().getString("linkValues.website")));
        this.getCommand("donate").setExecutor(new CommandInfo("Donate Link: ", this.getConfig().getString("linkValues.donate")));
        this.getCommand("tutorial").setExecutor(new CommandInfo("Tutorial Link: ", this.getConfig().getString("linkValues.youtube")));

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
