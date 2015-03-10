package de.craften.plugins.mcguilib;


/**
 * A view with one page.
 */
public class SinglePageView extends View {
    private int index = 0;

    /**
     * Creates a new view with the given title and size.
     *
     * @param title The title of this view
     * @param size  The size of this view, must be a multiple of 9
     */
    public SinglePageView(String title, int size) {
        super(title, size);
    }

    @Override
    public void addElement(GuiElement element) {
        super.insertElement(index, element);
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
