package me.ohvalsgod.ehub;

import lombok.Getter;
import me.ohvalsgod.bridge.BridgeAPI;
import me.ohvalsgod.bridge.BridgePlugin;
import me.ohvalsgod.bukkitlib.BukkitLib;
import me.ohvalsgod.bukkitlib.board.Assemble;
import me.ohvalsgod.bukkitlib.command.CommandHandler;
import me.ohvalsgod.bukkitlib.config.ConfigHelper;
import me.ohvalsgod.ehub.board.HubAssembleAdapter;
import me.ohvalsgod.ehub.interactable.InteractableItem;
import me.ohvalsgod.ehub.inventory.HubInventory;
import me.ohvalsgod.ehub.listener.BungeeHandler;
import me.ohvalsgod.ehub.listener.HubHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class HubPlugin extends JavaPlugin {

    @Getter private static HubPlugin instance;

    private HubInventory hubInventory;
    private ConfigHelper configHelper;
    private BridgeAPI bridgeAPI;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        bridgeAPI = new BridgeAPI(getPlugin(BridgePlugin.class));

        configHelper = new ConfigHelper("config", getDataFolder());

        new InteractableItem(instance);
        hubInventory = new HubInventory(instance);

        new HubHandler(instance);

        BukkitLib.getLibrary().setAssemble(new Assemble(instance, new HubAssembleAdapter()));

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeHandler());

        Bukkit.getOnlinePlayers().forEach(o -> hubInventory.update(o));

        CommandHandler.loadCommandsFromPackage(this, "me.ohvalsgod.ehub.command");
    }

    @Override
    public void onDisable() {
        BukkitLib.getLibrary().getAssemble().cleanup();
    }
}
