package com.jombles.qol;

import org.bukkit.command.CommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CommandLinks implements CommandExecutor {

    private boolean enabled;

    private String discord;
    private String website;
    private String donate;

    private Main plugin;

    public CommandLinks(boolean enabled, Main main){
        super();

        this.enabled = enabled;

        this.plugin = main;

        this.discord = plugin.getConfig().getString("linkValues.discord");
        this.website = plugin.getConfig().getString("linkValues.website");
        this.donate = plugin.getConfig().getString("linkValues.donate");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;

            // checks if the command is enabled
            if (!enabled){
                player.sendMessage(ChatColor.GREEN + "This QOL Command is not enabled");
                player.sendMessage(ChatColor.GREEN + "If you believe it should be, contact you server administrator");
                return false;
            }

            player.sendMessage(ChatColor.GREEN + "Helpful Server Links:");
            player.sendMessage(ChatColor.GREEN + "Discord: " + ChatColor.DARK_GREEN + this.discord);
            player.sendMessage(ChatColor.GREEN + "Website: " + ChatColor.DARK_GREEN + this.website);
            player.sendMessage(ChatColor.GREEN + "Donate: " + ChatColor.DARK_GREEN + this.donate);
        }

        return true;
    }
}
