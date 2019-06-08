package me.ohvalsgod.ehub.command;

import me.ohvalsgod.bukkitlib.command.Command;
import org.bukkit.entity.Player;

public class HubCommands {

    @Command(
            names = "setspawn",
            permissionNode = "ehub.spawn.set"
    )
    public static void setSpawn(Player player) {
        player.getWorld().setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
        player.sendMessage(String.format("§aSet spawn location to: §e%s§f, §e%s§f, §e%s", player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ()));
    }

    @Command(
            names = "spawn"
    )
    public static void spawn(Player player) {
        player.teleport(player.getWorld().getSpawnLocation());
        player.sendMessage("§aTeleported!");
    }

}
