package com.ttaylorr.uhc.pvp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.ttaylorr.uhc.pvp.commands.LeavePVPCommandHandler;
import com.ttaylorr.uhc.pvp.commands.PVPCommandHandler;
import com.ttaylorr.uhc.pvp.events.PlayerDeathHandler;
import com.ttaylorr.uhc.pvp.events.PlayerRespawnHandler;

public class PVPManager extends JavaPlugin {

	private static PVPManager instance;
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public void onEnable() {
		instance = this;
		
		getCommand("pvp").setExecutor(new PVPCommandHandler(this));
		getCommand("leavepvp").setExecutor(new LeavePVPCommandHandler(this));
		
		Bukkit.getPluginManager().registerEvents(new PlayerRespawnHandler(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeathHandler(), this);
	}
	
	public static PVPManager getInstance() {
		return instance;
	}
	
}
