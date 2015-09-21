package de.craften.plugins.mcguilib.text;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * A convenient builder for simple and multiline texts with a fluent interface.
 */
public abstract class TextBuilder {
    /**
     * Appends a new string. It will be completely unformatted.
     *
     * @param content string to append
     * @return this instance
     */
    public abstract TextBuilder append(String content);

    /**
     * Colors the last appended string black.
     *
     * @return this instance
     */
    public abstract TextBuilder black();

    /**
     * Colors the last appended string dark blue.
     *
     * @return this instance
     */
    public abstract TextBuilder darkBlue();

    /**
     * Colors the last appended string dark green.
     *
     * @return this instance
     */
    public abstract TextBuilder darkGreen();

    /**
     * Colors the last appended string dark aqua.
     *
     * @return this instance
     */
    public abstract TextBuilder darkAqua();

    /**
     * Colors the last appended string dark red.
     *
     * @return this instance
     */
    public abstract TextBuilder darkRed();

    /**
     * Colors the last appended string dark purple.
     *
     * @return this instance
     */
    public abstract TextBuilder darkPurple();

    /**
     * Colors the last appended string gold.
     *
     * @return this instance
     */
    public abstract TextBuilder gold();

    /**
     * Colors the last appended string gray.
     *
     * @return this instance
     */
    public abstract TextBuilder gray();

    /**
     * Colors the last appended string dark gray.
     *
     * @return this instance
     */
    public abstract TextBuilder darkGray();

    /**
     * Colors the last appended string blue.
     *
     * @return this instance
     */
    public abstract TextBuilder blue();

    /**
     * Colors the last appended string green.
     *
     * @return this instance
     */
    public abstract TextBuilder green();

    /**
     * Colors the last appended string aqua.
     *
     * @return this instance
     */
    public abstract TextBuilder aqua();

    /**
     * Colors the last appended string red.
     *
     * @return this instance
     */
    public abstract TextBuilder red();

    /**
     * Colors the last appended string light purple.
     *
     * @return this instance
     */
    public abstract TextBuilder lightPurple();

    /**
     * Colors the last appended string yellow.
     *
     * @return this instance
     */
    public abstract TextBuilder yellow();

    /**
     * Colors the last appended string white.
     *
     * @return this instance
     */
    public abstract TextBuilder white();

    /**
     * Sets the  color of the last appended string to the given color.
     *
     * @param color color
     * @return this instance
     * @throws IllegalArgumentException if the given {@link ChatColor} is not a color
     */
    public abstract TextBuilder color(ChatColor color) throws IllegalArgumentException;

    /**
     * Makes the last appended string appear obfuscated (randomly changing letters).
     *
     * @return this instance
     */
    public abstract TextBuilder obfuscated();

    /**
     * Makes the last appended string bold.
     *
     * @return this instance
     */
    public abstract TextBuilder bold();

    /**
     * Strikes the last appended string.
     *
     * @return this instance
     */
    public abstract TextBuilder strike();

    /**
     * Underlines the last appended string.
     *
     * @return this instance
     */
    public abstract TextBuilder underline();

    /**
     * Makes the last appended string italic.
     *
     * @return this instance
     */
    public abstract TextBuilder italic();

    /**
     * Adds a line break at the current position.
     *
     * @return this instance
     */
    public abstract TextBuilder newLine();

    /**
     * Gets the formatted text as one single String. If the text has multiple lines, they are merged and separated by
     * one space.
     *
     * @return the entire text as one String
     */
    public abstract String getSingleLine();

    /**
     * Gets the lines of this text, automatically wrapping them after the given number of characters while keeping
     * formatting intact.
     *
     * @param lineLength maximum line length
     * @return the lines
     */
    public abstract List<String> getLines(int lineLength);

    /**
     * Gets the lines of this text, automatically wrapping lines after 25 characters while keeping
     * formatting intact.
     *
     * @return the lines
     */
    public final List<String> getLines() {
        return getLines(25);
    }

    /**
     * Sends the formatted text to the given destination. Multiple lines are sent as multiple messages.
     *
     * @param destination destination to send the text to
     */
    public abstract void sendTo(CommandSender destination);

    /**
     * Sends the formatted text to all players. Multiple lines are sent as multiple messages.
     */
    public abstract void broadcast();

    /**
     * Creates a new empty text builder.
     *
     * @return new empty text builder
     */
    public static TextBuilder create() {
        return new SimpleTextBuilder();
    }

    /**
     * Creates a new text builder with the given content.
     *
     * @param content content to start with
     * @return new text builder with the given content
     */
    public static TextBuilder create(String content) {
        return new SimpleTextBuilder(content);
    }
}
