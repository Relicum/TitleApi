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

import com.relicum.titleapi.Components.TitleComponents;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * TitleSender is a quick and easy class to send Titles to a player, this class is meant to be used in conjunction with.
 * <p>{@link com.relicum.titleapi.Components.TitleBuilder} and {@link com.relicum.titleapi.Components.TitleComponents} .
 * <p>The 3 class combine to provide a way to persist the values to disk load them from disk and directly send it to players.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class TitleSender {

    private CraftPlayer p;
    private boolean playerSet = false;
    private TitleComponents components;

    private TitleSender(TitleComponents components) {
        this.components = components;
    }

    /**
     * Get new instance of TitleSender passing in {@link com.relicum.titleapi.Components.TitleComponents}
     *
     * @param components the instance of {@link com.relicum.titleapi.Components.TitleComponents}
     * @return the instance of TitleSender.
     */
    public static TitleSender get(TitleComponents components) {
        return new TitleSender(components);
    }

    /**
     * Sets player to send the Titles to, this object can be reused by setting the player to a different one and calling send.
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.p = (CraftPlayer) player;
        this.playerSet = true;
    }

    /**
     * Send the Title packets to the player
     *
     * @throws Exception the exception if a player has not been set.
     */
    public void send() throws Exception {

        if (!isPlayerSet()) throw new Exception("No player has been set");
        else {
            if (components.isUseClear())
                sendClearPacket();
            if (components.isUseReset())
                sendResetPacket();
            if (components.isUseTimes())
                sendTimes();
            if (components.isUseTitle())
                sendTitle();
            if (components.isUseSubTitle())
                sendSubTitle();

            p = null;
            playerSet = false;
        }

    }


    private void sendTitle() {

        p.getHandle().playerConnection.sendPacket(ActionPackets.getTitle(components.getTheTitle()));

    }

    private void sendSubTitle() {

        p.getHandle().playerConnection.sendPacket(ActionPackets.getSubTitle(components.getTheSubTitle()));
    }

    private void sendTimes() {

        p.getHandle().playerConnection.sendPacket(ActionPackets.getTimes(components.getFadeIn(), components.getStay(), components.getFadeOut()));
    }


    private void sendClearPacket() {

        p.getHandle().playerConnection.sendPacket(ActionPackets.getClear());
    }


    private void sendResetPacket() {


        p.getHandle().playerConnection.sendPacket(ActionPackets.getReset());
    }


    private boolean isPlayerSet() {
        return playerSet;
    }
}
