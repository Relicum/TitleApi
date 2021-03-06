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

/**
 * TabBuilder used to create the parts of a Header and Footer packet.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class TabBuilder {


    private String header;
    private String footer;

    private TabBuilder() {

    }

    public static TabBuilder get() {
        return new TabBuilder();
    }

    public TabBuilder setHeader(String header) {
        this.header = header;
        return this;
    }

    public TabBuilder setFooter(String footer) {
        this.footer = footer;
        return this;
    }


    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }
}
