package fr.games.tasks;

import fr.games.listeners.DuelSelector;
import fr.games.utils.ItemBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DuelsTask {
    @Getter
    private final Map<UUID, UUID> duels = new HashMap<>();
    public DuelsTask() {
        new DuelSelector();
    }
    public void creatingDuel(Player sender, Player target) {
        Inventory inv = Bukkit.createInventory(sender, 9*3, "§cDuels§7 - §6"+target.getName());
        inv.setItem(12, new ItemBuilder(Material.SHEARS).setName("§cShifoumi").setLore("","§7Vous permet de défier§c "+target.getName()+"§7 au simple jeu du pierre, feuille, ciseau","").toItemStack());
        duels.put(sender.getUniqueId(), target.getUniqueId());
        sender.openInventory(inv);
    }
}