package nl.kaine.lobby;

import nl.kaine.lobby.database.SQL;
import nl.kaine.lobby.database.ConnectionListener;
import nl.kaine.lobby.menu.ServerSelector;
import nl.kaine.lobby.menu.listener.ServerClickListener;
import nl.kaine.lobby.player.PlayerManager;
import nl.kaine.lobby.player.balance.BalanceCommand;
import nl.kaine.lobby.scoreboard.ScoreboardCommand;
import nl.kaine.lobby.scoreboard.ScoreboardListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.Objects;

public class Lobby extends JavaPlugin {

    private SQL SQL;
    private PlayerManager playerManager;

    private ServerSelector serverSelector;

    @Override
    public void onEnable() {
        getLogger().info("Lobby plugin enabled.");

        SQL = new SQL();
        try {
            SQL.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Lobby | Database connected: " + SQL.isConnected());

        Objects.requireNonNull(getCommand("saldo")).setExecutor(new BalanceCommand(this));
        Objects.requireNonNull(getCommand("scoreboard")).setExecutor(new ScoreboardCommand(this));

        Bukkit.getPluginManager().registerEvents(new ScoreboardListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ServerClickListener(this), this);

        playerManager = new PlayerManager();
        serverSelector = new ServerSelector(this);

    }
    @Override
    public void onDisable() {
        getLogger().info("Lobby is disabled");
        SQL.disconnect();
    }

    public PlayerManager getPlayerManager() { return playerManager; }

    public SQL getDatabase() { return SQL; }
}
