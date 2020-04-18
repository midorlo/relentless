package de.midorlo.relentless.domain.mutators;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.Damage;

public class ElementDamageModifier extends AbstractDamageModifier<Element> {

    /**
     * @param identifier     An exact attribute to look for.
     * @param damageModifier a damage Value that gets added to the attack, when the identifier matches.
     */
    public ElementDamageModifier(Element identifier, Damage damageModifier) {
        super(identifier, damageModifier);
    }

    @Override
    public boolean matches(Attack attack) {
        return identifier.equals(attack.getBehemoth().getElement());
    }
}
