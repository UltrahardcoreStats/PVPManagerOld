package com.ttaylorr.uhc.pvp.events;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class PlayerDeathHandler implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (event.getEntity() != null) {
			if(event.getEntity().hasMetadata("UHC_PVP")) {
				event.setDroppedExp(0);
				event.getDrops().clear();
				
				event.getDrops().add(new Potion(PotionType.INSTANT_HEAL,2).splash().toItemStack(1));
				
				Bukkit.getWorld(event.getEntity().getWorld().getUID()).playSound(event.getEntity().getLocation(), Sound.WITHER_HURT, 1.0f, 1.0f);				
			}
		}
	}

}
