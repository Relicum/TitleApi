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

package com.relicum.titleapi.Components.ActionBar;

import com.relicum.titleapi.Reflection.WrappedChatPacket;

/**
 * Name: BaseMessage.java Created: 15 December 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface BaseMessage {


    /**
     * Gets the message to display on the action bar
     *
     * @return the raw text of the message
     */
    public String getRawText();

    /**
     * Gets the chat message wrapped in a {@link com.relicum.titleapi.Reflection.WrappedChatPacket}
     *
     * @return the chat packet
     */
    public WrappedChatPacket getChatPacket();


    /**
     * Gets total packets that this message will have send
     *
     * @return the total packets
     */
    public int getTotalPackets();

    /**
     * Gets period of delay between packets being sent, represented in ticks.
     *
     * @return the period
     */
    public int getPeriod();
}
