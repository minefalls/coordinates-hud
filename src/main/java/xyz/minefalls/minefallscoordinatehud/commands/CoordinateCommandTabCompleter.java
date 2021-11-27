package xyz.minefalls.minefallscoordinatehud.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoordinateCommandTabCompleter implements TabCompleter {

    private final List<String> toggleCommand = Arrays.asList("toggle");
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return (args.length > 0) ? StringUtil.copyPartialMatches(args[0], toggleCommand, new ArrayList<String>()) : null;
    }
}
