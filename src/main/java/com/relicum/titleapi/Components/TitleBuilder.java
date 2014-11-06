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
 * TitleBuilder is used to pass in all the values needed to create a Titles packet and Transform it into a instance of {@link com.relicum.titleapi.Components.TitleComponents} .
 * <p>This is useful when you want to save the values to disk as {@link com.relicum.titleapi.Components.TitleComponents} implements {@link org.bukkit.configuration.serialization.ConfigurationSerializable}
 * <p>Use <strong>&amp;</strong> and the color code to add color eg <strong>&amp;4</strong> for dark red.
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

    /**
     * Get new instance of TitleBuilder
     *
     * @return instance of itself for chaining
     */
    public static TitleBuilder get() {
        return new TitleBuilder();
    }

    /**
     * Set the time values for the animation and display length.
     * <p>Both fade in and out can both be set to -1 to have no animation effect.
     *
     * @param in  the fade in length in ticks.
     * @param st  the display length in ticks.
     * @param out the fade out length in ticks.
     * @return instance of itself for chaining.
     */
    public TitleBuilder withTimes(int in, int st, int out) {
        this.fadeIn = in;
        this.stay = st;
        this.fadeOut = out;
        this.useTimes = true;

        return this;
    }

    /**
     * The text used for the title.
     * <p>Use <strong>&amp;</strong> and the color code to add color eg <strong>&amp;4</strong> for dark red.
     *
     *
     * @param theTitle the the title text.
     * @return instance of itself for chaining.
     */
    public TitleBuilder withTitle(String theTitle) {
        this.theTitle = theTitle;
        this.useTitle = true;
        return this;
    }

    /**
     * The text used for the sub title.
     * <p>Use <strong>&amp;</strong> and the color code to add color eg <strong>&amp;4</strong> for dark red.
     *
     * @param theSubTitle the the sub title text.
     * @return instance of itself for chaining.
     */
    public TitleBuilder withSubTitle(String theSubTitle) {
        this.theSubTitle = theSubTitle;
        this.useSubTitle = true;
        return this;
    }

    /**
     * Send a clear packet before the messages.
     *
     * @param Clear set to true to have a clear packet sent.
     * @return instance of itself for chaining.
     */
    public TitleBuilder withClear(boolean Clear) {
        this.useClear = Clear;
        return this;
    }

    /**
     * Send a reset packet before the messages.
     *
     * @param Reset set to true to have a reset packet sent.
     * @return instance of itself for chaining.
     */
    public TitleBuilder withReset(boolean Reset) {
        this.useReset = Reset;
        return this;
    }

    /**
     * Build {@link com.relicum.titleapi.Components.TitleComponents} object
     *
     * @return the {@link com.relicum.titleapi.Components.TitleComponents} object.
     */
    public TitleComponents build() {
        return new TitleComponents(fadeIn, stay, fadeOut, theTitle, theSubTitle, useTimes, useTitle, useSubTitle, useClear, useReset);
    }


}
