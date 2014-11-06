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

import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_7_R4.ChatBaseComponent;

/**
 * Name: BaseComponents.java Created: 04 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class BaseComponents {

    public static ChatBaseComponent getChatComponent(String message) {

        return MSerialize.serializerChat(message);
    }

    public static TextComponent getTextComponent(String message) {

        return new TextComponent(MSerialize.colorize(message));
    }
}
