package de.alphahelix.achievementaddon.instances;

import com.google.gson.JsonElement;
import de.alphahelix.achievementaddon.AchievementAddon;
import de.alphahelix.alphalibary.file.SimpleJSONFile;
import de.alphahelix.alphalibary.item.ItemBuilder;
import de.alphahelix.uhcremastered.instances.CustomStatistic;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Achievement extends CustomStatistic implements Serializable {

    public static final Achievement SLAYER = new Achievement("SLAYER", "Slayer", new ItemStack(Material.DIAMOND_SWORD), "Placeholder", "Lore");
    public static final Achievement WINNER = new Achievement("WINNER", "Winner", new ItemStack(Material.EMERALD), "Placeholder", "Lore");
    public static final Achievement DRAGON_SLAYER = new Achievement("DRAGON_SLAYER", "Dragon slayer", new ItemStack(Material.DRAGON_EGG), "Placeholder", "Lore");
    public static final Achievement OMG_DIAMONDS = new Achievement("OMG_DIAMONDS", "OMG diamonds", new ItemStack(Material.DIAMOND), "Placeholder", "Lore");
    public static final Achievement BURN_BABY_BURN = new Achievement("BURN_BABY_BURN", "Burn baby, burn!", new ItemStack(Material.FLINT_AND_STEEL), "Placeholder", "Lore");
    public static final Achievement HIGHWAY_TO_HELL = new Achievement("HIGHWAY_TO_HELL", "Highway to hell", new ItemStack(Material.NETHERRACK), "Placeholder", "Lore");
    public static final Achievement GLOWING_IN_THE_DARK = new Achievement("GLOWING_IN_THE_DARK", "Glowing in the dark", new ItemStack(Material.SPECTRAL_ARROW), "Placeholder", "Lore");
    public static final Achievement SENOR_BOOM_BOOM = new Achievement("SENOR_BOOM_BOOM", "Senor boom boom", new ItemStack(Material.TNT), "Placeholder", "Lore");

    private String fileName;
    String name;
    private ItemStack icon;
    private ArrayList<String> description = new ArrayList<>();

    private Achievement(String fileName, String name, ItemStack icon, String... description) {
        this.fileName = fileName;
        this.name = name;
        this.icon = icon;
        Collections.addAll(this.description, description);
    }

    public static boolean isAchievement(Achievement a, Achievement b) {
        return a.fileName.equals(b.fileName);
    }

    public static Achievement getAchievement(int index) {
        switch (index) {
            case 0:
                return SLAYER;
            case 1:
                return WINNER;
            case 2:
                return DRAGON_SLAYER;
            case 3:
                return OMG_DIAMONDS;
            case 4:
                return BURN_BABY_BURN;
            case 5:
                return HIGHWAY_TO_HELL;
            case 6:
                return GLOWING_IN_THE_DARK;
            case 7:
                return SENOR_BOOM_BOOM;
            default:
                return SLAYER;
        }
    }

    public Achievement getCodeAchievement() {
        switch (fileName) {
            case "SLAYER":
                return SLAYER;
            case "WINNER":
                return WINNER;
            case "DRAGON_SLAYER":
                return DRAGON_SLAYER;
            case "OMG_DIAMONDS":
                return OMG_DIAMONDS;
            case "BURN_BABY_BURN":
                return BURN_BABY_BURN;
            case "HIGHWAY_TO_HELL":
                return HIGHWAY_TO_HELL;
            case "GLOWING_IN_THE_DARK":
                return GLOWING_IN_THE_DARK;
            case "SENOR_BOOM_BOOM":
                return SENOR_BOOM_BOOM;
            default:
                return this;
        }
    }

    public void copyFrom(Achievement achievement) {
        this.name = achievement.name;
        this.icon = achievement.getIcon(false, false);
        this.description = achievement.description;
    }

    public ItemStack getIcon(boolean unlocked, boolean lore) {
        ArrayList<String> desc = description;

        if (lore) {
            desc.add(0, " ");
            desc.add(1, AchievementAddon.getAchievementOptions().getLockingStatus(unlocked));
            desc.add(2, " ");
        }

        return new ItemBuilder(icon).setName(name).setLore(desc.toArray(new String[desc.size()]))
                .addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE).build();
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "fileName='" + fileName + '\'' +
                ", name='" + name + '\'' +
                ", icon=" + icon +
                ", description=" + description +
                '}';
    }

    @Override
    public JsonElement toFileValue() {
        return SimpleJSONFile.gson.toJsonTree(this);
    }
}
