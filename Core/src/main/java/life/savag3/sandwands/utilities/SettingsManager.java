package life.savag3.sandwands.utilities;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class SettingsManager {

    public SettingsManager() { }

    private static SettingsManager instance = new SettingsManager();

    public static SettingsManager getInstance() {
        return instance;
    }

    private FileConfiguration config;
    private File cFile;

    public void setup(JavaPlugin p) {

        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }

        cFile = new File(p.getDataFolder(), "config.yml");

        if (!cFile.exists()) {
            Bukkit.getLogger().info("Could not find /SandWands/config.yml, Creating it!");
            try {
                File en = new File(p.getDataFolder(), "config.yml");
                InputStream E = getClass().getResourceAsStream("/config.yml");
                copyFile(E, en);
            }catch (Exception e) {
                e.printStackTrace();
                Bukkit.getLogger().info("Could not create config.yml, This will most likely disable plugin!");
            }
        }

        config = YamlConfiguration.loadConfiguration(cFile);
        Bukkit.getLogger().info("Successfully Loaded config.yml!");
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(cFile);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cFile);
    }

    public static void copyFile(InputStream in, File out) throws Exception {
        try (InputStream fis = in; FileOutputStream fos = new FileOutputStream(out)) {
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        }
    }
}
