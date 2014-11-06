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

import com.relicum.titleapi.Components.MSerialize;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;
import org.spigotmc.ProtocolInjector;

/**
 * The type Action packets.
 */
public class ActionPackets {

    /**
     * Get the packet for the times for fade in, stay and fade out.
     * <p>All times are in ticks, set to -1 to not set a field.
     *
     * @param fadeIn  the fade in animation length
     * @param stay    the length of time the title stays displayed
     * @param fadeOut the fade out animation length.
     * @return the {@link org.spigotmc.ProtocolInjector.PacketTitle} packet for times.
     */
    protected static ProtocolInjector.PacketTitle getTimes(int fadeIn, int stay, int fadeOut) {

        return new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.TIMES, fadeIn, stay, fadeOut);
    }

    /**
     * Gets reset.
     *
     * @return the reset
     */
    protected static ProtocolInjector.PacketTitle getReset() {

        return new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.RESET);
    }

    /**
     * Gets clear.
     *
     * @return the clear
     */
    protected static ProtocolInjector.PacketTitle getClear() {

        return new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.CLEAR);
    }

    /**
     * Get title.
     *
     * @param message the message
     * @return the protocol injector . packet title
     */
    protected static ProtocolInjector.PacketTitle getTitle(String message) {

        return new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.TITLE, MSerialize.serializer(message));
    }

    /**
     * Get sub title.
     *
     * @param message the message
     * @return the protocol injector . packet title
     */
    protected static ProtocolInjector.PacketTitle getSubTitle(String message) {

        return new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.SUBTITLE, MSerialize.serializer(message));
    }

    /**
     * Get packet for sending Tab header and Footer displays.
     *
     * @param header the header
     * @param footer the footer
     * @return the {@link org.spigotmc.ProtocolInjector.PacketTabHeader} tab packet.
     */
    protected static ProtocolInjector.PacketTabHeader getTab(String header, String footer) {

        return new ProtocolInjector.PacketTabHeader(MSerialize.serializer(header), MSerialize.serializer(footer));
    }

    /**
     * Get Action Bar Packet
     *
     * @param message the message to display on the action bar.
     * @return the action bar packet {@link net.minecraft.server.v1_7_R4.PacketPlayOutChat} .
     */
    protected static PacketPlayOutChat getActionBar(String message) {

        return new PacketPlayOutChat(MSerialize.serializer(message), 2);
    }

}
