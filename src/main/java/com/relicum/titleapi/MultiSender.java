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

import com.relicum.titleapi.Components.MultiComponents;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * Name: MultiSender.java Created: 04 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class MultiSender {

    private MultiComponents components;
    private CraftPlayer p;
    private boolean playerSet = false;
    private long delay;
    private Integer total;
    private Plugin plugin;
    private BukkitTask task;
    private String test;

    private MultiSender(MultiComponents components, long delay, Plugin plugin) {
        this.components = components;
        this.delay = delay;
        this.plugin = plugin;
        this.total = components.getTotalSubs();
    }

    public static MultiSender get(MultiComponents components, long delay, Plugin plugin) {
        return new MultiSender(components, delay, plugin);
    }

    public void setPlayer(Player player) {
        this.p = (CraftPlayer) player;
        this.playerSet = true;
    }

    public void startSending() throws Exception {
        if (!isPlayerSet()) throw new Exception("No player has been set");
        else {
            System.out.println("Multi Send Start");
            this.task = new BukkitRunnable() {
                int count = 0;

                @Override
                public void run() {

                    if (count == 0) {
                        sendFirst();
                    } else if (count > 0 && count < total) {
                        send(count);
                    } else if (count == total) {
                        sendClearPacket();
                        System.out.println("Multi send end");
                        p = null;
                        playerSet = false;
                        cancel();
                    }

                    count++;
                }
            }.runTaskTimer(plugin, 20l, delay);
        }
    }

    public void sendFirst() {

        sendClearPacket();
        sendTimes();
        sendTitle();
        sendSubTitle(components.getNextSubTitle(0));

    }

    public void send(Integer c) {
        sendSubTitle(components.getNextSubTitle(c));
        sendTimes();
    }

    private void sendTitle() {

        p.getHandle().playerConnection.sendPacket(ActionPackets.getTitle(components.getTheTitle()));

    }

    private void sendSubTitle(String message) {

        p.getHandle().playerConnection.sendPacket(ActionPackets.getSubTitle(message));
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
