package xyz.minefalls.minefallscoordinatehud.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.minefalls.minefallscoordinatehud.ConfigManager;

public class CoordinateCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("You can't execute this command :(");
        }
        Player player = (Player) sender;

        if (args.length == 0 || !args[0].equalsIgnoreCase("toggle")){
            player.sendRawMessage("/coordinates toggle");
            return true;
        }

        if (ConfigManager.checkPlayerList(player) && ConfigManager.checkDisabledPlayerList(player)){
            ConfigManager.removePlayer(player);
            player.sendMessage("Your coordinates have been disabled");
            return true;
        }

        if (!ConfigManager.checkPlayerList(player) && ConfigManager.checkDisabledPlayerList(player)){
            ConfigManager.addPlayer(player);
            ConfigManager.removeDisabledPlayer(player);
            player.sendMessage("Your coordinates have been enabled");
            return true;
        }

        if (ConfigManager.checkPlayerList(player) && !ConfigManager.checkDisabledPlayerList(player)){
            ConfigManager.removePlayer(player);
            ConfigManager.addDisabledPlayer(player);
            player.sendMessage("Your coordinates have been disabled");
            return true;
        }

        return false;
    }
}
