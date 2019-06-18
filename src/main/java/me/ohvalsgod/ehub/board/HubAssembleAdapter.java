package me.ohvalsgod.ehub.board;

import me.ohvalsgod.bukkitlib.board.AssembleAdapter;
import me.ohvalsgod.ehub.HubPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HubAssembleAdapter implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        return HubPlugin.getInstance().getConfigHelper().getString("scoreboard.title");
    }

    @Override
    public List<String> getLines(Player player) {
        ArrayList<String> lines = new ArrayList<>(HubPlugin.getInstance().getConfigHelper().getStringList("scoreboard.lines"));
        lines.replaceAll(s -> s.replace("{current_players}", String.valueOf(Bukkit.getOnlinePlayers().size())));
        lines.replaceAll(s -> s.replace("{player_group}", HubPlugin.getInstance().getBridgeAPI().getUser(player.getUniqueId()).getActiveGrant().getPermissionsGroup().getName()));
        return lines;
    }
}
