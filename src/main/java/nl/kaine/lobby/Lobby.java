package nl.kaine.lobby;

import nl.kaine.lobby.player.BalanceCommand;
import nl.kaine.lobby.scoreboard.ScoreboardCommand;
import nl.kaine.lobby.scoreboard.ScoreboardListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Lobby extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Lobby plugin enabled.");

        Objects.requireNonNull(getCommand("saldo")).setExecutor(new BalanceCommand(this));
        Objects.requireNonNull(getCommand("scoreboard")).setExecutor(new ScoreboardCommand(this));
        //Objects.requireNonNull(getCommand("fly")).setExecutor(new );


        Bukkit.getPluginManager().registerEvents(new ScoreboardListener(this), this);
    }
    @Override
    public void onDisable() {
        getLogger().info("Lobby is disabled");
    }
}
