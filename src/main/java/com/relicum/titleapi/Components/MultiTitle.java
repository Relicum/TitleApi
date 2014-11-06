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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Name: MultiTile.java Created: 04 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class MultiTitle {


    private Map<String, String> subTitles = new HashMap<>(5);
    private int count = 0;

    private MultiTitle() {

    }

    public static MultiTitle get() {
        return new MultiTitle();
    }

    public MultiTitle addSubTile(String message) {

        switch (count) {

            case 0:
                subTitles.put("0", message);
                count++;
                break;
            case 1:
                subTitles.put("1", message);
                count++;
                break;
            case 2:
                subTitles.put("2", message);
                count++;
                break;
            case 3:
                subTitles.put("3", message);
                count++;
                break;
            case 4:
                subTitles.put("4", message);
                count++;
                break;
            default:
                break;

        }
        return this;
    }

    public List<String> getSubTitleList() {

        return (List<String>) subTitles.values();
    }


}
