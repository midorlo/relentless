package de.midorlo.relentless.model.combat;

import de.midorlo.relentless.model.combat.AttackResult;
import de.midorlo.relentless.model.player.Player;
import de.midorlo.relentless.model.behemoth.Behemoth;
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

    public void addAttackResult(AttackResult attackResult) {
//        log.info(attackResult.toString());
        attackResultsLog.add(attackResult);
    }
}
