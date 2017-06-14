package de.alphahelix.achievementaddon.inventories;

import de.alphahelix.achievementaddon.AchievementAddon;
import de.alphahelix.achievementaddon.instances.Achievement;
import de.alphahelix.alphalibary.inventorys.InventoryBuilder;
import de.alphahelix.uhcremastered.UHC;
import de.alphahelix.uhcremastered.enums.GState;
import de.alphahelix.uhcremastered.utils.StatsUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.WeakHashMap;

import static de.alphahelix.achievementaddon.AchievementAddon.getAchievementOptions;

public class AchievementInventory {

    private static WeakHashMap<String, Inventory> inventories = new WeakHashMap<>();

    public AchievementInventory() {
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onClick(PlayerInteractEvent e) {
                if (!GState.is(GState.LOBBY)) return;
                if (e.getItem() == null) return;
                if (e.getItem().getType() != AchievementAddon.getAchievementOptions().getIcon().getItemStack().getType())
                    return;

                openInv(e.getPlayer());
            }
        }, UHC.getInstance());
    }

    private void openInv(Player p) {

        if (inventories.containsKey(p.getName())) {
            p.openInventory(inventories.get(p.getName()));
            return;
        }

        InventoryBuilder ib = new InventoryBuilder(getAchievementOptions().getGuiName(), ((8 / 9) + 1) * 9) {
            @Override
            public void onOpen(InventoryOpenEvent inventoryOpenEvent) {
            }

            @Override
            public void onClose(InventoryCloseEvent inventoryCloseEvent) {
            }
        };

        for (int i = 0; i < 7; i++) {
            Achievement a = Achievement.getAchievement(i);
            ib.addItem(new InventoryBuilder.SimpleItem(a.getIcon(StatsUtil.getStatistics(p).hasCustomStatistic(a), true), i) {
                @Override
                public void onClick(InventoryClickEvent inventoryClickEvent) {
                    if (inventoryClickEvent.getClickedInventory().getTitle().equals(getAchievementOptions().getGuiName())) {

                        inventoryClickEvent.setCancelled(true);
                    }
                }
            });
        }

        inventories.put(p.getName(), ib.build());
        p.openInventory(inventories.get(p.getName()));
    }

}
