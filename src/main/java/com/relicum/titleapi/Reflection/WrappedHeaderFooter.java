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
 * WrappedHeaderFooter wrapped PlayerList header and footer packet.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class WrappedHeaderFooter extends WrappedPacket {

    private static final String NMS_CLASS_NAME = "PacketPlayOutPlayerListHeaderFooter";
    private static final Class<?> chatComponentClass = ReflectionUtil.getNMSClass("IChatBaseComponent");

    /**
     * Instantiates a new Wrapped header footer.
     *
     * @param chatComponent  the header component
     * @param chatComponent2 the footer component 2
     * @throws ReflectionException the reflection exception
     */
    public WrappedHeaderFooter(String chatComponent, String chatComponent2) throws ReflectionException {

        try {
            this.nmsClass = ReflectionUtil.getNMSClass(NMS_CLASS_NAME);
            this.constructor = nmsClass.getConstructor(chatComponentClass);
            WrappedChatSerializer wcs = new WrappedChatSerializer();
            this.nmsHandle = constructor.newInstance(wcs.serialize(chatComponent));
            this.setField(ReflectionUtil.getField(nmsClass, "b"), wcs.serialize(chatComponent2));

        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Constructing wrapped header footer packet", ex);
        }

    }
}
