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

import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * MessageCollection used to store a collection of messages THIS IS INCOMPLETE DO NOT USE.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class MessageCollection {

    private List<String> messages;

    public MessageCollection() {

        this.messages = new ArrayList<>();
    }

    public MessageCollection(List<String> list) {

        this.messages = new ArrayList<>(list);
    }

    public MessageCollection addMessage(String text) {
        this.messages.add(text);
        return this;
    }

    public void format(Player player) {

        print();
        List<String> tmp = new ArrayList<>();
        for (ListIterator<String> iterator = messages.listIterator(); iterator.hasNext(); ) {

            tmp.add(TitleApi.get().replaceHolders(iterator.next(), player));
        }
        messages.clear();
        messages.addAll(tmp);
        tmp.clear();
        print();
    }

    public List<ProtocolInjector.PacketTitle> getMessages() {

        List<ProtocolInjector.PacketTitle> tmp = new ArrayList<>();
        for (ListIterator<String> iterator = messages.listIterator(); iterator.hasNext(); ) {

            tmp.add(ActionPackets.getTitle(iterator.next()));
        }

        return tmp;
    }

    public MessageCollection getCopy() {

        return new MessageCollection(messages);

    }

    public void print() {
        System.out.println("Start messages ---");
        for (String s : messages) {
            System.out.println(s);
        }
        System.out.println("End of messages");
        System.out.println(" ");
        System.out.println("Total = " + messages.size());
    }

    public void removeFirst() {

        messages.remove(0);

    }


}
