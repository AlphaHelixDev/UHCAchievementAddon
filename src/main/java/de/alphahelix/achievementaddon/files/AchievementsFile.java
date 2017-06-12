package de.alphahelix.achievementaddon.files;

import de.alphahelix.achievementaddon.AchievementAddon;
import de.alphahelix.achievementaddon.instances.Achievement;
import de.alphahelix.achievementaddon.instances.AchievementOptions;
import de.alphahelix.alphalibary.file.SimpleFile;
import de.alphahelix.alphalibary.file.SimpleJSONFile;
import de.alphahelix.alphalibary.item.ItemBuilder;
import org.bukkit.Material;

public class AchievementsFile extends SimpleJSONFile {
    public AchievementsFile() {
        super(AchievementAddon.getInstance().getDataFolder().getAbsolutePath(), "achievements.json");
        initValues();
        initAchievements();
    }

    private void initValues() {
        if (!jsonContains("Achievements")) {

            setValue("Options", new AchievementOptions(
                    "§aAchievements",
                    "§7You just unlocked the achievement §8: §a[achievement]",
                    "§cLocked",
                    "§aUnlocked",
                    new SimpleFile.InventoryItem(new ItemBuilder(Material.BOOK).setName("§aAchievements").build(), 6)
            ));

            setValue("Achievements", new Achievement[] {
                    Achievement.SLAYER,
                    Achievement.BURN_BABY_BURN,
                    Achievement.DRAGON_SLAYER,
                    Achievement.GLOWING_IN_THE_DARK,
                    Achievement.HIGHWAY_TO_HELL,
                    Achievement.OMG_DIAMONDS,
                    Achievement.SENOR_BOOM_BOOM,
                    Achievement.WINNER
            });
        }
    }

    private void initAchievements() {
        for (Achievement a : getValue("Achievements", Achievement[].class)) {
            a.getCodeAchievement().copyFrom(a);
        }

        AchievementAddon.setAchievementOptions(getValue("Options", AchievementOptions.class));
    }
}
