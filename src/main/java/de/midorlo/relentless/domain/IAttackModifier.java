package de.midorlo.relentless.domain;

import de.midorlo.relentless.domain.combat.Attack;

public interface IAttackModifier {

    /**
     * Accounts for this modifier.
     * @param attack the attack.
     * @return the (eventually) modified attack.
     */
    Attack accountFor(Attack attack);
}
