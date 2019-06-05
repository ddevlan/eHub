package me.ohvalsgod.ehub.inventory;

import me.ohvalsgod.bklib.item.ItemBuilder;
import me.ohvalsgod.ehub.HubPlugin;
import me.ohvalsgod.ehub.interactable.InteractableItem;
import me.ohvalsgod.ehub.menu.ServerSelectorMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HubInventory {

    private Map<UUID, Long> toggleCooldown;
    private Map<Integer, ItemStack> items;

    public HubInventory(HubPlugin plugin) {
        items = new HashMap<>();
        toggleCooldown = new HashMap<>();

        items.put(40, new ItemBuilder()
                .withMaterial(Material.INK_SACK)
                .withData((byte)10)
                .withDisplayName("§9§l↣ §a§lShow Players §9§l↢")
                .create());

        items.put(0, new ItemBuilder()
                .withMaterial(Material.WATCH)
                .withDisplayName("§9§l↣ §e§lServer Selector §9§l↢")
                .create());

        items.put(1, new ItemBuilder()
                .withMaterial(Material.INK_SACK)
                .withData((byte)8)
                .withDisplayName("§9§l↣ §7§lHide Players §9§l↢")
                .create());

        items.put(4, new ItemBuilder()
                .withMaterial(Material.FEATHER)
                .withDisplayName("§9§l↣ §b§lCosmetics §9§l↢")
                .create());

        items.put(8, new ItemBuilder()
                .withMaterial(Material.ENDER_PEARL)
                .withDisplayName("§9§l↣ §5§lEnder Butt §9§l↢")
                .create());

        //  Server selector
        InteractableItem.getInteractions().put(items.get(0), player -> {
            new ServerSelectorMenu().openMenu(player);
        });

        //  Show players
        InteractableItem.getInteractions().put(items.get(40), player -> {
            if (System.currentTimeMillis() - toggleCooldown.getOrDefault(player.getUniqueId(), 0L) >= 5000) {
                for (Player o : Bukkit.getOnlinePlayers()) {
                    if (!player.getName().equals(o.getName())) {
                        player.showPlayer(o);
                    }
                }
                player.setItemInHand(items.get(1));
                player.updateInventory();
                player.sendMessage("§aYou have toggled player visibility on!");
                toggleCooldown.put(player.getUniqueId(), System.currentTimeMillis());
            } else {
                double magic = ((toggleCooldown.get(player.getUniqueId()) + 5000) - System.currentTimeMillis());
                player.sendMessage(String.format("§cPlease wait %s to toggle player visibility!", magic/1000 + "s"));
            }
        });

        //  Hide players
        InteractableItem.getInteractions().put(items.get(1), player -> {
            if (System.currentTimeMillis() - toggleCooldown.getOrDefault(player.getUniqueId(), 0L) >= 5000) {
                for (Player o : Bukkit.getOnlinePlayers()) {
                    if (!player.getName().equals(o.getName())) {
                        player.hidePlayer(o);
                    }
                }
                player.setItemInHand(items.get(40));
                player.updateInventory();
                player.sendMessage("§cYou have toggled player visibility off!");
                toggleCooldown.put(player.getUniqueId(), System.currentTimeMillis());
            } else {
                double magic = ((toggleCooldown.get(player.getUniqueId()) + 5000) - System.currentTimeMillis());
                player.sendMessage(String.format("§cPlease wait %s to toggle player visibility!", magic/1000 + "s"));
            }
        });

        //  Cosmetics
        InteractableItem.getInteractions().put(items.get(4), player -> {
            player.sendMessage("§cCosmetics coming soon...");
        });

        //  Ender butt
        InteractableItem.getInteractions().put(items.get(8), player -> {
            if (player.isInsideVehicle()) {
                player.getVehicle().remove();
            }

            EnderPearl pearl = player.launchProjectile(EnderPearl.class);
            pearl.setMetadata("enderbutt", new FixedMetadataValue(plugin, "true"));
            pearl.setPassenger(player);
            pearl.setShooter(player);
            pearl.setVelocity(player.getLocation().getDirection().multiply(2.0f));
            pearl.setBounce(true);

//            Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
//            pig.setMetadata("enderbutt", new FixedMetadataValue(plugin, "true"));
//            pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 60, 1));
//            pig.setPassenger(player);
//            pig.setVelocity(player.getLocation().getDirection().multiply(4.0f));
//
//            new BukkitRunnable() {
//                @Override
//                public void run() {
//                    pig.setPassenger(null);
//                    pig.remove();
//                }
//            }.runTaskLater(plugin, 15 * 2);
        });
    }

    public void update(Player player) {
        player.getInventory().clear();
        items.forEach((integer, itemStack) -> {
            if (integer < 36) {
                player.getInventory().setItem(integer, itemStack);
            }
        });
        player.updateInventory();
    }



}
