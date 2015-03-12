package de.craften.plugins.mcguilib;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * A button, represented by a clickable item with a title and an optional description.
 */
public class Button extends GuiElement {
    private Material material;
    private byte data;

    /**
     * Creates a new button with the given material, data, title and description.
     *
     * @param material    material of the {@link org.bukkit.inventory.ItemStack} to display
     * @param data        data byte of the {@link org.bukkit.inventory.ItemStack} to display
     * @param title       title of the button
     * @param description description of the button (displayed as lore, one string is one line)
     */
    public Button(Material material, byte data, String title, String... description) {
        setIcon(material, data);
        setTitle(title);
        setDescription(description);
    }

    /**
     * Sets the icon of this button.
     *
     * @param material material of the {@link org.bukkit.inventory.ItemStack} to display
     * @param data     data byte of the {@link org.bukkit.inventory.ItemStack} to display
     */
    public void setIcon(Material material, byte data) {
        this.material = material;
        this.data = data;
        repaint();
    }

    /**
     * Sets the icon of this button.
     *
     * @param material material of the {@link org.bukkit.inventory.ItemStack} to display
     */
    public void setIcon(Material material) {
        setIcon(material, (byte) 0);
    }

    /**
     * Creates a new button with the given material, title and description.
     *
     * @param material    material of the {@link org.bukkit.inventory.ItemStack} to display
     * @param title       title of the button
     * @param description description of the button (displayed as lore, one string is one line)
     */
    public Button(Material material, String title, String... description) {
        this(material, (byte) 0, title, description);
    }

    @Override
    public ItemStack createItem() {
        ItemStack is = new ItemStack(material, getNumber(), (short) 0, data);
        ItemMeta m = is.getItemMeta();
        m.setDisplayName(getTitle());
        m.setLore(getDescription());
        is.setItemMeta(m);
        return is;
    }
}
