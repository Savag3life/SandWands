package life.savag3.sandwands;

import life.savag3.sandwands.commands.CmdGive;
import life.savag3.sandwands.listeners.SandWandListener;
import life.savag3.sandwands.support.FactionsHook;
import life.savag3.sandwands.support.HookManager;
import life.savag3.sandwands.utilities.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.stream.Collectors;

public class Core extends JavaPlugin {

    public static Core c;

    private FileConfiguration config;
    private SettingsManager configUtl;

    public FactionsHook hook;

    @Override
    public void onEnable() {
        c = this;
        configUtl = new SettingsManager();
        configUtl.setup(this);

        this.config = configUtl.getConfig();

        new HookManager().setup();

        Bukkit.getPluginManager().registerEvents(new SandWandListener(), this);
        getCommand("sandwand").setExecutor(new CmdGive());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public FileConfiguration getConfig() {
        return this.config;
    }

    public static String color(String x) {
        return ChatColor.translateAlternateColorCodes('&', x);
    }

    public static List<String> color(List<String> x) {
        return x.stream().map(Core::color).collect(Collectors.toList());
    }

    public ItemStack enchant(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        item.setItemMeta(itemMeta);
        return item;
    }

    public void setClaiming(FactionsHook hook) {
        this.hook = hook;
    }
}
