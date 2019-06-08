package me.ohvalsgod.ehub.board;

import me.ohvalsgod.bukkitlib.board.AssembleAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class HubAssembleAdapter implements AssembleAdapter {
    @Override
    public String getTitle(Player player) {
        return "&5&lElusiveMC Network";
    }

    @Override
    public List<String> getLines(Player player) {
        return Arrays.asList(
                "&5&m--------------------",
                "&5&lOnline:",
                "" + Bukkit.getOnlinePlayers().size(),
                " ",
                "&5&lRank:",
                "Default",
                " ",
                "&5www.elusivemc.net",
                "&5&m--------------------"
        );
    }
}
