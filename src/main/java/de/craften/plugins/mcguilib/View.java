package de.craften.plugins.mcguilib;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * A view with one page.
 */
public abstract class View {
    private String title;
    private int size;
    private Map<Integer, GuiElement> buttons;
    private Map<GuiElement, Integer> buttonsBack;
    private ViewManager viewManager;
    private Player viewer;
    private Inventory inventory;

    /**
     * Creates a new view with the given title and the given size.
     *
     * @param title the title of this view
     * @param size  the size of this view, must be a multiple of 9
     */
    public View(String title, int size) {
        this.title = title;
        this.size = size;
        this.buttons = new HashMap<>();
        this.buttonsBack = new HashMap<>();
    }

    /**
     * Gets the {@link ViewManager} that is responsible for this view.
     *
     * @return the {@link ViewManager} that is responsible for this view
     */
    public final ViewManager getViewManager() {
        return viewManager;
    }

    /**
     * Sets the view manager for this view.
     *
     * @param viewManager the view manager that is responsible for this view
     */
    final void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Gets the title of this view.
     *
     * @return the title of this view
     */
    public String getTitle() {
        return title;
    }

    /**
     * Creates the inventory that represents this view.
     *
     * @return an inventory that represents this view
     */
    protected Inventory createInventory() {
        Inventory inventory = Bukkit.getServer().createInventory(viewer, size, getTitle());

        for (Map.Entry<Integer, GuiElement> b : buttons.entrySet()) {
            inventory.setItem(b.getKey(), b.getValue().createItem());
        }
        this.inventory = inventory;
        return inventory;
    }

    /**
     * Repaints this view.
     */
    public final void repaint() {
        if (viewer != null) {
            getViewManager().showView(viewer, this);
        }
    }

    /**
     * Closes this view.
     */
    public final void close() {
        if (viewer != null) {
            viewer.closeInventory();
        }
    }

    /**
     * Repaints this view.
     */
    protected final void repaint(GuiElement element) {
        inventory.setItem(buttonsBack.get(element), element.createItem());
    }

    /**
     * Gets the size of this view.
     *
     * @return the size of this view
     */
    public int getSize() {
        return size;
    }

    /**
     * Inserts an element at the given slot. If the slot already contains an element, it's overwritten.
     *
     * @param slot    the slot to insert the button
     * @param element the button to insert
     */
    protected void insertElement(int slot, GuiElement element) {
        buttons.put(slot, element);
        buttonsBack.put(element, slot);
        element.setParentView(this);
    }

    /**
     * Removes all buttons from this view.
     */
    protected void removeAllButtons() {
        buttons.clear();
        buttonsBack.clear();
    }

    /**
     * Checks if a button was clicked and calls the handler.
     *
     * @param event the original event
     */
    void onClick(InventoryClickEvent event) {
        if (event.getSlotType() == InventoryType.SlotType.CONTAINER) {
            GuiElement b = buttons.get(event.getSlot());
            if (b != null) {
                b.onClick(event);
            }
        }
    }

    /**
     * Adds an element to this view.
     *
     * @param element the element to add
     */
    public abstract void addElement(GuiElement element);

    /**
     * Removes an element from this view.
     *
     * @param element the element to remove
     */
    public void removeElement(GuiElement element) {
        Integer slot = buttonsBack.get(element);
        if (slot != null) {
            buttons.remove(slot);
            buttonsBack.remove(element);
            repaint();
        }
    }

    /**
     * Gets the player that this view is currently displayed to.
     *
     * @return player that this view is currently displayed to
     */
    protected Player getViewer() {
        return viewer;
    }

    void setViewer(Player viewer) {
        this.viewer = viewer;
    }
}
