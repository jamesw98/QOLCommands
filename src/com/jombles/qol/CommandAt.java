package com.jombles.qol;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

/**
 * usage:
 *  /ap <receiverName>
 *
 * this command mimics typing "@name" in a messaging app (discord, groupme, etc)
 * the player represented by receiver name will get an noise sent to their client and a text message letting them know
 * that the person executing the command wants to talk with them
 */
public class CommandAt implements CommandExecutor {

    private boolean enabled;
    private CooldownManager cm;

    public CommandAt(boolean enabled, CooldownManager cm){
        super();

        this.enabled = enabled;
        this.cm = cm;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // checks if the command is enabled
            if (!enabled){
                player.sendMessage(ChatColor.GREEN + "This QOL Command is not enabled");
                player.sendMessage(ChatColor.GREEN + "If you believe it should be, contact you server administrator");
                return false;
            }

            // makes sure correct number of arguments are used
            if (args.length != 1){
                player.sendMessage(ChatColor.GREEN + "Correct Usage: " + ChatColor.DARK_GREEN + "/ap <name>");
                return false;
            }

            // makes a player out of argument 0 (even is the player doesn't exist)
            String playerName = args[0];
            Player toSendTo = Bukkit.getPlayer(playerName);

            // if the player the sender wants to @ is
            if (toSendTo != null){

                if (cm.isInCooldown(player.getDisplayName())){
                    long currentTime = System.currentTimeMillis() - cm.checkPlayer(player.getDisplayName());
                    
                    if (TimeUnit.MILLISECONDS.toSeconds(currentTime) >= cm.getDef()){
                        cm.removePlayer(player.getDisplayName());
                    }
                    else {
                        player.sendMessage(ChatColor.GREEN + "Please wait before you can use this command again");
                        return false;
                    }
                }

                // if the player is also the sender
                if (player.getDisplayName().equals(toSendTo.getDisplayName())){
                    toSendTo.playSound(toSendTo.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                    player.sendMessage(ChatColor.GREEN + "You pinged yourself!");
                    cm.addPlayer(player.getDisplayName(), System.currentTimeMillis());
                    return true;
                }
                // if the player is another player on the server
                else {
                    // sends the sender a confirmation message
                    player.sendMessage(ChatColor.GREEN + "Ping Sent!");
                    // sends the receiver a ping and text message
                    toSendTo.playSound(toSendTo.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                    toSendTo.sendMessage(ChatColor.DARK_GREEN + player.getDisplayName() + ChatColor.GREEN + " wants to talk to you!");

                    cm.addPlayer(player.getDisplayName(), System.currentTimeMillis());
                }
            }
            else {
                // if the player they want to send a ping to doesn't exist this message will be sent to the sender
                player.sendMessage(ChatColor.GREEN + "Player: " + ChatColor.DARK_GREEN + args[0] + ChatColor.GREEN + " does not exist!");
                return false;
            }
        }
        return true;
    }
}
