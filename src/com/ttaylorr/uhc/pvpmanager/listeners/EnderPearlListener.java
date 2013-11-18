package com.ttaylorr.uhc.pvpmanager.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;

public class EnderPearlListener implements Listener {

	private int PEARL_MAX_HEIGHT;
	private boolean GIVE_BACK_PEARL;

	public EnderPearlListener(int PEARL_MAX_HEIGHT, boolean GIVE_BACK_PEARL) {
		this.PEARL_MAX_HEIGHT = PEARL_MAX_HEIGHT;
		this.GIVE_BACK_PEARL = GIVE_BACK_PEARL;
	}

	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		if (event.getCause() == TeleportCause.ENDER_PEARL) {
			if (event.getTo().getY() >= PEARL_MAX_HEIGHT) {
				if (GIVE_BACK_PEARL) {
					event.getPlayer().getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
				}
				event.setCancelled(true);
			}
		}
	}

}
