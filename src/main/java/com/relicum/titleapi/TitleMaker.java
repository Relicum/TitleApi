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

import com.relicum.titleapi.Components.TabBuilder;
import com.relicum.titleapi.Components.Title;
import com.relicum.titleapi.Components.TitleTimes;
import com.relicum.titleapi.Exception.ReflectionException;
import com.relicum.titleapi.Reflection.WrappedChatPacket;
import com.relicum.titleapi.Reflection.WrappedHeaderFooter;
import com.relicum.titleapi.Reflection.WrappedTitlePacket;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * API provides access to various methods to send Title, Tab Headers and Action Bar messages.
 * <p>For more information about Titles and their various options see <a href="http://minecraft.gamepedia.com/Commands#title">Minecraft Wiki</a>
 *
 * @author Relicum
 * @version 0.0.1
 */
public class TitleMaker {

    @Getter
    private final Plugin plugin;

    private final String testMessage;

    protected TitleMaker(Plugin plugin) {
        this.plugin = plugin;

        testMessage = "Test Message Successful";
    }

    /**
     * Used purely as a test method to see if you have successfully created a API instance.
     *
     * @return a string saying <strong>Test Message Successful</strong>
     */
    public String getTestMessage() {
        return testMessage;
    }

    /**
     * Get reset packet.
     * <p>Resets the subtitle text to blank text, the fade-in time to 20 (1 second fade-in), the stay time to 60 (3 seconds)
     * and the fade-out time to 20 (1 second fade-out) for the specified player(s).
     *
     * @return WrappedTitlePacket
     * @throws ReflectionException if an error occurs.
     */
    public WrappedTitlePacket getResetPacket() throws ReflectionException {

        return ActionPackets.getReset();
    }

    /**
     * Send reset packet to a player
     *
     * @param player the player
     * @throws ReflectionException if an error occurs.
     */
    public void sendResetPacket(Player player) throws ReflectionException {

        PacketSender.sendTitlePacket(player, ActionPackets.getReset());
    }

    /**
     * Get clear packet which will Clears the screen title from the screens of the specified player(s).
     * <p>If no screen title is currently being displayed, has no effect.
     *
     * @return WrappedTitlePacket
     * @throws ReflectionException if an error occurs.
     */
    public WrappedTitlePacket getClearPacket() throws ReflectionException {

        return ActionPackets.getClear();
    }

    /**
     * Send clear packet to a player.
     *
     * @param player the player
     * @throws ReflectionException if an error occurs.
     */
    public void sendClearPacket(Player player) throws ReflectionException {

        PacketSender.sendTitlePacket(player, ActionPackets.getClear());
    }

    /**
     * Get times packet.
     * <p>If a screen title is currently being displayed to the specified player(s)
     * changes the fade-in, stay, and fade-out times of the current screen title (and of all future screen titles).
     * <p>Otherwise, specifies the times for future screen titles to be displayed to the specified player(s).
     *
     * @param fadeIn  the length of time the fade in animation lasts, for no fade in pass -1
     * @param stay    the amount of time the title will be displayed
     * @param fadeOut the length of time the fade out animation lasts, for no fade out pass -1
     * @return the title packet for sending times.
     * @throws ReflectionException if there is a prob
     */
    public WrappedTitlePacket getTimesPacket(int fadeIn, int stay, int fadeOut) throws ReflectionException {

        return ActionPackets.getTimes(fadeIn, stay, fadeOut);
    }

    /**
     * Get the packet for the times for fade in, stay and fade out.
     * <p>All times are in ticks, set to -1 to not set a field.
     *
     * @param titleTimes instance of {@link TitleTimes}
     * @return WrappedTitlePacket.
     * @throws ReflectionException if there is a prob
     */
    public WrappedTitlePacket getTimesPacket(TitleTimes titleTimes) throws ReflectionException {

        return ActionPackets.getTimes(titleTimes);
    }

    /**
     * Send an individual times packet to a player.
     *
     * @param player the player
     * @param fadeIn  the length of time the fade in animation lasts, for no fade in pass -1
     * @param stay    the amount of time the title will be displayed
     * @param fadeOut the length of time the fade out animation lasts, for no fade out pass -1
     * @throws ReflectionException if any error occurs.
     */
    public void sendTimesPacket(Player player, int fadeIn, int stay, int fadeOut) throws ReflectionException {

        PacketSender.sendTitlePacket(player, ActionPackets.getTimes(fadeIn, stay, fadeOut));
    }

    /**
     * Send an individual times packet to a player.
     *
     * @param player     the player
     * @param titleTimes the object {@link TitleTimes}
     * @throws ReflectionException if an error occurs.
     */
    public void sendTimesPacket(Player player, TitleTimes titleTimes) throws ReflectionException {

        PacketSender.sendTitlePacket(player, ActionPackets.getTimes(titleTimes));
    }

    /**
     * Get Title Packet
     * <p>Use <strong>&amp;</strong> and the color code to add color eg <strong>&amp;4</strong> for dark red.
     *
     * @param message the message to display in the Title
     * @return WrappedTitlePacket
     * @throws ReflectionException
     */
    public WrappedTitlePacket getTitlePacket(String message) throws ReflectionException {

        return ActionPackets.getTitle(message);
    }

    /**
     * Gets title packet including times.
     *
     * @param message the message to display in the Title
     * @param fadeIn  the length of time the fade in animation lasts, for no fade in pass -1
     * @param stay    the amount of time the title will be displayed
     * @param fadeOut the length of time the fade out animation lasts, for no fade out pass -1
     * @return WrappedTitlePacket
     * @throws ReflectionException the reflection exception
     */
    public WrappedTitlePacket getTitleWithTimes(String message, int fadeIn, int stay, int fadeOut) throws ReflectionException {

        return ActionPackets.getTitleWithTimes(message, fadeIn, stay, fadeOut);
    }

    /**
     * Get Sub Title Packet
     * <p>Use <strong>&amp;</strong> and the color code to add color eg <strong>&amp;4</strong> for dark red.
     *
     * @param message the message to display in the Sub Title
     * @return WrappedTitlePacket
     * @throws ReflectionException if an error occurs.
     */
    public WrappedTitlePacket getSubTitlePacket(String message) throws ReflectionException {

        return ActionPackets.getSubTitle(message);
    }

    /**
     * Get Tab Header and Footer packet.
     * <p>Returns the packet with the header and footer message already serialized and colorized.
     * <p>Use <strong>&amp;</strong> and the color code to add color eg <strong>&amp;4</strong> for dark red.
     *
     * @param header the header text
     * @param footer the footer text
     * @return the List Header and Footer packet.
     */
    public WrappedHeaderFooter getTabHeaderPacket(String header, String footer) {

        return ActionPackets.getTab(header, footer);
    }

    /**
     * Get Action Bar Packet.
     * <p>Use <strong>&amp;</strong> and the color code to add color eg <strong>&amp;4</strong> for dark red.
     *
     * @param message the message to display on the action bar.
     * @return the action bar packet {@link WrappedChatPacket} .
     */
    public WrappedChatPacket getActionBarPacket(String message) {

        return ActionPackets.getActionBar(message);
    }

    /**
     * Send a single Action bar message to the player.
     * <p>Use <strong>&amp;</strong> and the color code to add color eg <strong>&amp;4</strong> for dark red.
     *
     * @param player  the player to send the action bar message to.
     * @param message the message to display on the action bar.
     * @throws ReflectionException if the ActionBar message was unable to be sent.
     */
    public void sendActionBar(Player player, String message) throws ReflectionException {

        PacketSender.sendActionBarPacket(player, ActionPackets.getActionBar(message));

    }

    /**
     * Send the tab Header and Footer to the player.
     *
     * @param player the player to send the header and footer packet to.
     * @param header the header text.
     * @param footer the footer text.
     * @throws ReflectionException the failed packet send exception
     */
    public void sendHeaderFooter(Player player, String header, String footer) throws ReflectionException {

        PacketSender.sendTabPacket(player, ActionPackets.getTab(header, footer));
    }

    /**
     * Get title times builder, simple build to create an object representing the values of a Times packet.
     *
     * <p>Define fadeIn,stay and fadeOut and use it as an easy way to pass the object to other objects.
     * <p>This can be passed to {@link #sendTimesPacket(org.bukkit.entity.Player, TitleTimes)} to send the times packet.
     *
     * @return {@link TitleTimes.TitleTimesBuilder} object.
     */
    public TitleTimes.TitleTimesBuilder getTitleTimesBuilder() {

        return TitleTimes.newBuilder();
    }


    public TabBuilder getTabBuilder() {

        return TabBuilder.get();
    }

    public TabSender getTabSender(String header, String footer) {

        return TabSender.get(header, footer);
    }

    public Title getTitle() {

        return StandardTitle.get();
    }

    public BasicTitle getNewBasicTitle() {

        return BasicTitle.get();
    }

    public void enablePlaceHolders() {

        TitleApi.get().setUpPlaceHolders();
    }

    /**
     * Replace all place holders in a string, to use this you need to first run {@link TitleMaker#enablePlaceHolders()} .
     * <p>Currently the place holders are built in and are not configurable directly.
     * <p>Only enable if your are actually going to use them.
     *
     * @param message the message to have any place holders replaced
     * @param player  the player
     * @return the formatted string
     */
    public String replaceHolders(String message, Player player) {

        return TitleApi.get().replaceHolders(message, player);
    }


}
