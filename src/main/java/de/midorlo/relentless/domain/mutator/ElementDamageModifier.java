package de.midorlo.relentless.domain.mutator;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.AttackDamage;

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
