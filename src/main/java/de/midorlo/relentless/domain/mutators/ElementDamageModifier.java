package de.midorlo.relentless.domain.mutators;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.attack.Attack;
import de.midorlo.relentless.domain.attack.AttackDamage;

public class ElementDamageModifier extends AbstractDamageModifier<Element> {

    /**
     * @param identifier     An exact attribute to look for.
     * @param attackDamageModifier a damage Value that gets added to the attack, when the identifier matches.
     */
    @SuppressWarnings("unused")
    public ElementDamageModifier(Element identifier, AttackDamage attackDamageModifier) {
        super(identifier, attackDamageModifier);
    }

    @Override
    public boolean matches(Attack attack) {
        return identifier.equals(attack.getBehemoth().getElement());
    }
}
