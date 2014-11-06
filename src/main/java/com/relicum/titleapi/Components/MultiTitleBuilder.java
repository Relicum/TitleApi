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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Name: MultiTitleBuilder.java Created: 04 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class MultiTitleBuilder {

    private Integer fadeIn = -1;
    private Integer stay = 60;
    private Integer fadeOut = -1;
    private String theTitle;
    private boolean useClear = false;
    private boolean useReset = false;
    private Map<String, String> subTitles = new HashMap<>(5);
    private int count = 0;

    private MultiTitleBuilder() {

    }

    public static MultiTitleBuilder get() {
        return new MultiTitleBuilder();
    }

    public MultiTitleBuilder addSubTile(String message) {

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

    public MultiTitleBuilder withTimes(int in, int st, int out) {
        this.fadeIn = in;
        this.stay = st;
        this.fadeOut = out;

        return this;
    }

    public MultiTitleBuilder withTitle(String theTitle) {
        this.theTitle = theTitle;

        return this;
    }

    public MultiTitleBuilder withClear(boolean Clear) {
        this.useClear = Clear;
        return this;
    }

    public MultiTitleBuilder withReset(boolean Reset) {
        this.useReset = Reset;
        return this;
    }

    private List<String> getListOfSubs() {
        List<String> list = new ArrayList<>(3);
        for (String s : subTitles.values()) {
            list.add(s);
        }
        return list;
    }

    public MultiComponents build() {

        return new MultiComponents(fadeIn, stay, fadeOut, theTitle, getListOfSubs(), useClear, useReset, subTitles.size());
    }
}
