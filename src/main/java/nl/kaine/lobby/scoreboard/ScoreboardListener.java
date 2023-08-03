package nl.kaine.lobby.scoreboard;

import nl.kaine.lobby.Lobby;
import nl.kaine.lobby.player.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class ScoreboardListener implements Listener {
    //Instance plugin
    static Lobby plugin;
    static PlayerProfile profile;

    private static UUID uuid;
    public ScoreboardListener(Lobby plugin) { this.plugin = plugin; }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent joinEvent) {
        toggleScoreboard(joinEvent.getPlayer());
    }
    static ArrayList<UUID> toggle = new ArrayList<>();

    /**
     * Executed when a player toggles their scorebord with: /scoreboard toggle
     * @param player
     */
    public static void toggleScoreboard(Player player) {
        if (toggle.contains(player.getUniqueId())) {
            toggle.remove(player.getUniqueId());
            player.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
        } else {
            toggle.add(player.getUniqueId());
            setScoreboard(player);
        }
    }

    /**
     * Creates scoreboard for a player
     * @param player
     */
    public static void setScoreboard(Player player) {

        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("lobby", "lorem");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Lobby");

        Score bottom = obj.getScore(ChatColor.YELLOW + "www.kainee.nl");
        bottom.setScore(1);

        Score empty = obj.getScore(" ");
        empty.setScore(2);

        Score playerName = obj.getScore(ChatColor.WHITE + "Naam: " + player.getName());
        playerName.setScore(3);

        empty.setScore(4);

        Score playerMoney = obj.getScore(ChatColor.WHITE + "Saldo: ");
        playerMoney.setScore(5);

        empty.setScore(6);

        player.setScoreboard(scoreboard);
    }
}
