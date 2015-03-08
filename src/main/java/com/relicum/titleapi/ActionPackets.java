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
import com.relicum.titleapi.Reflection.WrappedChatSerializer;
import com.relicum.titleapi.Reflection.WrappedHeaderFooter;
import com.relicum.titleapi.Reflection.WrappedTitlePacket;


/**
 * Action packets creates the different packets used to send Titles, Tabs and Action bar messages.
 * <p>Access to these methods can be found in the {@link TitleMaker} class.
 *
 * @author Relicum
 */
public class ActionPackets {

    private ActionPackets() {
    }

    /**
     * Get the packet for the times for fade in, stay and fade out.
     * <p>All times are in ticks, set to -1 to not set a field.
     *
     * @param fadeIn  the fade in animation length
     * @param stay    the length of time the title stays displayed
     * @param fadeOut the fade out animation length.
     * @return the times
     * @throws ReflectionException if an error occurs
     */
    protected static WrappedTitlePacket getTimes(int fadeIn, int stay, int fadeOut) throws ReflectionException {

        return new WrappedTitlePacket(fadeIn, stay, fadeOut);
    }

    /**
     * Get the packet for the times for fade in, stay and fade out.
     * <p>All times are in ticks, set to -1 to not set a field.
     *
     * @param titleTimes the title times {@link TitleTimes}
     * @return WrappedTitlePacket
     * @throws ReflectionException if an error occurs.
     */
    protected static WrappedTitlePacket getTimes(TitleTimes titleTimes) throws ReflectionException {

        return new WrappedTitlePacket(titleTimes.getFadeIn(), titleTimes.getStay(), titleTimes.getFadeOut());
    }


    /**
     * Get the reset packet, which resets the timings back to default.
     *
     * @return WrappedTitlePacket
     * @throws ReflectionException if an error occurs
     */
    protected static WrappedTitlePacket getReset() throws ReflectionException {

        return new WrappedTitlePacket(WrappedTitlePacket.TitleAction.RESET);
    }


    /**
     * Get a clear packet.
     *
     * @return WrappedTitlePacket
     * @throws ReflectionException if an error occurs
     */
    protected static WrappedTitlePacket getClear() throws ReflectionException {

        return new WrappedTitlePacket(WrappedTitlePacket.TitleAction.CLEAR);
    }

    /**
     * Get title packet.
     *
     * @param message the message
     * @return WrappedTitlePacket
     * @throws ReflectionException if an error occurs
     */
    protected static WrappedTitlePacket getTitle(String message) throws ReflectionException {

        return new WrappedTitlePacket(WrappedTitlePacket.TitleAction.TITLE, message);
    }

    /**
     * Get title packet with timings packet.
     *
     * @param message the message
     * @param fadeIn  the fade in animation length
     * @param stay    the length of time the title stays displayed
     * @param fadeOut the fade out animation length.
     * @return the title packet.
     * @throws ReflectionException if an error occurs
     */
    protected static WrappedTitlePacket getTitleWithTimes(String message, int fadeIn, int stay, int fadeOut) throws ReflectionException {

        return new WrappedTitlePacket(WrappedTitlePacket.TitleAction.TITLE, message, fadeIn, stay, fadeOut);
    }


    /**
     * Get Subtitle packet.
     *
     * @param message the message
     * @return WrappedTitlePacket
     * @throws ReflectionException if an error occurs.
     */
    protected static WrappedTitlePacket getSubTitle(String message) throws ReflectionException {

        return new WrappedTitlePacket(WrappedTitlePacket.TitleAction.SUBTITLE, message);

    }

    /**
     * Gets SubTitle with timings packet.
     *
     * @param message the message
     * @param fadeIn  the fade in animation length
     * @param stay    the length of time the title stays displayed
     * @param fadeOut the fade out animation length.
     * @return WrappedTitlePacket
     * @throws ReflectionException if an error occurs.
     */
    protected static WrappedTitlePacket getSubTitleWithTimes(String message, int fadeIn, int stay, int fadeOut) throws ReflectionException {

        return new WrappedTitlePacket(WrappedTitlePacket.TitleAction.SUBTITLE, message, fadeIn, stay, fadeOut);
    }


    /**
     * Get packet for sending Tab header and Footer displays.
     *
     * @param header the header in string format
     * @param footer the footer in string format
     * @return the tab packet.
     */
    protected static WrappedHeaderFooter getTab(String header, String footer) {
        try {
            return new WrappedHeaderFooter(header, footer);
        } catch (ReflectionException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Get Action Bar Packet
     *
     * @param message the message to display on the action bar.
     * @return the action bar packet {@link WrappedChatPacket} .
     */
    protected static WrappedChatPacket getActionBar(String message) {

        try {
            WrappedChatSerializer wcs = new WrappedChatSerializer();

            return new WrappedChatPacket(wcs.serialize(message), true);
        } catch (ReflectionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
