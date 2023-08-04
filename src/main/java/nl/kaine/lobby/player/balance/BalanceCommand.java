package nl.kaine.lobby.player.balance;

import nl.kaine.lobby.Lobby;
import nl.kaine.lobby.player.PlayerProfile;
import nl.kaine.lobby.scoreboard.ScoreboardListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.sql.SQLException;
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

        //Check if sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("Commando enkel uitvoerbaar als speler.");
            return true;
        }

        Player player = (Player) sender;
        PlayerProfile profile = plugin.getPlayerManager().getPlayer(player.getUniqueId());

        if (args.length == 0) {
            sender.sendMessage("------------------- [Lobby] -------------------");
            sender.sendMessage("Uw saldo: " + profile.getBalance());
            sender.sendMessage(" ");
            sender.sendMessage("De volgende commando's zijn beschikbaar:");
            sender.sendMessage("/saldo add <player> <amount>");
            sender.sendMessage("/saldo remove <player> <amount>");
            sender.sendMessage("----------------------------------------------");

            return true;
        }
        if (args.length >= 3) {
            String action = args[0].toLowerCase();
            String targetPlayerName = args[1];
            int amount;

            try {
                amount = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                sender.sendMessage("Onjuiste hoeveelheid.");
                return true;
            }
            Player targetPlayer = plugin.getServer().getPlayer(targetPlayerName);

            if (targetPlayer == null) {
                sender.sendMessage("Speler niet gevonden");
                return true;
            }

            PlayerProfile targetProfile = plugin.getPlayerManager().getPlayer(targetPlayer.getUniqueId());

            if (action.equals("add")) {
                targetProfile.setBalance(targetProfile.getBalance() + amount);
                sender.sendMessage("$ " + amount + " is toegevoegd aan: " + targetPlayerName + "'s saldo.");
            } else if (action.equals("remove")) {
                targetProfile.setBalance(targetProfile.getBalance() - amount);
                sender.sendMessage("$ " + amount + " is verwijderd van: " + targetPlayerName + "'s saldo.");
            } else {
                sender.sendMessage("Onjuiste actie. Gebruik '/saldo add' of '/saldo remove'.");
            }

            ScoreboardListener.setScoreboard(targetPlayer, targetProfile);

            try {
                new PlayerProfile(plugin, targetPlayer.getUniqueId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], List.of("add", "remove"), new ArrayList<>());
        }
        return new ArrayList<>();
    }
}
