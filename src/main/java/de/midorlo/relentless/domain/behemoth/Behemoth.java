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
    Double health;
    Element element;
    List<BehemothPart> behemothParts = new ArrayList<>();

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
