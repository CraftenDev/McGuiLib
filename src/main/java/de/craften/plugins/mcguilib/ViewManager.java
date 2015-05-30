package de.craften.plugins.mcguilib;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * A manager for views that dispatches click events and displays views to players.
 */
public class ViewManager implements Listener {
    private Plugin plugin;
    private Map<String, View> shownViews = new HashMap<>();

    /**
     * Creates a new {@link ViewManager} for the given plugin.
     *
     * @param plugin the plugin that uses this manager
     */
    public ViewManager(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Shows the given view to the given player.
     *
     * @param player player to show the view to
     * @param view   view to show
     */
    public void showView(Player player, View view) {
        view.setViewer(player);
        view.setViewManager(this);

        View current = shownViews.get(player.getName());
        Inventory inventory = player.getOpenInventory().getTopInventory();
        if (current == view && current.getTitle().equals(inventory.getTitle())) {
            inventory.setContents(view.createInventory().getContents());
        } else {
            player.openInventory(view.createInventory());
            registerView(player, view);
        }
    }

    /**
     * Closes all views.
     */
    public void closeAll() {
        for (View view : shownViews.values()) {
            view.close();
        }
    }

    private void registerView(Player player, View view) {
        shownViews.put(player.getName(), view);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        View view = shownViews.get(event.getWhoClicked().getName());
        if (view != null) {
            try {
                view.onClick(event);
            } catch (Exception e) {
                plugin.getLogger().log(Level.SEVERE, "[McGuiLib] An error occurred while processing the click event", e);
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        shownViews.remove(event.getPlayer().getName());
    }
}
