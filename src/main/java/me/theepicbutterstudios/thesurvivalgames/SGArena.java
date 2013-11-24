package me.theepicbutterstudios.thesurvivalgames;

import org.bukkit.Location;
 
import java.util.ArrayList;
import java.util.List;
 
public class SGArena {
 
    public int id = 0;
    Location spawn = null;
    List<String> players = new ArrayList<String>();
 
    /**
     * Constructs a new arena based off of a Location and an ID
     * 
     * @param id The ID the arena will have
     */
    
    public SGArena(int id) {
        this.id = id;
    }
 
    /**
     * Gets the ID of the arena
     * 
     * @return The ID of the arena
     */
    
    public int getId() {
        return this.id;
    }

    /**
     * Gets the list of players in the arena
     * 
     * @return List of players in the arena
     */
    
    public List<String> getPlayers() {
        return this.players;
    }
}