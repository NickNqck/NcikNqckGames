package fr.games.tasks;

import fr.games.DuelType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DuelStarter {

    public void startDuel(DuelType type, String duelist1, String duelist2) {
        Player duelist = Bukkit.getPlayer(duelist1);
        Player victim = Bukkit.getPlayer(duelist2);
        if (duelist != null && victim != null) {
            
        }
    }
}