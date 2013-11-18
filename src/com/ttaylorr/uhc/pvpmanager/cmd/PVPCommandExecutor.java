package com.ttaylorr.uhc.pvpmanager.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ttaylorr.uhc.pvpmanager.PVPManager;
import com.ttaylorr.uhc.pvpmanager.util.Countdown;
import com.ttaylorr.uhc.pvpmanager.util.CountdownType;

public class PVPCommandExecutor implements CommandExecutor {

	private PVPManager plugin;

	public PVPCommandExecutor(PVPManager plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length == 0) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Bukkit.getScheduler().runTaskLater(PVPManager.getInstance(), new Countdown(player, CountdownType.ENTER, 3), 0L);
				System.out.println("World guard name: " + plugin.getWorldGuard().getName());
				return true;
			} else {
				sender.sendMessage("You must be a player to do this!");
			}
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("enter")) {
				Player player = (Player) sender;
				Bukkit.getScheduler().runTaskLater(PVPManager.getInstance(), new Countdown(player, CountdownType.ENTER, 3), 0L);
				return true;
			} else if (args[0].equalsIgnoreCase("exit")) {
				Player player = (Player) sender;
				Bukkit.getScheduler().runTaskLater(PVPManager.getInstance(), new Countdown(player, CountdownType.EXIT, 3), 0L);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

		return false;
	}

}
