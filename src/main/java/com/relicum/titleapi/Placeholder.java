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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Placeholder
 *
 * @author Relicum
 * @version 0.0.1
 */
class Placeholder {

    private Map<HolderType, Holder> lookup;
    private Map<String, HolderType> holderLookup = new HashMap<>();
    private Pattern pattern = Pattern.compile("(\\{(?<res>[a-zA-Z_]+)\\})");

    public static Placeholder instance;

    protected Placeholder(Plugin plugin) {
        instance = this;
        lookup = new HashMap<>();


    }

    /**
     * Register a new place holder class which must extend {@link Holder}
     *
     * @param holder the object to register
     */
    public void registerHolder(Holder holder) {

        lookup.put(holder.getType(), holder);
        for (String s : holder.getPlaceholders()) {
            holderLookup.put(s, holder.getType());
        }

    }

    /**
     * Replace all placeholders with the corresponding text.
     * <p>This method will find and replace all placeholders in the string in one go.
     *
     * @param search the search
     * @param player the player
     * @return the string
     */
    public String replaceAll(String search, Player player) {

        final Matcher matcher = pattern.matcher(search);
        final StringBuffer sb = new StringBuffer();

        while (matcher.find()) {

            matcher.appendReplacement(sb, callBack(matcher.group("res"), player));
        }

        matcher.appendTail(sb);

        return sb.toString();
    }

    @SuppressWarnings("ConstantConditions")
    private String callBack(String result, Player player) {
        if (!holderLookup.containsKey(result)) return "";
        return lookup.get(holderLookup.get(result)).replaceAndReturn(result, player);

    }

    public Placeholder getInstance() {
        return instance;
    }

    @SuppressWarnings("ConstantConditions")
    public List<String> getHolder(HolderType type) {

        return lookup.get(type).getPlaceholders();
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Placeholder{");
        sb.append("lookup=").append(lookup);
        sb.append(", holderLookup=").append(holderLookup);
        sb.append(", pattern=").append(pattern);
        sb.append('}');
        return sb.toString();
    }
}
