package me.ohvalsgod.ehub.menu;

import lombok.val;
import me.ohvalsgod.bklib.menu.Button;
import me.ohvalsgod.bklib.menu.Menu;
import me.ohvalsgod.bklib.util.BungeeUtils;
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
                return "§a§lHCF";
            }

            @Override
            public List<String> getDescription(Player player) {
                return Arrays.asList(
                        "§ePlayers: §f({current_players}/{max_players})",
                        "",
                        "§7* §b30 man factions!",
                        "§7* §b15 minute KOTHs!",
                        "§7* §bOther weekly events!",
                        "",
                        "§7↣ §eClick to join the queue! §7↢");
            }

            @Override
            public Material getMaterial(Player player) {
                return Material.DIAMOND_SWORD;
            }

            @Override
            public byte getDamageValue(Player player) {
                return 0;
            }

            @Override
            public void clicked(Player player, int i, ClickType clickType, int i1) {
                BungeeUtils.send(player, "hcf");
            }
        });

        buttons.put(15, new Button() {
            @Override
            public String getName(Player player) {
                return "§a§lDeveloper Test";
            }

            @Override
            public List<String> getDescription(Player player) {
                return Arrays.asList(
                        "§ePlayers: §f({current_players}/{max_players})",
                        "",
                        "§7* §bNew gamemodes coming soon!!",
                        "§7* §bCheck @twitter for regular updates!!",
                        "§7* §bTop secrete clearance ☺",
                        "",
                        "§7↣ §eClick to join the queue! §7↢");
            }

            @Override
            public Material getMaterial(Player player) {
                return Material.DIAMOND_AXE;
            }

            @Override
            public byte getDamageValue(Player player) {
                return 0;
            }

            @Override
            public void clicked(Player player, int i, ClickType clickType, int i1) {
                BungeeUtils.send(player, "dev");
            }
        });

        for (int i = 0; i < 27; i++) {
            if (!buttons.containsKey(i)) {
                buttons.put(i, filler);
            }
        }

        return buttons;
    }
}
