package xyz.minefalls.minefallscoordinatehud;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.minefalls.minefallscoordinatehud.commands.CoordinateCommandTabCompleter;
import xyz.minefalls.minefallscoordinatehud.commands.CoordinateCommands;

public final class MineFallsCoordinateHUD extends JavaPlugin {

    private static MineFallsCoordinateHUD instance;

    public static MineFallsCoordinateHUD getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        this.saveConfig();

        instance = this;

        getCommand("coordinates").setExecutor(new CoordinateCommands());
        getCommand("coordinates").setTabCompleter(new CoordinateCommandTabCompleter());

        new CoordinatesRunnable().runTaskTimerAsynchronously(this, 0, this.getConfig().getInt("ticks"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
