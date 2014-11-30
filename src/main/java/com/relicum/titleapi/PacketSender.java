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
import com.relicum.titleapi.Reflection.WrappedChatPacket;
import com.relicum.titleapi.Reflection.WrappedHeaderFooter;
import com.relicum.titleapi.Reflection.WrappedTitlePacket;
import org.bukkit.entity.Player;

/**
 * PacketSender used to send out all packets from a centralised place.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class PacketSender {

    /**
     * Send title packet.
     *
     * @param player the player to send the packet to
     * @param packet the packet
     * @throws ReflectionException if the packet was unable to be sent.
     */
    protected static void sendTitlePacket(Player player, WrappedTitlePacket packet) throws ReflectionException {

        packet.send(player);

    }

    /**
     * Send action bar packet.
     *
     * @param player the player to send the packet to
     * @param packet the packet
     * @throws ReflectionException if the packet was unable to be sent.
     */
    protected static void sendActionBarPacket(Player player, WrappedChatPacket packet) throws ReflectionException {

        packet.send(player);

    }

    /**
     * Send Header and Footer packet.
     *
     * @param player the player to send the packet to
     * @param packet the packet
     * @throws ReflectionException if the packet was unable to be sent.
     */
    protected static void sendTabPacket(Player player, WrappedHeaderFooter packet) throws ReflectionException {

        packet.send(player);

    }
}
