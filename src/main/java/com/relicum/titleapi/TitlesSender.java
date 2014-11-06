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
 * Name: TitlesSender.java Created: 04 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class TitlesSender {

    private CraftPlayer p;
    private boolean playerSet = false;
    private TitleComponents components;

    private TitlesSender(TitleComponents components) {
        this.components = components;
    }

    public static TitlesSender get(TitleComponents components) {
        return new TitlesSender(components);
    }

    public void setPlayer(Player player) {
        this.p = (CraftPlayer) player;
        this.playerSet = true;
    }

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
