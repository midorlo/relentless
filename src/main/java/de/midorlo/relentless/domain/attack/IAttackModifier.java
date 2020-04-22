package de.midorlo.relentless.domain.attack;

import de.midorlo.relentless.domain.attack.Attack;

public interface IAttackModifier {

    /**
     * Accounts for this modifier.
     * @param attack the attack.
     * @return the (eventually) modified attack.
     */
    Attack accountFor(Attack attack);
}
