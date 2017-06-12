package de.alphahelix.achievementaddon;

import de.alphahelix.achievementaddon.files.AchievementsFile;
import de.alphahelix.achievementaddon.instances.AchievementOptions;
import de.alphahelix.achievementaddon.inventories.AchievementInventory;
import de.alphahelix.achievementaddon.listener.AchievementListener;
import de.alphahelix.uhcremastered.UHC;
import de.alphahelix.uhcremastered.addons.AddonCore;
import de.alphahelix.uhcremastered.addons.core.Addon;
import de.alphahelix.uhcremastered.enums.GState;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;

public class AchievementAddon extends Addon {

    private static AchievementAddon instance;
    private static AchievementOptions achievementOptions;

    public static AchievementAddon getInstance() {
        return instance;
    }

    public static AchievementOptions getAchievementOptions() {
        return achievementOptions;
    }

    public static void setAchievementOptions(AchievementOptions achievementOptions) {
        AchievementAddon.achievementOptions = achievementOptions;
    }

    @Override
    public void onEnable() {
        instance = this;

        new AchievementsFile();
        new AchievementInventory();
        new AchievementListener();

        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onJoin(PlayerJoinEvent e) {
                if (GState.is(GState.LOBBY))
                    e.getPlayer().getInventory().setItem(getAchievementOptions().getIcon().getSlot(), getAchievementOptions().getIcon().getItemStack());
            }
        }, UHC.getInstance());

        AddonCore.getLogger().log(Level.INFO, "Achievements - loaded");
    }
}
