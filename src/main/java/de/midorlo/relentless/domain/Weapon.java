package de.midorlo.relentless.domain;

import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.AttackDamage;
import de.midorlo.relentless.domain.combat.IAttackModifier;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Entity
public class Weapon implements IAttackModifier {

    @Id
    String name;

    @Basic(optional = false)
    Integer level;

    @Basic(optional = false)
    String description;

    @Basic(optional = false)
    ItemType type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,optional = false)
    Element element;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Perk> perks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    CellType primaryCellSocket;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    CellType secondaryCellSocket;

    public Weapon() {

    }

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

    public static Double getPowerFactor(Double differencedPower, int elementStrength) {
        double a = (elementStrength < 0) ? 0.9d : ((elementStrength == 0) ? 1d : 1.1d);
        double b = (differencedPower > 0) ? 0.00075 :  0.0015;
        double r = a + (differencedPower * b);
        r = r * 100;
        return Math.round(r) / 100d;
    }
}
