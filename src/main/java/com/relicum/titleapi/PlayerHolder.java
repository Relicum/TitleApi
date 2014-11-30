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

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * PlayerHolder used to define placeholders directly related to the {@link org.bukkit.entity.Player} object.
 * <p>Just add the name of the place holder wrapped with brackets <strong>{player}</strong> and use the {@link PlayerHolder} to do the replacing.
 * <p>By default placeholders is not activated to activate it call {@link TitleMaker#enablePlaceHolders()} method.
 * <p>Then pass the raw message to {@link com.relicum.titleapi.TitleApi#replaceHolders(String, org.bukkit.entity.Player)}
 *
 * @author Relicum
 * @version 0.0.1
 */
public class PlayerHolder extends Holder {


    protected PlayerHolder(HolderType type, Plugin plugin) {
        super(type, plugin);
        setPlaceholder("player");
        setPlaceholder("displayname");
        setPlaceholder("playerlistname");
        setPlaceholder("gamemode");
        setPlaceholder("hunger");
        setPlaceholder("health");
        setPlaceholder("xp");
        setPlaceholder("experience");
        setPlaceholder("world");
        setPlaceholder("location");
    }

    @Override
    public String replaceAndReturn(String place, Player player) {
        if (!validPlaceHolder(place)) return "";

        if (place.equalsIgnoreCase("player")) {

            return player.getName();
        }
        if (place.equalsIgnoreCase("displayname")) {

            return player.getDisplayName();
        }
        if (place.equalsIgnoreCase("playerlistname")) {

            return player.getPlayerListName();
        }
        if (place.equalsIgnoreCase("gamemode")) {

            return player.getGameMode().name();
        }
        if (place.equalsIgnoreCase("hunger")) {

            return String.valueOf(player.getFoodLevel());
        }
        if (place.equalsIgnoreCase("health")) {

            return String.valueOf(player.getHealth());
        }
        if (place.equalsIgnoreCase("xp")) {

            return String.valueOf(player.getExp());
        }
        if (place.equalsIgnoreCase("experience")) {
            return String.valueOf(player.getTotalExperience());
        }
        if (place.equalsIgnoreCase("world")) {

            return player.getWorld().getName();
        }
        if (place.equalsIgnoreCase("location")) {

            return "X = " + player.getLocation().getBlockX() + " Y = " + player.getLocation().getBlockY() + " Z = " + player.getLocation().getBlockZ();
        }


        return "";
    }
}
