package nl.kaine.lobby.player;

import nl.kaine.lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class ConnectionListener implements Listener {

    Lobby plugin;

    public ConnectionListener(Lobby plugin) {
        this.plugin = plugin;
    }

    /**
     * Executes a check if player exists in Database, if not it created a profile in PlayerProfile.java
     * @param e
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player playerProfile = e.getPlayer();

        try {
            PlayerProfile playerProfileData = new PlayerProfile(plugin, playerProfile.getUniqueId());
            plugin.getPlayerManager().addPlayer(playerProfile.getUniqueId(), playerProfileData);
        } catch (SQLException ex) {
            playerProfile.kickPlayer("Data niet ingeladen");
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        plugin.getPlayerManager().removePlayer(e.getPlayer().getUniqueId());
    }
}
