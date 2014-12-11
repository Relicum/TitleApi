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

/**
 * Implementation of a scrolling string.
 *
 * @author DarkBlade12
 */
public class ScrollingString {

    private String original;
    private int width;
    private int position;


    public ScrollingString(String original, int width) {
        if (width <= 0)
            throw new IllegalArgumentException("Width value has to be greater than 0");
        else if (original.length() < width)
            throw new IllegalArgumentException("String length has to be greater than the width value");
        this.original = original;
        this.width = width;
    }

    public void scrollForward() {
        position++;
        if (position == original.length())
            reset();
    }

    public void scrollBackward() {
        position--;
        if (position < 0)
            position = original.length() - 1;
    }

    public void reset() {
        position = 0;
    }

    public void setOriginal(String original) {
        if (original.length() < width)
            throw new IllegalArgumentException("String length has to be greater than the width value");
        this.original = original;
        reset();
    }

    public void append(String s) {
        original += s;
    }

    public String getOriginal() {
        return this.original;
    }

    public String getScrolled() {
        int e = position + width;
        return e > original.length() ? original.substring(position, original.length()) + original.substring(0, width - (original.length() - position)) : original.substring(position, e);
    }

    public int getWidth() {
        return this.width;
    }

    public int getPosition() {
        return this.position;
    }
}
