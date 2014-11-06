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

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * StatsHolder any valid {@link org.bukkit.Statistic.Type#UNTYPED} stat can be used full list can be found at {@link org.bukkit.Statistic}
 * <p>To use the stat add them to your text and prefix the stat with <strong>stat_</strong> the name of the stat should be input in lower case.
 *
 * @author Relicum
 * @version 0.0.1
 */
class StatsHolder extends Holder {


    protected StatsHolder(HolderType type, Plugin plugin) {
        super(type, plugin);

        for (Statistic statistic : Statistic.values()) {
            setPlaceholder("stat_" + statistic.name().toLowerCase());
        }


    }

    @Override
    public String replaceAndReturn(String place, Player player) {
        if (!validPlaceHolder(place)) return "";

        return String.valueOf(player.getStatistic(Statistic.valueOf(place.substring(5, place.length()).toUpperCase())));

    }
}
