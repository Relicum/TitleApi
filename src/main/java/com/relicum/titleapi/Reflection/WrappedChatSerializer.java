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

import com.relicum.titleapi.Components.TextProcessor;
import com.relicum.titleapi.Exception.ReflectionException;

import java.lang.reflect.Method;

/**
 * WrappedChatSerializer
 *
 * @author dmulloy2
 * @version 0.0.1
 */
public class WrappedChatSerializer extends AbstractWrapper {
    private static final String NMS_CLASS_NAME = "ChatSerializer";

    private final Method a;

    public WrappedChatSerializer() throws ReflectionException {
        try {
            this.nmsClass = ReflectionUtil.getNMSClass(NMS_CLASS_NAME);
            this.constructor = null;
            this.nmsHandle = null;

            this.a = ReflectionUtil.getMethod(nmsClass, "a", String.class);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Constructing chat serializer", ex);
        }
    }

    public final Object serialize(String json) throws ReflectionException {
        return invokeMethod(a, TextProcessor.convertAndColorize(json));
    }
}
