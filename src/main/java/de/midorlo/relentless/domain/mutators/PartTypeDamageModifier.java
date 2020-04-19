package de.midorlo.relentless.domain.mutators;

import de.midorlo.relentless.domain.behemoth.BehemothPartType;
import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.Damage;

/**
 * Holds a Damage Modifier that Reacts to a targeted Part Type
 */
public class PartTypeDamageModifier extends AbstractDamageModifier<BehemothPartType> {

    /**
     * New Instance
     * @param partType the part type to identify
     * @param mutator    changed damage values
     */
    public PartTypeDamageModifier(BehemothPartType partType, Damage mutator) {
        super(partType, mutator);
    }

    @Override
    public boolean matches(Attack attack) {
        return identifier.equals(attack.getBehemothPart().getType());
    }
}
