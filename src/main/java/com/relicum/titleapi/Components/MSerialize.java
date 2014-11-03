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

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;

/**
 * MSerialize used internally to serialize messages and convert color codes.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class MSerialize {

    public static String colorize(String string) {

        return string.replaceAll("(&([a-fklmnor0-9]))", "\u00A7$2");
    }

    public static IChatBaseComponent serializer(String text) {

        return ChatSerializer.a(TextConverter.convert(colorize(text)));

    }
}
