package xyz.minefalls.minefallscoordinatehud;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CoordinatesRunnable extends BukkitRunnable {

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            if (!ConfigManager.checkPlayerList(player) && !ConfigManager.checkDisabledPlayerList(player)) {

                ConfigManager.addPlayer(player);

                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, (new ComponentBuilder("" +
                        ChatColor.GOLD + "X: " + ChatColor.RESET + Math.round(player.getLocation().getX()) + ChatColor.GRAY + " | " +
                        ChatColor.GOLD + "Y: " + ChatColor.RESET + Math.round(player.getLocation().getY()) + ChatColor.GRAY + " | " +
                        ChatColor.GOLD + "Z: " + ChatColor.RESET + Math.round(player.getLocation().getZ()) + ChatColor.GRAY + " | " +
                        ChatColor.GOLD + getPlayerDirection(player)
                )).create());
            }

            if (ConfigManager.checkPlayerList(player)){
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, (new ComponentBuilder("" +
                        ChatColor.GOLD + "X: " + ChatColor.RESET + Math.round(player.getLocation().getX()) + ChatColor.GRAY + " | " +
                        ChatColor.GOLD + "Y: " + ChatColor.RESET + Math.round(player.getLocation().getY()) + ChatColor.GRAY + " | " +
                        ChatColor.GOLD + "Z: " + ChatColor.RESET + Math.round(player.getLocation().getZ()) + ChatColor.GRAY + " | " +
                        ChatColor.GOLD + getPlayerDirection(player)
                )).create());
            }
        }
    }

    public static String getPlayerDirection(Player player){

        int yaw = (int) player.getLocation().getYaw();
        if (yaw < 0) yaw += 360;
        yaw /= 22.5;

        return switch (yaw) {
            case 0, 15 -> "South";
            case 1, 2 -> "South-West";
            case 3, 4 -> "West";
            case 5, 6 -> "North-West";
            case 7, 8 -> "North";
            case 9, 10 -> "North-East";
            case 11, 12 -> "East";
            case 13, 14 -> "South-East";
            default -> "";
        };
    }
}
