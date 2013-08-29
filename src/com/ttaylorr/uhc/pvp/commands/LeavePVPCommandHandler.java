package com.ttaylorr.uhc.pvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ttaylorr.uhc.pvp.LeaveTimer;
import com.ttaylorr.uhc.pvp.PVPManager;

public class LeavePVPCommandHandler implements CommandExecutor {

	PVPManager manager;

	public LeavePVPCommandHandler(PVPManager manager) {
		this.manager = manager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender != null) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(player.hasMetadata("UHC_PVP")) {
					player.removeMetadata("UHC_PVP", PVPManager.getInstance());

					Bukkit.getScheduler().runTaskAsynchronously(PVPManager.getInstance(), new LeaveTimer(player,3));
					
				} else {
					player.sendMessage(ChatColor.GOLD + "[PVP] - " + ChatColor.BLUE + "You must be in PVP to leave it.");
				}
				
				return true;
			}
		} else {
			return false;
		}
		return false;

	}

}
