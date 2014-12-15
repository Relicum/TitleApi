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

import com.relicum.titleapi.Reflection.WrappedChatPacket;

import java.util.ArrayList;
import java.util.List;

/**
 * ActionPart is an implementation of a single message that is displayed using the ActionBar.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class ActionPart extends AbstractActionPart {


    public ActionPart(String message, int totalPackets, int period) {
        super(message, totalPackets, period);
    }

    @Override
    public List<WrappedChatPacket> process() {

        List<WrappedChatPacket> list = new ArrayList<>(getTotalPackets() - 1);


        for (int i = 0; i < getTotalPackets(); i++) {
            list.add(i, getChatPacket());
        }

        return list;
    }
}
