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

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;

/**
 * TitleSender used to send a single Title to the specified player.
 * <p>The only setting you have to set is the Title, the rest are optional.
 * <p>
 * <p>When ready just call the {@link TitleSender#send()} to display title to player.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class TitleSender {

    private CraftPlayer p;
    private Integer[] times = new Integer[3];
    private String theTitle;
    private String theSubTitle = "";
    private boolean useTimes = false;
    private boolean useTitle = false;
    private boolean useSubTitle = false;
    private boolean useClear = false;
    private boolean useReset = false;

    private TitleSender(Player player) {

        this.p = (CraftPlayer) player;
    }

    /**
     * Create a new TitleSender and returns the instance for chaining other methods.
     *
     * @param player the player to display the title to.
     * @return instance of itself for chaining.
     */
    public static TitleSender get(Player player) {
        return new TitleSender(player);
    }

    /**
     * Sets times.
     *
     * @param in  the in
     * @param st  the st
     * @param out the out
     * @return instance of itself for chaining.
     */
    public TitleSender setTimes(int in, int st, int out) {

        this.times[0] = in;
        this.times[1] = st;
        this.times[2] = out;

        this.useTimes = true;

        return this;

    }

    /**
     * Sets title.
     *
     * @param mess the title message
     * @return instance of itself for chaining.
     */
    public TitleSender setTitle(String mess) {

        this.theTitle = mess;
        this.useTitle = true;
        return this;
    }

    /**
     * Sets sub title.
     *
     * @param mess the sub message
     * @return instance of itself for chaining.
     */
    public TitleSender setSubTitle(String mess) {

        this.theSubTitle = mess;
        this.useSubTitle = true;
        return this;
    }

    /**
     * Set if a clear packet should be sent
     *
     * @param clear true to send a clear packet
     * @return instance of itself for chaining.
     */
    public TitleSender setSendClear(boolean clear) {
        this.useClear = clear;
        return this;
    }

    /**
     * Set if a reset packet should be sent
     *
     * @param reset true to send a reset packet
     * @return instance of itself for chaining.
     */
    public TitleSender setSendReset(boolean reset) {
        this.useReset = reset;
        return this;
    }

    /**
     * Create Title and display to player.
     */
    public void send() {

        if (useClear) {
            sendClearPacket();
        }
        if (useReset) {
            sendResetPacket();
        }
        if (useTimes) {
            sendTimes();
        }
        if (useTitle) {
            sendTitle();
        }
        if (useSubTitle) {
            sendSubTitle();
        } else sendSubTitle();

        p = null;

    }


    private void sendTitle() {

        p.getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.TITLE, MSerialize.serializer(theTitle)));

    }

    private void sendSubTitle() {

        p.getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.SUBTITLE, MSerialize.serializer(theSubTitle)));

    }

    private void sendTimes() {

        p.getHandle().playerConnection.sendPacket(ActionPackets.getTimes(times[0], times[1], times[2]));
    }


    private void sendClearPacket() {

        p.getHandle().playerConnection.sendPacket(ActionPackets.getClear());
    }


    private void sendResetPacket() {


        p.getHandle().playerConnection.sendPacket(ActionPackets.getReset());
    }
}
