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

import net.minecraft.server.v1_7_R4.ChatBaseComponent;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;

/**
 * ChatSerialize used internally to serialize messages and convert color codes.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class ChatSerialize {


    public static String colorize(String string) {

        return string.replaceAll("(&([a-fklmnor0-9]))", "\u00A7$2");
    }

    /**
     * Escape and colorize any string.
     *
     * @param message the message to escape and colorize
     * @return the formatted string
     */
    public static String textConvert(String message) {

        return TextProcessor.convertAndColorize(message);
    }

    /**
     * Serializer, escape and colorize the text returning it as an instance of {@link net.minecraft.server.v1_7_R4.IChatBaseComponent}
     * <p>If you are using placeholders they should be formatted before creating this object. This object can be used directly as a message
     * for Titles or Tab Headers and Footers.
     *
     * @param text the text to be serialize,
     * @return the {@link net.minecraft.server.v1_7_R4.IChatBaseComponent}
     */
    public static IChatBaseComponent serializer(String text) {

        return ChatSerializer.a(TextProcessor.convertAndColorize(text));

    }

    /**
     * Serializer, escape and colorize the text returning it as an instance of {@link net.minecraft.server.v1_7_R4.ChatBaseComponent}
     * <p>If you are using placeholders they should be formatted before creating this object.
     *
     * @param text the text to be serialize,
     * @return the {@link net.minecraft.server.v1_7_R4.ChatBaseComponent}
     */
    public static ChatBaseComponent serializerChat(String text) {

        return (ChatBaseComponent) ChatSerializer.a(TextProcessor.convertAndColorize(text));

    }

}
