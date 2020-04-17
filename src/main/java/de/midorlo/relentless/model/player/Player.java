package de.midorlo.relentless.model.player;

import de.midorlo.relentless.model.behemoth.Behemoth;
import de.midorlo.relentless.model.behemoth.BehemothPartType;
import de.midorlo.relentless.model.combat.Attack;
import de.midorlo.relentless.model.combat.AttackMove;
import de.midorlo.relentless.model.combat.AttackResult;
import de.midorlo.relentless.model.combat.IAttackModifier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Human's Avatar in the World.
 */
@Data
@ToString
@EqualsAndHashCode
@Log
public class Player {

    String name;
    Loadout loadout;

    /**
     * Attack a Behemoth.
     * Will attack the head (per definition)
     *
     * @param behemoth the Behemoth
     */
    public AttackResult attack(AttackMove attackMove, Behemoth behemoth) {
        return Attack.builder()
                .player(this)
                .behemoth(behemoth)
                .targetPart(BehemothPartType.Head)
                .attackMove(attackMove)
                .build()
                .finish();
    }

    public List<IAttackModifier> getModifiers() {
        return new ArrayList<>(); //todo gather when impl cells
    }
}


