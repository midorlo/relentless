package de.midorlo.relentless.domain.attack;

/**
 * Defines how applying an attack to a target should be implemented.
 */
public interface IAttackConsumer extends IAttackModifier {

    /**
     * Consumes an attack, applying the attackDamage to itself.
     * @param attack the attack.
     * @return result.
     */
    AttackResult consume(Attack attack);
}
