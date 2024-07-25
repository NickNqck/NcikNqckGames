package fr.games.tasks;

import fr.games.DuelType;
import fr.games.listeners.DuelSelector;
import fr.games.utils.ItemBuilder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class DuelsTask {
    private final Map<UUID, UUID> duels = new HashMap<>();
    private final Map<DuelType, Map<UUID, UUID>> inDuels = new HashMap<>();

    public DuelsTask() {
        new DuelSelector();
    }
    public void initializeDuel(final Player commander, final Player target) {
        duels.put(commander.getUniqueId(), target.getUniqueId());
        commander.sendMessage("§7Vous avez défier§c "+target.getName()+"§7.");
        chooseDuel(commander, target);
    }
    private void chooseDuel(Player sender, Player target) {
        Inventory inv = Bukkit.createInventory(sender, 9*3, "§cDuels§7 - §6"+target.getName());
        inv.setItem(12, new ItemBuilder(Material.SHEARS).setName("§cShifoumi").setLore("","§7Vous permet de défier§c "+target.getName()+"§7 au simple jeu du pierre, feuille, ciseau","").toItemStack());
        duels.put(sender.getUniqueId(), target.getUniqueId());
        sender.openInventory(inv);
    }
    public void onChoose(UUID clicker, DuelType type) {
        if (duels.containsKey(clicker)) {
            Player target = Bukkit.getPlayer(duels.get(clicker));
            Player player = Bukkit.getPlayer(clicker);
            if (target != null && player != null) {
              //  Map<UUID, UUID>
                //inDuels.put(type, Collections.(clicker, target.getUniqueId()).toArray(new UUID[0]));
                TextComponent toSend = new TextComponent("§c"+player.getName()+"§7 vous défie en duel de "+type.getName()+"\n\n");
                TextComponent acceptMessage = new TextComponent("§a§lACCEPTER");
                acceptMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel accept "+clicker.toString()));
                toSend.addExtra(acceptMessage);
                toSend.addExtra("\n\n");
                TextComponent refuseMessage = new TextComponent("§c§lREFUSER");
                refuseMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel refuse "+clicker.toString()));
                toSend.addExtra(refuseMessage);
                target.spigot().sendMessage(toSend);
                player.sendMessage("§c"+target.getName()+"§7 à reçus une requête de duel.");
                player.closeInventory();
            }
        }
    }

    public void CancelDuel(UUID canceller) {
        for (Map<UUID, UUID> inDuels : this.inDuels.values()) {
            if (inDuels.containsKey(canceller)) {
                inDuels.remove(canceller, inDuels.get(canceller));
            } else if (inDuels.containsValue(canceller)) {
                for (UUID uuid : inDuels.keySet()) {
                    if (inDuels.get(uuid).equals(canceller)) {
                        inDuels.remove(uuid, canceller);
                    }
                }
            }
            break;
        }
    }
}