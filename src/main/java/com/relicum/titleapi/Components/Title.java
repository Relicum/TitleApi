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

import org.bukkit.entity.Player;

/**
 * Represents a configuration of a title.
 * A title in Minecraft consists of a main title and a sub title.
 * It will {@link #fadeIn(int)}, {@link #stay(int)}, and {@link #fadeOut(int)}
 * for a specified amount of time.
 * In most cases you will want to {@link #reset()} the current title first so
 * your title won't be affected by a previous one.
 *
 * @author Relicum MD_5
 */
public interface Title {
    /**
     * Set the title to send to the player.
     *
     * @param text The text to use as the title.
     * @return This title configuration.
     */
    public Title title(String text);

    /**
     * Set the subtitle to send to the player.
     *
     * @param text The text to use as the subtitle.
     * @return This title configuration.
     */
    public Title subTitle(String text);

    /**
     * Set the duration in ticks of the fade in effect of the title.
     * Once this period of time is over the title will stay for the amount
     * of time specified in {@link #stay(int)}.
     * The default value for the official Minecraft version is 20 (1 second).
     *
     * @param ticks The amount of ticks (1/20 second) for the fade in effect.
     * @return This title configuration.
     */
    public Title fadeIn(int ticks);

    /**
     * Set the duration in ticks how long the title should stay on the screen.
     * Once this period of time is over the title will fade out using the duration
     * specified in {@link #fadeOut(int)}.
     * The default value for the official Minecraft version is 60 (3 seconds).
     *
     * @param ticks The amount of ticks (1/20 second) for the fade in effect.
     * @return This title configuration.
     */
    public Title stay(int ticks);

    /**
     * Set the duration in ticks of the fade out effect of the title.
     * The default value for the official Minecraft version is 20 (1 second).
     *
     * @param ticks The amount of ticks (1/20 second) for the fade out effect.
     * @return This title configuration.
     */
    public Title fadeOut(int ticks);

    /**
     * Remove the currently displayed title from the player's screen.
     * This will keep the currently used display times and will only remove the title.
     *
     * @return This title configuration.
     */
    public Title clear();

    /**
     * Remove the currently displayed title from the player's screen
     * and set the configuration back to the default values.
     *
     * @return This title configuration.
     */
    public Title reset();

    /**
     * If a Times packet should be sent, default is false.
     * <p>Call this method if you want a times packet to be sent.
     *
     * @return This title configuration.
     */
    public Title useTimes();

    /**
     * Send this title configuration to the specified player.
     *
     * @param player The player to send the title to.
     * @return This title configuration.
     */
    public Title send(Player player);


}
