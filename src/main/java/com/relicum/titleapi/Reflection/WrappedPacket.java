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

package com.relicum.titleapi.Reflection;

import com.relicum.titleapi.Exception.ReflectionException;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * WrappedPacket
 *
 * @author dmulloy2
 * @version 0.0.1
 */
public abstract class WrappedPacket extends AbstractWrapper {
    public final void send(Player player) throws ReflectionException {
        try {
            Object nmsPlayer = ReflectionUtil.getHandle(player);
            Field playerConnectionField = ReflectionUtil.getField(nmsPlayer.getClass(), "playerConnection");
            Object playerConnection = playerConnectionField.get(nmsPlayer);
            Method sendPacket = ReflectionUtil.getMethod(playerConnection.getClass(), "sendPacket");
            sendPacket.invoke(playerConnection, nmsHandle);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Sending packet to " + player.getName(), ex);
        }
    }

    public final void sendToServer(Player player) throws ReflectionException {
        try {
            Object nmsPlayer = ReflectionUtil.getHandle(player);
            Field playerConnectionField = ReflectionUtil.getField(nmsPlayer.getClass(), "playerConnection");
            Object playerConnection = playerConnectionField.get(nmsPlayer);
            Method a = ReflectionUtil.getMethod(playerConnection.getClass(), "a", nmsClass);
            a.invoke(playerConnection, nmsHandle);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Sending packet from " + player.getName() + " to the server", ex);
        }
    }
}
