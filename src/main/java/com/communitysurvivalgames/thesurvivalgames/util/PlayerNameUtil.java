package com.communitysurvivalgames.thesurvivalgames.util;

import java.util.ArrayList;
import org.bukkit.entity.Player;

public class PlayerNameUtil {

    public static int getNameUUID(Player p) {
        return stringToHash(p.getName());
    }

    public static int getNameUUID(String p) {
        return stringToHash(p);
    }

    public static ArrayList<String> getDevs() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Quantum64");
        list.add("calebbfmv");
        list.add("TrolLDOod3");
        list.add("Relicum");
        list.add("sam4215");
       //TODO: What's you IGN everyone?
        return list;
    }

    public static ArrayList<String> getAwesomePeople() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Jonthespartan29");
        list.add("GregoriusMC"); //TODO XD
        return list;
    }

    private static int stringToHash(String s) {
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = 31 * h + s.charAt(i);
        }
        return h;
    }
}
