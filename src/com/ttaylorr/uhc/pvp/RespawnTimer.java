package com.ttaylorr.uhc.pvp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class RespawnTimer implements Runnable {

	Player player;
	int time;
	boolean respawning;

	public RespawnTimer(Player player, int time, boolean respawning) {
		this.player = player;
		this.time = time;
		this.respawning = respawning;
	}

	@Override
	public void run() {
		if (player != null) {
			if (time != 0) {
				if (time != 1) {
					player.sendMessage(ChatColor.GOLD + "[PVP] - " + ChatColor.GREEN + time + ChatColor.BLUE + " seconds until " + (respawning ? "respawn" : "spawn"));
				} else {
					player.sendMessage(ChatColor.GOLD + "[PVP] - " + ChatColor.GREEN + time + ChatColor.BLUE + " second until " + (respawning ? "respawn" : "spawn"));
				}

				this.player.playSound(this.player.getLocation(), Sound.NOTE_PIANO, 1.0f, 1.19f);

				Bukkit.getScheduler().runTaskLater(PVPManager.getInstance(), new RespawnTimer(this.player, this.time - 1, this.respawning), 20L);
			} else {

				ArmorKit kit = new ArmorKit();

				kit.applyKit(this.player);

				Location l = null;
				while (l == null) {
					l = kit.getRandomLocation();

					if (l != null) {
						this.player.teleport(l);
						this.player.sendMessage(ChatColor.GOLD + "[PVP] - " + ChatColor.BLUE + "tried to teleport you to " + ChatColor.GREEN + l.getX() + ", " + l.getY() + ", " + l.getZ());
					}
				}

				// this.player.teleport(kit.getRandomLocation());

				if (player.getGameMode() != GameMode.SURVIVAL) this.player.setGameMode(GameMode.SURVIVAL);

				this.player.playSound(this.player.getLocation(), Sound.NOTE_PIANO, 1.0f, 1.59f);
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.sendMessage(ChatColor.GOLD + "[PVP] - " + ChatColor.GREEN + ChatColor.stripColor(player.getName()) + ChatColor.BLUE + " has entered PVP!");
				}
			}
		}
	}

}
