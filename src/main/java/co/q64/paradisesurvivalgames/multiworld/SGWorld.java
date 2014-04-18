/**
 * Name: SGWorld.java Created: 16 December 2013
 *
 * @version 1.0.0
 */

package co.q64.paradisesurvivalgames.multiworld;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class SGWorld {

	private Location center = null;
	private String displayName;
	private boolean inLobby;

	public List<Location> locs = new ArrayList<>();
	private String name;
	public List<BlockState> t2 = new ArrayList<>();

	private WorldCreator wc;

	public SGWorld(String name, String map) {
		this.name = name;
		displayName = map;

		wc = new WorldCreator(name);
		wc.environment(World.Environment.NORMAL);
		wc.type(WorldType.NORMAL);
	}

	public World create() {
		if (Bukkit.getServer().getWorld(name) != null) {
			Bukkit.getLogger().severe("Could not load world because world " + name + " is already loaded.  If you just used the /reload command, ignore this error, it's all cool!");
		}

		return wc.createWorld();
	}

	private void deleteFiles(File path) {
		if (path.exists()) {
			File files[] = path.listFiles();
			for (File file : files != null ? files : new File[0]) {
				if (file.isDirectory()) {
					deleteFiles(file);
				} else {
					file.delete();
				}
			}
		}
		path.delete();
	}

	public Location getCenter() {
		return center;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getName() {
		return name;
	}

	public World getWorld() {
		return name != null ? Bukkit.getServer().getWorld(name) : null;
	}

	public void init(List<Location> locs, List<BlockState> t2) {
		Bukkit.getServer().getWorld(name).setDifficulty(Difficulty.EASY);
		this.locs = locs;
		this.t2 = t2;
		for (Location l : locs) {
			for (Location loc : locs) {
				if (Math.abs(l.getBlockX()) - Math.abs(loc.getBlockX()) <= 2) {
					int radius = (int) (loc.distance(l) / 2);
					center = loc.subtract(radius, loc.getY(), loc.getZ());
				}
			}
		}
	}

	public boolean isInLobby() {
		return inLobby;
	}

	/**
	 * Adds the next spawn into the list of spawns
	 *
	 * @param loc The location of the spawn
	 */
	public void nextSpawn(Location loc) {
		locs.add(loc);
		Bukkit.getLogger().info("Registered spawn point - List: " + locs.toString() + " Loc: " + loc.toString());
	}

	public void remove() {
		World world = Bukkit.getServer().getWorld(name);

		for (Player p : world.getPlayers()) {
			p.kickPlayer("Sorry, this world is getting deleted, and we felt you didn't want to as well!");
			//TODO teleport player somewhere safe
		}
		for (Entity e : world.getEntities()) {
			e.remove();
		}
		for (Chunk c : world.getLoadedChunks()) {
			c.unload(false, false);
			world.unloadChunk(c);
		}
		Bukkit.getServer().unloadWorld(world, false);
		deleteFiles(world.getWorldFolder());
	}

	public void setDisplayName(String name) {
		this.displayName = name;
	}

	public void setInLobby(boolean b) {
		inLobby = b;
	}

	@Override
	public String toString() {
		return "SGWorld.java - Display name: " + this.displayName + " Locs: " + this.locs + " Chests: " + this.t2;
	}
}