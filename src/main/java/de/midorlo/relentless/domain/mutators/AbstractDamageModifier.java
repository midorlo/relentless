package de.midorlo.relentless.domain.mutators;

import de.midorlo.relentless.domain.attack.IAttackModifier;
import de.midorlo.relentless.domain.attack.Attack;
import de.midorlo.relentless.domain.attack.AttackDamage;
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
    protected AttackDamage attackDamageModifier;

    /**
     * New Instance
     * @param identifier An exact attribute to look for.
     * @param attackDamageModifier a damage Value that gets added to the attack, when the identifier matches.
     */
    public AbstractDamageModifier(T identifier, AttackDamage attackDamageModifier) {
        this.identifier = identifier;
        this.attackDamageModifier = attackDamageModifier;
    }

    public abstract boolean matches(Attack attack);

    @Override
    public  Attack accountFor(Attack attack) {
        if (matches(attack)) {
            attack.getAttackDamage().add(this.attackDamageModifier);
        }
        return attack;
    }
}
