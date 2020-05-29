package com.jombles.qol;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHelp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (sender instanceof Player){
            Player player = (Player) sender;

            player.sendMessage(ChatColor.DARK_GREEN + "QOL Commands " + ChatColor.GREEN + "- by jamesw98");
            player.sendMessage(ChatColor.DARK_GREEN + "/qol " + ChatColor.GREEN + "- what you are reading right now");
            player.sendMessage(ChatColor.DARK_GREEN + "/poke <name> " + ChatColor.GREEN + "- pings <name> with sound and text");
            player.sendMessage(ChatColor.DARK_GREEN + "/links " + ChatColor.GREEN + "- shows helpful links for the server");
        }
        return true;
    }
}
