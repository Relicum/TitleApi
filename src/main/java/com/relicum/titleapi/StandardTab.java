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
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;

/**
 * Name: StandardTab.java Created: 04 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class StandardTab implements Tab {

    private IChatBaseComponent header;
    private IChatBaseComponent footer;

    private StandardTab() {

    }

    protected static StandardTab get() {
        return new StandardTab();
    }

    /**
     * Sets the text for the tab header
     *
     * @param text the text
     * @return instance of itself for chaining
     */
    @Override
    public Tab setHeader(IChatBaseComponent text) {
        this.header = text;
        return this;
    }

    /**
     * Sets the text for the tab foot
     *
     * @param text the text to display on tab footer
     * @return instance of itself for chaining
     */
    @Override
    public Tab setFooter(IChatBaseComponent text) {
        this.footer = text;
        return this;
    }

    /**
     * Send the Header and Footer to player
     *
     * @param player the {@link org.bukkit.entity.Player}
     * @return instance of itself for chaining
     */
    @Override
    public Tab send(Player player) {

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTabHeader(header, footer));

        return this;
    }
}
