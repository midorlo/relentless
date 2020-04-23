package de.midorlo.relentless.domain.combat;

import de.midorlo.relentless.domain.behemoth.Behemoth;
import de.midorlo.relentless.domain.player.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Instanced Fight Between n players and m Behemoths.
 */
@Data
@ToString
@EqualsAndHashCode
@Log
public class Hunt {
    List<Player> players = new ArrayList<>();
    List<Behemoth> behemoths = new ArrayList<>();
    List<AttackResult> attackResultsLog = new ArrayList<>();

    public void saveResult(AttackResult attackResult) {
        attackResultsLog.add(attackResult);
    }
}
