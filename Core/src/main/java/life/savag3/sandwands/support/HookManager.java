package life.savag3.sandwands.support;

import life.savag3.sandwands.Core;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class HookManager {

    public void setup() {
        FactionsHook claim;

        if (Bukkit.getPluginManager().getPlugin("Factions") != null) {
            Plugin p = Bukkit.getPluginManager().getPlugin("Factions");
            switch (p.getDescription().getMain()) {
                case "com.massivecraft.factions.FactionsPlugin":
                    if (p.getDescription().getAuthors().contains("Driftay")) {
                        // Saber Factions
                        Bukkit.getLogger().info("Saber Factions Found! Attempting to Hook into it...");
                        try {
                            claim = (FactionsHook) Class.forName(Core.class.getPackage().getName() + ".support.impl.SaberSupport").newInstance();
                            Core.c.setClaiming(claim);
                            Bukkit.getLogger().info("Saver Factions Support Added!");
                        } catch (Exception e) {
                            Bukkit.getLogger().info("Error Loading Support for Saber Factions!");
                            Bukkit.getLogger().info("Please report this to the Supreme Developers...");
                            e.printStackTrace();
                        }
                    }
                case "com.massivecraft.factions.SavageFactions":
                    // SavageFactions
                    Bukkit.getLogger().info("Savage Factions Found! Attempting to Hook into it...");
                    try {
                        claim = (FactionsHook) Class.forName(Core.class.getPackage().getName() + ".support.impl.SavageSupport").newInstance();
                        Core.c.setClaiming(claim);
                        Bukkit.getLogger().info("Savage Factions Support Added!");
                    } catch (Exception e) {
                        Bukkit.getLogger().info("Error Loading Support for Savage Factions!");
                        Bukkit.getLogger().info("Please report this to the Supreme Developers...");
                        e.printStackTrace();
                    }
                    break;
                case "com.massivecraft.factions.Factions":
                    // MassiveCore Factions
                    Bukkit.getLogger().info("Massive Factions Found! Attempting to Hook into it...");
                    try {
                        claim = (FactionsHook) Class.forName(Core.class.getPackage().getName() + ".support.impl.MCoreSupport").newInstance();
                        Core.c.setClaiming(claim);
                        Bukkit.getLogger().info("MassiveFactions Support Added!");
                    } catch (Exception e) {
                        Bukkit.getLogger().info("Error Loading Support for Massive Factions!");
                        Bukkit.getLogger().info("Please report this to the Supreme Developers...");
                        e.printStackTrace();
                    }
                    break;
                case "com.massivecraft.factions.P":
                    // Supreme Factions
                    Bukkit.getLogger().info("Supreme Factions Found! Attempting to Hook into it...");
                    try {
                        claim = (FactionsHook) Class.forName(Core.class.getPackage().getName() + ".support.impl.SupremeFactions").newInstance();
                        Core.c.setClaiming(claim);
                        Bukkit.getLogger().info("Supreme Factions Support Added!");
                    } catch (Exception e) {
                        Bukkit.getLogger().info("Error Loading Support for Supreme Factions!");
                        Bukkit.getLogger().info("Please report this to the Supreme Developers...");
                        e.printStackTrace();
                    }
            }
        }
    }

}
