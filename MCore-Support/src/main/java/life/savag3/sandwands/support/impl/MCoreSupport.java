package life.savag3.sandwands.support.impl;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import life.savag3.sandwands.Core;
import life.savag3.sandwands.support.FactionsHook;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MCoreSupport extends FactionsHook {
    public boolean canUseHere(Player p, Location x) {
        Faction factionAt = BoardColl.get().getFactionAt(PS.valueOf(x));

        if (factionAt == FactionColl.get().getNone())
            if (Core.c.getConfig().getBoolean("Settings.Allow-In.Wilderness"))
                return true;

        MPlayer fme = MPlayer.get(p);
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
