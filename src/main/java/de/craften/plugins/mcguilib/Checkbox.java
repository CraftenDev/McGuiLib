package de.craften.plugins.mcguilib;


import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * A checkbox, represented with a clickable item with a title and an optional description that changes when clicked.
 */
public class Checkbox extends GuiElement {
    private boolean isChecked = false;
    protected Material enabledMaterial;
    protected byte enabledData;
    protected Material disabledMaterial;
    protected byte disabledData;
    private boolean autoDescriptionEnabled;

    /**
     * Creates a new checkbox with the given title and description.
     *
     * @param title       Title of the checkbox
     * @param description Description of the checkbox (displayed as lore, one string is one line)
     */
    public Checkbox(String title, String... description) {
        this(Material.WOOL, (byte) 15, Material.GLASS, (byte) 0, title, description);
    }

    /**
     * Creates a new button with the given material, data, title and description.
     *
     * @param enabledMaterial  Material of the {@link org.bukkit.inventory.ItemStack} to display when checked
     * @param enabledData      Data byte of the {@link org.bukkit.inventory.ItemStack} to display when checked
     * @param disabledMaterial Material of the {@link org.bukkit.inventory.ItemStack} to display when not checked
     * @param disabledData     Data byte of the {@link org.bukkit.inventory.ItemStack} to display when not checked
     * @param title            Title of the checkbox
     * @param description      Description of the checkbox (displayed as lore, one string is one line)
     */
    public Checkbox(Material enabledMaterial, byte enabledData, Material disabledMaterial, byte disabledData, String title, String... description) {
        this.enabledMaterial = enabledMaterial;
        this.enabledData = enabledData;
        this.disabledMaterial = disabledMaterial;
        this.disabledData = disabledData;
        setTitle(title);
        setDescription(description);
    }

    @Override
    public ItemStack createItem() {
        ItemStack is = new ItemStack(isChecked() ? enabledMaterial : disabledMaterial, 1,
                (short) 0, isChecked() ? enabledData : disabledData);
        ItemMeta m = is.getItemMeta();
        m.setDisplayName(getTitle());
        m.setLore(getDescription());
        is.setItemMeta(m);
        return is;
    }

    @Override
    void onClick(InventoryClickEvent event) {
        setChecked(!isChecked());
        super.onClick(event);
    }

    /**
     * Checks if this checkbox is checked.
     *
     * @return True if the button is checked, false if not
     */
    public boolean isChecked() {
        return isChecked;
    }

    /**
     * Sets the checked status of this checkbox and repaints it (if required).
     *
     * @param isChecked True to check this checkbox, false to uncheck it
     */
    public void setChecked(boolean isChecked) {
        if (this.isChecked != isChecked) {
            this.isChecked = isChecked;

            if (isAutoDescriptionEnabled()) {
                setDescription(isChecked() ? "Enabled" : "Disabled");
            } else { //setDescription() already repaints
                repaint();
            }
        } else if (isAutoDescriptionEnabled()) {
            setDescription(isChecked() ? "Enabled" : "Disabled");
        }
    }

    public void setAutoDescriptionEnabled(boolean autoDescriptionEnabled) {
        this.autoDescriptionEnabled = autoDescriptionEnabled;
    }

    public boolean isAutoDescriptionEnabled() {
        return autoDescriptionEnabled;
    }
}
