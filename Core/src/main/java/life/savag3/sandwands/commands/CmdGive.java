package life.savag3.sandwands.commands;

import life.savag3.sandwands.Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CmdGive implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("sandwand.give")) {
            commandSender.sendMessage(Core.color("&cYou do not have permission to do this!"));
            return true;
        }

        ItemStack item = new ItemStack(Material.getMaterial(Core.c.getConfig().getString("SandWand.Type")), 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Core.color(Core.c.getConfig().getString("SandWand.Display-Name")));
        meta.setLore(Core.color(Core.c.getConfig().getStringList("SandWand.Lore")));
        item.setItemMeta(meta);

        if (Core.c.getConfig().getBoolean("SandWand.Enchanted"))
            item = Core.c.enchant(item);

        if (strings.length == 0)
            if (commandSender instanceof Player) {
                ((Player) commandSender).getInventory().addItem(item);
                commandSender.sendMessage(Core.color(Core.c.getConfig().getString("Messages.Given-Wand")));
                return true;
            } else {
                commandSender.sendMessage("This command must have a player specified if sent from console!");
                return true;
            }
        else {
            if (strings[0] == null) {
                commandSender.sendMessage("&cError executing command! Argument was null!");
                return true;
            }
            Player p = Bukkit.getPlayer(strings[0]);
            if (p == null || !p.isOnline()) {
                commandSender.sendMessage("&cError executing command! Player " + strings[0] + " could not be found!");
                return true;
            }

            p.getInventory().addItem(item);
            p.sendMessage(Core.color(Core.c.getConfig().getString("Messages.Given-Wand")));

            return true;
        }
    }
}
