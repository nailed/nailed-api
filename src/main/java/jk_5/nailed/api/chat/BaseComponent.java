/*
 * Nailed, a Minecraft PvP server framework
 * Copyright (C) jk-5 <http://github.com/jk-5/>
 * Copyright (C) Nailed team and contributors <http://github.com/nailed/>
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the MIT License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the MIT License along with
 * this program. If not, see <http://opensource.org/licenses/MIT/>.
 */

package jk_5.nailed.api.chat;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import jk_5.nailed.api.util.Checks;

/**
 * No description given
 *
 * @author jk-5
 */
public abstract class BaseComponent {

    BaseComponent parent;

    /**
     * The color of this component and any child components (unless overridden)
     */
    @Nullable
    private ChatColor color;
    /**
     * Whether this component and any child components (unless overridden) is
     * bold
     */
    @Nullable
    private Boolean bold;
    /**
     * Whether this component and any child components (unless overridden) is
     * italic
     */
    @Nullable
    private Boolean italic;
    /**
     * Whether this component and any child components (unless overridden) is
     * underlined
     */
    @Nullable
    private Boolean underlined;
    /**
     * Whether this component and any child components (unless overridden) is
     * strikethrough
     */
    @Nullable
    private Boolean strikethrough;
    /**
     * Whether this component and any child components (unless overridden) is
     * obfuscated
     */
    @Nullable
    private Boolean obfuscated;

    /**
     * Appended components that inherit this component's formatting and events
     */
    @Nullable
    private List<BaseComponent> extra;

    /**
     * The action to preform when this component (and child components) are
     * clicked
     */
    @Nullable
    private ClickEvent clickEvent;

    /**
     * The action to preform when this component (and child components) are
     * hovered over
     */
    @Nullable
    private HoverEvent hoverEvent;

    protected BaseComponent() {
    }

    BaseComponent(@Nonnull BaseComponent old) {
        Checks.notNull(old, "old");

        setColor(old.getColorRaw());
        setBold(old.isBoldRaw());
        setItalic(old.isItalicRaw());
        setUnderlined(old.isUnderlinedRaw());
        setStrikethrough(old.isStrikethroughRaw());
        setObfuscated(old.isObfuscatedRaw());
        setClickEvent(old.getClickEvent());
        setHoverEvent(old.getHoverEvent());
    }

    /**
     * Converts the components to a string that uses the old formatting codes
     * ({@link jk_5.nailed.api.chat.ChatColor#COLOR_CHAR}
     *
     * @param components the components to convert
     * @return the string in the old format
     */
    @Nonnull
    public static String toLegacyText(@Nonnull BaseComponent... components) {
        Checks.noNullElements(components, "components");
        StringBuilder builder = new StringBuilder();
        for(BaseComponent msg : components){
            builder.append(msg.toLegacyText());
        }
        return builder.toString();
    }

    /**
     * Converts the components into a string without any formatting
     *
     * @param components the components to convert
     * @return the string as plain text
     */
    @Nonnull
    public static String toPlainText(@Nonnull BaseComponent... components) {
        Checks.noNullElements(components, "components");
        StringBuilder builder = new StringBuilder();
        for(BaseComponent msg : components){
            builder.append(msg.toPlainText());
        }
        return builder.toString();
    }

    /**
     * Returns the color of this component. This uses the parent's color if this
     * component doesn't have one. {@link jk_5.nailed.api.chat.ChatColor#WHITE}
     * is returned if no color is found.
     *
     * @return the color of this component
     */
    @Nonnull
    public ChatColor getColor() {
        if(color == null){
            if(parent == null){
                return ChatColor.WHITE;
            }
            return parent.getColor();
        }
        return color;
    }

    /**
     * Returns the color of this component without checking the parents color.
     * May return null
     *
     * @return the color of this component
     */
    @Nullable
    public ChatColor getColorRaw() {
        return color;
    }

    /**
     * Returns whether this component is bold. This uses the parent's setting if
     * this component hasn't been set. false is returned if none of the parent
     * chain has been set.
     *
     * @return whether the component is bold
     */
    public boolean isBold() {
        if(bold == null){
            return parent != null && parent.isBold();
        }
        return bold;
    }

    /**
     * Returns whether this component is bold without checking the parents
     * setting. May return null
     *
     * @return whether the component is bold
     */
    @Nullable
    public Boolean isBoldRaw() {
        return bold;
    }

    /**
     * Returns whether this component is italic. This uses the parent's setting
     * if this component hasn't been set. false is returned if none of the
     * parent chain has been set.
     *
     * @return whether the component is italic
     */
    public boolean isItalic() {
        if(italic == null){
            return parent != null && parent.isItalic();
        }
        return italic;
    }

    /**
     * Returns whether this component is italic without checking the parents
     * setting. May return null
     *
     * @return whether the component is italic
     */
    @Nullable
    public Boolean isItalicRaw() {
        return italic;
    }

    /**
     * Returns whether this component is underlined. This uses the parent's
     * setting if this component hasn't been set. false is returned if none of
     * the parent chain has been set.
     *
     * @return whether the component is underlined
     */
    public boolean isUnderlined() {
        if(underlined == null){
            return parent != null && parent.isUnderlined();
        }
        return underlined;
    }

    /**
     * Returns whether this component is underlined without checking the parents
     * setting. May return null
     *
     * @return whether the component is underlined
     */
    @Nullable
    public Boolean isUnderlinedRaw() {
        return underlined;
    }

    /**
     * Returns whether this component is strikethrough. This uses the parent's
     * setting if this component hasn't been set. false is returned if none of
     * the parent chain has been set.
     *
     * @return whether the component is strikethrough
     */
    public boolean isStrikethrough() {
        if(strikethrough == null){
            return parent != null && parent.isStrikethrough();
        }
        return strikethrough;
    }

    /**
     * Returns whether this component is strikethrough without checking the
     * parents setting. May return null
     *
     * @return whether the component is strikethrough
     */
    @Nullable
    public Boolean isStrikethroughRaw() {
        return strikethrough;
    }

    /**
     * Returns whether this component is obfuscated. This uses the parent's
     * setting if this component hasn't been set. false is returned if none of
     * the parent chain has been set.
     *
     * @return whether the component is obfuscated
     */
    public boolean isObfuscated() {
        if(obfuscated == null){
            return parent != null && parent.isObfuscated();
        }
        return obfuscated;
    }

    /**
     * Returns whether this component is obfuscated without checking the parents
     * setting. May return null
     *
     * @return whether the component is obfuscated
     */
    @Nullable
    public Boolean isObfuscatedRaw() {
        return obfuscated;
    }

    public void setExtra(@Nonnull List<BaseComponent> components) {
        Checks.noNullElements(components, "components");
        for(BaseComponent component : components){
            component.parent = this;
        }
        extra = components;
    }

    /**
     * Appends a text element to the component. The text will inherit this
     * component's formatting
     *
     * @param text the text to append
     */
    public void addExtra(@Nonnull String text) {
        addExtra(new TextComponent(text));
    }

    /**
     * Appends a component to the component. The text will inherit this
     * component's formatting
     *
     * @param component the component to append
     */
    public void addExtra(@Nonnull BaseComponent component) {
        if(extra == null){
            extra = new ArrayList<BaseComponent>();
        }
        component.parent = this;
        extra.add(component);
    }

    /**
     * Returns whether the component has any formatting or events applied to it
     *
     * @return Whether any formatting or events are applied
     */
    public boolean hasFormatting() {
        return color != null || bold != null
                || italic != null || underlined != null
                || strikethrough != null || obfuscated != null
                || hoverEvent != null || clickEvent != null;
    }

    /**
     * Converts the component into a string without any formatting
     *
     * @return the string as plain text
     */
    @Nonnull
    public String toPlainText() {
        StringBuilder builder = new StringBuilder();
        toPlainText(builder);
        return builder.toString();
    }

    void toPlainText(@Nonnull StringBuilder builder) {
        if(extra != null){
            for(BaseComponent e : extra){
                e.toPlainText(builder);
            }
        }
    }

    /**
     * Converts the component to a string that uses the old formatting codes
     * ({@link jk_5.nailed.api.chat.ChatColor#COLOR_CHAR}
     *
     * @return the string in the old format
     */
    @Nonnull
    public String toLegacyText() {
        StringBuilder builder = new StringBuilder();
        toLegacyText(builder);
        return builder.toString();
    }

    void toLegacyText(@Nonnull StringBuilder builder) {
        if(extra != null){
            for(BaseComponent e : extra){
                e.toLegacyText(builder);
            }
        }
    }

    @Nullable
    public List<BaseComponent> getExtra() {
        return extra;
    }

    @Nullable
    public ClickEvent getClickEvent() {
        return clickEvent;
    }

    @Nullable
    public HoverEvent getHoverEvent() {
        return hoverEvent;
    }

    public void setColor(@Nullable ChatColor color) {
        this.color = color;
    }

    public void setBold(@Nullable Boolean bold) {
        this.bold = bold;
    }

    public void setItalic(@Nullable Boolean italic) {
        this.italic = italic;
    }

    public void setUnderlined(@Nullable Boolean underlined) {
        this.underlined = underlined;
    }

    public void setStrikethrough(@Nullable Boolean strikethrough) {
        this.strikethrough = strikethrough;
    }

    public void setObfuscated(@Nullable Boolean obfuscated) {
        this.obfuscated = obfuscated;
    }

    public void setClickEvent(@Nullable ClickEvent clickEvent) {
        this.clickEvent = clickEvent;
    }

    public void setHoverEvent(@Nullable HoverEvent hoverEvent) {
        this.hoverEvent = hoverEvent;
    }
}
