package com.ttaylorr.uhc.pvpmanager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.ttaylorr.uhc.pvpmanager.cmd.PVPCommandExecutor;

public class PVPManager extends JavaPlugin {

	private static PVPManager instance;
	private static ProtectedRegion parentRegion;
	
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		instance = this;
		getCommand("pvp").setExecutor(new PVPCommandExecutor(this));
		
		parentRegion = getWorldGuard().getRegionManager(Bukkit.getWorld(getConfig().getString("spawn.world"))).getRegion(getConfig().getString("spawn.parent"));
	}

	public static PVPManager getInstance() {
		return instance;
	}
	
	public WorldGuardPlugin getWorldGuard() {
	    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        return null;
	    }
	 
	    return (WorldGuardPlugin) plugin;
	}
	
	public static ProtectedRegion getParentRegion() {
		return parentRegion;
	}
	
}
