package me.ohvalsgod.ehub.menu;

import io.github.thatkawaiisam.redstone.shared.RedstoneSharedAPI;
import io.github.thatkawaiisam.redstone.shared.server.RedstoneServer;
import io.github.thatkawaiisam.redstone.shared.server.ServerState;
import lombok.val;
import me.ohvalsgod.bukkitlib.menu.Button;
import me.ohvalsgod.bukkitlib.menu.Menu;
import me.ohvalsgod.bukkitlib.util.BungeeUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerSelectorMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "§8Select a server to join";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        val buttons = new HashMap<Integer, Button>();

        Button filler = Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 15, "");

        buttons.put(11, new Button() {
            @Override
            public String getName(Player player) {
                return RedstoneSharedAPI.getServer("HCFactions").getData().getState() != ServerState.OFFLINE ? "§a§lHCFactions":"§c§lHCFactions";
            }

            @Override
            public List<String> getDescription(Player player) {
                RedstoneServer redstoneServer = RedstoneSharedAPI.getServer("HCFactions");
                return Arrays.asList(
                        String.format("§ePlayers: §f(%s/%s)", redstoneServer.getData().getOnlinePlayers(), redstoneServer.getData().getMaxPlayers()),
                        "",
                        "§7* §b30 man factions!",
                        "§7* §b15 minute KOTHs!",
                        "§7* §bOther weekly events!",
                        "",
                        (redstoneServer.getData().getState() != ServerState.OFFLINE ? (redstoneServer.getData().getState() != ServerState.ONLINE ? "§c" + redstoneServer.getServerID() + " is whitelisted.":"§7⟹ §eClick to join the queue! §7⟸"):"§eThis server is offline."));
            }

            @Override
            public Material getMaterial(Player player) {
                return RedstoneSharedAPI.getServer("HCFactions").getData().getState() != ServerState.OFFLINE ? Material.DIAMOND_AXE:Material.REDSTONE_BLOCK;
            }

            @Override
            public byte getDamageValue(Player player) {
                return 0;
            }

            @Override
            public void clicked(Player player, int i, ClickType clickType, int i1) {
                if (RedstoneSharedAPI.getServer("HCFactions").getData().getState() != ServerState.OFFLINE) {
                    BungeeUtils.send(player, "HCFactions");
                }
            }
        });

        buttons.put(15, new Button() {

            @Override
            public String getName(Player player) {
                return RedstoneSharedAPI.getServer("Dev-1").getData().getState() != ServerState.OFFLINE ? "§a§lDeveloper Test":"§c§lDeveloper Test";
            }

            @Override
            public List<String> getDescription(Player player) {
                RedstoneServer redstoneServer = RedstoneSharedAPI.getServer("Dev-1");
                return Arrays.asList(
                        String.format("§ePlayers: §f(%s/%s)", redstoneServer.getData().getOnlinePlayers(), redstoneServer.getData().getMaxPlayers()),
                        "",
                        "§7* §bNew gamemodes coming soon!!",
                        "§7* §bCheck @twitter for regular updates!!",
                        "§7* §bTop secrete clearance ☺",
                        "",
                        (redstoneServer.getData().getState() != ServerState.OFFLINE ? (redstoneServer.getData().getState() != ServerState.ONLINE ? "§c" + redstoneServer.getServerID() + " is whitelisted.":"§7⟹ §eClick to join the queue! §7⟸"):"§eThis server is offline."));
            }

            @Override
            public Material getMaterial(Player player) {
                return RedstoneSharedAPI.getServer("Dev-1").getData().getState() != ServerState.OFFLINE ? Material.DIAMOND_AXE:Material.REDSTONE_BLOCK;
            }

            @Override
            public byte getDamageValue(Player player) {
                return 0;
            }

            @Override
            public void clicked(Player player, int i, ClickType clickType, int i1) {
                if (RedstoneSharedAPI.getServer("Dev-1").getData().getState() != ServerState.OFFLINE) {
                    BungeeUtils.send(player, "Dev-1");
                }
            }
        });

        for (int i = 0; i < 27; i++) {
            if (!buttons.containsKey(i)) {
                buttons.put(i, filler);
            }
        }

        return buttons;
    }

    @Override
    public boolean isAutoUpdate() {
        return true;
    }
}
