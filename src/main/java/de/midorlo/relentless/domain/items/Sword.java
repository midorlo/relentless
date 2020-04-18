package de.midorlo.relentless.domain.items;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.Damage;
import de.midorlo.relentless.domain.mutators.AbstractDamageModifier;
import de.midorlo.relentless.domain.mutators.ElementDamageModifier;
import de.midorlo.relentless.domain.mutators.IAttackModifier;

import java.util.ArrayList;
import java.util.List;

public class Sword extends Weapon implements IAttackModifier {

    List<AbstractDamageModifier> damageModifierList;

    public Sword() {
        this.damageModifierList = new ArrayList<>();
        damageModifierList.add(
                new ElementDamageModifier(
                        getElement(),
                        new Damage(

                        )
                )
        );

    }



    @Override
    public Attack accountFor(Attack attack) {
        damageModifierList.forEach(modifier -> modifier.accountFor(attack));
        return attack;
    }
}
