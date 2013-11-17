package com.ttaylorr.uhc.pvpmanager.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.ttaylorr.uhc.pvpmanager.PVPManager;

public class PVPUtils {

	private WorldGuardPlugin wg;

	public PVPUtils(WorldGuardPlugin wg) {
		this.wg = wg;
	}

	public void spawnPlayer(Player player, World world) {
		HashMap<ProtectedRegion, Integer> regions = new HashMap<ProtectedRegion, Integer>();

		for (ProtectedRegion r : wg.getRegionManager(world).getRegions().values()) {
			if (r.getParent().equals(PVPManager.getParentRegion())) {
				int count = 0;

				for (Player p : Bukkit.getOnlinePlayers()) {
					org.bukkit.util.Vector vec = p.getLocation().toVector();

					if (r.contains(new com.sk89q.worldedit.Vector(vec.getX(), vec.getY(), vec.getZ()))) {
						count++;
					}
				}

				regions.put(r, count);

			}
		}

		int minPlayers = 0;
		ProtectedRegion r = null;
		ProtectedRegion toSpawn = null;

		for (ProtectedRegion pr : regions.keySet()) {
			if (regions.get(r) < minPlayers) {
				minPlayers = regions.get(r);
				r = pr;
			}
		}

		if (minPlayers == 0) {
			ArrayList<ProtectedRegion> emptyRegions = new ArrayList<ProtectedRegion>();

			for (ProtectedRegion r1 : regions.keySet()) {
				if (regions.get(r1).equals(0)) {
					emptyRegions.add(r1);
				}
			}

			if (emptyRegions.size() == 0) {
				spawnPlayer(player, world);
			} else {
				Random rand = new Random();
				toSpawn = emptyRegions.get(rand.nextInt(emptyRegions.size()));
			}
		} else {
			toSpawn = r;
		}

		player.teleport(new Location(world, toSpawn.getMinimumPoint().getX() + 0.5, toSpawn.getMinimumPoint().getY(), toSpawn.getMinimumPoint().getZ() + 0.5));
	}

	public static boolean isInAABB(Location l1, Location min, Location max) {
		if (distance(min.getX(), max.getX()) != distance(l1.getX(), min.getX()) + distance(l1.getX(), max.getX())) return false;
		if (distance(min.getY(), max.getY()) != distance(l1.getY(), min.getY()) + distance(l1.getY(), max.getY())) return false;
		if (distance(min.getY(), max.getY()) != distance(l1.getY(), min.getY()) + distance(l1.getY(), max.getY())) return false;

		return true;
	}

	public static double distance(double x1, double x2) {
		return Math.abs(x2 - x1);
	}

}
