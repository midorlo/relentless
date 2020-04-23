package de.midorlo.relentless.domain.gear;


import de.midorlo.relentless.domain.MockedRepository;
import de.midorlo.relentless.domain.combat.Attack;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class WeaponTest {

    @Test
    public void accountForTest() {
        Weapon weapon = MockedRepository.mockWeapon();
        Attack attack = Attack.builder()
                .player(MockedRepository.mockPlayer())
                .behemoth(MockedRepository.mockBehemoth())
                .targetPart(MockedRepository.mockHitzone())
                .attackMove(MockedRepository.mockMoveset().getAttacks().get(0))
                .build();
        Attack modifiedAttack = weapon.accountFor(attack);
        assertThat(modifiedAttack, equalTo(attack));
    }


    @Test
    public void getPowerFactorTest() {
        assertThat(Weapon.getPowerFactor(0d, 0), equalTo(1d));
        assertThat(Weapon.getPowerFactor(0d, 1), equalTo(1.1d));
        assertThat(Weapon.getPowerFactor(0d, -1), equalTo(0.9d));
        assertThat(Weapon.getPowerFactor(-500d, 0), equalTo(0.25));
        assertThat(Weapon.getPowerFactor(-500d, 1), equalTo(0.35));
        assertThat(Weapon.getPowerFactor(-500d, -1), equalTo(0.15));
        assertThat(Weapon.getPowerFactor(1000d, 0), equalTo(1.75));
        assertThat(Weapon.getPowerFactor(1000d, 1), equalTo(1.85));
        assertThat(Weapon.getPowerFactor(1000d, -1), equalTo(1.65));
    }
}
