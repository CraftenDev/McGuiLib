package de.craften.plugins.mcguilib.text;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * A builder for single line text.
 */
class SimpleTextBuilder extends TextBuilder {
    private StringBuilder output = new StringBuilder();
    private String content;

    private ChatColor color = null;
    private boolean obfuscated = false;
    private boolean bold = false;
    private boolean strikethrough = false;
    private boolean underline = false;
    private boolean italic = false;

    /**
     * Starts a new text with the given content.
     *
     * @param content string to start the text with
     */
    SimpleTextBuilder(String content) {
        this.content = content;
    }

    /**
     * Starts a new empty text.
     */
    SimpleTextBuilder() {
        this(null);
    }

    @Override
    public TextBuilder append(String content) {
        flush();

        if (color != null || obfuscated || bold || strikethrough || underline || italic)
            output.append(ChatColor.RESET);

        this.content = content;

        color = null;
        obfuscated = false;
        bold = false;
        strikethrough = false;
        underline = false;
        italic = false;

        return this;
    }

    @Override
    public TextBuilder black() {
        this.color = ChatColor.BLACK;
        return this;
    }

    @Override
    public TextBuilder darkBlue() {
        this.color = ChatColor.DARK_BLUE;
        return this;
    }

    @Override
    public TextBuilder darkGreen() {
        this.color = ChatColor.DARK_GREEN;
        return this;
    }

    @Override
    public TextBuilder darkAqua() {
        this.color = ChatColor.DARK_AQUA;
        return this;
    }

    @Override
    public TextBuilder darkRed() {
        this.color = ChatColor.DARK_RED;
        return this;
    }

    @Override
    public TextBuilder darkPurple() {
        this.color = ChatColor.DARK_PURPLE;
        return this;
    }

    @Override
    public TextBuilder gold() {
        this.color = ChatColor.GOLD;
        return this;
    }

    @Override
    public TextBuilder gray() {
        this.color = ChatColor.GRAY;
        return this;
    }

    @Override
    public TextBuilder darkGray() {
        this.color = ChatColor.DARK_GRAY;
        return this;
    }

    @Override
    public TextBuilder blue() {
        this.color = ChatColor.BLUE;
        return this;
    }

    @Override
    public TextBuilder green() {
        this.color = ChatColor.GREEN;
        return this;
    }

    @Override
    public TextBuilder aqua() {
        this.color = ChatColor.AQUA;
        return this;
    }

    @Override
    public TextBuilder red() {
        this.color = ChatColor.RED;
        return this;
    }

    @Override
    public TextBuilder lightPurple() {
        this.color = ChatColor.LIGHT_PURPLE;
        return this;
    }

    @Override
    public TextBuilder yellow() {
        this.color = ChatColor.YELLOW;
        return this;
    }

    @Override
    public TextBuilder white() {
        this.color = ChatColor.WHITE;
        return this;
    }

    @Override
    public TextBuilder color(ChatColor color) throws IllegalArgumentException {
        if (!color.isColor()) {
            throw new IllegalArgumentException(color + " is not a color");
        }
        this.color = color;
        return this;
    }

    @Override
    public TextBuilder obfuscated() {
        obfuscated = !obfuscated;
        return this;
    }

    @Override
    public TextBuilder bold() {
        bold = !bold;
        return this;
    }

    @Override
    public TextBuilder strike() {
        strikethrough = !strikethrough;
        return this;
    }

    @Override
    public TextBuilder underline() {
        underline = !underline;
        return this;
    }

    @Override
    public TextBuilder italic() {
        italic = !italic;
        return this;
    }

    @Override
    public MultilineTextBuilder newLine() {
        return new MultilineTextBuilder(this).newLine();
    }

    @Override
    public String getSingleLine() {
        flush();
        return output.toString();
    }

    @Override
    public List<String> getLines(int lineLength) {
        return new MultilineTextBuilder(this).getLines(lineLength);
    }

    @Override
    public void sendTo(CommandSender destination) {
        destination.sendMessage(getSingleLine());
    }

    @Override
    public void broadcast() {
        Bukkit.broadcastMessage(getSingleLine());
    }

    @Override
    public void broadcast(Iterable<CommandSender> destinations) {
        for (CommandSender destination : destinations) {
            sendTo(destination);
        }
    }

    private void flush() {
        if (content == null || content.length() == 0)
            return;

        if (color != null)
            output.append(color);

        if (obfuscated)
            output.append(ChatColor.MAGIC);
        if (bold)
            output.append(ChatColor.BOLD);
        if (strikethrough)
            output.append(ChatColor.STRIKETHROUGH);
        if (underline)
            output.append(ChatColor.UNDERLINE);
        if (italic)
            output.append(ChatColor.ITALIC);

        output.append(content);
    }
}