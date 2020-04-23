package de.midorlo.relentless.domain.combat;

public interface IAttackModifier {

    /**
     * Accounts for this modifier.
     * @param attack the attack.
     * @return the (eventually) modified attack.
     */
    Attack accountFor(Attack attack);
}
