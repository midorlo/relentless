package de.midorlo.relentless.domain.combat;

import de.midorlo.relentless.domain.MockedRepository;
import de.midorlo.relentless.domain.behemoth.Behemoth;
import de.midorlo.relentless.domain.items.Weapon;
import de.midorlo.relentless.domain.player.Loadout;
import de.midorlo.relentless.domain.player.Player;
import lombok.extern.java.Log;
import org.testng.annotations.Test;

import java.util.List;

@Log
public class HuntTest {

    /**
     * Tests for a hunt to be creatable and executable, at least thorough an attack.
     */
    @Test
    public void testHunt() {
        Hunt hunt = MockedRepository.mockHunt();
        Player player = hunt.getPlayers().get(0);
        Behemoth behemoth = hunt.getBehemoths().get(0);

        while (behemoth.getHealth() > 0) {
            Loadout loadout = player.getLoadout();
            Weapon weapon = loadout.getWeapon();
            List<List<AttackMove>> moveSets = weapon.getMoveSets();
            List<AttackMove> attackMoves = moveSets.get(0);
            AttackMove attackMove = attackMoves.get(0);
            AttackResult attackResult = player.attack(attackMove, behemoth);
            log.info(attackResult.toString());
            hunt.addAttackResult(attackResult);
        }
        log.info("Behemoth killed after " + hunt.getAttackResultsLog().size() + " attacks");
    }
}