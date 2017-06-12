package de.alphahelix.achievementaddon.instances;

import de.alphahelix.alphalibary.file.SimpleFile;

public class AchievementOptions {

    private String guiName, justUnlocked, locked, unlocked;
    private SimpleFile.InventoryItem icon;

    public AchievementOptions(String guiName, String justUnlocked, String locked, String unlocked, SimpleFile.InventoryItem icon) {
        this.guiName = guiName;
        this.justUnlocked = justUnlocked;
        this.locked = locked;
        this.unlocked = unlocked;
        this.icon = icon;
    }

    public String getGuiName() {
        return guiName;
    }

    public String getJustUnlocked(Achievement a) {
        return justUnlocked.replace("[achievement", a.getName());
    }

    public String getLocked() {
        return locked;
    }

    public String getUnlocked() {
        return unlocked;
    }

    public SimpleFile.InventoryItem getIcon() {
        return icon;
    }
}
