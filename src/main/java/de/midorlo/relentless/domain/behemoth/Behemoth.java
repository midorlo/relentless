package de.midorlo.relentless.domain.behemoth;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.AttackResult;
import de.midorlo.relentless.domain.mutators.IAttackModifier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Player's Enemy.
 */
@Data
@ToString
@EqualsAndHashCode
@Log
public class Behemoth implements IAttackModifier {

    String name;
    Integer thread;
    Double health;
    Element element;
    List<BehemothPart> behemothParts = new ArrayList<>();

    //@formatter:off
    public Integer getPower() {
        int power = 0;
        switch (thread) {
            case 30:power += 75;
            case 22:power += 75;
            case 19:power += 25;
            case 18:power += 50;
            case 17:power += 50;
            case 16:power += 25;
            case 15:power += 25;
            case 14:power += 25;
            case 13:power += 25;
            case 12:power += 25;
            case 11:power += 25;
            case 10:power += 25;
            case 9: power += 25;
            case 8: power += 25;
            case 7: power += 25;
            case 6: power += 50;
            case 5: power += 50;
            case 4: power += 50;
            case 3: power += 25;
            case 2: power += 25;
            case 1: power += 50;
        }
        return power;
    }
    //@formatter:on

    public void setHealth(Double health) {
        this.health = health;
    }

    @Override
    public Attack accountFor(Attack attack) {
        return attack;
    }

    public AttackResult consume(Attack attackNetto) {
        Double oldHealth = getHealth(),
                healthDamage = attackNetto.getDamage().getHealthDamageNetto(),
                newHealth = oldHealth - healthDamage,
                oldPartHealth = attackNetto.getBehemothPart().getHealth(),
                partDamage = attackNetto.getDamage().getPartDamageNetto(),
                newPartHealth = oldPartHealth - partDamage;

        setHealth(newHealth);
        attackNetto.getBehemothPart().setHealth(newPartHealth);

        return new AttackResult(
                attackNetto.getPlayer().getName(),
                attackNetto.getBehemoth().getName(),
                attackNetto.getBehemothPart().getType(),
                oldHealth,
                healthDamage,
                newHealth,
                oldPartHealth,
                partDamage,
                newPartHealth
        );
    }

    public List<IAttackModifier> getModifiers() {
        return new ArrayList<>();
    }
}
