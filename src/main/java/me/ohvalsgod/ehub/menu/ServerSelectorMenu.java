package me.ohvalsgod.ehub.menu;

import me.ohvalsgod.redstone.shared.RedstoneSharedAPI;
import me.ohvalsgod.redstone.shared.server.RedstoneServer;
import me.ohvalsgod.redstone.shared.server.ServerState;
import lombok.val;
import me.ohvalsgod.bukkitlib.menu.Button;
import me.ohvalsgod.bukkitlib.menu.Menu;
import me.ohvalsgod.bukkitlib.util.BungeeUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.*;

public class ServerSelectorMenu extends Menu {

    private RedstoneServer kitpvp;
    private RedstoneServer teams;

    public ServerSelectorMenu() {
        kitpvp = RedstoneSharedAPI.getServer("KitPvP");
        teams = RedstoneSharedAPI.getServer("Teams");
    }

    @Override
    public String getTitle(Player player) {
        return "§8Select a server to join";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        val buttons = new HashMap<Integer, Button>();

        Button filler = Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 15, "");

//        buttons.put(10, new Button() {
//            @Override
//            public String getName(Player player) {
//                return RedstoneSharedAPI.getServer("HCFactions").getData().getState() != ServerState.OFFLINE ? "§a§lHCFactions":"§c§lHCFactions";
//            }
//
//            @Override
//            public List<String> getDescription(Player player) {
//                RedstoneServer redstoneServer = RedstoneSharedAPI.getServer("  ");
//                return Arrays.asList(
//                        String.format("§ePlayers: §f(%s/%s)", redstoneServer.getData().getOnlinePlayers(), redstoneServer.getData().getMaxPlayers()),
//                        "",
//                        "§7* §b30 man factions!",
//                        "§7* §b15 minute KOTHs!",
//                        "§7* §bOther weekly events!",
//                        "",
//                        (redstoneServer.getData().getState() != ServerState.OFFLINE ? (redstoneServer.getData().getState() != ServerState.ONLINE ? "§c" + redstoneServer.getServerID() + " is whitelisted.":"§7⟹ §eClick to join the queue! §7⟸"):"§eThis server is offline."));
//            }
//
//            @Override
//            public Material getMaterial(Player player) {
//                return RedstoneSharedAPI.getServer("HCFactions").getData().getState() != ServerState.OFFLINE ? Material.DIAMOND_AXE:Material.REDSTONE_BLOCK;
//            }
//
//            @Override
//            public byte getDamageValue(Player player) {
//                return 0;
//            }
//
//            @Override
//            public void clicked(Player player, int i, ClickType clickType, int i1) {
//                if (RedstoneSharedAPI.getServer("HCFactions").getData().getState() != ServerState.OFFLINE) {
//                    BungeeUtils.send(player, "HCFactions");
//                }
//            }
//        });

        buttons.put(11, new Button() {

            @Override
            public String getName(Player player) {
                return kitpvp.getData().getState() != ServerState.OFFLINE ? "§a§lKitPvP":"§c§lKitPvP";
            }

            @Override
            public List<String> getDescription(Player player) {
                List<String> lore = new ArrayList<>(getHeaderForServer(kitpvp));
                lore.add("§7* §bWeekly events!");
                lore.add("§7* §bDaily challenges!");
                lore.add("§7* §bKillstreak rewards!");
                lore.add("§7* §bCustom enchants!");
                lore.add("§7* §bBountys!");
                lore.addAll(getFooterForServer(kitpvp));
                return lore;
            }

            @Override
            public Material getMaterial(Player player) {
                return kitpvp.getData().getState() != ServerState.OFFLINE ? Material.GOLDEN_APPLE:Material.REDSTONE_BLOCK;
            }

            @Override
            public byte getDamageValue(Player player) {
                return 0;
            }

            @Override
            public void clicked(Player player, int i, ClickType clickType, int i1) {
                if (kitpvp.getData().getState() != ServerState.OFFLINE) {
                    BungeeUtils.send(player, "KitPvP");
                }
            }
        });

        buttons.put(15, new Button() {

            @Override
            public String getName(Player player) {
                return RedstoneSharedAPI.getServer("Teams").getData().getState() != ServerState.OFFLINE ? "§a§lTeams":"§c§lTeams";
            }

            @Override
            public List<String> getDescription(Player player) {
                List<String> lore = new ArrayList<>(getHeaderForServer(teams));
                lore.add("§7* §b15 man factions!");
                lore.add("§7* §b10 minutes KOTHs!");
                lore.add("§7* §bOther weekly events!");
                lore.addAll(getFooterForServer(teams));

                return lore;
            }

            @Override
            public Material getMaterial(Player player) {
                return teams.getData().getState() != ServerState.OFFLINE ? Material.DIAMOND_SWORD:Material.REDSTONE_BLOCK;
            }

            @Override
            public byte getDamageValue(Player player) {
                return 0;
            }

            @Override
            public void clicked(Player player, int i, ClickType clickType, int i1) {
                if (teams.getData().getState() != ServerState.OFFLINE) {
                    BungeeUtils.send(player, "Teams");
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

    private List<String> getHeaderForServer(RedstoneServer server) {
        List<String> lore = new ArrayList<>();

        if (teams.getData().getState() != ServerState.OFFLINE) {
            lore.add(String.format("§ePlayers: §f(%s/%s)", teams.getData().getOnlinePlayers(), teams.getData().getMaxPlayers()));

            if (teams.getData().getState() == ServerState.WHITELISTED) {
                lore.add("§cThis server is whitelisted.");
            }
        }

        lore.add("");
        return lore;
    }

    private List<String> getFooterForServer(RedstoneServer server) {
        List<String> lore = new ArrayList<>();

        lore.add("");

        if (teams.getData().getState() != ServerState.OFFLINE) {
            lore.add("§7⟹ §eClick to join the queue! §7⟸");
        } else {
            lore.add("§cThis server is offline.");
        }

        return lore;
    }

}
