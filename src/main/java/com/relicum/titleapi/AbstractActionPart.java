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

import com.relicum.titleapi.Components.ActionBar.BaseMessage;
import com.relicum.titleapi.Reflection.WrappedChatPacket;
import org.apache.commons.lang.Validate;

import java.util.List;

/**
 * AbstractActionPart represents a warped message which is part all of or all of the message/messages sent as a {@link com.relicum.titleapi.ActionBroadcast}
 * <p>You need to impliment the {@link #process} method to create an ordered list of {@link com.relicum.titleapi.Reflection.WrappedChatPacket} that represent this message.
 *
 * @author Relicum
 * @version 0.0.1
 */
public abstract class AbstractActionPart implements BaseMessage {


    private WrappedChatPacket chatPacket;

    private String rawMessages;

    private int totalPackets;

    private int period;

    public AbstractActionPart(String message, int totalPackets, int period) {
        Validate.notNull(message);
        this.totalPackets = totalPackets;
        this.period = period;
        this.rawMessages = message;
        this.wrapMessage(getRawText());

    }

    protected void wrapMessage(String message) {

        this.chatPacket = ActionPackets.getActionBar(message);
    }

    /**
     * Process the message into a list of {@link com.relicum.titleapi.Reflection.WrappedChatPacket}
     *
     * @return the list of {@link com.relicum.titleapi.Reflection.WrappedChatPacket} ready to be sent to the ActionBar
     */
    public abstract List<WrappedChatPacket> process();


    /**
     * {@inheritDoc}
     */
    @Override
    public WrappedChatPacket getChatPacket() {

        return chatPacket;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalPackets() {
        return totalPackets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPeriod() {
        return period;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getRawText() {
        return this.rawMessages;
    }


}
