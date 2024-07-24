package fr.games;

import fr.games.commands.Duel;
import fr.games.tasks.DuelsTask;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;


public final class NickNqckGames extends JavaPlugin {
    @Getter
    private static NickNqckGames instance;
    @Getter
    private DuelsTask duelsTask;
    @Override
    public void onEnable() {
        instance = this;
        this.duelsTask = new DuelsTask();
        getCommand("duel").setExecutor(new Duel());
    }
    @Override
    public void onDisable() {
    }
}