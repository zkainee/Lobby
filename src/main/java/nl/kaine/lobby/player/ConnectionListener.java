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
        Player player = e.getPlayer();

        try {
            PlayerProfile playerProfile = new PlayerProfile(plugin, player);
            plugin.getPlayerManager().addPlayer(player.getUniqueId(), playerProfile);
        } catch (SQLException ex) {
            player.kickPlayer("Data niet ingeladen");
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        plugin.getPlayerManager().removePlayer(e.getPlayer().getUniqueId());
    }
}
