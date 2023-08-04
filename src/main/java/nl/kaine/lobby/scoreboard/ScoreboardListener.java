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
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class ScoreboardListener implements Listener {
    //Instance plugin
    static Lobby plugin;

    public ScoreboardListener(Lobby plugin) { this.plugin = plugin; }

    // Store original scoreboard for each player
    static HashMap<UUID, Scoreboard> originalScoreboards = new HashMap<>();

    static ArrayList<UUID> toggle = new ArrayList<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (player != null && !toggle.contains(player.getUniqueId())) {
            try {
                PlayerProfile profile = new PlayerProfile(plugin, player.getUniqueId());
                setScoreboard(player, profile);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Executed when a player toggles their scorebord with: /scoreboard toggle
     * @param player
     */
    public static void toggleScoreboard(Player player, PlayerProfile profile) {
        if (toggle.contains(player.getUniqueId())) {
            toggle.remove(player.getUniqueId());
            player.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
        } else {
            toggle.add(player.getUniqueId());
            setScoreboard(player, profile);
        }
    }

    /**
     * Creates scoreboard for a player
     * @param player
     * @param profile
     */
    public static void setScoreboard(Player player, PlayerProfile profile) {

        // Get the original scoreboard or create a new one if it doesn't exist
        Scoreboard originalScoreboard = originalScoreboards.getOrDefault(player.getUniqueId(), Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());

        // Create a new scoreboard for the player
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

        int balance = profile.getBalance();

        Score playerMoney = obj.getScore(ChatColor.WHITE + "Saldo: " + balance);
        playerMoney.setScore(5);

        empty.setScore(6);

        originalScoreboards.put(player.getUniqueId(), scoreboard);
        player.setScoreboard(scoreboard);
    }
    public static void updateScoreboard(Player player, PlayerProfile profile) {
        Scoreboard scoreboard = player.getScoreboard();
        Objective obj = scoreboard.getObjective("lobby");

        if (obj != null) {
            // Find the existing score for balance and update it
            Score balanceScore = obj.getScore(ChatColor.WHITE + "Saldo: " + profile.getBalance());
            balanceScore.setScore(5);
        }
    }
}
