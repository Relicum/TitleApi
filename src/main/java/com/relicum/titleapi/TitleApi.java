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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.TabExecutor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * Name: TitleApi.java Created: 03 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class TitleApi extends JavaPlugin implements Listener {

    private static TitleApi instance;
    private List<String> pluginNames = new ArrayList<>();
    private Map<UUID, Integer> playerVersion = Collections.synchronizedMap(new HashMap<>());
    private boolean beingUsed;
    private TabExecutor cmd;
    private Placeholder placeholder;

    @Override
    public void onEnable() {

        instance = this;
        beingUsed = false;
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Title API is being enabled");

    }

    @Override
    public void onDisable() {
        pluginNames.clear();
        playerVersion.clear();
    }

    public static TitleApi get() {

        return instance;
    }

    /**
     * Get an instance of {@link com.relicum.titleapi.API} .
     * <p>You must use this method to be a new instance of {@link com.relicum.titleapi.API} you can not directly
     * instantiate a new instance as it is protected.
     * <p>A plugin can not create more than one instance of TitleApi.
     *
     * @param plugin your plugin
     * @return the new instance of {@link com.relicum.titleapi.API}
     * @throws java.lang.Exception if a plugin tries to create multiple instances of TitleApi
     */
    public API getTitleApi(Plugin plugin) throws Exception {


        if (pluginNames.contains(plugin.getName())) {

            throw new Exception("This plugin " + plugin.getName() + " tried to create multiple instances of TitleApi");

        } else {

            if (!beingUsed) {
                getServer().getPluginManager().registerEvents(this, this);
                getLogger().info(plugin.getName() + " has hooked in first start listeners");
                beingUsed = true;
            }

            pluginNames.add(plugin.getName());
            plugin.getLogger().info("TitleApi Successfully Hooked");
            return new API(plugin);
        }

    }

    /**
     * Replace holders.
     *
     * @param message the message
     * @param player  the player
     * @return the string
     */
    public String replaceHolders(String message, Player player) {

        if (placeholder == null)
            setUpPlaceHolders();

        return placeholder.replaceAll(message, player);
    }

    protected void setUpPlaceHolders() {

        placeholder = new Placeholder(this);
        placeholder.registerHolder(new PlayerHolder(HolderType.PLAYER, this));
        placeholder.registerHolder(new StatsHolder(HolderType.STATS, this));

        cmd = new PlaceCommand(placeholder.getHolder(HolderType.PLAYER), placeholder.getHolder(HolderType.STATS), placeholder);
        getCommand("testholder").setExecutor(cmd);
        getCommand("testholder").setTabCompleter(cmd);

    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {

        playerVersion.put(e.getPlayer().getUniqueId(), ((CraftPlayer) e.getPlayer()).getHandle().playerConnection.networkManager.getVersion());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        if (!playerVersion.containsKey(e.getPlayer().getUniqueId())) return;

        playerVersion.remove(e.getPlayer().getUniqueId());
    }

    public boolean checkVersion(UUID uuid) {

        // if(!playerVersion.containsKey(uuid))return false;

        Integer i = playerVersion.get(uuid);

        if (i == 47) {
            return true;
        } else return false;
    }

}
