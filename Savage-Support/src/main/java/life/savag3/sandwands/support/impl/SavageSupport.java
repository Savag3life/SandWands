package life.savag3.sandwands.support.impl;

import com.massivecraft.factions.*;
import life.savag3.sandwands.Core;
import life.savag3.sandwands.support.FactionsHook;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SavageSupport extends FactionsHook {
    public boolean canUseHere(Player p, Location x) {
        Faction factionAt = Board.getInstance().getFactionAt(new FLocation(x));

        if (factionAt == Factions.getInstance().getWilderness())
            if (Core.c.getConfig().getBoolean("Settings.Allow-In.Wilderness"))
                return true;

        FPlayer fme = FPlayers.getInstance().getByPlayer(p);
        if (fme.getFaction() == factionAt) return true;

        switch (fme.getFaction().getRelationTo(factionAt)) {
            case ALLY:
                return Core.c.getConfig().getBoolean("Settings.Allow-In.Ally");
            case TRUCE:
                return Core.c.getConfig().getBoolean("Settings.Allow-In.Truce");
            case NEUTRAL:
                return Core.c.getConfig().getBoolean("Settings.Allow-In.Neutral");
            case ENEMY:
                return Core.c.getConfig().getBoolean("Settings.Allow-In.Enemy");
            default: return false;
        }
    }
}
