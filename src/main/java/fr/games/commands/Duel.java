package fr.games.commands;

import fr.games.NickNqckGames;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Duel implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player sender = (Player) commandSender;
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    NickNqckGames.getInstance().getDuelsTask().initializeDuel(sender, target);
                    return true;
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("refuse")) {
                    UUID target = UUID.fromString(args[1]);
                    System.out.println(target);
                    NickNqckGames.getInstance().getDuelsTask().CancelDuel(target);
                    return true;
                }
                if (args[0].equalsIgnoreCase("accept")) {
                    UUID target = UUID.fromString(args[1]);
                    NickNqckGames.getInstance().getDuelsTask().AcceptDuel(target);
                    return true;
                }
            }

        }
        return false;
    }
}
