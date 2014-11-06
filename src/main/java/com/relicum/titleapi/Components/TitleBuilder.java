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
 * Name: TitleBuilder.java Created: 04 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class TitleBuilder {

    private Integer fadeIn = -1;
    private Integer stay = 60;
    private Integer fadeOut = -1;
    private String theTitle;
    private String theSubTitle = "";
    private boolean useTimes = false;
    private boolean useTitle = false;
    private boolean useSubTitle = false;
    private boolean useClear = false;
    private boolean useReset = false;

    private TitleBuilder() {

    }

    public static TitleBuilder get() {
        return new TitleBuilder();
    }

    public TitleBuilder withTimes(int in, int st, int out) {
        this.fadeIn = in;
        this.stay = st;
        this.fadeOut = out;
        this.useTimes = true;

        return this;
    }

    public TitleBuilder withTitle(String theTitle) {
        this.theTitle = theTitle;
        this.useTitle = true;
        return this;
    }

    public TitleBuilder withSubTitle(String theSubTitle) {
        this.theSubTitle = theSubTitle;
        this.useSubTitle = true;
        return this;
    }

    public TitleBuilder withClear(boolean Clear) {
        this.useClear = Clear;
        return this;
    }

    public TitleBuilder withReset(boolean Reset) {
        this.useReset = Reset;
        return this;
    }

    public TitleComponents build() {
        return new TitleComponents(fadeIn, stay, fadeOut, theTitle, theSubTitle, useTimes, useTitle, useSubTitle, useClear, useReset);
    }


}
