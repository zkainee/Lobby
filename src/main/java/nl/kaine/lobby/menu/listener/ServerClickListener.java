package nl.kaine.lobby.menu.listener;

import nl.kaine.lobby.Lobby;
import nl.kaine.lobby.menu.ServerSelector;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;

public class ServerClickListener implements Listener {

    Lobby plugin;

    public ServerClickListener(Lobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().setItem(4, ServerSelector.serverSelector);
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (event.getHand() != EquipmentSlot.HAND || event.getItem() == null) {
            return;
        }

        Player player = event.getPlayer();
        if (event.getItem().equals(ServerSelector.serverSelector)) {
            ServerSelector.ServerSelectorMenu(player);
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) {
            return;
        }
        String inventoryMenuName = event.getView().getTitle();

        if (inventoryMenuName.equals("Server Selector")) {
            event.setCancelled(true);

            switch (event.getRawSlot()) {
                case 11:
                    break;
                case 13:
                    break;
                case 15:
                    break;
                case 26:
                    break;
            }
        }
    }
}
