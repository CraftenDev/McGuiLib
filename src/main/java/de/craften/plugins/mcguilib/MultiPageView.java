package de.craften.plugins.mcguilib;


import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * A multi-page view with automatic navigation.
 */
public class MultiPageView extends View {
    private int page;
    private List<GuiElement> elements;
    protected Button nextButton;
    protected Button backButton;

    /**
     * Creates a new multi-paste view with the given title and size.
     * One additional row for navigation is automatically added. Note that {@link #getSize()} will return the size you
     * specify here, though.
     *
     * @param title The title of this view
     * @param size  The size of this view, must be a multiple of 9
     */
    public MultiPageView(String title, int size) {
        this(title, size, 0);
    }

    /**
     * Creates a new multi-paste view with the given title and size that will initially show the given page.
     * One additional row for navigation is automatically added. Note that {@link #getSize()} will return the size you
     * specify here, though.
     *
     * @param title The title of this view
     * @param size  The size of this view, must be a multiple of 9
     * @param page  The page to show initially, zero-based
     */
    public MultiPageView(String title, int size, int page) {
        super(title, size + 9);
        elements = new ArrayList<>();
        this.page = page;
    }

    @Override
    public int getSize() {
        return super.getSize() - 9;
    }

    @Override
    public String getTitle() {
        return getPageCount() > 1 ? super.getTitle() + " (" + (page + 1) + "/" + getPageCount() + ")" : super.getTitle();
    }

    /**
     * Gets the current page number, counting from zero.
     *
     * @return The current page number, counting from zero
     */
    public int getPage() {
        return page;
    }

    @Override
    protected Inventory createInventory() {
        onCreateInventory();
        return super.createInventory();
    }

    /**
     * Gets called just before the inventory is created and may be used to insert additional buttons.
     */
    protected void onCreateInventory() {
        removeAllButtons();
        int i = 0;
        for (GuiElement b : getDisplayedButtons()) {
            insertElement(i, b);
            i++;
        }
        addNavigationButtons();
    }

    /**
     * Add the navigation buttons.
     */
    protected void addNavigationButtons() {
        if (page > 0) {
            backButton = new Button(Material.SIGN, "Previous page");
            backButton.setOnClick(new ClickListener() {
                @Override
                public void clicked(InventoryClickEvent event) {
                    page--;
                    repaint();
                }
            });
            insertElement(getSize(), backButton);
        } else {
            backButton = null;
        }

        if (page < getPageCount() - 1) {
            nextButton = new Button(Material.SIGN, "Next page");
            nextButton.setOnClick(new ClickListener() {
                @Override
                public void clicked(InventoryClickEvent event) {
                    page++;
                    repaint();
                }
            });
            insertElement(getSize() + 8, nextButton);
        } else {
            nextButton = null;
        }
    }

    /**
     * Gets the elements that should be displayed on the current page.
     *
     * @return The elements that should be displayed on the current page
     */
    protected List<GuiElement> getDisplayedButtons() {
        return elements.subList(
                page * getSize(),
                page * getSize() + getSize() > elements.size() ? elements.size() : page * getSize() + getSize());
    }

    /**
     * Gets the number of pages of this view.
     *
     * @return The number of pages of this view
     */
    public int getPageCount() {
        if (elements.size() <= getSize())
            return 1;
        return elements.size() / getSize() + (elements.size() % getSize() > 0 ? 1 : 0);
    }

    @Override
    public void addElement(GuiElement element) {
        elements.add(element);
    }

    @Override
    public void removeElement(GuiElement element) {
        elements.remove(element);
        repaint();
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        if (event.getSlot() == getSize() && backButton != null) {
            backButton.onClick(event);
        } else if (event.getSlot() == getSize() + 8 && nextButton != null) {
            nextButton.onClick(event);
        } else {
            super.onClick(event);
        }
    }
}
