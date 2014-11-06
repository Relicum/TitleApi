/*
 * TitleApi is a development API for Minecraft Titles and Tabs, developed by Relicum
 * Copyright (C) 2014.  Chris Lutte
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.relicum.titleapi;

import com.relicum.titleapi.Components.ChatSerialize;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Name: PlaceCommand.java Created: 06 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
class PlaceCommand implements TabExecutor {

    private List<String> HTYPES;
    private List<String> players;
    private List<String> stats;
    private final Placeholder placeholder;

    public PlaceCommand(List<String> p, List<String> st, Placeholder placeholder) {
        this.placeholder = placeholder;
        HTYPES = new ArrayList<>();
        for (HolderType type : HolderType.values()) {
            HTYPES.add(type.name());
        }
        HTYPES.add("tostring");
        this.players = p;
        this.stats = st;


    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {


        if (command.getName().equalsIgnoreCase("testholder")) {
            if ((strings[0].equals("PLAYER") || strings[0].equals("STATS")) && strings[1] != null) {
                if (!(sender instanceof Player)) {

                    sender.sendMessage(ChatColor.RED + "Error this command can only be run as a player");
                    return true;
                }
                sender.sendMessage(ChatColor.GREEN + "The place holder is " + ChatColor.GOLD + strings[1]);

                String message = "&9&o{player} the stat is {" + strings[1] + "}";

                String result = placeholder.replaceAll(message, (Player) sender);

                try {
                    StandardTitle.get().fadeIn(-1).stay(60).fadeOut(-1).title(ChatSerialize.serializer(result)).clear().send((Player) sender);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return true;
            }

        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] strings) {

        if (strings.length == 1) {

            return StringUtil.copyPartialMatches(strings[0], HTYPES, new ArrayList<>(HTYPES.size()));

        }

        if (strings.length == 2) {
            if (strings[0].equals("PLAYER")) {

                return StringUtil.copyPartialMatches(strings[1], players, new ArrayList<>(players.size()));
            }

            if (strings[0].equals("STATS")) {

                return StringUtil.copyPartialMatches(strings[1], stats, new ArrayList<>(stats.size()));
            }

        }

        return Collections.emptyList();
    }
}
