package co.q64.paradisesurvivalgames.objects;

import co.q64.paradisesurvivalgames.multiworld.SGWorld;

public class MapHash {
	private final int id;
	private final SGWorld w;

	public MapHash(SGWorld w, int id) {
		this.w = w;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public SGWorld getWorld() {
		return w;
	}
}