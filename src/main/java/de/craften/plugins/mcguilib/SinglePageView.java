package de.craften.plugins.mcguilib;


/**
 * A view with one page.
 */
public class SinglePageView extends View {
    private int index = 0;

    /**
     * Creates a new view with the given title and size.
     *
     * @param title the title of this view
     * @param size  the size of this view, must be a multiple of 9
     */
    public SinglePageView(String title, int size) {
        super(title, size);
    }

    /**
     * Adds the given element to this view and increments the position for the next element.
     * If the given element is null, a blank slot is added.
     *
     * @param element the element to add, null to add a blank slot
     */
    @Override
    public void addElement(GuiElement element) {
        if (element != null) {
            super.insertElement(index, element);
        }
        index++;
    }

    @Override
    public void insertElement(int slot, GuiElement element) {
        super.insertElement(slot, element);
    }

    @Override
    public void removeAllButtons() {
        super.removeAllButtons();
    }
}
