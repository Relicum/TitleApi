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

package com.relicum.titleapi.Components;

import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;

/**
 * TabSender Simple Method to set Tab Header and Footer.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class TabSender {

    private IChatBaseComponent header;
    private IChatBaseComponent footer;

    private TabSender(IChatBaseComponent header, IChatBaseComponent footer) {
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
    public static TabSender get(IChatBaseComponent header, IChatBaseComponent footer) {
        return new TabSender(header, footer);
    }

    /**
     * Set the players Tab Header and Footer.
     *
     * @param player the {@link org.bukkit.entity.Player} to set the Tab header and footer for.
     */
    public void sendToPlayer(Player player) {

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTabHeader(header, footer));

    }

}
