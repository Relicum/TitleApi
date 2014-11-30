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

import java.util.List;

/**
 * Name: MultiComponents.java Created: 04 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class MultiComponents {

    private Integer fadeIn;
    private Integer stay;
    private Integer fadeOut;
    private String theTitle;
    private List<String> theSubTitles;
    private Boolean useClear;
    private Boolean useReset;
    private Integer totalSubs;

    public MultiComponents(Integer fadeIn, Integer stay, Integer fadeOut, String theTitle, List<String> theSubTitles, Boolean useClear, Boolean useReset, Integer totalSubs) {
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
        this.theTitle = theTitle;
        this.theSubTitles = theSubTitles;
        this.useClear = useClear;
        this.useReset = useReset;
        this.totalSubs = totalSubs;
    }


    /**
     * Gets fadeOut.
     *
     * @return Value of fadeOut.
     */
    public Integer getFadeOut() {
        return fadeOut;
    }

    /**
     * Gets fadeIn.
     *
     * @return Value of fadeIn.
     */
    public Integer getFadeIn() {
        return fadeIn;
    }

    /**
     * Gets theSubTitles.
     *
     * @param index next title
     * @return Value of theSubTitles.
     */
    public String getNextSubTitle(int index) {
        return theSubTitles.get(index);
    }

    /**
     * Gets useClear.
     *
     * @return Value of useClear.
     */
    public Boolean getUseClear() {
        return useClear;
    }

    /**
     * Gets stay.
     *
     * @return Value of stay.
     */
    public Integer getStay() {
        return stay;
    }

    /**
     * Gets useReset.
     *
     * @return Value of useReset.
     */
    public Boolean getUseReset() {
        return useReset;
    }

    /**
     * Gets theTitle.
     *
     * @return Value of theTitle.
     */
    public String getTheTitle() {
        return theTitle;
    }


    /**
     * Gets totalSubs.
     *
     * @return Value of totalSubs.
     */
    public Integer getTotalSubs() {
        return totalSubs;
    }
}
