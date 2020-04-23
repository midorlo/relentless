package de.midorlo.relentless.domain.combat;

import de.midorlo.relentless.domain.MockedRepository;
import de.midorlo.relentless.domain.behemoth.Behemoth;
import de.midorlo.relentless.domain.behemoth.Hitzone;
import de.midorlo.relentless.domain.player.Player;
import lombok.extern.java.Log;
import org.testng.annotations.Test;

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
        behemoth.setThread(5);
        behemoth.getBehemothParts().get(0).setHitzone(Hitzone.body);
        WeaponAttack weaponAttack = player.getLoadout().getWeapon().getMoveSets().get(0).get(0);
        weaponAttack.setDamage(80);
        weaponAttack.setType(AttackType.Blunt);



        while (behemoth.getHealth() > 0) {
            AttackResult attackResult = player.attack(weaponAttack, behemoth);
            log.info(attackResult.toString());
            hunt.saveResult(attackResult);
        }
        log.info("Behemoth killed after " + hunt.getAttackResultsLog().size() + " attacks");
    }
}