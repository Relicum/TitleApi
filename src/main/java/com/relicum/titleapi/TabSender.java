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
import org.bukkit.entity.Player;

/**
 * TabSender Simple Method to set Tab Header and Footer.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class TabSender {

    private String header;
    private String footer;

    private TabSender(String header, String footer) {
        this.header = header;
        this.footer = footer;
    }

    /**
     * Create a new TabSender, both the header and the footer must be set.
     *
     * @param header the header
     * @param footer the footer
     * @return instance of itself for chaining.
     */
    public static TabSender get(String header, String footer) {
        return new TabSender(header, footer);
    }

    /**
     * Set the players Tab Header and Footer.
     *
     * @param player the {@link org.bukkit.entity.Player} to set the Tab header and footer for.
     * @throws ReflectionException if an error occurs.
     */
    public void sendToPlayer(Player player) throws ReflectionException {

        PacketSender.sendTabPacket(player, ActionPackets.getTab(header, footer));

    }

}
