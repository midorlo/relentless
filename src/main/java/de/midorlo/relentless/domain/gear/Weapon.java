package de.midorlo.relentless.domain.gear;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.AttackDamage;
import de.midorlo.relentless.domain.combat.IAttackModifier;
import de.midorlo.relentless.domain.combat.WeaponAttack;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"moveSets"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Log
@Entity
public class Weapon extends Gear implements IAttackModifier {

    @Deprecated protected transient List<List<WeaponAttack>> moveSets = new ArrayList<>();

    public Integer getPower() {
        int power = 0;
        switch (getLevel()) {
            case 15: power += 10;
            case 14: power += 10;
            case 13: power += 10;
            case 12: power += 10;
            case 11: power += 10;
            case 10: power += 40;
            case  9: power += 40;
            case  8: power += 40;
            case  7: power += 40;
            case  6: power += 40;
            case  5: power += 40;
            case  4: power += 40;
            case  3: power += 40;
            case  2: power += 40;
            case  1: power += 40;
            default: break;
        }
        return power;
    }

    @Override
    public Attack accountFor(Attack attack) {
        attack.getAttackDamage().add(getPowerFactor(attack));
        return attack;
    }

    /**
     * Gets the Weapon AttackDamage of a specific Attack Context.
     * @param attackContext context
     * @return weapon AttackDamage
     */
    protected AttackDamage getPowerFactor(Attack attackContext) {
        double differencedPower = getPower() - attackContext.getBehemoth().getPower();
        Element behemothElement = attackContext.getBehemoth().getElement();

        int elementStrength = Element.compareForAttack(getElement(), behemothElement);
        double powerFactor = getPowerFactor(differencedPower, elementStrength);


        AttackDamage attackDamage = new AttackDamage();
        attackDamage.setPowerFactor(powerFactor);
        return attackDamage;
    }

    static Double getPowerFactor(Double differencedPower, int elementStrength) {
        double a = (elementStrength < 0) ? 0.9d : ((elementStrength == 0) ? 1d : 1.1d);
        double b = (differencedPower > 0) ? 0.00075 :  0.0015;
        double r = a + (differencedPower * b);
        r = r * 100;
        return Math.round(r) / 100d;
    }
}
