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
 * TitleTimes is used to create the values for a Times packets.
 * <p>Both fade in and fade out are optional and set to -1 as default which means there is not any animation.
 * <p>All times are represented in ticks. Call {@link TitleTimes.TitleTimesBuilder#build()} to be the instance of {@link com.relicum.titleapi.Components.TitleTimes}
 *
 * @author Relicum
 * @version 0.0.1
 */
public class TitleTimes {

    private int fadeIn = -1;
    private int stay = 60;
    private int fadeOut = -1;

    private TitleTimes(TitleTimesBuilder titleTimesBuilder) {
        fadeIn = titleTimesBuilder.fadeIn;
        stay = titleTimesBuilder.stay;
        fadeOut = titleTimesBuilder.fadeOut;
    }

    /**
     * New builder to create a new object representing a Times packet.
     *
     * @return the builder
     */
    public static TitleTimesBuilder newBuilder() {
        return new TitleTimesBuilder();
    }

    /**
     * Gets the length of time the title should stay displayed
     *
     * @return number of ticks it should be displayed.
     */
    public int getStay() {
        return stay;
    }

    /**
     * Gets the length of time for the fade out animation.
     *
     * @return number of ticks it should be displayed.
     */
    public int getFadeOut() {
        return fadeOut;
    }

    /**
     * Gets the length of time for the fade out animation.
     *
     * @return number of ticks it should be displayed.
     */
    public int getFadeIn() {
        return fadeIn;
    }


    public static final class TitleTimesBuilder {
        private int fadeIn;
        private int stay;
        private int fadeOut;

        private TitleTimesBuilder() {
        }

        /**
         * Set the duration in ticks of the fade in effect of the title.
         * Once this period of time is over the title will stay for the amount
         * of time specified in {@link #withStay(int)}.
         * The default value for the official Minecraft version is 20 (1 second).
         *
         * @param fadeIn The amount of ticks (1/20 second) for the fade in effect.
         * @return This title configuration.
         */
        public TitleTimesBuilder withFadeIn(int fadeIn) {
            this.fadeIn = fadeIn;
            return this;
        }

        /**
         * Set the duration in ticks how long the title should stay on the screen.
         * Once this period of time is over the title will fade out using the duration
         * specified in {@link #withFadeOut(int)}
         * The default value for the official Minecraft version is 60 (3 seconds).
         *
         * @param stay The amount of ticks (1/20 second) for the fade in effect.
         * @return This title configuration.
         */
        public TitleTimesBuilder withStay(int stay) {
            this.stay = stay;
            return this;
        }

        /**
         * Set the duration in ticks of the fade out effect of the title.
         * The default value for the official Minecraft version is 20 (1 second).
         *
         * @param fadeOut The amount of ticks (1/20 second) for the fade out effect.
         * @return This title configuration.
         */
        public TitleTimesBuilder withFadeOut(int fadeOut) {
            this.fadeOut = fadeOut;
            return this;
        }

        /**
         * Build and return new {@link com.relicum.titleapi.Components.TitleTimes} object
         *
         * @return the {@link com.relicum.titleapi.Components.TitleTimes}
         */
        public TitleTimes build() {
            return new TitleTimes(this);
        }
    }


}
