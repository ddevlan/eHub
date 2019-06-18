package me.ohvalsgod.ehub.listener;

import me.ohvalsgod.ehub.HubPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.List;

public class HubHandler implements Listener {

    private HubPlugin plugin;

    private List<String> motd;

    public HubHandler(HubPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        motd = plugin.getConfigHelper().getStringList("motd");
    }

    @EventHandler
    public void move(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (event.getTo().getY() <= 0) {
            player.teleport(player.getWorld().getSpawnLocation());
        }

        if (player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
            if (player.isOnGround()) {
                if (!player.getAllowFlight()) {
                    player.setAllowFlight(true);
                }
            }
        }
    }

    @EventHandler
    public void toggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
            event.setCancelled(true);
            player.setFlying(false);
            player.setAllowFlight(false);
            player.setVelocity(player.getLocation().getDirection().multiply(1.5f).setY(1.2f));
            player.getWorld().playSound(player.getLocation(), Sound.BAT_TAKEOFF, 1.0F, 1.0F);
            player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 4);
            player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 4);
        }
    }

    @EventHandler
    public void damage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void food(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
    }

    @EventHandler
    public void quit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
        event.getPlayer().setWalkSpeed(0.6f);
        plugin.getHubInventory().update(event.getPlayer());

        for (String string : motd) {
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', string.replace("{display_name}", event.getPlayer().getDisplayName())));
        }
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        if (!event.getPlayer().hasPermission("ehub.build.break")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().hasPermission("ehub.build.place")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void pickup(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void click(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            if (event.getWhoClicked().getGameMode() != GameMode.CREATIVE) {
                event.setCancelled(true);
                plugin.getHubInventory().update((Player) event.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void weather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void dismount(EntityDismountEvent event) {
        if (event.getDismounted().hasMetadata("enderbutt")) {
            event.getDismounted().remove();
        }
    }

    @EventHandler
    public void hit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof EnderPearl) {
            event.getEntity().remove();
        }
    }

    @EventHandler
    public void spawn(EntitySpawnEvent event) {
        if (!(event.getEntity() instanceof Player || event.getEntity() instanceof Projectile)) {
            event.setCancelled(true);
        }
    }

}
