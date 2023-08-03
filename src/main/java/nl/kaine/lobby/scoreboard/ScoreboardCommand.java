package nl.kaine.lobby.scoreboard;

import nl.kaine.lobby.Lobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ScoreboardCommand implements CommandExecutor, TabCompleter {

    Lobby plugin;
    public ScoreboardCommand(Lobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Check command
        if (!command.getName().equalsIgnoreCase("scoreboard"))
            return false;
        Player player = null;

        //Check if sender is a player
        if (sender instanceof Player ) {
            player = (Player) sender;
            player.getUniqueId();
        }

        // /scoreboard
        // no args = help message
        if (args.length == 0) {
            sender.sendMessage(("------------------- [Lobby] -------------------"));
            sender.sendMessage(("De volgende commando's zijn beschikbaar:"));
            sender.sendMessage(("/scoreboard <toggle>"));
            sender.sendMessage(("----------------------------------------------"));

            return true;
        }

        if (args[0].equalsIgnoreCase("toggle")) {
            ScoreboardListener.toggleScoreboard(Objects.requireNonNull(player));
        }
        return true;
    }
    // /scoreboard toggle
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], List.of("toggle"), new ArrayList<>());
        }
        return new ArrayList<>();
    }
}
