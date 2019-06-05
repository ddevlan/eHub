package me.ohvalsgod.ehub.board;

import me.ohvalsgod.bklib.board.AssembleAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class HubAssembleAdapter implements AssembleAdapter {
    @Override
    public String getTitle(Player player) {
        return "&6&lElusiveMC Network";
    }

    @Override
    public List<String> getLines(Player player) {
        return Arrays.asList(
                "&7&m--------------------",
                "&6Online:",
                "" + Bukkit.getOnlinePlayers().size(),
                " ",
                "&6Rank:",
                "Default",
                " ",
                "&6www.elusivemc.net",
                "&7&m--------------------"
        );
    }
}
