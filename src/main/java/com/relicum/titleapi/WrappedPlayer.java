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

import com.relicum.titleapi.Reflection.WrappedChatPacket;
import com.relicum.titleapi.Reflection.WrappedHeaderFooter;
import com.relicum.titleapi.Reflection.WrappedPacket;
import com.relicum.titleapi.Reflection.WrappedTitlePacket;
import org.bukkit.entity.Player;

import java.lang.ref.WeakReference;

/**
 * WrappedPlayer holds a single player wrapped in a {@link java.lang.ref.WeakReference} .
 * <p>This object contains methods to send all types of Packets connected to Titles, Tab Headers and Action Bar.
 * <p>This gives complete freedom to send the packets how ever you choose to.
 * <p>This object deliberately does not store any data apart from the {@link java.lang.ref.WeakReference} to the player to keep it as light weight as possible.
 * <p>Every send method throws it's exceptions as player reference might of been de referenced or collected by the garbage collector.
 * <p>It is put to the you to handle the thrown exceptions correctly.
 * <p>As it is the player object it is unlikely to be GC'd or lose its reference, if the player logs out this object will automatically be de referenced.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class WrappedPlayer {

    private WeakReference<Player> player;


    /**
     * Instantiates a new Wrapped player.
     * <p>The player will internally be wrapped with a {@link java.lang.ref.WeakReference}
     *
     * @param play the {@link org.bukkit.entity.Player}
     */
    public WrappedPlayer(Player play) {


        this.player = new WeakReference<>(play);

    }

    /**
     * Remove the currently displayed title from the player's screen
     * and set the configuration back to the default values.
     *
     * @throws Exception the most like to indicate the player object has been de referenced.
     */
    public void sendResetPacket() throws Exception {

        PacketSender.sendTitlePacket(player.get(), ActionPackets.getReset());

    }

    /**
     * Remove the currently displayed title from the player's screen.
     * This will keep the currently used display times and will only remove the title.
     *
     * @throws Exception the most like to indicate the player object has been de referenced.
     */
    public void sendClearPacket() throws Exception {

        PacketSender.sendTitlePacket(player.get(), ActionPackets.getClear());
    }

    /**
     * Send animation Times packet.
     *
     * @param packetTimes {@link WrappedTitlePacket}
     * @throws Exception the most like to indicate the player object has been de referenced.
     */
    public void sendTimesPacket(WrappedTitlePacket packetTimes) throws Exception {

        sendPacket(packetTimes);
    }

    /**
     * Send Title packet.
     *
     * @param packetTitle {@link WrappedTitlePacket}
     * @throws Exception the most like to indicate the player object has been de referenced.
     */
    public void sendTitlePacket(WrappedTitlePacket packetTitle) throws Exception {

        sendPacket(packetTitle);
    }

    /**
     * Send SubTitle packet.
     *
     * @param packetSubTitle the {@link WrappedTitlePacket}
     * @throws Exception the most like to indicate the player object has been de referenced.
     */
    public void sendSubTitlePacket(WrappedTitlePacket packetSubTitle) throws Exception {

        sendPacket(packetSubTitle);
    }

    /**
     * Send TabHeader packet, Both header and footer must contain a value.
     *
     * @param packetTabHeader {@link WrappedHeaderFooter}
     * @throws Exception the most like to indicate the player object has been de referenced.
     */
    public void sendTabPacket(WrappedHeaderFooter packetTabHeader) throws Exception {

        sendPacket(packetTabHeader);
    }

    /**
     * Send Action Bar Packet
     *
     * @param packetActionBar WrappedChatPacket packet configured as a system packet
     * @throws Exception the most like to indicate the player object has been de referenced.
     */
    public void sendActionBarPacket(WrappedChatPacket packetActionBar) throws Exception {

        sendPacket(packetActionBar);
    }

    @SuppressWarnings("ConstantConditions")
    protected void sendPacket(WrappedPacket packet) throws Exception {
        if (player.get() == null)
            throw new Exception("Reference to current player object has been lost", new Throwable("Player object is dereferenced"));
        packet.send(player.get());

    }


    /**
     * Gets the player object wrapped in a {@link java.lang.ref.WeakReference} object.
     *
     * @return wrapped player.
     */
    public WeakReference<Player> getPlayer() {
        return player;
    }

    /**
     * Clear the {@link java.lang.ref.WeakReference} to the player, call this when you no longer need this object.
     */
    public void clear() {

        this.player.clear();
    }
}
