package nl.kaine.lobby.player;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager implements Listener {

    private HashMap<UUID, PlayerProfile> players = new HashMap<>();

    public PlayerProfile getPlayer(UUID uuid) {
        return players.get(uuid);
    }
    public void addPlayer(UUID uuid ,PlayerProfile player) {
        players.put(uuid, player);
    }
    public void removePlayer(UUID uuid) {
        players.remove(uuid);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent player) {
        player.getPlayer().setGameMode(GameMode.ADVENTURE);
    }
}
