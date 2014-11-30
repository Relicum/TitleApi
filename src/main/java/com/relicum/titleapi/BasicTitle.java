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

import com.relicum.titleapi.Components.TitleTimes;

/**
 * BasicTitle is the simplest way to send a Title object to a player.
 * <p>It is only a wrapper for {@link com.relicum.titleapi.StandardTitle} except you are not required to create various Chat objects and serialize the messages.
 * As it does all that for you. To use color use the <strong>&amp;</strong> character followed by the color code. In all classes in Title API you use <strong>&amp;</strong> to add color.
 * <p>When you call either of the methods {@link #withClear()} or {@link #withReset()} these packets will automatically be sent for you, both are optional.
 * <p><strong>Below is a simple usage example.</strong> The methods can be called in any order apart from the last 2 !!
 * <pre>
 *     <code>
 *            BasicTitle.get()
 *            .withFadeIn(5)
 *            .withStay(100)
 *            .withFadeOut(-1)
 *            .clear()
 *            .withTitle("&amp;5This is the main title &amp;awith color")
 *            .withSubTitle("&amp;bThis is the sub title")
 *            .getTitleObject()
 *            .send(player);
 *
 * </code>
 * </pre>
 *
 * @author Relicum
 * @version 0.0.1
 */
public class BasicTitle {

    private StandardTitle title = new StandardTitle();

    private BasicTitle() {

    }


    protected static BasicTitle get() {
        return new BasicTitle();
    }

    /**
     * Easy method for passing in all 3 Times parameters at once.
     * <p>Either use this method to set the Times or the separate ones {@link #withFadeIn(int)} , {@link #withStay(int)} , {@link #withFadeOut(int)} not both.
     *
     * @param times the instance of {@link com.relicum.titleapi.Components.TitleTimes} object
     * @return instance of itself for chaining.
     */
    public BasicTitle withTimes(TitleTimes times) {

        title.useTimes()
                .fadeIn(times.getFadeIn())
                .stay(times.getStay())
                .fadeOut(times.getFadeOut());

        return this;
    }

    /**
     * Set the duration in ticks of the fade in effect of the title.
     * Once this period of time is over the title will stay for the amount
     * of time specified in {@link #withStay(int)} .
     * The default value for the official Minecraft version is 20 (1 second).
     *
     * @param ticks The amount of ticks (1/20 second) for the fade in effect.
     * @return instance of itself for chaining.
     */
    public BasicTitle withFadeIn(int ticks) {
        title.fadeIn(ticks);
        title.useTimes();
        return this;
    }

    /**
     * Set the duration in ticks of the fade out effect of the title.
     * The default value for the official Minecraft version is 20 (1 second).
     *
     * @param ticks The amount of ticks (1/20 second) for the fade out effect.
     * @return instance of itself for chaining.
     */
    public BasicTitle withFadeOut(int ticks) {
        title.fadeOut(ticks);
        title.useTimes();
        return this;
    }

    /**
     * Set the duration in ticks how long the title should stay on the screen.
     * Once this period of time is over the title will fade out using the duration
     * specified in {@link #withFadeOut(int)}.
     * The default value for the official Minecraft version is 60 (3 seconds).
     *
     * @param ticks The amount of ticks (1/20 second) for the fade in effect.
     * @return instance of itself for chaining.
     */
    public BasicTitle withStay(int ticks) {
        title.stay(ticks);
        title.useTimes();
        return this;
    }

    /**
     * Set the title to send to the player.
     *
     * @param text The text to use as the title.
     * @return instance of itself for chaining.
     */
    public BasicTitle withTitle(String text) {

        title.title(text);

        return this;
    }

    /**
     * Set the subtitle to send to the player.
     *
     * @param text The text to use as the subtitle.
     * @return instance of itself for chaining.
     */
    public BasicTitle withSubTitle(String text) {

        title.subTitle(text);
        return this;
    }

    /**
     * Remove the currently displayed title from the player's screen
     * and set the configuration back to the default values.
     *
     * @return instance of itself for chaining.
     */
    public BasicTitle withReset() {

        title.reset();
        return this;
    }

    /**
     * Remove the currently displayed title from the player's screen.
     * This will keep the currently used display times and will only remove the title.
     *
     * @return instance of itself for chaining.
     */
    public BasicTitle withClear() {

        title.clear();
        return this;
    }

    /**
     * Get the fully configured title object all you need to do is call the send method {@link com.relicum.titleapi.StandardTitle#send(org.bukkit.entity.Player)} .
     *
     * @return the {@link com.relicum.titleapi.StandardTitle} object which is ready to be sent to a player.
     */
    public StandardTitle getTitleObject() {
        return title;
    }
}
