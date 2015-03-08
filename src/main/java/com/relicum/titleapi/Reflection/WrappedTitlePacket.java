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
 * WrappedTitlePacket used to create Title packets which are then sent to players.
 * <p>This is 100% built using reflection so should be fairly version proof.
 * <p>This is mainly for internal use only advanced users should use this class directly
 *
 * @author Relicum
 * @version 0.0.1
 */
public class WrappedTitlePacket extends WrappedPacket {

    private static final String NMS_CLASS_NAME = "PacketPlayOutTitle";
    private static final Class<?> chatComponentClass = ReflectionUtil.getNMSClass("IChatBaseComponent");

    private static Class enumTitleAction = ReflectionUtil.getNMSClass("PacketPlayOutTitle$EnumTitleAction");


    /**
     * Instantiates a new Wrapped title packet.
     *
     * @param in   the length in seconds of fade in animation
     * @param stay the length in seconds the title will display
     * @param out  the length in seconds of fade out animation
     * @throws ReflectionException if there was a problem creating the packets due to reflection
     */
    public WrappedTitlePacket(int in, int stay, int out) throws ReflectionException {

        try {
            this.nmsClass = ReflectionUtil.getNMSClass(NMS_CLASS_NAME);
            this.constructor = nmsClass.getConstructor(int.class, int.class, int.class);
            this.nmsHandle = constructor.newInstance(in, stay, out);

        } catch (Throwable ex) {

            throw ReflectionException.fromThrowable("Constructing wrapped title packet times only", ex);

        }
    }

    /**
     * Instantiates a new Wrapped title packet.
     * <p>All color encoding is done for you. If you are using placeholders make sure you have parsed them before passing it here.
     *
     * @param action        the {@link WrappedTitlePacket.TitleAction} which refines the packet type
     * @param chatComponent the message for the Title of SubTitle
     * @throws ReflectionException if there was a problem creating the packets due to reflection
     */
    public WrappedTitlePacket(WrappedTitlePacket.TitleAction action, String chatComponent) throws ReflectionException
    {

        try {
            this.nmsClass = ReflectionUtil.getNMSClass(NMS_CLASS_NAME);

            if (enumTitleAction.isEnum()) {

                WrappedChatSerializer wcs = new WrappedChatSerializer();

                this.constructor = nmsClass.getConstructor(enumTitleAction, chatComponentClass);

                Object v = Enum.valueOf(enumTitleAction, action.name());

                this.nmsHandle = constructor.newInstance(v, wcs.serialize(chatComponent));

            } else
                throw new Throwable("Error with the enumTitleAction assignment");

        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Constructing wrapped title packet action and message", ex);
        }
    }

    /**
     * Instantiates a new Wrapped title packet.
     *
     * @param action        the {@link WrappedTitlePacket.TitleAction} which refines the packet type
     * @param chatComponent the message for the Title of SubTitle
     * @param in            the length in seconds of fade in animation
     * @param stay          the length in seconds the title will display
     * @param out           the length in seconds of fade out animation
     * @throws ReflectionException if there was a problem creating the packets due to reflection
     */
    public WrappedTitlePacket(WrappedTitlePacket.TitleAction action, String chatComponent, int in, int stay, int out) throws ReflectionException
    {

        try {

            this.nmsClass = ReflectionUtil.getNMSClass(NMS_CLASS_NAME);

            if (enumTitleAction.isEnum()) {

                WrappedChatSerializer wcs = new WrappedChatSerializer();

                this.constructor = nmsClass.getConstructor(enumTitleAction, chatComponentClass, int.class, int.class, int.class);

                Object v = Enum.valueOf(enumTitleAction, action.name());

                this.nmsHandle = constructor.newInstance(v, wcs.serialize(chatComponent), in, stay, out);
            } else
                throw new Throwable("Error with the enumTitleAction assignment");


        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Constructing wrapped title packet action message and times", ex);
        }
    }

    /**
     * Instantiates a new Wrapped title packet, ONLY USE FOR RESET AND CLEAR
     *
     * @param action either RESET or CLEAR {@link WrappedTitlePacket.TitleAction}
     * @throws ReflectionException if there was a problem creating the packets due to reflection
     */
    public WrappedTitlePacket(WrappedTitlePacket.TitleAction action) throws ReflectionException
    {

        try {
            this.nmsClass = ReflectionUtil.getNMSClass(NMS_CLASS_NAME);

            if (action.name().equalsIgnoreCase("RESET") || action.name().equalsIgnoreCase("CLEAR")) {

                this.constructor = nmsClass.getConstructor(enumTitleAction, chatComponentClass);

                if (enumTitleAction.isEnum()) {
                    Object v = Enum.valueOf(enumTitleAction, action.name());
                    this.nmsHandle = constructor.newInstance(v, null);
                } else {
                    throw new Throwable("Error constructing new instance");
                }
            } else
                throw new Throwable("Action invalid, only RESET and CLEAR ALLOWED");
        } catch (Throwable ex) {

            throw ReflectionException.fromThrowable("Constructing wrapped title packet clear or reset", ex);
        }


    }

    /**
     * TitleAction this mirrors an internal NMS class this is used to define the actions related to a Title. <p>The reason to mirror the class is to make it easier to have full
     * reflection of the package.
     *
     * @author Relicum
     * @version 0.0.1
     */
    public static enum TitleAction
    {
        TITLE, SUBTITLE, TIMES, CLEAR, RESET
    }
}
