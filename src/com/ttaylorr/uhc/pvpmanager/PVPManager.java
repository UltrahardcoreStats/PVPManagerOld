package com.ttaylorr.uhc.pvpmanager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.ttaylorr.uhc.pvpmanager.cmd.PVPCommandExecutor;
import com.ttaylorr.uhc.pvpmanager.listeners.EnderPearlListener;

public class PVPManager extends JavaPlugin {

	private static PVPManager instance;
	private static ProtectedRegion parentRegion;

	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {

		instance = this;

		saveDefaultConfig();

		if (getWorldGuard() == null) {
			this.getLogger().severe("Cannot find dependencies!  Unloading...");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
		}

		enableModules();
		enableCommands();

		parentRegion = getWorldGuard().getRegionManager(Bukkit.getWorld(getConfig().getString("spawn.world"))).getRegion(getConfig().getString("spawn.parent"));
	
	}

	public static PVPManager getInstance() {
		return instance;
	}

	private void enableModules() {
		if (getConfig().getBoolean("pearls.enabled")) {
			int pearlMaxHeight = getConfig().getInt("pearls.max-height");
			boolean giveBackPearls = getConfig().getBoolean("pearls.give-back");

			getServer().getPluginManager().registerEvents(new EnderPearlListener(pearlMaxHeight, giveBackPearls), this);
		}
	}
	
	private void enableCommands() {
		getCommand("pvp").setExecutor(new PVPCommandExecutor(this));
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
