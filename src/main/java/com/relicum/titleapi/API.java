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

import com.relicum.titleapi.Components.*;
import lombok.Getter;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_7_R4.ChatBaseComponent;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.spigotmc.ProtocolInjector;

/**
 * API provides access to various methods to send Title, Tab Headers and Action Bar messages.
 * <p>For more information about Titles and their various options see <a href="http://minecraft.gamepedia.com/Commands#title">Minecraft Wiki</a>
 *
 * @author Relicum
 * @version 0.0.1
 */
public class API {

    @Getter
    private final Plugin plugin;

    private final ProtocolInjector.PacketTitle reset;

    private final ProtocolInjector.PacketTitle clear;

    private final String testMessage;

    protected API(Plugin plugin) {
        this.plugin = plugin;
        this.reset = ActionPackets.getReset();
        this.clear = ActionPackets.getClear();
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
     * @return the ProtocolInjector.PacketTitle reset packet
     */
    public ProtocolInjector.PacketTitle getResetPacket() {

        return ActionPackets.getReset();
    }

    /**
     * Get clear packet which will Clears the screen title from the screens of the specified player(s).
     * <p>If no screen title is currently being displayed, has no effect.
     *
     * @return the ProtocolInjector.PacketTitle clear packet
     */
    public ProtocolInjector.PacketTitle getClearPacket() {

        return ActionPackets.getClear();
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
     * @return the ProtocolInjector.PacketTitle times packet
     */
    public ProtocolInjector.PacketTitle getTimesPacket(int fadeIn, int stay, int fadeOut) {

        return ActionPackets.getTimes(fadeIn, stay, fadeOut);
    }

    /**
     * Get Title Packet
     * <p>Use <strong>&amp;</strong> and the color code to add color eg <strong>&amp;4</strong> for dark red.
     *
     * @param message the message to display in the Title
     * @return the ProtocolInjector.PacketTitle Title packet
     */
    public ProtocolInjector.PacketTitle getTitlePacket(String message) {

        return ActionPackets.getTitle(message);
    }

    /**
     * Get Sub Title Packet
     * <p>Use <strong>&amp;</strong> and the color code to add color eg <strong>&amp;4</strong> for dark red.
     *
     * @param message the message to display in the Sub Title
     * @return the ProtocolInjector.PacketTitle Title packet
     */
    public ProtocolInjector.PacketTitle getSubTitlePacket(String message) {

        return ActionPackets.getSubTitle(message);
    }

    /**
     * Get Tab Header and Footer packet.
     * <p>Returns the packet with the header and footer message already serialized and colorized.
     * <p>Use <strong>&amp;</strong> and the color code to add color eg <strong>&amp;4</strong> for dark red.
     *
     * @param header the header text
     * @param footer the footer text
     * @return the ProtocolInjector.PacketTabHeader packet.
     */
    public ProtocolInjector.PacketTabHeader getTabHeaderPacket(String header, String footer) {

        return ActionPackets.getTab(header, footer);
    }

    /**
     * Get Action Bar Packet.
     * <p>Use <strong>&amp;</strong> and the color code to add color eg <strong>&amp;4</strong> for dark red.
     *
     * @param message the message to display on the action bar.
     * @return the action bar packet {@link PacketPlayOutChat} .
     */
    public PacketPlayOutChat getActionBarPacket(String message) {

        return ActionPackets.getActionBar(message);
    }

    public boolean checkVersion(Player player) {

        return TitleApi.get().checkVersion(player.getUniqueId());
    }

    public TitleSender getTitleSender(Player player) {

        return TitleSender.get(player);
    }

    public TitleBuilder getTitleBuilder() {
        return TitleBuilder.get();
    }

    public TitlesSender getTitlesSender(TitleComponents components) {

        return TitlesSender.get(components);
    }

    public IChatBaseComponent getIChatBaseComponent(String message) {

        return MSerialize.serializer(message);
    }

    public ChatBaseComponent getChatComponent(String message) {

        return MSerialize.serializerChat(message);
    }

    public TextComponent getTextComponent(String message) {

        return BaseComponents.getTextComponent(message);
    }

    public MultiTitleBuilder getMultiTitleBuilder() {

        return MultiTitleBuilder.get();
    }

    public MultiSender getMultiSender(MultiComponents components, long delay) {

        return MultiSender.get(components, delay, getPlugin());
    }

    public TabBuilder getTabBuilder() {

        return TabBuilder.get();
    }

    public TabSender getTabSender(IChatBaseComponent header, IChatBaseComponent footer) {

        return TabSender.get(header, footer);
    }

    public void sendActionBar(Player player, String message) {

        ActionBar.sendToPlayer(player, message);
    }

    public Title getTitle() {

        return StandardTitle.get();
    }

    public Tab getTab() {

        return StandardTab.get();
    }

    public BasicTitle getNewBasicTitle() {

        return BasicTitle.get();
    }

    public void enablePlaceHolders() {

        TitleApi.get().setUpPlaceHolders();
    }

    /**
     * Replace all place holders in a string, to use this you need to first run {@link API#enablePlaceHolders()} .
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
