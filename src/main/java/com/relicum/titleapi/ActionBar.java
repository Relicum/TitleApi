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

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * ActionBar senders messages that are displayed in the users Action Bar
 *
 * @author Relicum
 * @version 0.0.1
 */
public class ActionBar {

    /**
     * Set the Action Bar message for the specified player.
     *
     * @param player  the {@link org.bukkit.entity.Player}
     * @param message the message to display in the action bar.
     */
    public static void sendToPlayer(Player player, String message) {

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(ActionPackets.getActionBar(message));
    }
}
