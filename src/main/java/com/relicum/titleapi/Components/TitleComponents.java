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

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TitleComponents is built using {@link TitleBuilderOld} .
 * <p>Holds all the values required to send a basic Title and Sub Title to the player with the advantage that this class already implements {@link org.bukkit.configuration.serialization.ConfigurationSerializable} .
 * <p>This allows you to save this instances of this object to the standard config.yml file like it was any other value. Nothing extra is required when saving and loading it from file.
 * <p>You will need to add this to your plugins onEnable, needs to be the first or second line of the method <i>ConfigurationSerialization.registerClass(TitleComponents.class);</i>
 * <p>Use <strong>&amp;</strong> and the color code to add color eg <strong>&amp;4</strong> for dark red.
 * <pre>
 *     <code>
 *      TitleComponents titleBuilder = TitleBuilderOld.get()
 *          .withSubTitle("&amp;5This is the sub title")
 *          .withTitle("&amp;aThe main title")
 *          .withTimes(-1,80,-1)
 *          .withReset(true)
 *          .build();
 *     getConfig().set("title.settings",titleBuilder);
 *     </code>
 * </pre>
 * <p>Then to load the object back from file and send it directly to a player, see {@link com.relicum.titleapi.TitleSender} .
 * <pre>
 *     <code>
 *      TitleComponents titleBuilder= (TitleComponents) getConfig().get("title.settings");
 *
 *      TitleSender sender=  TitleSender.get(titleBuilder);
 *      sender.setPlayer(player);
 *      sender.send();
 *     </code>
 * </pre>
 *
 * @author Relicum
 * @version 0.0.1
 */
@SerializableAs("TitleComponents")
public class TitleComponents implements ConfigurationSerializable {

    private final Integer fadeIn;
    private final Integer stay;
    private final Integer fadeOut;
    private final String theTitle;
    private final String theSubTitle;
    private final Boolean useTimes;
    private final Boolean useTitle;
    private final Boolean useSubTitle;
    private final Boolean useClear;
    private final Boolean useReset;

    public TitleComponents(Integer fadeIn, Integer stay, Integer fadeOut, String theTitle, String theSubTitle, boolean useTimes, boolean useTitle, boolean useSubTitle, boolean useClear, boolean useReset) {
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
        this.theTitle = theTitle;
        this.theSubTitle = theSubTitle;
        this.useTimes = useTimes;
        this.useTitle = useTitle;
        this.useSubTitle = useSubTitle;
        this.useClear = useClear;
        this.useReset = useReset;
    }

    public TitleComponents(Map<String, Object> map) {
        this.fadeIn = (Integer) map.get("fadeIn");
        this.stay = (Integer) map.get("stay");
        this.fadeOut = (Integer) map.get("fadeOut");
        this.theTitle = (String) map.get("theTitle");
        this.theSubTitle = (String) map.get("theSubTitle");
        this.useTimes = (Boolean) map.get("useTimes");
        this.useTitle = (Boolean) map.get("useTitle");
        this.useSubTitle = (Boolean) map.get("useSubTitle");
        this.useClear = (Boolean) map.get("useClear");
        this.useReset = (Boolean) map.get("useReset");

    }


    /**
     * Gets useReset.
     *
     * @return Value of useReset.
     */
    public boolean isUseReset() {
        return useReset;
    }

    /**
     * Gets useSubTitle.
     *
     * @return Value of useSubTitle.
     */
    public boolean isUseSubTitle() {
        return useSubTitle;
    }

    /**
     * Gets useClear.
     *
     * @return Value of useClear.
     */
    public boolean isUseClear() {
        return useClear;
    }

    /**
     * Gets theSubTitle.
     *
     * @return Value of theSubTitle.
     */
    public String getTheSubTitle() {
        return theSubTitle;
    }

    /**
     * Gets useTitle.
     *
     * @return Value of useTitle.
     */
    public boolean isUseTitle() {
        return useTitle;
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
     * Gets stay.
     *
     * @return Value of stay.
     */
    public Integer getStay() {
        return stay;
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
     * Gets useTimes.
     *
     * @return Value of useTimes.
     */
    public boolean isUseTimes() {
        return useTimes;
    }

    public Map<String, Object> serialize() {
        Map<String, Object> data = new LinkedHashMap<>();

        for (Field field : getClass().getDeclaredFields()) {
            if (Modifier.isTransient(field.getModifiers()))
                continue;

            try {
                boolean accessible = field.isAccessible();

                field.setAccessible(true);

                if (field.getType().equals(Integer.TYPE)) {
                    if (field.getInt(this) != 0)
                        data.put(field.getName(), field.getInt(this));
                } else if (field.getType().equals(Long.TYPE)) {
                    if (field.getLong(this) != 0)
                        data.put(field.getName(), field.getLong(this));
                } else if (field.getType().equals(Boolean.TYPE)) {
                    if (field.getBoolean(this))
                        data.put(field.getName(), field.getBoolean(this));
                } else if (field.getType().isAssignableFrom(Collection.class)) {
                    if (!((Collection<?>) field.get(this)).isEmpty())
                        data.put(field.getName(), field.get(this));
                } else if (field.getType().isAssignableFrom(String.class)) {
                    if (((String) field.get(this)) != null)
                        data.put(field.getName(), field.get(this));
                } else if (field.getType().isAssignableFrom(Map.class)) {
                    if (!((Map<?, ?>) field.get(this)).isEmpty())
                        data.put(field.getName(), field.get(this));
                } else {
                    if (field.get(this) != null)
                        data.put(field.getName(), field.get(this));
                }

                field.setAccessible(accessible);
            } catch (Throwable ignored) {
            }
        }

        return data;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TitleComponents{");
        sb.append("fadeIn=").append(fadeIn);
        sb.append(", stay=").append(stay);
        sb.append(", fadeOut=").append(fadeOut);
        sb.append(", theTitle='").append(theTitle).append('\'');
        sb.append(", theSubTitle='").append(theSubTitle).append('\'');
        sb.append(", useTimes=").append(useTimes);
        sb.append(", useTitle=").append(useTitle);
        sb.append(", useSubTitle=").append(useSubTitle);
        sb.append(", useClear=").append(useClear);
        sb.append(", useReset=").append(useReset);
        sb.append(", rialize=").append(serialize());
        sb.append('}');
        return sb.toString();
    }
}
