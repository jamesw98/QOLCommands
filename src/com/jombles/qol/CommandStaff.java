package com.jombles.qol;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class CommandStaff implements CommandExecutor {

    private Main plugin;
    private List<String> staffNames;
    private boolean enabled;

    public CommandStaff(boolean enabled, Main main){
        this.plugin = main;

        staffNames = new ArrayList<String>();

        staffNames = this.plugin.getConfig().getStringList("staffList");
        this.enabled = enabled;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            Inventory staff = Bukkit.getServer().createInventory(p, 9, "Staff Members");

            for (int i = 0; i < staffNames.size(); i++){
                String currentName = staffNames.get(i);

                String currentItem = this.plugin.getConfig().getString("staffMembers." + currentName + ".item");
                String currentRank = this.plugin.getConfig().getString("staffMembers." + currentName + ".rank");
                String currentIgn = this.plugin.getConfig().getString("staffMembers." + currentName + ".ign");
                String currentDisc = this.plugin.getConfig().getString("staffMembers." + currentName + ".discord");

                System.out.println(currentItem);
                System.out.println(Material.getMaterial("minecraft:coal_ore"));

                ItemStack ref1 = new ItemStack(Material.getMaterial(currentItem));
                ItemMeta metaref1 = ref1.getItemMeta();
                ArrayList<String> lore= new ArrayList<String>();

                lore.add(" ");
                lore.add("§aRank: §2" + currentIgn);
                lore.add(" ");
                lore.add("§atest.net/help");

                metaref1.setLore(lore);
                metaref1.setDisplayName("§2§l" + currentIgn);

                ref1.setItemMeta(metaref1);
                staff.setItem(5, ref1);
            }

            //Here opens the inventory
            p.openInventory(staff);
        }
        return false;
    }
}

