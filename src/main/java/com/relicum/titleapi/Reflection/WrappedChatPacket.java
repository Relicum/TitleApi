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

/**
 * WrappedChatPacket to send the Action Bar message.
 *
 * @author dmulloy2
 * @version 0.0.1
 */
public class WrappedChatPacket extends WrappedPacket {
    private static final String NMS_CLASS_NAME = "PacketPlayOutChat";
    private static final Class<?> chatComponentClass = ReflectionUtil.getNMSClass("IChatBaseComponent");

    /**
     * Instantiates a new WrappedChatPacket.
     * <p>Requires sys set to true if its an Action bar Packet.
     *
     * @param chatComponent the chat component
     * @param sys           the boolean to specify if its a system chat message, set to true if it is, of false to send a normal Chat packet.
     * @throws ReflectionException the reflection exception
     */
    public WrappedChatPacket(Object chatComponent, boolean sys) throws ReflectionException {

        try {
            this.nmsClass = ReflectionUtil.getNMSClass(NMS_CLASS_NAME);
            this.constructor = nmsClass.getConstructor(chatComponentClass);
            this.nmsHandle = constructor.newInstance(chatComponent);
            if (sys)
                this.setField(ReflectionUtil.getField(this.nmsClass, "b"), (byte) 2);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Constructing wrapped chat packet", ex);
        }
    }
}
