package com.ttaylorr.uhc.pvpmanager.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ttaylorr.uhc.pvpmanager.PVPManager;

public class Countdown implements Runnable {

	Player player;
	CountdownType type;
	int timeLeft = -1;

	public Countdown(Player player, CountdownType type) {
		this.player = player;
		this.timeLeft = 3;
		this.type = type;
	}

	public Countdown(Player player, CountdownType type, int timeLeft) {
		this.player = player;
		this.timeLeft = timeLeft;
		this.type = type;
	}

	@Override
	public void run() {
		if (timeLeft > 0) {
			player.sendMessage("[PVP] - " + timeLeft + " " + (timeLeft == 1 ? "second" : "seconds") + " until " + this.type.getMessage() + "!");
			Bukkit.getScheduler().runTaskLater(PVPManager.getInstance(), new Countdown(this.player, this.type, this.timeLeft - 1), 20L);
		} else {
			if (this.getType() == CountdownType.ENTER || this.getType() == CountdownType.RESPAWN) {
				PVPUtils pu = new PVPUtils((new PVPManager()).getWorldGuard());
				pu.spawnPlayer(this.player, this.player.getLocation().getWorld());
			} else if (this.getType() == CountdownType.EXIT) {

			}
		}
	}

	public Player getPlayer() {
		return this.player;
	}

	public int timeLeft() {
		return this.timeLeft;
	}

	public CountdownType getType() {
		return this.type;
	}
}
