package de.midorlo.relentless.domain.mutators;

import de.midorlo.relentless.domain.attack.AttackDamage;
import de.midorlo.relentless.domain.behemoth.Hitzone;
import de.midorlo.relentless.domain.attack.Attack;

/**
 * Holds a AttackDamage Modifier that Reacts to a targeted Part Type
 */
@SuppressWarnings("unused")
public class PartTypeDamageModifier extends AbstractDamageModifier<Hitzone> {

    /**
     * New Instance
     * @param partType the part type to identify
     * @param mutator    changed damage values
     */
    public PartTypeDamageModifier(Hitzone partType, AttackDamage mutator) {
        super(partType, mutator);
    }

    @SuppressWarnings("unused")
    @Override
    public boolean matches(Attack attack) {
        return identifier.equals(attack.getBehemothPart().getHitzone());
    }
}
