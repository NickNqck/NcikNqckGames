package fr.games.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Duel implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player sender = (Player) commandSender;
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {

                }
            }
        }
        return false;
    }
}
