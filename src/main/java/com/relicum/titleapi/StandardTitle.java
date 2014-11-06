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

import com.relicum.titleapi.Components.Title;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;

/**
 * StandardTitle
 *
 * @author Relicum
 * @version 0.0.1
 */
public class StandardTitle implements Title {

    private IChatBaseComponent titleText;
    private IChatBaseComponent subTitleText;
    private Integer fadeIn;
    private Integer stay;
    private Integer fadeOut;
    private boolean useTheTimes = false;
    private boolean useClear = false;
    private boolean useReset = false;
    private boolean useTitle = false;
    private boolean useSub = false;

    protected StandardTitle() {

    }

    protected static Title get() {
        return new StandardTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title title(IChatBaseComponent text) {
        this.titleText = text;
        this.useTitle = true;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title subTitle(IChatBaseComponent text) {
        this.subTitleText = text;
        this.useSub = true;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title fadeIn(int ticks) {
        this.fadeIn = ticks;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title stay(int ticks) {
        this.stay = ticks;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title fadeOut(int ticks) {
        this.fadeOut = ticks;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title clear() {
        this.useClear = true;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title reset() {
        this.useReset = true;
        return this;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Title useTimes() {
        this.useTheTimes = true;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title send(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        if (useClear)
            craftPlayer.getHandle().playerConnection.sendPacket(ActionPackets.getClear());

        if (useReset)
            craftPlayer.getHandle().playerConnection.sendPacket(ActionPackets.getReset());

        craftPlayer.getHandle().playerConnection.sendPacket(ActionPackets.getTimes(fadeIn, stay, fadeOut));

        if (useTitle)
            craftPlayer.getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.TITLE, titleText));

        if (useSub)
            craftPlayer.getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.SUBTITLE, subTitleText));
        craftPlayer = null;
        return this;
    }
}
