package de.midorlo.relentless.domain.mutator;

import de.midorlo.relentless.domain.Hitzone;
import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.AttackDamage;

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
