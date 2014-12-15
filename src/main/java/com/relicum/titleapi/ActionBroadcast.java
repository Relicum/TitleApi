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
import org.bukkit.plugin.Plugin;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * ActionBroadcast sends ActionBar messages to all players on a server.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class ActionBroadcast implements Runnable {

    private final int taskId;
    private final Queue<WrappedChatPacket> chatMessages;
    private final Plugin plugin;

    public ActionBroadcast(ActionPart packets, Plugin plugin) {
        this.plugin = plugin;
        this.chatMessages = new ArrayDeque<>(packets.process());
        this.taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, 0, packets.getPeriod());
    }

    @Override
    public void run() {

        if (!chatMessages.isEmpty()) {

            WrappedChatPacket packet = chatMessages.poll();

            plugin.getServer().getOnlinePlayers().stream().forEach(p -> {
                try {
                    PacketSender.sendActionBarPacket(p, packet);
                    System.out.println("Action Part being sent to " + p.getName());
                } catch (ReflectionException e) {
                    e.printStackTrace();
                }
            });

        } else {
            System.out.println("Finished sending Action Parts");
            plugin.getServer().getScheduler().cancelTask(taskId);
        }


    }
}
