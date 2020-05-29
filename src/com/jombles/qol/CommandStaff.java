package com.jombles.qol;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandStaff implements CommandExecutor {

    private Main plugin;
    List<String> staffNames;

    public CommandStaff(Main main){
        this.plugin = main;

        staffNames = this.plugin.getConfig().getStringList("staffList");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (sender instanceof Player){
            Player player = (Player) sender;

            for (String i : staffNames){
                player.sendMessage(this.plugin.getConfig().getString("staffMembers." + i + ".discord"));
            }
        }

        return true;
    }
}
