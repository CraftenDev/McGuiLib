package de.craften.plugins.mcguilib.text;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A text builder that supports automatic line wrapping.
 */
class MultilineTextBuilder extends TextBuilder {
    private List<String> lines;
    private TextBuilder msg;

    /**
     * Creates a new multiline text builder that contains the given text.
     *
     * @param str text to start with
     */
    MultilineTextBuilder(String str) {
        msg = new SimpleTextBuilder(str);
        lines = new ArrayList<>();
    }

    /**
     * Creates a new multiline text builder that uses the given text builder for the first line.
     *
     * @param textBuilder text builder to use for the first line
     */
    MultilineTextBuilder(TextBuilder textBuilder) {
        msg = textBuilder;
        lines = new ArrayList<>();
    }

    @Override
    public MultilineTextBuilder append(String str) {
        msg.append(str);
        return this;
    }

    @Override
    public MultilineTextBuilder black() {
        msg.black();
        return this;
    }

    @Override
    public MultilineTextBuilder darkBlue() {
        msg.darkBlue();
        return this;
    }

    @Override
    public MultilineTextBuilder darkGreen() {
        msg.darkGreen();
        return this;
    }

    @Override
    public MultilineTextBuilder darkAqua() {
        msg.darkAqua();
        return this;
    }

    @Override
    public MultilineTextBuilder darkRed() {
        msg.darkRed();
        return this;
    }

    @Override
    public MultilineTextBuilder darkPurple() {
        msg.darkPurple();
        return this;
    }

    @Override
    public MultilineTextBuilder gold() {
        msg.gold();
        return this;
    }

    @Override
    public MultilineTextBuilder gray() {
        msg.gray();
        return this;
    }

    @Override
    public MultilineTextBuilder darkGray() {
        msg.darkGray();
        return this;
    }

    @Override
    public MultilineTextBuilder blue() {
        msg.blue();
        return this;
    }

    @Override
    public MultilineTextBuilder green() {
        msg.green();
        return this;
    }

    @Override
    public MultilineTextBuilder aqua() {
        msg.aqua();
        return this;
    }

    @Override
    public MultilineTextBuilder red() {
        msg.red();
        return this;
    }

    @Override
    public MultilineTextBuilder lightPurple() {
        msg.lightPurple();
        return this;
    }

    @Override
    public MultilineTextBuilder yellow() {
        msg.yellow();
        return this;
    }

    @Override
    public MultilineTextBuilder white() {
        msg.white();
        return this;
    }

    @Override
    public MultilineTextBuilder obfuscated() {
        msg.obfuscated();
        return this;
    }

    @Override
    public MultilineTextBuilder bold() {
        msg.bold();
        return this;
    }

    @Override
    public MultilineTextBuilder strike() {
        msg.strike();
        return this;
    }

    @Override
    public MultilineTextBuilder underline() {
        msg.underline();
        return this;
    }

    @Override
    public MultilineTextBuilder italic() {
        msg.italic();
        return this;
    }

    @Override
    public MultilineTextBuilder newLine() {
        lines.add(msg.getSingleLine()); //lines contains all enforced lines (manual linebreaks)
        msg = new SimpleTextBuilder();
        return this;
    }

    @Override
    public String getSingleLine() {
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line).append(" ");
        }
        sb.append(msg.getSingleLine());
        return sb.toString();
    }

    @Override
    public void sendTo(CommandSender destination) {
        for (String line : getLines(200)) {
            destination.sendMessage(line);
        }
    }

    @Override
    public List<String> getLines(int lineLength) {
        List<String> result = new ArrayList<>();
        lines.add(msg.getSingleLine());
        for (String line : lines) { //lines contains all enforced lines (manual linebreaks)
            result.addAll(autoWrap(line, lineLength));
        }
        return result;
    }

    private static List<String> autoWrap(final String str, final int lineLength) {
        FormatMemory memory = new FormatMemory(str);

        List<String> result = new ArrayList<>(str.length() / lineLength);
        String[] words = str.split(" ");
        String[] strippedWords = ChatColor.stripColor(str).split(" ");
        StringBuilder sb = new StringBuilder();
        int currentLine = 0;
        int index = 0;

        for (int i = 0; i < words.length; i++) {
            if (strippedWords[i].length() <= lineLength - currentLine || currentLine == 0) {
                sb.append(words[i]).append(" ");
                currentLine += strippedWords[i].length() + 1;
            } else {
                sb.setLength(sb.length() - 1); //remove the last space
                if (result.isEmpty()) {
                    result.add(sb.toString());
                } else {
                    result.add(memory.getFormattingsAt(index) + sb.toString());
                }
                index += sb.length();
                sb.setLength(0);
                sb.append(words[i]).append(" ");
                currentLine = strippedWords[i].length() + 1;
            }
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1); //remove the last space
            result.add(memory.getFormattingsAt(index) + sb.toString());
        }

        return result;
    }

    private static class FormatMemory {
        private List<FormatStart> formattings = new ArrayList<>();
        private static final Pattern FORMAT_CODES = Pattern.compile("§[0-9a-fklmnor]");

        public FormatMemory(String str) {
            formattings.add(new FormatStart(0, ""));
            Matcher matcher = FORMAT_CODES.matcher(str);
            String chars = "";
            while (matcher.find()) {
                if (matcher.group(0).equals("§r")) {
                    chars = "";
                } else {
                    chars += matcher.group(0);
                }
                formattings.add(new FormatStart(matcher.end(), chars));
            }
        }

        public String getFormattingsAt(int index) {
            int i = 0;
            while (i < formattings.size() - 1 && formattings.get(i + 1).startIndex <= index) {
                i++;
            }
            return formattings.get(i).formattings;
        }

        @Override
        public String toString() {
            return formattings.toString();
        }

        private static class FormatStart {
            private final int startIndex;
            private final String formattings;

            private FormatStart(int startIndex, String formattings) {
                this.startIndex = startIndex;
                this.formattings = formattings;
            }

            @Override
            public String toString() {
                return startIndex + ": " + formattings;
            }
        }
    }
}
