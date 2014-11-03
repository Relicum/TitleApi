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

import com.relicum.titleapi.Components.ActionPackets;
import com.relicum.titleapi.Components.TitleSender;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.spigotmc.ProtocolInjector;

/**
 * API provides access to various methods to send Title, Tab Header and Action Bar messages.
 * <p>For more information about Titles and their various options see <a href="http://minecraft.gamepedia.com/Commands#title">Minecraft Wiki</a>
 *
 * @author Relicum
 * @version 0.0.1
 */
public class API {

    @Getter
    private final Plugin plugin;

    private final String testMessage;

    protected API(Plugin plugin) {
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

    public boolean checkVersion(Player player) {

        return TitleApi.get().checkVersion(player.getUniqueId());
    }

    public TitleSender getTitleSender(Player player) {

        return TitleSender.get(player);
    }
}
