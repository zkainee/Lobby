package nl.kaine.lobby.player;

import nl.kaine.lobby.Lobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class BalanceCommand implements CommandExecutor, TabCompleter {

    Lobby plugin;

    public BalanceCommand(Lobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Check command
        if (!command.getName().equalsIgnoreCase("saldo"))
            return false;
        Player player = null;

        //Check if sender is a player
        if (sender instanceof Player ) {
            player = (Player) sender;
            player.getUniqueId();
        }
        if (args.length == 0) {
            sender.sendMessage(("------------------- [Lobby] -------------------"));
            sender.sendMessage(("De volgende commando's zijn beschikbaar:"));
            sender.sendMessage(("/saldo <player>"));
            sender.sendMessage(("/saldo add <player> <amount>"));
            sender.sendMessage(("/saldo remove <player> <amount>"));
            sender.sendMessage(("----------------------------------------------"));

            return true;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], List.of("add", "remove"), new ArrayList<>());
        }
        return new ArrayList<>();
    }
}
