package com.ttaylorr.uhc.pvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import com.ttaylorr.uhc.pvp.PVPManager;
import com.ttaylorr.uhc.pvp.RespawnTimer;

public class PVPCommandHandler implements CommandExecutor {

	PVPManager manager;

	public PVPCommandHandler(PVPManager manager) {
		this.manager = manager;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (sender != null) {
				Player player = (Player) sender;

				if(player.hasMetadata("UHC_PVP")) {
					player.sendMessage(ChatColor.GOLD + "[PVP] - " + ChatColor.BLUE + "You are already in PVP!");
				} else {
					player.setMetadata("UHC_PVP", new FixedMetadataValue(PVPManager.getInstance(), true));

					Bukkit.getScheduler().runTaskAsynchronously(PVPManager.getInstance(), new RespawnTimer(player, 3, false));		

				}
				
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

}
