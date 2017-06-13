package de.alphahelix.achievementaddon.listener;

import de.alphahelix.achievementaddon.AchievementAddon;
import de.alphahelix.achievementaddon.instances.Achievement;
import de.alphahelix.alphalibary.listener.SimpleListener;
import de.alphahelix.uhcremastered.UHC;
import de.alphahelix.uhcremastered.enums.GState;
import de.alphahelix.uhcremastered.instances.PlayerStatistic;
import de.alphahelix.uhcremastered.utils.StatsUtil;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class AchievementListener extends SimpleListener {

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        if (GState.is(GState.LOBBY) || GState.is(GState.RESTART)) return;
        if (e.getEntity().getKiller() == null) return;
        PlayerStatistic stats = StatsUtil.getStatistics(e.getEntity().getKiller());

        if (e.getEntityType() == EntityType.ENDER_DRAGON) {
            if (!stats.hasCustomStatistic(Achievement.DRAGON_SLAYER)) {
                stats.addCustomStatistic(Achievement.DRAGON_SLAYER);
                e.getEntity().getKiller().sendMessage(UHC.getGameOptions().getChatPrefix() + AchievementAddon.getAchievementOptions().getJustUnlocked(Achievement.DRAGON_SLAYER));
                StatsUtil.pushCache(e.getEntity().getKiller());
            }
        } else if (e.getEntityType() == EntityType.PLAYER) {
            if (e.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FIRE ||
                    e.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FIRE_TICK ||
                    e.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.LAVA) {
                if(!stats.hasCustomStatistic(Achievement.BURN_BABY_BURN)) {
                    stats.addCustomStatistic(Achievement.BURN_BABY_BURN);
                    e.getEntity().getKiller().sendMessage(UHC.getGameOptions().getChatPrefix() + AchievementAddon.getAchievementOptions().getJustUnlocked(Achievement.BURN_BABY_BURN));
                    StatsUtil.pushCache(e.getEntity().getKiller());
                }
            } else if (e.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
                if (!stats.hasCustomStatistic(Achievement.SENOR_BOOM_BOOM)) {
                    stats.addCustomStatistic(Achievement.SENOR_BOOM_BOOM);
                    e.getEntity().getKiller().sendMessage(UHC.getGameOptions().getChatPrefix() + AchievementAddon.getAchievementOptions().getJustUnlocked(Achievement.SENOR_BOOM_BOOM));
                    StatsUtil.pushCache(e.getEntity().getKiller());
                }
            }

            if(stats.getKills() >= 500) {
                if(!stats.hasCustomStatistic(Achievement.SLAYER)) {
                    stats.addCustomStatistic(Achievement.SLAYER);
                    e.getEntity().getKiller().sendMessage(UHC.getGameOptions().getChatPrefix() + AchievementAddon.getAchievementOptions().getJustUnlocked(Achievement.SLAYER));
                    StatsUtil.pushCache(e.getEntity().getKiller());
                }
            }
        }
    }

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        if (GState.is(GState.LOBBY) || GState.is(GState.RESTART)) return;
        if(e.getBlock().getType() != Material.DIAMOND_ORE) return;
        PlayerStatistic stats = StatsUtil.getStatistics(e.getPlayer());

        if(!stats.hasCustomStatistic(Achievement.OMG_DIAMONDS)) {
            stats.addCustomStatistic(Achievement.OMG_DIAMONDS);
            e.getPlayer().sendMessage(UHC.getGameOptions().getChatPrefix() + AchievementAddon.getAchievementOptions().getJustUnlocked(Achievement.OMG_DIAMONDS));
            StatsUtil.pushCache(e.getPlayer());
        }
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent e) {
        if(GState.is(GState.LOBBY) || GState.is(GState.RESTART)) return;
        if(e.getCause() != PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) return;
        if(e.getTo().getWorld().getEnvironment() != World.Environment.NETHER) return;
        PlayerStatistic stats = StatsUtil.getStatistics(e.getPlayer());

        if(!stats.hasCustomStatistic(Achievement.HIGHWAY_TO_HELL)) {
            stats.addCustomStatistic(Achievement.HIGHWAY_TO_HELL);
            e.getPlayer().sendMessage(UHC.getGameOptions().getChatPrefix() + AchievementAddon.getAchievementOptions().getJustUnlocked(Achievement.HIGHWAY_TO_HELL));
            StatsUtil.pushCache(e.getPlayer());
        }
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent e) {
        if(GState.is(GState.LOBBY) || GState.is(GState.RESTART)) return;
        if (!(e.getItem().getType().name().contains("HELMET") ||
                e.getItem().getType().name().contains("CHESTPLATE") ||
                e.getItem().getType().name().contains("LEGGINGS") ||
                e.getItem().getType().name().contains("BOOTS"))) return;
        PlayerStatistic stats = StatsUtil.getStatistics(e.getEnchanter());

        if(!stats.hasCustomStatistic(Achievement.GLOWING_IN_THE_DARK)) {
            stats.addCustomStatistic(Achievement.GLOWING_IN_THE_DARK);
            e.getEnchanter().sendMessage(UHC.getGameOptions().getChatPrefix() + AchievementAddon.getAchievementOptions().getJustUnlocked(Achievement.GLOWING_IN_THE_DARK));
            StatsUtil.pushCache(e.getEnchanter());
        }
    }
}
