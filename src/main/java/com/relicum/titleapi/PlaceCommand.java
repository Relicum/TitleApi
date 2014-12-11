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

import com.relicum.titleapi.Exception.ReflectionException;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
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
    private final com.relicum.titleapi.Placeholder placeholder;
    private List<Character> colorCharacter = Arrays.asList('4', 'c', '6', 'e', '2', 'a', 'b', '3', '1', '9', '7', 'f');
    List<String> mess = new ArrayList<>();
    List<String> subs = new ArrayList<>();

    public PlaceCommand(List<String> p, List<String> st, com.relicum.titleapi.Placeholder placeholder) {
        this.placeholder = placeholder;
        HTYPES = new ArrayList<>();
        for (HolderType type : HolderType.values()) {
            HTYPES.add(type.name());
        }
        HTYPES.add("tostring");
        this.players = p;
        this.stats = st;
        String str = "%s&l>>     &6{0}&%s&l     <<";
        String org = "%sPVPING NinJA";

        String n1 = "%s&l>>        ";
        String n2 = "        &%s&l<<";

        for (int i = 0; i < 6; i++) {
            String t;
            int l1 = n1.length();
            int l2 = n2.length();


            t = n1.substring(0, l1 - i) + "&6{0}" + n2.substring(i, l2);
            mess.add(i, String.format("&" + t, colorCharacter.get(i), colorCharacter.get(i)));
            mess.add(i + 1, String.format("&" + t, colorCharacter.get(i + 1), colorCharacter.get(i + 1)));


        }


        for (int i = 0; i < 11; i++) {
            subs.add(i, String.format("&" + org, colorCharacter.get(i)));
            // mess.add(i, String.format(str, colorCharacter.get(i), colorCharacter.get(i)));
        }

        System.out.println(mess.toString());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("testcount")) {

            Player player = (Player) sender;

            new BukkitRunnable() {
                int count = 50;
                int sub = 10;
                int sub2 = 10;
                String subt = "&%sMy SubTitle";

                @Override
                public void run() {
                    // String tit="&%s&l>>     &6{0}     &%s&l<<";


                    if (count == 0) {
                        try {
                            player.playSound(player.getLocation(), Sound.EXPLODE, 20.0f, 1.0f);
                            PacketSender.sendTitlePacket(player, ActionPackets.getClear());
                        } catch (ReflectionException e) {
                            e.printStackTrace();
                        }
                        cancel();
                    }


                    try {
                        if (count % 5 == 0) {
                            player.playSound(player.getLocation(), Sound.CLICK, 10.0f, 1.0f);
                            PacketSender.sendTitlePacket((Player) sender, ActionPackets.getTitle(mess.get(sub).replace("{0}", String.valueOf(sub))));
                        }
                        PacketSender.sendTitlePacket((Player) sender, ActionPackets.getSubTitle(subs.get(sub2)));

                    } catch (ReflectionException e) {
                        e.printStackTrace();
                    }

                    if (count == 50) {

                        try {
                            PacketSender.sendTitlePacket(player, ActionPackets.getTimes(0, 22, 0));
                            PacketSender.sendTitlePacket((Player) sender, ActionPackets.getTitle(mess.get(sub).replace("{0}", String.valueOf(sub))));
                            PacketSender.sendTitlePacket((Player) sender, ActionPackets.getSubTitle(subs.get(sub2)));
                        } catch (ReflectionException e) {
                            e.printStackTrace();
                        }
                    }

                    count--;
                    if (sub2 == 0)
                        sub2 = 10;
                    else sub2--;
                    if (count % 5 == 0)
                        sub--;
                }
            }.runTaskTimer(TitleApi.get(), 0, 4l);

            return true;
        }

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
                    //PacketSender.sendTitlePacket((Player)sender,ActionPackets.getTimes(10,100,10));
                    PacketSender.sendTitlePacket((Player) sender, ActionPackets.getSubTitle(result));

                    PacketSender.sendTitlePacket((Player) sender, ActionPackets.getTitleWithTimes(result, 50, 120, 50));
                } catch (ReflectionException e) {
                    e.printStackTrace();
                }

                return true;
            }

        }

        if (command.getName().equalsIgnoreCase("testbar")) {
            Player player = (Player) sender;
            try {
                PacketSender.sendActionBarPacket(player, ActionPackets.getActionBar("&aThis is an action bar message"));
            } catch (ReflectionException e) {
                e.printStackTrace();
            }

            return true;
        }

        if (command.getName().equalsIgnoreCase("testtab")) {
            Player player = (Player) sender;

            try {
                PacketSender.sendTabPacket(player, ActionPackets.getTab("&b\u2560 \u254D &6PVPing Ninja Network &b\u254D \u2563", "&aYou are now on the latest 1.8 Spigot\n&6We hope you enjoy your stay"));
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
            return true;
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
