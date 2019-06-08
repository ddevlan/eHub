package me.ohvalsgod.ehub.interactable;

import lombok.Getter;
import me.ohvalsgod.bukkitlib.util.Callback;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class InteractableItem implements Listener {

    @Getter private static Map<ItemStack, Callback<Player>> interactions;

    private ItemStack itemStack;

    public InteractableItem(JavaPlugin plugin) {
        interactions = new HashMap<>();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public InteractableItem(ItemStack itemStack, Callback<Player> callback) {
        this.itemStack = itemStack;
        interactions.put(itemStack, callback);
    }

    @EventHandler
    public void interact(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player player = event.getPlayer();

            if (player.getItemInHand() != null) {
                if (player.getItemInHand().hasItemMeta()) {
                    if (interactions.keySet().contains(player.getItemInHand())) {
                        event.setCancelled(true);
                        interactions.get(player.getItemInHand()).callback(player);
                        player.updateInventory();
                    }
                }
            }
        }
    }

}
