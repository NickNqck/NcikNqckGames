package fr.games.listeners;

import fr.games.DuelType;
import fr.games.NickNqckGames;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class DuelSelector implements Listener {

    public DuelSelector() {
        Bukkit.getServer().getPluginManager().registerEvents(this, NickNqckGames.getInstance());
    }
    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player clicker = (Player) event.getWhoClicked();
            if (event.getClickedInventory().getTitle().contains("§cDuels§7 - §6")) {
                if (event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.SHEARS)) {
                    NickNqckGames.getInstance().getDuelsTask().onChoose(clicker.getUniqueId(), DuelType.SHIFOUMI);
                }
                event.setCancelled(true);
            }
        }
    }
}