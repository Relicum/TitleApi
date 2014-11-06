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

import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import org.bukkit.entity.Player;

/**
 * Tab set the Header and Footer tabs.
 *
 * @author Relicum
 */
public interface Tab {


    /**
     * Sets the text for the tab header
     *
     * @param text the text
     * @return instance of itself for chaining
     */
    public Tab setHeader(IChatBaseComponent text);

    /**
     * Sets the text for the tab foot
     *
     * @param text the text to display on tab footer
     * @return instance of itself for chaining
     */
    public Tab setFooter(IChatBaseComponent text);

    /**
     * Send the Header and Footer to player
     *
     * @param player the {@link org.bukkit.entity.Player}
     * @return instance of itself for chaining
     */
    public Tab send(Player player);
}
