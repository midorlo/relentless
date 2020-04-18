package de.midorlo.relentless.domain.mutators;

import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.Damage;
import lombok.Data;
import lombok.extern.java.Log;

/**
 * A unified way to represend an attack mutator.
 * As one cannot simply persist a predicate, an implementation class is required
 * per identifier. In result, data binding is still achieved for mutators' values
 * (so changing the values in reaction to game patches will be trivial)
 *
 * @param <T> type of the selector.
 */
@Data
@Log
public abstract class AbstractDamageModifier<T> implements IAttackModifier {

    protected T identifier;
    protected Damage damageModifier;

    /**
     * New Instance
     * @param identifier An exact attribute to look for.
     * @param damageModifier a damage Value that gets added to the attack, when the identifier matches.
     */
    public AbstractDamageModifier(T identifier, Damage damageModifier) {
        this.identifier = identifier;
        this.damageModifier = damageModifier;
    }

    public abstract boolean matches(Attack attack);

    @Override
    public  Attack accountFor(Attack attack) {
        if (matches(attack)) {
            attack.getDamage().add(this.damageModifier);
        }
        return attack;
    }
}
