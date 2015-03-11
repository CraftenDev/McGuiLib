package de.craften.plugins.mcguilib.text;

import org.bukkit.ChatColor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MultilineTextBuilderTest {
    @Test
    public void testLineTrimming() {
        List<String> actual = new MultilineTextBuilder("This is a very long text without any formattings.").getLines();
        List<String> expected = Arrays.asList("This is a very long text", "without any formattings.");
        assertEquals("Automatically wrapped lines should not end with space", expected, actual);
    }

    @Test
    public void testFormattingAfterLinebreak() {
        List<String> actual = new MultilineTextBuilder("This is a test. I like tests.").red().getLines();
        List<String> expected = Arrays.asList(ChatColor.RED + "This is a test. I like", ChatColor.RED + "tests.");
        assertEquals("Formatting should be applied to automatically created new lines", expected, actual);
    }

    @Test
    public void testFormattingAfterLinebreakWithoutSpace() {
        List<String> actual = new MultilineTextBuilder("Click to leave this island.").gray()
                .append("This cannot be undone!").red().getLines();
        List<String> expected = Arrays.asList(ChatColor.GRAY + "Click to leave this",
                ChatColor.GRAY + "island." + ChatColor.RESET + ChatColor.RED + "This cannot be",
                ChatColor.RED + "undone!");
        assertEquals("Formatting should be applied correctly spaces", expected, actual);
    }

    @Test
    public void testFormattingAfterManualLinebreak() {
        List<String> actual = new MultilineTextBuilder("This is").red().newLine().append("another test").getLines();
        List<String> expected = Arrays.asList(ChatColor.RED + "This is", "another test");
        assertEquals("Formatting should be reset after manual linebreak", expected, actual);
    }

    @Test
    public void testWrapping() {
        List<String> actual = new MultilineTextBuilder("This is a text that can be perfectly wrapped.").getLines(15);
        int maximum = 0;
        for (String s : actual) {
            maximum = Math.max(maximum, s.length());
        }

        assertTrue("Line length should be <= maximum line length if all words are shorter than the line length", maximum <= 15);
    }

    @Test
    public void testWrappingTooLongLines() {
        List<String> actual = new MultilineTextBuilder("ThisIsTooLong to fit a line").getLines(6);
        List<String> expected = Arrays.asList("ThisIsTooLong", "to fit", "a line");
        assertEquals("Too long words shouldn't be broken.", expected, actual);
    }
}