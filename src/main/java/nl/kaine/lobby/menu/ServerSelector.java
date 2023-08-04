package nl.kaine.lobby.menu;

import nl.kaine.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ServerSelector {

    Lobby plugin;
    public static ItemStack serverSelector;

    public ServerSelector(Lobby plugin) {
        this.plugin = plugin;
        createServerSelector();
    }

    /**
     * Executes the Menu builds for the server selector
     * @param player
     */
    public static void ServerSelectorMenu(Player player) {

        Inventory inv = Bukkit.createInventory(player, 27, ChatColor.RED.toString() + ChatColor.BOLD + "Server Selector");

        ItemStack skyblockSelector = new ItemStack(Material.GRASS_BLOCK, 1);
        ItemMeta skyblockMeta = skyblockSelector.getItemMeta();
        Objects.requireNonNull(skyblockMeta).setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Skyblock");
        List<String> skyblockLore = new ArrayList<>();
        skyblockLore.add(" ");
        skyblockLore.add(ChatColor.GRAY + "Stijg naar grote hoogten op onze Minecraft Skyblock-server, ");
        skyblockLore.add(ChatColor.GRAY +  "waar jij je eigen eilandrijk bouwt. ");
        skyblockLore.add(ChatColor.GRAY + "Verzamel, handel en overleef terwijl je ");
        skyblockLore.add(ChatColor.GRAY + "de uitdagingen van de zwevende eilanden aangaat. ");
        skyblockLore.add(" ");
        skyblockLore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Klik om te joinen");
        skyblockMeta.setLore(skyblockLore);
        skyblockSelector.setItemMeta(skyblockMeta);

        ItemStack survivalSelector = new ItemStack(Material.GOLDEN_AXE,1);
        ItemMeta survivalMeta = survivalSelector.getItemMeta();
        Objects.requireNonNull(survivalMeta).setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Survival");

        List<String> survivalLore = new ArrayList<>();
        survivalLore.add(" ");
        survivalLore.add(ChatColor.GRAY + "Kies je pad in onze Minecraft Survival server, ");
        survivalLore.add(ChatColor.GRAY + "waar avontuur en overleven samenkomen. ");
        survivalLore.add(ChatColor.GRAY + "Verken een gevaarlijke wereld, bouw, vecht ");
        survivalLore.add(ChatColor.GRAY + "en bewijs je overlevingsvaardigheden! ");
        survivalLore.add(" ");
        survivalLore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Klik om te joinen");
        survivalMeta.setLore(survivalLore);
        survivalSelector.setItemMeta(survivalMeta);

        ItemStack creativeSelector = new ItemStack(Material.BRICKS,1);
        ItemMeta creativeMeta = creativeSelector.getItemMeta();
        Objects.requireNonNull(creativeMeta).setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Creative");

        List<String> creativeLore = new ArrayList<>();
        creativeLore.add(" ");
        creativeLore.add(ChatColor.GRAY + "Laat je creativiteit de vrije loop op onze Minecraft Creative-server, ");
        creativeLore.add(ChatColor.GRAY + "waar bouwen geen grenzen kent. ");
        creativeLore.add(ChatColor.GRAY + "Ontwerp meesterwerken en deel je artistieke visie ");
        creativeLore.add(ChatColor.GRAY + "met anderen in een oneindige speelwereld. ");
        creativeLore.add(" ");
        creativeLore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Klik om te joinen");
        creativeMeta.setLore(creativeLore);
        creativeSelector.setItemMeta(creativeMeta);

        ItemStack closeSelector = new ItemStack(Material.BARRIER, 1);
        ItemMeta closeMeta = closeSelector.getItemMeta();
        Objects.requireNonNull(closeMeta).setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "Sluiten");
        List<String> closeLore = new ArrayList<>();
        closeLore.add(" ");
        closeLore.add(ChatColor.GRAY + "Sluit de Selector af.");
        closeLore.add(" ");
        closeMeta.setLore(closeLore);
        closeSelector.setItemMeta(closeMeta);

        inv.setItem(11, skyblockSelector);

        inv.setItem(13, survivalSelector);

        inv.setItem(15, creativeSelector);

        inv.setItem(26, closeSelector);

        player.openInventory(inv);
    }

    public static void createServerSelector() {
        ItemStack selector = new ItemStack(Material.COMPASS, 1);
        ItemMeta selectorMeta = selector.getItemMeta();
        Objects.requireNonNull(selectorMeta).setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Server Selector");
        selectorMeta.setLore(Arrays.asList(ChatColor.GRAY + "Click mij"));
        selector.setItemMeta(selectorMeta);
        serverSelector = selector;
    }
}
