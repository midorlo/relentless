package de.midorlo.relentless.domain.items;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.Damage;
import de.midorlo.relentless.domain.combat.WeaponAttack;
import de.midorlo.relentless.domain.combat.AttackType;
import de.midorlo.relentless.domain.mutators.IAttackModifier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"moveSets"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Log
@SuppressWarnings("SameReturnValue")
public class Weapon extends Gear implements IAttackModifier {

    List<List<WeaponAttack>> moveSets = new ArrayList<>();
    AttackType attackType;

    /**
     * Disregard everything but maxxed out weapons for now.
     * @return 550.
     */
    public Integer getPower() {
        return 550; //todo handle levelled power
    }

    @Override
    public Attack accountFor(Attack attack) {
        attack.getDamage().add(getPowerFactor(attack));
        return attack;
    }

    /**
     * Gets the Weapon Damage of a specific Attack Context.
     * @param attackContext context
     * @return weapon Damage
     */
    protected Damage getPowerFactor(Attack attackContext) {
        double differencedPower = getPower() - attackContext.getBehemoth().getPower();
        Element behemothElement = attackContext.getBehemoth().getElement();
        double powerFactor = getPowerFactor(differencedPower, Element.compareForAttack(getElement(), behemothElement));
        Damage damage = new Damage();
        damage.setPowerFactor(powerFactor);
        return damage;
    }

    static Double getPowerFactor(Double differencedPower, int elementStrength) {
        double a = (elementStrength < 0) ? 0.9d : ((elementStrength == 0) ? 1d : 1.1d);
        double b = (differencedPower > 0) ? 0.00075 :  0.0015;
        double r = a + (differencedPower * b);
        r = r * 100;
        return Math.round(r) / 100d;
    }
}
