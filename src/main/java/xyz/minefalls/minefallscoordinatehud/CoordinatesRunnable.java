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
                        ChatColor.GOLD + "X: " + ChatColor.RESET + Math.round(player.getLocation().getX()) +
                        ChatColor.GOLD + "Y: " + ChatColor.RESET + Math.round(player.getLocation().getY()) +
                        ChatColor.GOLD + "Z: " + ChatColor.RESET + Math.round(player.getLocation().getZ()) +
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

        String direction = "South";
        float yaw = player.getLocation().getYaw();

        if ( yaw < 0 ) yaw += 360;
        yaw %= 360;

        int i = (int) ((yaw + 8) / 22.5);

        /*
         * stupid ass direction finding logic
         *
         * probably sucks but i don't wanna fix it
         */
        switch(i){
            case 0:
                direction = "South";
                break;
            case 1:
            case 2:
            case 3:
                direction = "South-West";
                break;
            case 4:
                direction = "West";
                break;
            case 5:
            case 6:
            case 7:
                direction = "North-West";
                break;
            case 8:
                direction = "North";
                break;
            case 9:
            case 10:
            case 11:
                direction = "North-East";
                break;
            case 12:
                direction = "East";
                break;
            case 13:
            case 14:
            case 15:
                direction = "South-East";
                break;
        }
        return direction;
    }
}
