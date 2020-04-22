package de.midorlo.relentless.domain;

import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.AttackResult;

/**
 * Defines how applying an attack to a target should be implemented.
 */
public interface IAttackConsumer extends IAttackModifier {

    /**
     * Consumes an attack, applying the damage to itself.
     * @param attack the attack.
     * @return result.
     */
    AttackResult consume(Attack attack);
}
