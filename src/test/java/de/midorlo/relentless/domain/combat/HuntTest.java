//package de.midorlo.relentless.domain.combat;
//
//import de.midorlo.relentless.domain.*;
//import lombok.extern.java.Log;
//import org.testng.annotations.Test;
//
//@Log
//public class HuntTest {
//
//    /**
//     * Tests for a hunt to be creatable and executable, at least thorough an attack.
//     */
//    @Test
//    public void testHunt() {
//        Hunt hunt = MockedRepository.mockHunt();
//        Player player = hunt.getPlayers().get(0);
//        Behemoth behemoth = hunt.getBehemoths().get(0);
//        behemoth.setThread(5);
//        behemoth.getBehemothParts().get(0).setHitzone(Hitzone.body);
//        Skill skill = player.getLoadout().getWeapon().getSkillSets().get(0).getAttacks().get(0);
//        skill.setDamage(80);
//        skill.setType(new AttackType("Blunt"));
//
//
//
//        while (behemoth.getHealth() > 0) {
//            AttackResult attackResult = player.attack(skill, behemoth);
//            log.info(attackResult.toString());
//            hunt.saveResult(attackResult);
//        }
//        log.info("Behemoth killed after " + hunt.getAttackResultsLog().size() + " attacks");
//    }
//}