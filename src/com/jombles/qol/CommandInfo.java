package com.jombles.qol;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandInfo implements CommandExecutor {

    private String message;
    private String link;

    public CommandInfo(String message, String link){
        this.message = message;
        this.link = link;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage(ChatColor.GREEN + message + ChatColor.DARK_GREEN + link);
        }
        return true;
    }
}
