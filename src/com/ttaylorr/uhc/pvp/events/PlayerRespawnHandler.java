package com.ttaylorr.uhc.pvp.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.ttaylorr.uhc.pvp.PVPManager;
import com.ttaylorr.uhc.pvp.RespawnTimer;

public class PlayerRespawnHandler implements Listener {

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if (event.getPlayer() != null) {
			if(event.getPlayer().hasMetadata("UHC_PVP")) {
				final Player player = event.getPlayer();

				Bukkit.getScheduler().runTaskLater(PVPManager.getInstance(), new Runnable() {
					public void run() {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 127), true);
						player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 127), true);
						player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60, 127), true);
						player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 127), true);
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, -127), true);
					}
				}, 1L);

				Bukkit.getScheduler().runTaskAsynchronously(PVPManager.getInstance(), new RespawnTimer(event.getPlayer(), 3, true));
				
			}
		}
	}
}
