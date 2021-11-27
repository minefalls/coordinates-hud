package xyz.minefalls.minefallscoordinatehud;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class ConfigManager {

    private static final MineFallsCoordinateHUD instance__ = MineFallsCoordinateHUD.getInstance();
    private static FileConfiguration configg = instance__.getConfig();
    private static List<String> coordinatedplayers = configg.getStringList("coordinatedcoordinatedplayers");
    private static List<String> disabledCoordinatePlayers = configg.getStringList("disabledcoordinate");


    public static boolean checkPlayerList(Player player){

        /* checks if the player is in the enabled coordinates list */

        return coordinatedplayers.contains(player.getDisplayName());
    }

    public static boolean checkDisabledPlayerList(Player player){
        return disabledCoordinatePlayers.contains(player.getDisplayName());
    }

    public static void addDisabledPlayer(Player player){
        if (!disabledCoordinatePlayers.contains(player.getDisplayName())){
            disabledCoordinatePlayers.add(player.getDisplayName());
        }
        configg.set("disabledcoordinate", disabledCoordinatePlayers);
        instance__.saveConfig();
    }

    public static void removeDisabledPlayer(Player player){
        disabledCoordinatePlayers.remove(player.getDisplayName());
        configg.set("disabledcoordinate", disabledCoordinatePlayers);
        instance__.saveConfig();
    }

    public static void addPlayer(Player player){

        /* adds the player to the coordinate-enabled list */

        if (!coordinatedplayers.contains(player.getDisplayName())){
            coordinatedplayers.add(player.getDisplayName());
        }
        configg.set("coordinatedcoordinatedplayers", coordinatedplayers);
        instance__.saveConfig();
    }

    public static void removePlayer(Player player){

        /* removes the player from the coordinate-enabled list */

        coordinatedplayers.remove(player.getDisplayName());
        configg.set("coordinatedcoordinatedplayers", coordinatedplayers);
        instance__.saveConfig();
    }
}
