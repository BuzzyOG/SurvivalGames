package com.communitysurvivalgames.thesurvivalgames.runnables;

import com.communitysurvivalgames.thesurvivalgames.TheSurvivalGames;
import com.communitysurvivalgames.thesurvivalgames.managers.ArenaManager;
import com.communitysurvivalgames.thesurvivalgames.objects.SGArena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import java.util.logging.Level;

/**
 * Name: Scoreboard.java Edited: 8 December 2013
 *
 * @version 1.0.0
 */
public class Scoreboard implements Runnable {

    TheSurvivalGames plugin;

    public Scoreboard(TheSurvivalGames base) {
        plugin = base;
    }

    public void run() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            final Objective objective = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR);

            if (objective == null) {
                createScoreboard(player);
            } else {
                updateScoreboard(player, false);
            }
        }
    }

    private void createScoreboard(Player player) {
        org.bukkit.scoreboard.Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Global", "dummy");
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lWelcome, " + player.getDisplayName()));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        if (player.isOnline()) {
            try {
                player.setScoreboard(scoreboard);
            } catch (IllegalStateException ex) {
                Bukkit.getLogger().log(Level.SEVERE, "The scoreboard could not be created for player {0}!", player.getDisplayName());
                return;
            }

            updateScoreboard(player, true);
        }
    }

    private void updateScoreboard(Player player, boolean complete) {
        final Objective objective = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
        if (!ArenaManager.getManager().isInGame(player)) {
            objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lWelcome, " + player.getDisplayName()));
            sendScore(objective, "&a&lPoints", 11, complete);
            sendScore(objective, "&6&l" + getPlugin().getPlayerData(player).getPoints() + "   ", 10, complete);
            sendScore(objective, "&r", 9, complete);
            sendScore(objective, "&e&lRank", 8, complete);
            sendScore(objective, "&f" + getPlugin().getPlayerData(player).getRank(), 7, complete);
            sendScore(objective, "&0", 6, complete);
            sendScore(objective, "&4&lKills", 5, complete);
            sendScore(objective, "&6&l" + getPlugin().getPlayerData(player).getKills() + "  ", 4, complete);
            sendScore(objective, "&c", 3, complete);
            sendScore(objective, "&dWins", 2, complete);
            sendScore(objective, "&6&l" + getPlugin().getPlayerData(player).getWins() + " ", 1, complete);
            return;
        }
        SGArena arena = ArenaManager.getManager().getArena(player);
        if (arena.getState() == SGArena.ArenaState.WAITING_FOR_PLAYERS) {
            objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lWaiting For Players..."));
            sendScore(objective, "&eMax Players", 14, complete);
            sendScore(objective, "&f" + arena.getMaxPlayers(), 13, complete);
            sendScore(objective, "&0", 12, complete);
            sendScore(objective, "&eMin Players", 11, complete);
            sendScore(objective, "&f" + arena.getMinPlayers(), 10, complete);
            sendScore(objective, "&r", 9, complete);
            sendScore(objective, "&ePlayers", 8, complete);
            sendScore(objective, "&f" + arena.getPlayers().size(), 7, complete);
            sendScore(objective, "&f", 6, complete);
            sendScore(objective, "&4&lClass", 5, complete);
            sendScore(objective, "*null*", 4, complete); //TODO
            sendScore(objective, "&c", 3, complete);
            sendScore(objective, "&a&lPoints", 2, complete);
            sendScore(objective, "&6&l" + getPlugin().getPlayerData(player).getPoints(), 1, complete);
            return;
        }

        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lSurvival Games"));

        /*
         sendScore(objective, "&bKills", arena.getKills(player), complete); TODO
         sendScore(objective, "&alive", arena.getAlive(), complete); TODO
         sendScore(objective, "&4Dead", arena.getDead(player), complete); TODO
         sendScore(objective, "&7Spectating", arena.getSpectating(), complete); TODO
         sendScore(objective, "&eTime", arena.getSpectating(), complete); TODO
         */
        //TODO Probably end up having something that switches the scoreboard back and forth about every ~5 to showing all players and their lives and this
    }

    public static void sendScore(Objective objective, String title, int value, boolean complete) {

        final Score score = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.translateAlternateColorCodes('&', title)));


        if (complete && value == 0) {
            // Have to use this because the score wouldn't send otherwise
            score.setScore(-1);
        }

        score.setScore(value);
    }

    public TheSurvivalGames getPlugin() {
        return plugin;
    }

    public static void registerScoreboard(TheSurvivalGames plugin) {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Scoreboard(plugin), 5, 20);
    }
}
