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

import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Holder.java Created: 06 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
abstract class Holder {

    private List<String> placeholders;

    private boolean playerNeeded = true;

    private final HolderType type;

    private final Plugin plugin;

    protected Holder(HolderType type, Plugin plugin) {

        this.type = type;
        this.plugin = plugin;
        this.placeholders = new ArrayList<>();
    }


    public HolderType getType() {

        return type;
    }

    public Plugin getPlugin() {

        return plugin;
    }

    public void setPlayerNeeded(boolean needs) {

        this.playerNeeded = needs;
    }

    public boolean isPlayerNeeded() {

        return playerNeeded;
    }

    public boolean validPlaceHolder(String holder) {

        return placeholders.contains(holder);
    }

    public void setPlaceholder(String holder) {

        Validate.notNull(holder, "The place holder seems to be null");

        placeholders.add(holder.replaceAll("\\{", "").toLowerCase());

    }

    public abstract String replaceAndReturn(String string, Player player);

    public List<String> getPlaceholders() {

        return placeholders;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Holder{");
        sb.append("placeholders=").append(placeholders.toString());
        sb.append(", playerNeeded=").append(playerNeeded);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
