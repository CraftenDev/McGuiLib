package de.craften.plugins.mcguilib;


import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * A listener for click events.
 */
public interface ClickListener {
    /**
     * Gets called when the element is clicked.
     *
     * @param event the original event
     */
    void clicked(InventoryClickEvent event);
}
