package de.craften.plugins.mcguilib.text;

import org.bukkit.ChatColor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimpleTextBuilderTest {
    @Test
    public void testAppend() throws Exception {
        assertEquals("one,two", new SimpleTextBuilder("one").append(",two").getSingleLine());
    }

    @Test
    public void testBlack() throws Exception {
        assertEquals(ChatColor.BLACK + "test", new SimpleTextBuilder("test").black().getSingleLine());
    }

    @Test
    public void testDarkBlue() throws Exception {
        assertEquals(ChatColor.DARK_BLUE + "test", new SimpleTextBuilder("test").darkBlue().getSingleLine());
    }

    @Test
    public void testDarkGreen() throws Exception {
        assertEquals(ChatColor.DARK_GREEN + "test", new SimpleTextBuilder("test").darkGreen().getSingleLine());
    }

    @Test
    public void testDarkAqua() throws Exception {
        assertEquals(ChatColor.DARK_AQUA + "test", new SimpleTextBuilder("test").darkAqua().getSingleLine());
    }

    @Test
    public void testDarkRed() throws Exception {
        assertEquals(ChatColor.DARK_RED + "test", new SimpleTextBuilder("test").darkRed().getSingleLine());
    }

    @Test
    public void testDarkPurple() throws Exception {
        assertEquals(ChatColor.DARK_PURPLE + "test", new SimpleTextBuilder("test").darkPurple().getSingleLine());
    }

    @Test
    public void testGold() throws Exception {
        assertEquals(ChatColor.GOLD + "test", new SimpleTextBuilder("test").gold().getSingleLine());
    }

    @Test
    public void testGray() throws Exception {
        assertEquals(ChatColor.GRAY + "test", new SimpleTextBuilder("test").gray().getSingleLine());
    }

    @Test
    public void testDarkGray() throws Exception {
        assertEquals(ChatColor.DARK_GRAY + "test", new SimpleTextBuilder("test").darkGray().getSingleLine());
    }

    @Test
    public void testBlue() throws Exception {
        assertEquals(ChatColor.BLUE + "test", new SimpleTextBuilder("test").blue().getSingleLine());
    }

    @Test
    public void testGreen() throws Exception {
        assertEquals(ChatColor.GREEN + "test", new SimpleTextBuilder("test").green().getSingleLine());
    }

    @Test
    public void testAqua() throws Exception {
        assertEquals(ChatColor.AQUA + "test", new SimpleTextBuilder("test").aqua().getSingleLine());
    }

    @Test
    public void testRed() throws Exception {
        assertEquals(ChatColor.RED + "test", new SimpleTextBuilder("test").red().getSingleLine());
    }

    @Test
    public void testLightPurple() throws Exception {
        assertEquals(ChatColor.LIGHT_PURPLE + "test", new SimpleTextBuilder("test").lightPurple().getSingleLine());
    }

    @Test
    public void testYellow() throws Exception {
        assertEquals(ChatColor.YELLOW + "test", new SimpleTextBuilder("test").yellow().getSingleLine());
    }

    @Test
    public void testWhite() throws Exception {
        assertEquals(ChatColor.WHITE + "test", new SimpleTextBuilder("test").white().getSingleLine());
    }

    @Test
    public void testObfuscated() throws Exception {
        assertEquals(ChatColor.MAGIC + "test", new SimpleTextBuilder("test").obfuscated().getSingleLine());
    }

    @Test
    public void testBold() throws Exception {
        assertEquals(ChatColor.BOLD + "test", new SimpleTextBuilder("test").bold().getSingleLine());
    }

    @Test
    public void testStrikethrough() throws Exception {
        assertEquals(ChatColor.STRIKETHROUGH + "test", new SimpleTextBuilder("test").strike().getSingleLine());
    }

    @Test
    public void testUnderline() throws Exception {
        assertEquals(ChatColor.UNDERLINE + "test", new SimpleTextBuilder("test").underline().getSingleLine());
    }

    @Test
    public void testItalic() throws Exception {
        assertEquals(ChatColor.ITALIC + "test", new SimpleTextBuilder("test").italic().getSingleLine());
    }

    @Test
    public void testColorFirst() throws Exception {
        assertEquals("Color code before formatting code",
                ChatColor.RED + "" + ChatColor.BOLD + "It works!",
                new SimpleTextBuilder("It works!").bold().red().getSingleLine());
    }

    @Test
    public void testMultipleAppend() throws Exception {
        assertEquals("Appending text multiple times should work as expected",
                ChatColor.RED + "" + ChatColor.BOLD + "It works! " + ChatColor.RESET + ":-) " + ChatColor.BLUE + "Yeah!",
                new SimpleTextBuilder("It works! ").bold().red().append(":-) ").append("Yeah!").blue().getSingleLine());
    }

    @Test
    public void testReturnedInstance() throws Exception {
        TextBuilder msg = new SimpleTextBuilder("foo");
        assertTrue("Instance returned by .append() should be exactly the same instance", msg == msg.append("bar"));
    }

    @Test
    public void testMultilineTransformation() throws Exception {
        List<String> actual = new SimpleTextBuilder("Hello world").red().getLines(25);
        assertEquals("Text of text builder should be taken as first line", Arrays.asList(ChatColor.RED + "Hello world"), actual);

        actual = new SimpleTextBuilder("Hello world").red().newLine().append("test").getLines(25);
        assertEquals("Text of text builder should be taken as first line", Arrays.asList(ChatColor.RED + "Hello world", "test"), actual);
    }

    @Test
    public void testColor() throws Exception {
        boolean exceptionThrown = false;
        try {
            new SimpleTextBuilder("test").color(ChatColor.BOLD);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue("color() should throw an InvalidArgumentException if the argument is not a color", exceptionThrown);

        assertEquals(ChatColor.RED + "test", new SimpleTextBuilder("test").color(ChatColor.RED).getSingleLine());
    }
}