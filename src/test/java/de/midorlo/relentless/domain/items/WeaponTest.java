package de.midorlo.relentless.domain.items;


import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.MockedRepository;
import de.midorlo.relentless.domain.behemoth.BehemothPartType;
import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.Damage;
import de.midorlo.relentless.domain.combat.DamageType;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class WeaponTest {

    @Test
    public void accountForTest() {
        Weapon weapon = new Weapon();
        weapon.setDamageType(DamageType.Slashing);
        weapon.setName("Mana Sword");
        weapon.setElement(Element.Radiant);
        weapon.setDescription("It's something");
        Attack attack = Attack.builder()
                .player(MockedRepository.mockPlayer())
                .behemoth(MockedRepository.mockBehemoth())
                .targetPart(BehemothPartType.Head)
                .attackMove(MockedRepository.mockMoveset().get(0))
                .build();
        attack = weapon.accountFor(attack);
        System.out.println("");
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