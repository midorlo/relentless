package de.midorlo.relentless.domain;

import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.AttackResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

/**
 * Represents a Human's Avatar in the World.
 */
@Data
@ToString
@EqualsAndHashCode
@Log
public class Player {

    String name;
    Integer health;
    Integer stamina;
    Loadout loadout;

    /**
     * Attack a Behemoth.
     * Will attack the head (per definition)
     *
     * @param behemoth the Behemoth
     */
    public AttackResult attack(Skill skill, Behemoth behemoth) {
        return Attack.builder()
                .player(this)
                .behemoth(behemoth)
                .targetPart(behemoth.getBehemothParts().get(0).getHitzone())
                .attackMove(skill)
                .build()
                .doAttack();
    }
}


