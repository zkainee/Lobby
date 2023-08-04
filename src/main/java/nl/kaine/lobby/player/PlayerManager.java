package nl.kaine.lobby.player;

import org.bukkit.event.Listener;

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

}
