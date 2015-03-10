package de.craften.plugins.mcguilib;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A GUI element that can be added to a view.
 */
public abstract class GuiElement {
    private String title;
    private List<String> description;
    private View parentView;
    private ClickListener onClick = null;
    private boolean allowRepaint = true;

    /**
     * Gets the title of this element.
     *
     * @return The title of this element
     */
    public final String getTitle() {
        return title;
    }

    /**
     * Sets the title of this element.
     *
     * @param title The new title of this element
     */
    public final void setTitle(String title) {
        this.title = title;
        repaint();
    }

    /**
     * Gets the description of this element.
     *
     * @return The description of this element
     */
    public List<String> getDescription() {
        if (description != null)
            return Collections.unmodifiableList(description);
        else
            return Collections.emptyList();
    }

    /**
     * Sets the description of this element.
     *
     * @param lines The description of this element
     */
    public final void setDescription(String... lines) {
        this.description = Arrays.asList(lines);
        repaint();
    }

    /**
     * Sets the description of this element.
     *
     * @param lines The description of this element
     */
    public final void setDescription(List<String> lines) {
        this.description = lines;
        repaint();
    }

    /**
     * Repaints this gui element.
     */
    protected final void repaint() {
        if (parentView != null && allowRepaint)
            parentView.repaint(this);
    }

    /**
     * Enables or disables repainting of this gui element.
     *
     * @param allowRepaint whether repainting this element should be possible
     */
    public void setAllowRepaint(boolean allowRepaint) {
        this.allowRepaint = allowRepaint;
    }

    /**
     * Removes this button from the view.
     */
    public void remove() {
        getParentView().removeElement(this);
    }

    /**
     * Calls the click handler, if any.
     *
     * @param event The original event
     */
    void onClick(InventoryClickEvent event) {
        if (onClick != null)
            onClick.clicked(event);
    }

    /**
     * Sets the listener that will be called when this element is clicked.
     *
     * @param handler The listener that will be called when this element is clicked
     */
    public final void setOnClick(ClickListener handler) {
        this.onClick = handler;
    }

    /**
     * Gets the view that contains this element.
     *
     * @return The view that contains this element
     */
    public final View getParentView() {
        return parentView;
    }

    /**
     * Sets the view that contains this element.
     *
     * @param parentView The view that contains this element
     */
    final void setParentView(View parentView) {
        this.parentView = parentView;
    }

    /**
     * Creates the {@link org.bukkit.inventory.ItemStack} that represents this gui element.
     *
     * @return The {@link org.bukkit.inventory.ItemStack} that represents this gui element
     */
    public abstract ItemStack createItem();
}
