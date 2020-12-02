package life.savag3.sandwands.listeners;

import life.savag3.sandwands.Core;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class SandWandListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null || e.getPlayer().getItemInHand() == null) return;
        ItemStack item = e.getPlayer().getItemInHand();
        if (item.getType() != Material.getMaterial(Core.c.getConfig().getString("SandWand.Type"))) return;
        if (e.getClickedBlock().getType() != Material.SAND && e.getClickedBlock().getType() != Material.GRAVEL) {
            e.getPlayer().sendMessage(Core.color(Core.c.getConfig().getString("Messages.Not-Sand")));
            return;
        }

        ItemMeta meta = item.getItemMeta();
        if (!ChatColor.stripColor(meta.getDisplayName()).equals(ChatColor.stripColor(Core.color(Core.c.getConfig().getString("SandWand.Display-Name"))))) return;

        if (Core.c.hook != null) {
            if (!Core.c.hook.canUseHere(e.getPlayer(), e.getClickedBlock().getLocation())) {
                e.getPlayer().sendMessage(Core.color(Core.c.getConfig().getString("Messages.No-Faction-Access")));
                return;
            }
        }

        Location start = e.getClickedBlock().getLocation();
        int count = 0;
        for (int y = 255; y >= 1; --y) {
            start.setY(y);
            final Block b = start.getBlock();
            if (b.getType().equals(Material.SAND) || b.getType().equals(Material.GRAVEL)) {
                b.setType(Material.AIR);
                count++;
            }
        }
        e.getPlayer().sendMessage(Core.color(Core.c.getConfig().getString("Messages.Stack-Removed").replace("{amount}", count + "")));
    }
}
