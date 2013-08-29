package com.ttaylorr.uhc.pvp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LeaveTimer implements Runnable {

	Player player;
	int time;

	public LeaveTimer(Player player, int time) {
		this.player = player;
		this.time = time;
	}

	@Override
	public void run() {
		if (player != null) {
			if (time != 0) {
				if (time != 1) {
					player.sendMessage(ChatColor.GOLD + "[PVP] - " + ChatColor.GREEN + time + ChatColor.BLUE + " seconds until exit");
				} else {
					player.sendMessage(ChatColor.GOLD + "[PVP] - " + ChatColor.GREEN + time + ChatColor.BLUE + " second until exit");
				}

				this.player.playSound(this.player.getLocation(), Sound.NOTE_PIANO, 1.0f, 1.19f);
				
				Bukkit.getScheduler().runTaskLater(PVPManager.getInstance(), new LeaveTimer(this.player, this.time - 1), 20L);
			} else {
				player.getInventory().clear();
				player.getInventory().setArmorContents(null);
				
				player.teleport(Bukkit.getWorld("spawn").getSpawnLocation());

				this.player.playSound(this.player.getLocation(), Sound.NOTE_PIANO, 1.0f, 1.59f);
				
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.sendMessage(ChatColor.GOLD + "[PVP] - " + ChatColor.GREEN + ChatColor.stripColor(player.getName()) + ChatColor.BLUE + " has left PVP!");
				}
			}
		}
	}
}
